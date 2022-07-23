/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.controller;

import com.delivery.Utils.Utils;
import com.delivery.pojo.DonHang;
import com.delivery.pojo.GioDauGia;
import com.delivery.service.BookingService;
import com.delivery.service.OrderService;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PC
 */
@RestController
public class ApiDonHangController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BookingService bookingService;

    /*
    - @JsonIgnore: Từ chối lấy thông tin
     */
    //Trả về dữ liệu, dùng @PathVariable để hứng id đơn hàng, lưu ý dùng đối tượng số nguyên cho biến: Integer
    @GetMapping("/api/daugia/{orderId}")
    //Javascript: Xài request body thay model addtribute    
    public ResponseEntity<Integer> donHangDauGia(@PathVariable(value = "orderId") Integer orderId, HttpSession session) {
        /*Tiếp theo phương thức lấy thông tin từ id ra và tiến hành thao tác đấu giá,
         cần tạo 1 lớp đối tượng hứng thông tin nhưng không phải là lớp POJO - GioDauGia,
        từ lớp hứng id bỏ vào trong Session trong phiên làm việc đó đấu giá những đơn hàng nào
        - xài HttpSession - một phiên làm việc chứ không phải Session trong Hibernate
        - Xác định key và value để đưa vào map và giúp hiển thị những đơn đấu giá 
         */
        //Lấy giỏ hàng trong phiên làm việc hiện tại
        long millis = System.currentTimeMillis();
        java.sql.Date ngayhientai = new java.sql.Date(millis);
        Map<Integer, GioDauGia> gioDauGiaTaiXe = (Map<Integer, GioDauGia>) session.getAttribute("cart");
        /*
        - Xác định 2 bước: 
        + Nếu trong phiên làm việc session có đơn đấu giá thì sẽ lấy đơn đã đấu giá trong session ra
        + Nếu chưa có đơn đấu giá nào thì bắt buộc tạo giỏ chứa các đơn hàng đấu giá của tài xế
         */
        if (gioDauGiaTaiXe == null) {
            gioDauGiaTaiXe = new HashMap<>();
        }
        //Nếu tài xế đã từng đấu giá 1 đơn hàng nào đó thì hiển thị ra, không có bất kỳ lượt đấu giá nào bắt đầu tạo giỏ - Key: BookingId-Thành phần còn lại
        DonHang dh = this.orderService.getDonHangById(orderId);

        if (gioDauGiaTaiXe.containsKey(orderId) == true) {
            //Đã có đấu giá
            GioDauGia c = gioDauGiaTaiXe.get(orderId);
            c.setQuantity(c.getQuantity() + 1);
        } else {

            //Bỏ đối tượng đơn hàng vào giỏ đấu giá tài xế
            GioDauGia c = new GioDauGia();
            if (dh.getNgayYeuCauGiao().compareTo(ngayhientai) < 0) {
                c = new GioDauGia();
                c.setNgayYeuCauGiao(null);
                c.setOrderId(dh.getId());
                c.setOrderName(dh.getOrderName());
                c.setPrice(dh.getPrice());
                c.setFreeShip(dh.getFreeShip());
                c.setTotal(dh.getTotal());
                c.setQuantity(1);
                gioDauGiaTaiXe.put(orderId, c);
            } else {
                c = new GioDauGia();
                c.setOrderId(dh.getId());
                c.setOrderName(dh.getOrderName());
                c.setPrice(dh.getPrice());
                c.setFreeShip(dh.getFreeShip());
                c.setTotal(dh.getTotal());
                c.setNgayYeuCauGiao(dh.getNgayYeuCauGiao());
                c.setQuantity(1);
                gioDauGiaTaiXe.put(orderId, c);
            }
        }
        session.setAttribute("cart", gioDauGiaTaiXe);

        //Xài int trả về tổng số lượng đơn hàng
        return new ResponseEntity<>(Utils.countCart(gioDauGiaTaiXe), HttpStatus.OK);

    }

    //Xóa items trong giỏ đấu giá, remove khỏi session hiện tại - Xóa giỏ đấu giá đơn hàng
    @DeleteMapping("api/daugia/{orderId}")
    public ResponseEntity<Integer> deleteItemsGio(@PathVariable(value = "orderId") Integer orderId, HttpSession session) {
        Map<Integer, GioDauGia> gioDauGiaTaiXe = (Map<Integer, GioDauGia>) session.getAttribute("cart");
        // Kiểm tra giỏ có đơn hàng chưa và trong giỏ có id của đơn hàng cần xóa
        if (gioDauGiaTaiXe != null && gioDauGiaTaiXe.containsKey(orderId)) {
            gioDauGiaTaiXe.remove(orderId);
            session.setAttribute("cart", gioDauGiaTaiXe);
        }
        return new ResponseEntity<>(Utils.countCart(gioDauGiaTaiXe), HttpStatus.OK);
    }

    //Xác nhận đơn hàng đấu giá
    @PostMapping("/api/pay")
    public HttpStatus pay(HttpSession session) {
        if (this.bookingService.addBook((Map<Integer, GioDauGia>) session.getAttribute("cart")) == true) {
            session.removeAttribute("cart");
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    //Xóa đơn hàng
    @DeleteMapping("/api/admin/xoadonhang/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDonHang(@PathVariable(name = "orderId") int orderId) {
        this.orderService.deleteOrders(orderId);
    }
}

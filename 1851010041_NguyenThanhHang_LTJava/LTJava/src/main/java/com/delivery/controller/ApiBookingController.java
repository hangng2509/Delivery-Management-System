/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.controller;

import com.delivery.pojo.DonHang;
import com.delivery.pojo.MailInfo;
import com.delivery.pojo.Shipper;
import com.delivery.pojo.User;
import com.delivery.service.BookingService;
import com.delivery.service.MailService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api")
public class ApiBookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private MailService mailService;

    @Autowired
    private HttpServletRequest request;

    //Xác nhận người chiến thắng đấu giá
    @GetMapping("/admin/xacnhandaugia/{bookingid}")
    @ResponseStatus(HttpStatus.OK)
    public void xacnhanbooking(@PathVariable(name = "bookingid") int bookingid) {

        String from = "rainnguyen1409@gmail.com";
        //Email người chiến thắng
        String url = "http://localhost:8080/DeliveryProject/quanlygiaohang";
        String email = this.bookingService.getBookingById(bookingid).getShipperDauGia().getIdShipper().getEmail();
        String subject = "Ting Ting Ting! Chúc mừng đấu giá thành công";
        String body = "<br>Cảm ơn tài xế: "
                + this.bookingService.getBookingById(bookingid).getShipperDauGia().getIdShipper().getName()
                + " đã đấu giá thành công đơn hàng "
                + this.bookingService.getBookingById(bookingid).getDonHangDauGia().getOrderName()
                + "<br>Địa chỉ lấy hàng: " + "<b>" + this.bookingService.getBookingById(bookingid).getDonHangDauGia().getPickupAddress() + "</b>"
                + "<br> Địa chỉ cần giao: " + "<b>" + this.bookingService.getBookingById(bookingid).getDonHangDauGia().getReceiveAddress() + "</b>"
                + "<br>Mọi chi tiết xin liên hệ tại số: <b>" + this.bookingService.getBookingById(bookingid).getKhachHangQuyetDinh().getIdCus().getPhone()
                + "</b><br><b>Xin chân thành cảm ơn!</b>"
                + "<br><a href ='" + url + "'>Xem kết quả chi tiết tại đây</a>";
        MailInfo mailXacNhan = new MailInfo(from, email, subject, body);
        mailService.send(mailXacNhan);
        bookingService.xacnhanbooking(bookingid);
    }

    //Xác nhận giao hàng thành công - Email tự động về khách hàng sở hữu đơn hàng
    @GetMapping("/xacnhangiaohang/{bookingid}")
    @ResponseStatus(HttpStatus.OK)
    public void xacnhangiaohang(@PathVariable(name = "bookingid") int bookingid) {
        User ship = this.bookingService.getBookingById(bookingid).getShipperDauGia().getIdShipper();

        DonHang dhXacNhanGiao = this.bookingService.getBookingById(bookingid).getDonHangDauGia();

        User u = dhXacNhanGiao.getIdKhachHangSoHuuDonHang().getIdCus();

        Date ngayHienTai = new Date();
        String from = "hn192509@gmail.com";
        String to = u.getEmail();
        String subject = "Ting ting ting! Chúng tôi là OU Delivery tới đây";
        String url = "http://localhost:8080/DeliveryProject/admin/orders/" + dhXacNhanGiao.getId();
        String body = "<br>- Đơn hàng: " + dhXacNhanGiao.getOrderName() + " thuộc Chủ sở hữu bài đăng " + u.getName() + "  được xác nhận giao thành công"
                + "<br>- Mã đơn hàng: " + dhXacNhanGiao.getId()
                + "<br>- Vào thời gian " + ngayHienTai
                + "<br>- Tài xế giao hàng: " + ship.getName()
                + "<br>- Mọi thắc mắc liên lạc với tài xế tại số điện thoại: " + ship.getPhone()
                + "<br>- Xem đơn hàng của bạn tại đây <a href ='" + url + "'>Chi tiết tại đây</a>";
        MailInfo mailXacNhan = new MailInfo(from, to, subject, body);
        mailService.send(mailXacNhan);
        this.bookingService.xacnhangiaohang(bookingid);
    }

    //Xóa những người đấu giá thất bại
    @DeleteMapping("/admin/xoadaugia/{bookingId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletebooking(@PathVariable(name = "bookingId") int bookingId) {
        String from = "rainnguyen1409@gmail.com";
        //Email người thua
        String url = "http://localhost:8080/DeliveryProject/dsdonhang";
        String email = this.bookingService.getBookingById(bookingId).getShipperDauGia().getIdShipper().getEmail();
        String subject = "Ting Ting Ting! Xin cảm ơn";
        String body = "<br>Cảm ơn tài xế: "
                + this.bookingService.getBookingById(bookingId).getShipperDauGia().getIdShipper().getName()
                + " đấu giá món hàng "
                + this.bookingService.getBookingById(bookingId).getDonHangDauGia().getOrderName()
                + "<br>Rất tiếc bạn đã đấu giá thất bại đơn hàng này. Mời bạn vào hệ thống và đấu giá lại đơn hàng khác"
                + "<br><b>Xin chân thành cảm ơn!</b>"
                + "<br><a href ='" + url + "'>Danh sách đơn hàng có trong hệ thống tại đây</a>";
        MailInfo mailXacNhan = new MailInfo(from, email, subject, body);
        mailService.send(mailXacNhan);

        this.bookingService.deleteBooking(bookingId);
    }

}

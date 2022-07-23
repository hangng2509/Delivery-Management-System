/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.controller;

import com.delivery.Utils.Utils;
import com.delivery.pojo.DonHang;
import com.delivery.pojo.GioDauGia;
import com.delivery.pojo.KhuyenMai;
import com.delivery.pojo.User;
import com.delivery.service.BookingService;
import com.delivery.service.KhuyenMaiService;
import com.delivery.service.OrderService;
import com.delivery.service.PaymentService;
import com.delivery.service.UserService;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PC
 */
@Controller
@ControllerAdvice
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private KhuyenMaiService khuyenMaiService;

    @Autowired
    private BookingService bookingService;

    @ModelAttribute
    public void commonAttribute(Model model, HttpSession session) {
        model.addAttribute("cartCounter", Utils.countCart((Map<Integer, GioDauGia>) session.getAttribute("cart")));
    }

    @RequestMapping("/admin")
    public String test() {
        return "admin";
    }

    //Xác nhận giao hàng thành công
    @RequestMapping("/quanlygiaohang")
    public String quanLyGiaoHang(Model model, HttpSession session) {
        User shipDangNhap = (User) session.getAttribute("currentUser");
        model.addAttribute("QLGH", this.bookingService.getBookingXacNhan(shipDangNhap));
        return "quanlygiaohang";
    }

    @RequestMapping("/admin/orders")
    public String listDH(Model model, @RequestParam(required = false) Map<String, String> params) {
        //Nếu có biến page có giá trị, còn không mặc định là 1
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        //biến counter: hứng số lượng đơn hàng từ CSDL 
        /*Math.ceil: là làm tròn lên, ví dụ 4.5 -> 5. Cụ thể hơn: là có 13 phần tử, mỗi trang 6 phần tử thì thay vì nó hiển thị 2 trang 
        và ẩn 1 phần tử 
        => nó sẽ hiển thị thêm trang thứ 3 để chứa phần tử bị ẩn
         */
        model.addAttribute("counter", this.orderService.countOrder());
        model.addAttribute("orders", this.orderService.getOrdersByUserId(params.getOrDefault("kw", ""), page));
        return "quanlydonhang";
    }

    @GetMapping("/admin/orders/themdonhang")
    public String hienThiThemDH(Model model, @RequestParam(value = "orderId", defaultValue = "0") int orderId) {
        if (orderId > 0) {
            model.addAttribute("order", this.orderService.getDonHangById(orderId));
        } else {
            model.addAttribute("order", new DonHang());
        }
        model.addAttribute("promos", this.khuyenMaiService.getDsKhuyenMaiHoatDong());

        return "themdonhang";
    }

    @PostMapping("/admin/orders/themdonhang")
    public String addDH(Model model, @ModelAttribute(value = "order") @Valid DonHang donHang, BindingResult rs) {
        model.addAttribute("promos", this.khuyenMaiService.getDsKhuyenMaiHoatDong());
        if (!rs.hasErrors()) {
            DonHang dhTim = this.orderService.getDonHangById(donHang.getId());
            if (donHang.getId() > 0) {
                if (dhTim != null) {
                    donHang.setIdKhachHangSoHuuDonHang(dhTim.getIdKhachHangSoHuuDonHang());
                    if (donHang.getQuantity() <= 0) {
                        donHang.setQuantity(dhTim.getQuantity());
                    }
                    if (donHang.getFreight() <= 0.00) {
                        donHang.setFreight(dhTim.getFreight());
                    }
                    if (donHang.getImgUploadFile().isEmpty() == true) {
                        donHang.setAnh(dhTim.getAnh());
                    }
                    // Công thức tính tiền nếu số lượng lớn hơn 5 thì tiền ship sẽ được tính là 20000 còn bé hơn thì được tính là 10000
                    BigDecimal tienShipDonHang = new BigDecimal(0);
                    BigDecimal soLuongDonHang = new BigDecimal(donHang.getQuantity());

                    KhuyenMai km = this.khuyenMaiService.getKhuyenMaiById(donHang.getKhuyenMaiApDung().getId());

                    BigDecimal giaTriKhuyenMai = new BigDecimal(km.getGiaTriKhuyenMai());
                    BigDecimal phiKhoiLuongDonHang = new BigDecimal(0);
                    BigDecimal tienKhuyenMai = new BigDecimal(0);
                    BigDecimal tongPhiVanChuyen = new BigDecimal(0);

                    if (donHang.getQuantity() > 5) {
                        tienShipDonHang = new BigDecimal(20000);
                    } else {
                        tienShipDonHang = new BigDecimal(10000);
                    }
                    // từ 0-2kg phí khối lượng sẽ là 4000 trên 1 đơn hàng
                    // Tổng phí vận chuyển =  soLuongDonHang * 4000 + tienShipDonHang - tienKhuyenMai
                    if (donHang.getFreight() > 0.00 && donHang.getFreight() <= 0.2) {
                        phiKhoiLuongDonHang = new BigDecimal(4000).multiply(soLuongDonHang);
                        tienKhuyenMai = phiKhoiLuongDonHang.add(tienShipDonHang).multiply(giaTriKhuyenMai);
                        tongPhiVanChuyen = phiKhoiLuongDonHang.add(tienShipDonHang).subtract(tienKhuyenMai);
                        donHang.setFreeShip(tongPhiVanChuyen);
                        BigDecimal tong = (donHang.getPrice().multiply(soLuongDonHang)).add(tongPhiVanChuyen);
                        donHang.setTotal(tong);
                    } else if (donHang.getFreight() > 0.2 && donHang.getFreight() <= 1) {
                        phiKhoiLuongDonHang = new BigDecimal(4500).multiply(soLuongDonHang);
                        tienKhuyenMai = phiKhoiLuongDonHang.add(tienShipDonHang).multiply(giaTriKhuyenMai);
                        tongPhiVanChuyen = phiKhoiLuongDonHang.add(tienShipDonHang).subtract(tienKhuyenMai);
                        donHang.setFreeShip(tongPhiVanChuyen);
                        BigDecimal tong = (donHang.getPrice().multiply(soLuongDonHang)).add(tongPhiVanChuyen);
                        donHang.setTotal(tong);
                    } else if (donHang.getFreight() > 1 && donHang.getFreight() <= 2) {
                        phiKhoiLuongDonHang = new BigDecimal(5000).multiply(soLuongDonHang);
                        tienKhuyenMai = phiKhoiLuongDonHang.add(tienShipDonHang).multiply(giaTriKhuyenMai);
                        tongPhiVanChuyen = phiKhoiLuongDonHang.add(tienShipDonHang).subtract(tienKhuyenMai);
                        donHang.setFreeShip(tongPhiVanChuyen);
                        BigDecimal tong = (donHang.getPrice().multiply(soLuongDonHang)).add(tongPhiVanChuyen);
                        donHang.setTotal(tong);
                    } else if (donHang.getFreight() > 2 && donHang.getFreight() <= 3) {
                        phiKhoiLuongDonHang = new BigDecimal(5500).multiply(soLuongDonHang);
                        tienKhuyenMai = phiKhoiLuongDonHang.add(tienShipDonHang).multiply(giaTriKhuyenMai);
                        tongPhiVanChuyen = phiKhoiLuongDonHang.add(tienShipDonHang).subtract(tienKhuyenMai);
                        donHang.setFreeShip(tongPhiVanChuyen);
                        BigDecimal tong = (donHang.getPrice().multiply(soLuongDonHang)).add(tongPhiVanChuyen);
                        donHang.setTotal(tong);
                    } else if (donHang.getFreight() > 3 && donHang.getFreight() <= 4) {
                        phiKhoiLuongDonHang = new BigDecimal(6000).multiply(soLuongDonHang);
                        tienKhuyenMai = phiKhoiLuongDonHang.add(tienShipDonHang).multiply(giaTriKhuyenMai);
                        tongPhiVanChuyen = phiKhoiLuongDonHang.add(tienShipDonHang).subtract(tienKhuyenMai);
                        donHang.setFreeShip(tongPhiVanChuyen);
                        BigDecimal tong = (donHang.getPrice().multiply(soLuongDonHang)).add(tongPhiVanChuyen);
                        donHang.setTotal(tong);
                    } else if (donHang.getFreight() > 4 && donHang.getFreight() <= 5) {
                        phiKhoiLuongDonHang = new BigDecimal(7000).multiply(soLuongDonHang);
                        tienKhuyenMai = phiKhoiLuongDonHang.add(tienShipDonHang).multiply(giaTriKhuyenMai);
                        tongPhiVanChuyen = phiKhoiLuongDonHang.add(tienShipDonHang).subtract(tienKhuyenMai);
                        donHang.setFreeShip(tongPhiVanChuyen);
                        BigDecimal tong = (donHang.getPrice().multiply(soLuongDonHang)).add(tongPhiVanChuyen);
                        donHang.setTotal(tong);
                    } else {
                        phiKhoiLuongDonHang = new BigDecimal(9000).multiply(soLuongDonHang);
                        tienKhuyenMai = phiKhoiLuongDonHang.add(tienShipDonHang).multiply(giaTriKhuyenMai);
                        tongPhiVanChuyen = phiKhoiLuongDonHang.add(tienShipDonHang).subtract(tienKhuyenMai);
                        donHang.setFreeShip(tongPhiVanChuyen);
                        BigDecimal tong = (donHang.getPrice().multiply(soLuongDonHang)).add(tongPhiVanChuyen);
                        donHang.setTotal(tong);
                    }
                    if (this.orderService.addOrUpdateOrders(donHang) == true) {
                        return "redirect:/admin/orders";
                    } else {
                        model.addAttribute("errAddOrders", "Đăng đơn hàng thất bại!!!");
                    }
                } else {
                    model.addAttribute("err", "Chưa có dữ liệu về đơn hàng");
                }
            } else {
                //Kiểm tra ngày lấy hàng, ngày mong muốn giao so với ngày hiện tại
                long millis = System.currentTimeMillis();
                java.sql.Date ngayHienTai = new java.sql.Date(millis);

                if (donHang.getNgayLayHang().compareTo(ngayHienTai) < 0) {
                    model.addAttribute("errNgayLayHang", "Ngày lấy hàng phải sau ngày hiện tại");
                    return "themdonhang";
                }
                if (donHang.getNgayYeuCauGiao().compareTo(ngayHienTai) < 0) {
                    model.addAttribute("errNgayYeuCauGiao", "Ngày mong muốn giao phải sau ngày hiện tại");
                    return "themdonhang";
                }
                if (donHang.getFreight() <= 0.00) {
                    model.addAttribute("errFr", "Xin nhập khối lượng đơn hàng");
                    return "themdonhang";
                }
                if (donHang.getQuantity() <= 0) {
                    model.addAttribute("errQuan", "Xin nhập số lượng đơn hàng");
                    return "themdonhang";
                }
                // Công thức tính tiền nếu số lượng lớn hơn 5 thì tiền ship sẽ được tính là 20000 còn bé hơn thì được tính là 10000
                BigDecimal tienShipDonHang = new BigDecimal(0);
                BigDecimal soLuongDonHang = new BigDecimal(donHang.getQuantity());
                KhuyenMai km = this.khuyenMaiService.getKhuyenMaiById(donHang.getKhuyenMaiApDung().getId());
                BigDecimal giaTriKhuyenMai = new BigDecimal(km.getGiaTriKhuyenMai());
                BigDecimal phiKhoiLuongDonHang = new BigDecimal(0);
                BigDecimal tienKhuyenMai = new BigDecimal(0);
                BigDecimal tongPhiVanChuyen = new BigDecimal(0);
                if (donHang.getQuantity() > 5) {
                    tienShipDonHang = new BigDecimal(20000);
                } else {
                    tienShipDonHang = new BigDecimal(10000);
                }
                // từ 0-2kg phí khối lượng sẽ là 4000 trên 1 đơn hàng
                // Tổng phí vận chuyển =  soLuongDonHang * 4000 + tienShipDonHang - tienKhyenMai
                if (donHang.getFreight() > 0.00 && donHang.getFreight() <= 0.2) {
                    phiKhoiLuongDonHang = new BigDecimal(4000).multiply(soLuongDonHang);
                    tienKhuyenMai = phiKhoiLuongDonHang.add(tienShipDonHang).multiply(giaTriKhuyenMai);
                    tongPhiVanChuyen = phiKhoiLuongDonHang.add(tienShipDonHang).subtract(tienKhuyenMai);
                    donHang.setFreeShip(tongPhiVanChuyen);
                    BigDecimal tong = (donHang.getPrice().multiply(soLuongDonHang)).add(tongPhiVanChuyen);
                    donHang.setTotal(tong);
                } else if (donHang.getFreight() > 0.2 && donHang.getFreight() <= 1) {
                    phiKhoiLuongDonHang = new BigDecimal(4500).multiply(soLuongDonHang);
                    tienKhuyenMai = phiKhoiLuongDonHang.add(tienShipDonHang).multiply(giaTriKhuyenMai);
                    tongPhiVanChuyen = phiKhoiLuongDonHang.add(tienShipDonHang).subtract(tienKhuyenMai);
                    donHang.setFreeShip(tongPhiVanChuyen);
                    BigDecimal tong = (donHang.getPrice().multiply(soLuongDonHang)).add(tongPhiVanChuyen);
                    donHang.setTotal(tong);
                } else if (donHang.getFreight() > 1 && donHang.getFreight() <= 2) {
                    phiKhoiLuongDonHang = new BigDecimal(5000).multiply(soLuongDonHang);
                    tienKhuyenMai = phiKhoiLuongDonHang.add(tienShipDonHang).multiply(giaTriKhuyenMai);
                    tongPhiVanChuyen = phiKhoiLuongDonHang.add(tienShipDonHang).subtract(tienKhuyenMai);
                    donHang.setFreeShip(tongPhiVanChuyen);
                    BigDecimal tong = (donHang.getPrice().multiply(soLuongDonHang)).add(tongPhiVanChuyen);
                    donHang.setTotal(tong);
                } else if (donHang.getFreight() > 2 && donHang.getFreight() <= 3) {
                    phiKhoiLuongDonHang = new BigDecimal(5500).multiply(soLuongDonHang);
                    tienKhuyenMai = phiKhoiLuongDonHang.add(tienShipDonHang).multiply(giaTriKhuyenMai);
                    tongPhiVanChuyen = phiKhoiLuongDonHang.add(tienShipDonHang).subtract(tienKhuyenMai);
                    donHang.setFreeShip(tongPhiVanChuyen);
                    BigDecimal tong = (donHang.getPrice().multiply(soLuongDonHang)).add(tongPhiVanChuyen);
                    donHang.setTotal(tong);
                } else if (donHang.getFreight() > 3 && donHang.getFreight() <= 4) {
                    phiKhoiLuongDonHang = new BigDecimal(6000).multiply(soLuongDonHang);
                    tienKhuyenMai = phiKhoiLuongDonHang.add(tienShipDonHang).multiply(giaTriKhuyenMai);
                    tongPhiVanChuyen = phiKhoiLuongDonHang.add(tienShipDonHang).subtract(tienKhuyenMai);
                    donHang.setFreeShip(tongPhiVanChuyen);
                    BigDecimal tong = (donHang.getPrice().multiply(soLuongDonHang)).add(tongPhiVanChuyen);
                    donHang.setTotal(tong);
                } else if (donHang.getFreight() > 4 && donHang.getFreight() <= 5) {
                    phiKhoiLuongDonHang = new BigDecimal(7000).multiply(soLuongDonHang);
                    tienKhuyenMai = phiKhoiLuongDonHang.add(tienShipDonHang).multiply(giaTriKhuyenMai);
                    tongPhiVanChuyen = phiKhoiLuongDonHang.add(tienShipDonHang).subtract(tienKhuyenMai);
                    donHang.setFreeShip(tongPhiVanChuyen);
                    BigDecimal tong = (donHang.getPrice().multiply(soLuongDonHang)).add(tongPhiVanChuyen);
                    donHang.setTotal(tong);
                } else {
                    phiKhoiLuongDonHang = new BigDecimal(9000).multiply(soLuongDonHang);
                    tienKhuyenMai = phiKhoiLuongDonHang.add(tienShipDonHang).multiply(giaTriKhuyenMai);
                    tongPhiVanChuyen = phiKhoiLuongDonHang.add(tienShipDonHang).subtract(tienKhuyenMai);
                    donHang.setFreeShip(tongPhiVanChuyen);
                    BigDecimal tong = (donHang.getPrice().multiply(soLuongDonHang)).add(tongPhiVanChuyen);
                    donHang.setTotal(tong);
                }

                MultipartFile file = donHang.getImgUploadFile();
                if (file.isEmpty()) {
                    model.addAttribute("mess", "Bắt buộc phải thêm hình ảnh sản phẩm");
                    return "themdonhang";
                }
                if (donHang.getSoNgay() >= 0) {
                    if (this.orderService.addOrUpdateOrders(donHang) == true) {
                        return "redirect:/admin/orders";
                    } else {
                        model.addAttribute("errAddOrders", "Đăng đơn hàng thất bại!!!");
                    }
                }
                model.addAttribute("errNgay", "Ngày lấy hàng phải trước ngày mong muốn giao hàng");
            }
        }
        return "themdonhang";
    }

    @RequestMapping(value = "/order/{orderId}")
    public String chiTietDonHang(Model model, @PathVariable(value = "orderId") int orderId) {
        model.addAttribute("orderCT", this.orderService.getDonHangById(orderId));
        model.addAttribute("booking", this.bookingService.getBooking(orderId));
        return "chitietdonhang";
    }

    @RequestMapping(value = "/lichsugiaohangshipper/{shipperId}")
    public String test(Model model, @PathVariable(value = "shipperId") int shipperId, HttpSession session, @RequestParam(required = true) boolean trangThai) {
        User user = (User) session.getAttribute("currentUser");
        User userFind = this.userService.getUserById(shipperId);
        if (userFind == null) {
            model.addAttribute("err", "Khong Tim Thay User");
        } else {
            if (user.getId() == shipperId) {
                if (trangThai == true) {
                    model.addAttribute("listOrder", this.orderService.getOrderOfShipper(shipperId, true));
                } else if (trangThai == false) {
                    model.addAttribute("listOrder", this.orderService.getOrderOfShipper(shipperId, false));
                }
            }
        }

        return "lichsugiaohangshipper";
    }

    @RequestMapping(value = "/lichsugiaohangshipper/all/{shipperId}")
    public String tatCaDonHangShipper(Model model, @PathVariable(value = "shipperId") int shipperId, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        User userFind = this.userService.getUserById(shipperId);
        if (userFind == null) {
            model.addAttribute("err", "Khong Tim Thay User");
        } else {
            if (user.getId() == shipperId) {
                model.addAttribute("listOrder", this.orderService.getOrderOfShipper(shipperId));
            }
        }

        return "lichsugiaohangshipper";
    }
}

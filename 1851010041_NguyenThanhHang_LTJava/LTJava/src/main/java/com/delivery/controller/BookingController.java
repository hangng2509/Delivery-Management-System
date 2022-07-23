/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.controller;

import com.delivery.pojo.Booking;
import com.delivery.pojo.MailInfo;
import com.delivery.pojo.Shipper;
import com.delivery.service.BookingService;
import com.delivery.service.MailService;
import com.delivery.service.OrderService;
import com.delivery.service.UserService;
import com.delivery.validator.WebAppValidator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author PC
 */
@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private MailService mailService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    //Hiển thị chi tiết đơn hàng - chi tiết các tài xế đấu giá đơn hàng đó
    @RequestMapping(value = "/admin/orders/{orderId}")
    public String chiTietDonHangQL(Model model, @PathVariable(value = "orderId") int orderId) {
        model.addAttribute("orderCT", this.orderService.getDonHangById(orderId));
        model.addAttribute("booking", this.bookingService.getBooking(orderId));
        return "quanlychitiet";
    }

    //Gửi mail cho tài xế
    @ResponseBody
    @RequestMapping(value = "/admin/send-mail-to-shipper")
    public boolean quanLyGuiMail(Model model, MailInfo mail, HttpServletRequest request
    ) {
        String subject = "Kết quả đấu giá đơn hàng";
        mail.setSubject(subject);
        //Gửi mail cho Shipper, Shipper gửi mail lại cho khách hàng
        try {

            String id = request.getParameter("id");
            String link = request.getRequestURL().toString().replace("admin/send-mail-to-shipper", "order/" + id);

            mail.setBody(mail.getBody()
                    + "<br>Mã đơn hàng: " + id
                    + "<hr><a href = '" + link + "'>Xem chi tiết đơn hàng tại đây</a>");
            if (mail.getFrom().isEmpty() == true || mail.getTo().isEmpty() == true || mail.getBody().isEmpty() == true) {
                return false;
            }
            mailService.send(mail);
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.controller;

import com.delivery.Utils.Utils;
import com.delivery.pojo.GioDauGia;
import com.delivery.service.CommentService;
import com.delivery.service.OrderService;
import com.delivery.service.ShipperService;
import com.delivery.service.StatsService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author PC
 */
@ControllerAdvice
@Controller
public class HomeController {

    @Autowired
    private ShipperService shipper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CommentService commentService;

    @ModelAttribute
    public void commonAttribute(Model model, HttpSession session) {
        model.addAttribute("cartCounter", Utils.countCart((Map<Integer, GioDauGia>) session.getAttribute("cart")));
        model.addAttribute("currentUser", session.getAttribute("currentUser"));

    }

    @RequestMapping("/")
    public String test() {

        return "baseLayout";
    }

    //Danh sách đơn hàng và nếu đăng nhập dưới dạng admin có thể xem các đơn chưa giao, đang và đã giao
    @RequestMapping("/dsdonhang")
    public String listDH(Model model, @RequestParam(required = false) Map<String, String> params) {
        //Nếu có biến page nếu có giá trị, còn không mặc định là 1

        int page = Integer.parseInt(params.getOrDefault("page", "1"));

        Date layHangDate = null;
        Date yeuCauGiaoDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("counter", this.orderService.countOrder());
        model.addAttribute("orderShip", this.orderService.getOrdersByShip(params.getOrDefault("kw", "")));
        model.addAttribute("orderNotShip", this.orderService.getOrdersWithoutShip(params.getOrDefault("kw", "")));

        try {

            String ngayLayHang = params.getOrDefault("ngayLayHang", null);
            String ngayYeuCauGiao = params.getOrDefault("ngayYeuCauGiao", null);
            if (ngayLayHang != null) {
                layHangDate = format.parse(ngayLayHang);
            }
            if (ngayYeuCauGiao != null) {
                yeuCauGiaoDate = format.parse(ngayYeuCauGiao);
            }

        } catch (ParseException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("orders", this.orderService.getOrders(params.getOrDefault("kw", ""), page, layHangDate, yeuCauGiaoDate));
        return "dsdonhang";
    }

    //Danh sách tài xế
    @RequestMapping("/dstaixe")
    public String listShipper(Model model,
            @RequestParam(required = false) Map<String, String> params
    ) {
        //Nếu có biến page nếu có giá trị, còn không mặc định là 1
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        model.addAttribute("counter", this.shipper.countShip());
        model.addAttribute("shippers", this.shipper.getListShippers(page));
        return "dsshipper";
    }

    //Chi tiết tài xế
    @RequestMapping(value = "/dstaixe/{shipId}")
    public String chiTietTaiXe(Model model,
            @PathVariable(value = "shipId") int shipId,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page
    ) {
        model.addAttribute("ships", this.shipper.getShipperById(shipId));
        model.addAttribute("comments", this.commentService.getCommentByShipperId(shipId, page));
        model.addAttribute("commentsCounter", this.commentService.countComment(shipId));
        return "chitietshipper";
    }

}

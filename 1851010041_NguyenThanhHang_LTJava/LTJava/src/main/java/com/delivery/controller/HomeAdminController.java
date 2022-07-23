/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.controller;

import com.delivery.service.StatsService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author PC
 */
@Controller
public class HomeAdminController {

    @Autowired
    private StatsService statsService;

    //Thống kế tần suất giao hàng thành công
    @GetMapping("/admin/orderStats")
    public String orderStats(Model model) {

        model.addAttribute("tanSuatChart", this.statsService.tanSuatGiaoHangStats());

        return "orderStats";
    }

    //Thống kê doanh thu khách hàng có đơn hàng giao thành công
    @GetMapping("/admin/cusStats")
    public String cusStats(Model model, @RequestParam(required = false) Map<String, String> params) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date fromDate = null;
        Date toDate = null;

        String from = params.getOrDefault("fromDate", null);
        String to = params.getOrDefault("toDate", null);

        try {
            if (from != null) {
                fromDate = format.parse(from);
            }
            if (to != null) {
                toDate = format.parse(to);
            }

        } catch (ParseException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("doanhThuCusChart", this.statsService.getDoanhThuKhachHangStats(fromDate, toDate));
        return "cusStats";
    }

    //Thông kê doanh thu của từng tài xế
    @GetMapping("/admin/shipStats")
    public String shipStats(Model model, @RequestParam(required = false) Map<String, String> params) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date fromDate = null;
        Date toDate = null;

        String from = params.getOrDefault("fromDate", null);
        String to = params.getOrDefault("toDate", null);

        try {
            if (from != null) {
                fromDate = format.parse(from);
            }
            if (to != null) {
                toDate = format.parse(to);
            }

        } catch (ParseException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addAttribute("doanhThuShipChart", this.statsService.getDoanhThuTaiXeStats(fromDate, toDate));
        return "shipStats";
    }

    //Thống kê doanh thu tất cả tài xế theo từng tháng
    @GetMapping("/admin/thongketatcataixe")
    public String doanhThuTXAll(Model model, @RequestParam(required = false) Map<String, String> params) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date fromDate = null;
        Date toDate = null;

        String from = params.getOrDefault("fromDate", null);
        String to = params.getOrDefault("toDate", null);

        try {
            if (from != null) {
                fromDate = format.parse(from);
            }
            if (to != null) {
                toDate = format.parse(to);
            }

        } catch (ParseException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        model.addAttribute("doanhThuShipChart", this.statsService.getDoanhThuTatCaTaiXeStats(fromDate, toDate));
        return "thongketaixe";
    }
}

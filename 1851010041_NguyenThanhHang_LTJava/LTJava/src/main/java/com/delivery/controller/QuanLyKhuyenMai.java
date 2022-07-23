/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.controller;

import com.delivery.pojo.KhuyenMai;
import com.delivery.pojo.User;
import com.delivery.service.KhuyenMaiService;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.delivery.service.ShipperService;

/**
 *
 * @author PC
 */
@Controller
public class QuanLyKhuyenMai {

    @Autowired
    private KhuyenMaiService khuyenMaiService;

    @RequestMapping("/admin/quanlykhuyenmai")
    public String quanLyKhuyenMai(Model model) {
        model.addAttribute("promotions", this.khuyenMaiService.getDSKhuyenMai());
        return "quanlykhuyenmai";
    }

    @GetMapping("/admin/quanlykhuyenmai/themkhuyenmai")
    public String hienThiThemKhuyenMai(Model model, @RequestParam(value = "proId", defaultValue = "0") int proId) {
        if (proId > 0) {
            model.addAttribute("promotions", this.khuyenMaiService.getKhuyenMaiById(proId));
        } else {
            model.addAttribute("promotions", new KhuyenMai());
        }
        return "themkhuyenmai";
    }

    //Thêm khuyến mãi - cập nhật khuyến mãi
    @PostMapping(value = "/admin/quanlykhuyenmai/themkhuyenmai")
    public String khuyenMai(Model model,
            @ModelAttribute(name = "promotions") @Valid KhuyenMai khuyenMai,
            BindingResult result) throws IOException {
        if (khuyenMai.getGiaTriKhuyenMai() > 0.5) {
            model.addAttribute("errGiaTri", "Giá trị khuyến mãi không được hơn 50%");
            return "themkhuyenmai";
        }
        if (!result.hasErrors()) {
            if (khuyenMaiService.addOrUpdateKhuyenMai(khuyenMai) == true) {
                return "redirect:/admin/quanlykhuyenmai";
            } else {
                model.addAttribute("errAddKhuyenMai", "Thêm/Cập nhật thông tin khuyến mãi thất bại");
            }
        }
        return "themkhuyenmai";
    }
}

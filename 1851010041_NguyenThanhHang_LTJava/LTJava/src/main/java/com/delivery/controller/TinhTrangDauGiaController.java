/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.controller;

import com.delivery.Utils.Utils;
import com.delivery.pojo.GioDauGia;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author PC
 */
@Controller
public class TinhTrangDauGiaController {

    //Hiển thị giỏ đấu giá ra ngoài client
    @GetMapping("/cart")
    public String tinhTrangDauGiaView(Model model, HttpSession session) {
        Map<Integer, GioDauGia> gioDauGiaTaiXe = (Map<Integer, GioDauGia>) session.getAttribute("cart");

        if (gioDauGiaTaiXe != null) {
            /*
            - Kiểm tra coi tài xế đã đấu giá đơn hàng chưa, nếu đấu giá thì hiển thị values từ giỏ đấu giá, 
            -Nếu chưa thì giỏ đấu giá bằng null để hiển thị.
             */
            model.addAttribute("carts", gioDauGiaTaiXe.values());

        } else {
            model.addAttribute("carts", null);
        }

        return "cart";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.controller;

import com.cloudinary.Cloudinary;
import com.delivery.pojo.User;
import com.delivery.service.UserService;
import com.delivery.validator.WebAppValidator;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.delivery.service.ShipperService;

@Controller
public class QuanLyShipperController {

    @Autowired
    private ShipperService shipperSevice;

    @Autowired
    private UserService userService;

    @Autowired
    private WebAppValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);

    }

    @RequestMapping(value = "/admin/quanlytaixe")
    public String quanLyTaiXe(Model model) {
        model.addAttribute("shippers", this.shipperSevice.getListShippers());
        return "quanlytaixe";
    }

    @GetMapping("/admin/quanlytaixe/themtaixe")
    public String hienThiThemNV(Model model) {
        model.addAttribute("user", new User());
        return "themtaixe";
    }

    //Thêm tài xế
    @PostMapping(value = "/admin/quanlytaixe/themtaixe")
    public String registerNvProcess(Model model,
            @ModelAttribute(name = "user")
            @Valid User user,
            BindingResult result) throws IOException {

        if (!result.hasErrors()) {
            if (user.getImg().isEmpty()) {
                model.addAttribute("mess", "Yêu cầu có CMND/CCCD");
                return "themtaixe";
            }
            if (userService.checkUsername(user.getUsername())) {
                model.addAttribute("messusername", "Đã tồn tại tên đăng nhập này!");
                return "themtaixe";
            }
            if (user.getId() <= 0 && user.getImg().getBytes().length == 0) {
                model.addAttribute("mess", "Yêu cầu phải có CMND/CCCD");
                return "themtaixe";
            }
            if (userService.addUserShipper(user) == true) {
                return "redirect:/admin/quanlytaixe";
            } else {
                model.addAttribute("errAddShipper", "Thêm tài xế thất bại");

            }

        }
        return "themtaixe";
    }

    @GetMapping("/admin/quanlytaixe/edittaixe")
    public String editProfile(Model model, @RequestParam(value = "shipperId", defaultValue = "0") int shipperId) {
        User ship = this.userService.getUserById(shipperId);
        ship.setPassword("");
        model.addAttribute("shipperPro", ship);
        return "edittaixe";
    }

    //Cập nhật tài xế
    @PostMapping("/admin/quanlytaixe/edittaixe")
    public String editProfilePage(Model model, @ModelAttribute(value = "shipperPro") @Valid User user,
            BindingResult result, @RequestParam(value = "shipperId", defaultValue = "0") int shipperId) {
        if (!result.hasErrors()) {
            User ship = this.userService.getUserById(user.getId());

            if (ship != null) {
                if (user.getImg().isEmpty() == true) {
                    user.setAvatar(ship.getAvatar());
                }
                if (this.userService.updateUser(user) == true) {
                    return "redirect:/admin/quanlytaixe";
                } else {
                    model.addAttribute("errAddShipper", "Cập nhật tài xế thất bại");
                }
            } else {
                model.addAttribute("errAddShipper", "Mời bạn thêm hình ảnh trước khi cập nhật thông tin");
            }
        }
        return "edittaixe";
    }
}

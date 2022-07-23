/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.delivery.pojo.User;
import com.delivery.service.UserService;
import com.delivery.validator.WebAppValidator;
import java.io.IOException;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PC
 */
@Controller
public class DangKyController {

    @Autowired
    private UserService userService;

    @Autowired
    private WebAppValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);

    }

    @GetMapping("/dangky")
    public String dangkiView(Model model) {
        model.addAttribute("user", new User());
        return "dangky";
    }

    //Post form đăng ký lên server
    @PostMapping(value = "/dangky")
    public String registerProcess(Model model,
            @ModelAttribute(name = "user") @Valid User user,
            BindingResult result) throws IOException {
        if (!result.hasErrors()) {
            MultipartFile file = user.getImg();
            if (file.isEmpty()) {
                model.addAttribute("mess", "Bắt buộc phải làm đúng yêu cầu thêm hình ảnh");
                return "dangky";
            }
            if (userService.checkUsername(user.getUsername())) {
                model.addAttribute("messusername", "Đã tồn tại tên đăng nhập này!");
                return "dangky";
            }
            userService.addUser(user);
            return "redirect:/login";
        }
        return "dangky";
    }
}

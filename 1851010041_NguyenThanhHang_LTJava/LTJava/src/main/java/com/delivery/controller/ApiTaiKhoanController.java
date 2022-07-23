/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.controller;

import com.delivery.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api")
public class ApiTaiKhoanController {

    @Autowired
    private UserService userService;

    //Xác nhận tài khoản cho Shipper có quyền hoạt động trong hệ thống
//    @GetMapping("/admin/xacnhantaixe/{shipperid}")
//    @ResponseStatus(HttpStatus.OK)
//    public void xacnhantaikhoan(@PathVariable(name = "shipperid") int shipperid) {
//        userService.xacnhantaixe(shipperid);
//    }
    //Xóa tài xế
    @DeleteMapping("/admin/xoataixe/{shipperId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleleShipper(@PathVariable(name = "shipperId") int shipperId) {
        this.userService.deleteUser(shipperId);

    }

}

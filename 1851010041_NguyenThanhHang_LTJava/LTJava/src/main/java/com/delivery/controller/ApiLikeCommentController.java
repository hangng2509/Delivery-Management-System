/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.controller;

import com.delivery.pojo.BinhLuan;
import com.delivery.pojo.Thich;
import com.delivery.pojo.User;
import com.delivery.service.ShipperService;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PC
 */
@RestController
public class ApiLikeCommentController {

    @Autowired
    private ShipperService shipperService;

    //Thêm bình luận
    @PostMapping(path = "/api/add-comment", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BinhLuan> addComment(@RequestBody Map<String, String> params, HttpSession session) {
        User u = (User) session.getAttribute("currentUser");
        try {
            String nd = params.get("nd");
            Integer shipId = Integer.parseInt(params.get("shipId"));

            BinhLuan bl = this.shipperService.addBinhLuan(nd, shipId, u);

            return new ResponseEntity<>(bl, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //Thay đổi trạng thái like or unlike
    @PostMapping(path = "/api/thichOrKhongThich", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Thich> thichOrKhongThich(@RequestBody Map<String, String> params) {
        try {
            String userHienTai = params.get("username");
            Integer shipId = Integer.parseInt(params.get("shipId"));
            Thich thich = new Thich();

            if (shipperService.ktShipperUserLike(userHienTai, shipId) == true) {
                if (this.shipperService.unLikeShipper(userHienTai, shipId) == true) {
                    return new ResponseEntity<>(thich, HttpStatus.OK);
                }
            } else {
                if (this.shipperService.likeShipper(userHienTai, shipId, thich) == true) {
                    return new ResponseEntity<>(thich, HttpStatus.CREATED);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

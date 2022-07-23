/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.service.impl;

import com.delivery.pojo.BinhLuan;
import com.delivery.pojo.Shipper;
import com.delivery.pojo.Thich;
import com.delivery.pojo.User;
import com.delivery.repository.ShipperRepository;
import com.delivery.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.delivery.service.ShipperService;
import com.delivery.service.UserService;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author PC
 */
@Service
public class ShipperServiceImpl implements ShipperService {

    @Autowired
    private ShipperRepository shipperReponsitory;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Shipper> getListShippers(int page) {
        return this.shipperReponsitory.getListShippers(page);
    }

    @Override
    public List<Shipper> getListShippers() {
        return this.shipperReponsitory.getListShippers();
    }

    @Override
    public Shipper getShipperById(int shipId) {
        return this.shipperReponsitory.getShipperById(shipId);
    }

    @Override
    public long countShip() {
        return this.shipperReponsitory.countShip();
    }

    @Override
    public BinhLuan addBinhLuan(String nd, int shipperId, User u) {
        Shipper ship = this.shipperReponsitory.getShipperById(shipperId);

        BinhLuan c = new BinhLuan();
        c.setNoiDung(nd);
        c.setShipperID(ship);
        c.setUserCom(u);
        //Lịch hiện tại bao gồm ngày và giờ
        Calendar cal = Calendar.getInstance();
        c.setNgayBinhLuan(new Timestamp(cal.getTimeInMillis()));

        return this.shipperReponsitory.addBinhLuan(c);
    }

    @Override
    public boolean ktShipperUserLike(String username, int shipId) {
        User currentUser = this.userRepository.getUsers(username).get(0);
        Shipper ship = this.shipperReponsitory.getShipperById(shipId);

        List<Thich> ktLuotThichShipper = this.shipperReponsitory.ktLuotThichShip(currentUser, ship);
        if (!ktLuotThichShipper.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean likeShipper(String username, int shipId, Thich thich) {
        User userHienTai = this.userRepository.getUsers(username).get(0);
        Shipper shipDuocLike = this.shipperReponsitory.getShipperById(shipId);

        int soLuotThichHienTai = shipDuocLike.getSoLuotThich() + 1;

        thich.setShipperIDThich(shipDuocLike);
        thich.setUserThich(userHienTai);

        shipDuocLike.setSoLuotThich(soLuotThichHienTai);
        return this.shipperReponsitory.likeShipper(shipDuocLike, thich);
    }

    @Override
    public boolean unLikeShipper(String username, int shipId) {
        User userHienTai = this.userRepository.getUsers(username).get(0);
        Shipper shipperDuocLike = this.shipperReponsitory.getShipperById(shipId);

        int soLuotThichHienTai = shipperDuocLike.getSoLuotThich() - 1;

        shipperDuocLike.setSoLuotThich(soLuotThichHienTai);

        //Lấy danh sách lượt thích của username đối với shipper nào
        Thich luotThichHistory = this.shipperReponsitory.ktLuotThichShip(userHienTai, shipperDuocLike).get(0);

        return this.shipperReponsitory.unLikeShipper(shipperDuocLike, luotThichHistory);
    }

}

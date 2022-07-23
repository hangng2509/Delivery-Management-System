/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.service;

import com.delivery.pojo.BinhLuan;
import com.delivery.pojo.Shipper;
import com.delivery.pojo.Thich;
import com.delivery.pojo.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PC
 */
public interface ShipperService {

    List<Shipper> getListShippers(int page);

    List<Shipper> getListShippers();

    Shipper getShipperById(int shipId);

    long countShip();

    BinhLuan addBinhLuan(String nd, int shipperId, User u);

    boolean ktShipperUserLike(String username, int shipId);

    boolean likeShipper(String username, int shipId, Thich thich);

    boolean unLikeShipper(String username, int shipId);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.repository;

import com.delivery.pojo.BinhLuan;
import com.delivery.pojo.Shipper;
import com.delivery.pojo.Thich;
import com.delivery.pojo.User;
import java.util.List;

/**
 *
 * @author PC
 */
public interface ShipperRepository {

    List<Shipper> getListShippers(int page);

    List<Shipper> getListShippers();

    Shipper getShipperById(int shipId);

    long countShip();

    BinhLuan addBinhLuan(BinhLuan binhLuan);

    List<Thich> ktLuotThichShip(User userHienTai, Shipper ship);

    boolean likeShipper(Shipper shipDuocLike, Thich thich);

    boolean unLikeShipper(Shipper shipDuocLike, Thich thich);
}

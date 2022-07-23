/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.delivery.pojo.Customer;
import com.delivery.pojo.DonHang;
import com.delivery.repository.OrderRepository;
import com.delivery.service.OrderService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<DonHang> getOrders(String kw, int page, Date layHangDate, Date yeuCauGiaoDate) {
        return this.orderRepository.getOrders(kw, page, layHangDate, yeuCauGiaoDate);
    }

    @Override
    public boolean addOrUpdateOrders(DonHang dh) {
        if (dh.getId() > 0) {
            dh.setTrangThaiDonHang(DonHang.TrangThai.CHUA_GIAO);
            if (dh.getImgUploadFile().isEmpty() == true) {
                return this.orderRepository.addOrUpdateOrders(dh);
            } else {
                try {
                    Map r = this.cloudinary.uploader().upload(dh.getImgUploadFile().getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                    //Lấy api từ cloudinary về
                    String img = (String) r.get("secure_url");
                    dh.setAnh(img);
                    return this.orderRepository.addOrUpdateOrders(dh);

                } catch (IOException ex) {
                    System.err.println("BUG: " + ex.getMessage());
                }
                return false;
            }
        } else {
            try {
                Map r = this.cloudinary.uploader().upload(dh.getImgUploadFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                //Lấy api từ cloudinary về
                String img = (String) r.get("secure_url");
                dh.setAnh(img);
                return this.orderRepository.addOrUpdateOrders(dh);
            } catch (IOException ex) {
                System.err.println("BUG: " + ex.getMessage());
            }
            return false;
        }
    }

    @Override
    public long countOrder() {
        return this.orderRepository.countOrder();
    }

    @Override
    public DonHang getDonHangById(int orderId) {
        return this.orderRepository.getDonHangById(orderId);
    }

    @Override
    public List<DonHang> getOrdersByUserId(String kw, int page) {
        return this.orderRepository.getOrdersByUserId(kw, page);
    }

    @Override
    public boolean deleteOrders(int orderId) {
        return this.orderRepository.deleteOrders(orderId);
    }

    @Override
    public List<Object[]> getOrdersByShip(String kw) {
        return this.orderRepository.getOrdersByShip(kw);
    }

    @Override
    public List<DonHang> getOrdersWithoutShip(String kw) {
        return this.orderRepository.getOrdersWithoutShip(kw);
    }

    @Override
    public List<Object[]> getOrderOfShipper(int idShiper,boolean trangThaiGiao) {
       return this.orderRepository.getOrderOfShipper(idShiper,trangThaiGiao);
    }

    @Override
    public List<Object[]> getOrderOfShipper(int idShiper) {
        return this.orderRepository.getOrderOfShipper(idShiper);
    }
}

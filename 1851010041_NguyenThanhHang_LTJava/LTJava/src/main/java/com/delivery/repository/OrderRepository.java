/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.repository;

import com.delivery.pojo.Customer;
import com.delivery.pojo.DonHang;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PC
 */
public interface OrderRepository {

    List<DonHang> getOrdersByUserId(String kw, int page);

    boolean addOrUpdateOrders(DonHang donHang);

    /* Việc duyệt mảng và đếm số lượng phần tử sẽ khiến hệ thống trở nên trì truệ 
    và rất lâu trong hệ thống nên cần sử dụng phương thức đếm mảng
     */
    long countOrder();

    DonHang getDonHangById(int orderId);

    List<DonHang> getOrders(String kw, int page, Date layHangDate, Date yeuCauGiaoDate);

    boolean deleteOrders(int orderId);

    List<Object[]> getOrdersByShip(String kw);

    List<DonHang> getOrdersWithoutShip(String kw);

    List<Object[]> getOrderOfShipper(int idShiper, boolean trangThaiGiao);

    List<Object[]> getOrderOfShipper(int shipperId);
}

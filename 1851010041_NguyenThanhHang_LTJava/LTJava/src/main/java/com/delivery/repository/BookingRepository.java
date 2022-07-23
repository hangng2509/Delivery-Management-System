/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.repository;

import com.delivery.pojo.Booking;
import com.delivery.pojo.DonHang;
import com.delivery.pojo.GioDauGia;
import com.delivery.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PC
 */
public interface BookingRepository {

    boolean addBook(Map<Integer, GioDauGia> gioDauGiaTaiXe);

    List<Object[]> getBooking(int orderId);

    Booking getBookingById(int bookingId);

    void xacnhanbooking(int bookingid);

    boolean deleteBooking(int bookingid);

    List<Object[]> getBookingXacNhan(User u);

    void xacnhangiaohang(DonHang dhGiaoHang);
}

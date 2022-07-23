/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.service.impl;

import com.delivery.pojo.Booking;
import com.delivery.pojo.DonHang;
import com.delivery.pojo.GioDauGia;
import com.delivery.pojo.User;
import com.delivery.repository.BookingRepository;
import com.delivery.service.BookingService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public boolean addBook(Map<Integer, GioDauGia> gioDauGiaTaiXe) {
        if (gioDauGiaTaiXe != null) {
            return this.bookingRepository.addBook(gioDauGiaTaiXe);
        }
        return false;
    }

    @Override
    public List<Object[]> getBooking(int orderId) {
        return this.bookingRepository.getBooking(orderId);
    }

    @Override
    public void xacnhanbooking(int bookingid) {
        this.bookingRepository.xacnhanbooking(bookingid);
    }

    @Override
    public boolean deleteBooking(int bookingid) {
        return this.bookingRepository.deleteBooking(bookingid);
    }

    @Override
    public List<Object[]> getBookingXacNhan(User u) {
        return this.bookingRepository.getBookingXacNhan(u);
    }

    @Override
    public Booking getBookingById(int i) {
        return this.bookingRepository.getBookingById(i);
    }

    @Override
    public void xacnhangiaohang(int bookingid) {

        Booking b = this.bookingRepository.getBookingById(bookingid);
        DonHang dhGiaoHang = b.getDonHangDauGia();

        dhGiaoHang.setTrangThaiDonHang(DonHang.TrangThai.GIAO_THANH_CONG);
        this.bookingRepository.xacnhangiaohang(dhGiaoHang);

    }

}

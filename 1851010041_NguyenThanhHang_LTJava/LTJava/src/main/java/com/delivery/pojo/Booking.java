/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date bookingDay;

    private boolean tinhTrangBook;
    //Số lần đấu giá
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private DonHang donHangDauGia;

    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private Shipper shipperDauGia;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer khachHangQuyetDinh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBookingDay() {
        return bookingDay;
    }

    public void setBookingDay(Date bookingDay) {
        this.bookingDay = bookingDay;
    }

    public boolean isTinhTrangBook() {
        return tinhTrangBook;
    }

    public void setTinhTrangBook(boolean tinhTrangBook) {
        this.tinhTrangBook = tinhTrangBook;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DonHang getDonHangDauGia() {
        return donHangDauGia;
    }

    public void setDonHangDauGia(DonHang donHangDauGia) {
        this.donHangDauGia = donHangDauGia;
    }

    public Shipper getShipperDauGia() {
        return shipperDauGia;
    }

    public void setShipperDauGia(Shipper shipperDauGia) {
        this.shipperDauGia = shipperDauGia;
    }

    public Customer getKhachHangQuyetDinh() {
        return khachHangQuyetDinh;
    }

    public void setKhachHangQuyetDinh(Customer khachHangQuyetDinh) {
        this.khachHangQuyetDinh = khachHangQuyetDinh;
    }
}

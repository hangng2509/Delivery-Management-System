/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    @MapsId
    private User idCus;

    @OneToMany(mappedBy = "idKhachHangSoHuuDonHang")
    private List<DonHang> dsDonHangTao;

    @OneToMany(mappedBy = "khachHangQuyetDinh")
    private List<Booking> bookingC;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getIdCus() {
        return idCus;
    }

    public void setIdCus(User idCus) {
        this.idCus = idCus;
    }

    public List<DonHang> getDsDonHangTao() {
        return dsDonHangTao;
    }

    public void setDsDonHangTao(List<DonHang> dsDonHangTao) {
        this.dsDonHangTao = dsDonHangTao;
    }

    public List<Booking> getBookingC() {
        return bookingC;
    }

    public void setBookingC(List<Booking> bookingC) {
        this.bookingC = bookingC;
    }

}

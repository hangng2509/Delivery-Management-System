/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "shipper")
public class Shipper implements Serializable {

    @Id
    private int id;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    @MapsId
    private User idShipper;

    @OneToMany(mappedBy = "shipperIDThich")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Thich> dsLuotThichDatDuoc;

    @OneToMany(mappedBy = "shipperID", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<BinhLuan> dsBinhLuanDatDuoc;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin adminID;

    @OneToMany(mappedBy = "shipperDauGia")
    private List<Booking> bookingS;

    private int soLuotThich;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getIdShipper() {
        return idShipper;
    }

    public void setIdShipper(User idShipper) {
        this.idShipper = idShipper;
    }

    public Admin getAdminID() {
        return adminID;
    }

    public void setAdminID(Admin adminID) {
        this.adminID = adminID;
    }

    public Collection<BinhLuan> getDsBinhLuanDatDuoc() {
        return dsBinhLuanDatDuoc;
    }

    public void setDsBinhLuanDatDuoc(Collection<BinhLuan> dsBinhLuanDatDuoc) {
        this.dsBinhLuanDatDuoc = dsBinhLuanDatDuoc;
    }

    public List<Thich> getDsLuotThichDatDuoc() {
        return dsLuotThichDatDuoc;
    }

    public void setDsLuotThichDatDuoc(List<Thich> dsLuotThichDatDuoc) {
        this.dsLuotThichDatDuoc = dsLuotThichDatDuoc;
    }

    public int getSoLuotThich() {
        return soLuotThich;
    }

    public void setSoLuotThich(int soLuotThich) {
        this.soLuotThich = soLuotThich;
    }

    public List<Booking> getBookingS() {
        return bookingS;
    }

    public void setBookingS(List<Booking> bookingS) {
        this.bookingS = bookingS;
    }

}

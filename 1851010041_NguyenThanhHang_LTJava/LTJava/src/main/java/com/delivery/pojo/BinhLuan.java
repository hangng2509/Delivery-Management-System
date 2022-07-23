/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "binhluan")
public class BinhLuan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Timestamp ngayBinhLuan;

    private String noiDung;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_shipper")
    private Shipper shipperID;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User userCom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getNgayBinhLuan() {
        return ngayBinhLuan;
    }

    public void setNgayBinhLuan(Timestamp ngayBinhLuan) {
        this.ngayBinhLuan = ngayBinhLuan;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Shipper getShipperID() {
        return shipperID;
    }

    public void setShipperID(Shipper shipperID) {
        this.shipperID = shipperID;
    }

    public User getUserCom() {
        return userCom;
    }

    public void setUserCom(User userCom) {
        this.userCom = userCom;
    }

}

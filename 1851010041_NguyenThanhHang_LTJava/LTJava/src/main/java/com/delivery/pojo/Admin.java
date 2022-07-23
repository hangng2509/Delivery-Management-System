/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Table(name = "admin")
public class Admin implements Serializable {

    @Id
    private int id;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    @MapsId
    private User idAdmin;

    @OneToMany(mappedBy = "adminID")
    private List<Shipper> dsShipperDuyetTaiKhoan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(User idAdmin) {
        this.idAdmin = idAdmin;
    }

    public List<Shipper> getDsShipperDuyetTaiKhoan() {
        return dsShipperDuyetTaiKhoan;
    }

    public void setDsShipperDuyetTaiKhoan(List<Shipper> dsShipperDuyetTaiKhoan) {
        this.dsShipperDuyetTaiKhoan = dsShipperDuyetTaiKhoan;
    }

}

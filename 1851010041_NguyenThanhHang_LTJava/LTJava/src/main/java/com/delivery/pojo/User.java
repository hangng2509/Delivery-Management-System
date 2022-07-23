/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    public static enum Role {
        ROLE_SHIPPER,
        ROLE_CUSTOMER,
        ROLE_ADMIN
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 45, message = "{user.username.sizeMsg}")
    private String username;

    @JsonIgnore
    @NotEmpty(message = "{user.password.err}")
    private String password;

    @NotEmpty(message = "{user.hoten.err}")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;

    private Boolean gender;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
            message = "{user.email.error.invalidMsg}")
    private String email;

    @Pattern(regexp = "\\d{10}",
            message = "{user.phone.error.invalidMsg}")
    private String phone;

    @NotEmpty(message = "{user.diachi.err}")
    private String address;

    private String avatar;

    @JsonIgnore
    @Transient
    private MultipartFile img;

    //ROLE_CUSTOMER
    //ROLE_ADMIN
    //ROLE_SHIPPER
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @Column(name = "join_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date joinDate;

    @JsonIgnore
    @Transient
    private String confirmPassword;

    @JsonIgnore
    private boolean active;

    @JsonIgnore
    @OneToOne(mappedBy = "idCus", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer;

    @JsonIgnore
    @OneToOne(mappedBy = "idShipper", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Shipper shipper;

    @JsonIgnore
    @OneToOne(mappedBy = "idAdmin", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Admin admin;

    @JsonIgnore
    @OneToMany(mappedBy = "userCom")
    private List<BinhLuan> binhLuanUser;

    @JsonIgnore
    @OneToMany(mappedBy = "userThich")
    private List<Thich> dsLuotThich;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<BinhLuan> getBinhLuanUser() {
        return binhLuanUser;
    }

    public void setBinhLuanUser(List<BinhLuan> binhLuanUser) {
        this.binhLuanUser = binhLuanUser;
    }

    public List<Thich> getDsLuotThich() {
        return dsLuotThich;
    }

    public void setDsLuotThich(List<Thich> dsLuotThich) {
        this.dsLuotThich = dsLuotThich;
    }

}

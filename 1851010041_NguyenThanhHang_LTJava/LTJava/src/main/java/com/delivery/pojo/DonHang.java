/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "donhang")
public class DonHang implements Serializable {

    public static enum TrangThai {
        CHUA_GIAO,
        DANG_DAU_GIA,
        DANG_GIAO,
        GIAO_THANH_CONG
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "{order.err.ten}")
    private String orderName;

    private BigDecimal total;

    @NotEmpty(message = "{order.err.mota}")
    private String description;
    //Upload hình ảnh sẽ lưu vào đây chuỗi đường dẫn trỏ img
    private String anh;

    //@Transient giành cho trường không có dưới csdl
    @Transient
    private MultipartFile imgUploadFile;

    @NotEmpty(message = "{order.pick.err}")
    private String pickupAddress;

    @NotEmpty(message = "{order.receive.err}")
    private String receiveAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    @NotNull(message = "{order.err.ngaylayhang}")
    private Date ngayLayHang;

    @NotNull(message = "{order.err.soluong}")
    private int quantity;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    @NotNull(message = "{order.err.ngayyeucaugiao}")
    private Date ngayYeuCauGiao;

    @NotNull(message = "{order.err.khoiluong}")
    private double freight;

    @Min(value = 20000, message = "{order.err.gia}")
    @Max(value = 10000000, message = "{order.err.gia}")
    @NotNull(message = "{order.err.giatien}")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private TrangThai trangThaiDonHang;

    private BigDecimal freeShip;

    @Transient
    private Long soNgay;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer idKhachHangSoHuuDonHang;

    @OneToMany(mappedBy = "donHangDauGia")
    private List<Booking> bookingDonHang;

    @ManyToOne
    @JoinColumn(name = "id_khuyenmai")
    private KhuyenMai khuyenMaiApDung;

    public KhuyenMai getKhuyenMaiApDung() {
        return khuyenMaiApDung;
    }

    public void setKhuyenMaiApDung(KhuyenMai khuyenMaiApDung) {
        this.khuyenMaiApDung = khuyenMaiApDung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public MultipartFile getImgUploadFile() {
        return imgUploadFile;
    }

    public void setImgUploadFile(MultipartFile imgUploadFile) {
        this.imgUploadFile = imgUploadFile;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public Date getNgayLayHang() {
        return ngayLayHang;
    }

    public void setNgayLayHang(Date ngayLayHang) {
        this.ngayLayHang = ngayLayHang;
    }

    public Customer getIdKhachHangSoHuuDonHang() {
        return idKhachHangSoHuuDonHang;
    }

    public void setIdKhachHangSoHuuDonHang(Customer idKhachHangSoHuuDonHang) {
        this.idKhachHangSoHuuDonHang = idKhachHangSoHuuDonHang;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getNgayYeuCauGiao() {
        return ngayYeuCauGiao;
    }

    public void setNgayYeuCauGiao(Date ngayYeuCauGiao) {
        this.ngayYeuCauGiao = ngayYeuCauGiao;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getFreeShip() {
        return freeShip;
    }

    public void setFreeShip(BigDecimal freeShip) {
        this.freeShip = freeShip;
    }

    public Long getSoNgay() {
        //Kiểm tra ngày lấy đơn hàng với ngày mong muốn giao hàng
        return Math.round((ngayYeuCauGiao.getTime() - ngayLayHang.getTime()) / (double) (24 * 60 * 60 * 1000));
    }

    public void setSoNgay(Long soNgay) {
        this.soNgay = soNgay;
    }

    public List<Booking> getBookingDonHang() {
        return bookingDonHang;
    }

    public void setBookingDonHang(List<Booking> bookingDonHang) {
        this.bookingDonHang = bookingDonHang;
    }

    public TrangThai getTrangThaiDonHang() {
        return trangThaiDonHang;
    }

    public void setTrangThaiDonHang(TrangThai trangThaiDonHang) {
        this.trangThaiDonHang = trangThaiDonHang;
    }

}

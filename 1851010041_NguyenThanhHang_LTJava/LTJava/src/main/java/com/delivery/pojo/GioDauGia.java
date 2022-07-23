/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author PC
 */
public class GioDauGia {

    private int orderId;
    private String orderName;
    private BigDecimal price;
    private BigDecimal freeShip;
    private BigDecimal total;
    private Date ngayYeuCauGiao;
    private int quantity;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

}

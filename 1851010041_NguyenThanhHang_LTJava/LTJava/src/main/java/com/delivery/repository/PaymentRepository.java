/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.repository;

import com.delivery.pojo.Payment;
import java.util.List;

/**
 *
 * @author PC
 */
public interface PaymentRepository {

    List<Payment> getDSHinhThucThanhToan();
}

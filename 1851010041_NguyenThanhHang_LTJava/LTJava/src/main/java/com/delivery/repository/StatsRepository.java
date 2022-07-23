/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.repository;

import java.util.Date;
import java.util.List;

/**
 *
 * @author PC
 */
public interface StatsRepository {

    List<Object[]> tanSuatGiaoHangStats();

    //Thống kê doanh thu theo khách hàng,shipper
    List<Object[]> getDoanhThuKhachHangStats(Date fromDate, Date toDate);

    List<Object[]> getDoanhThuTaiXeStats(Date fromDate, Date toDate);

    List<Object[]> getDoanhThuTatCaTaiXeStats(Date fromDate, Date toDate);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.service;

import java.util.Date;
import java.util.List;

/**
 *
 * @author PC
 */
public interface StatsService {

    List<Object[]> tanSuatGiaoHangStats();

    List<Object[]> getDoanhThuKhachHangStats(Date fromDate, Date toDate);

    List<Object[]> getDoanhThuTaiXeStats(Date fromDate, Date toDate);

    List<Object[]> getDoanhThuTatCaTaiXeStats(Date fromDate, Date toDate);
}

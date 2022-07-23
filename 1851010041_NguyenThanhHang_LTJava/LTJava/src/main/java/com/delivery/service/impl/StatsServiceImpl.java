/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.service.impl;

import com.delivery.repository.StatsRepository;
import com.delivery.service.StatsService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private StatsRepository statsRepository;

    @Override
    public List<Object[]> tanSuatGiaoHangStats() {
        return this.statsRepository.tanSuatGiaoHangStats();
    }

    @Override
    public List<Object[]> getDoanhThuKhachHangStats(Date fromDate, Date toDate) {
        return this.statsRepository.getDoanhThuKhachHangStats(fromDate, toDate);
    }

    @Override
    public List<Object[]> getDoanhThuTaiXeStats(Date fromDate, Date toDate) {
        return this.statsRepository.getDoanhThuTaiXeStats(fromDate, toDate);
    }

    @Override
    public List<Object[]> getDoanhThuTatCaTaiXeStats(Date date, Date date1) {
        return this.statsRepository.getDoanhThuTatCaTaiXeStats(date, date1);
    }

}

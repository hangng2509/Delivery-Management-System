/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.service.impl;

import com.delivery.pojo.KhuyenMai;
import com.delivery.repository.KhuyenMaiRepository;
import com.delivery.service.KhuyenMaiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class KhuyenMaiServiceImpl implements KhuyenMaiService {

    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;

    @Override
    public List<KhuyenMai> getDSKhuyenMai() {
        return this.khuyenMaiRepository.getDSKhuyenMai();
    }

    @Override
    public boolean addOrUpdateKhuyenMai(KhuyenMai khuyenMai) {
        return this.khuyenMaiRepository.addOrUpdateKhuyenMai(khuyenMai);
    }

    @Override
    public KhuyenMai getKhuyenMaiById(int i) {
        return this.khuyenMaiRepository.getKhuyenMaiById(i);
    }

    @Override
    public boolean deleteKhuyenMai(int i) {
        return this.khuyenMaiRepository.deleteKhuyenMai(i);
    }

    @Override
    public List<KhuyenMai> getDsKhuyenMaiHoatDong() {
        return  this.khuyenMaiRepository.getDsKhuyenMaiHoatDong();
    }
}

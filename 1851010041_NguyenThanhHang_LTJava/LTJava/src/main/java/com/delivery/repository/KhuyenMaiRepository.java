/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.repository;

import com.delivery.pojo.KhuyenMai;
import java.util.List;

/**
 *
 * @author PC
 */
public interface KhuyenMaiRepository {

    List<KhuyenMai> getDSKhuyenMai();

    boolean addOrUpdateKhuyenMai(KhuyenMai khuyenMai);

    KhuyenMai getKhuyenMaiById(int proId);

    boolean deleteKhuyenMai(int proId);
    
    List<KhuyenMai> getDsKhuyenMaiHoatDong ();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.Utils;

import com.delivery.pojo.GioDauGia;
import java.util.Map;

/**
 *
 * @author PC
 */
public class Utils {

    public static int countCart(Map<Integer, GioDauGia> gioDauGiaTaiXe) {
        int q = 0;
        if (gioDauGiaTaiXe != null) {
            for (GioDauGia c : gioDauGiaTaiXe.values()) {
                q += c.getQuantity();
            }
        }
        return q;

    }

    public static boolean ktNgay(Map<Integer, GioDauGia> gioDauGiaTaiXe) {
        boolean kt = true;
        long millis = System.currentTimeMillis();
        java.sql.Date ngayhientai = new java.sql.Date(millis);
        for (GioDauGia c : gioDauGiaTaiXe.values()) {
            if (c.getNgayYeuCauGiao().compareTo(ngayhientai) < 0) {
                kt = false;
            }
        }
        return kt;
    }
}

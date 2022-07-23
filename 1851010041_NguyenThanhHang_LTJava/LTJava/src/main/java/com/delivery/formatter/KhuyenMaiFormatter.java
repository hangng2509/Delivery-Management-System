/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.formatter;

import com.delivery.pojo.KhuyenMai;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class KhuyenMaiFormatter implements Formatter<KhuyenMai> {

    @Override
    public String print(KhuyenMai t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public KhuyenMai parse(String khuyenMaiId, Locale locale) throws ParseException {
        KhuyenMai km = new KhuyenMai();
        km.setId(Integer.parseInt(khuyenMaiId));
        return km;
    }

}

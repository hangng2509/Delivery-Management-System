/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.service;

import com.delivery.pojo.MailInfo;

/**
 *
 * @author PC
 */
public interface MailService {

    //Thông tin của Mail sẽ đc gửi lên
    void send(MailInfo mail);
}

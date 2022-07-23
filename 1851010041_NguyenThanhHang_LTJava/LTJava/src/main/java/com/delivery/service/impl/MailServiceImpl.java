/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.service.impl;

import com.delivery.pojo.MailInfo;
import com.delivery.pojo.User;
import com.delivery.service.MailService;
import com.delivery.service.UserService;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class MailServiceImpl implements MailService {

    //Người gửi
    @Autowired
    private JavaMailSender mailer;

    @Override
    public void send(MailInfo mail) {

        //Người gửi đưa thông tin tới server, server sẽ làm: 
        try {

            // Tạo mẫu tin nhắn
            MimeMessage message = mailer.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(mail.getFrom());
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(), true);

            //Và mail có chức năng đc phép reply lại người gửi
            helper.setReplyTo(mail.getFrom());

            //Nếu thông tin có bcc,cc thì mới helper mới set quyền bcc, cc
            if (mail.getCc() != null) {
                helper.setCc(mail.getCc());
            }
            if (mail.getBcc() != null) {
                helper.setBcc(mail.getBcc());
            }
            //Đối với các tập tin files
            if (mail.getFiles() != null) {
                //Cắt các tập tin files ra từng phần
                String[] path = mail.getFiles().split(";");
                for (String p : path) {
                    File file = new File(p);
                    helper.addAttachment(file.getName(), file);
                }
            }
            mailer.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(MailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

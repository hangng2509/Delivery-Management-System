/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.pojo;

/**
 *
 * @author PC
 */
public class MailInfo {

    String from;
    String to;
    //gửi cho nhiều người cùng 1 lúc và 1 ng nhận mail CÓ THỂ thấy danh sách người nhận mail còn lại
    String cc;
    //gửi cho nhiều người cùng 1 lúc và 1 ng nhận mail KHÔNG THỂ thấy danh sách người nhận mail còn lại
    String bcc;
    //Tiêu đề
    String subject;
    String body;
    String files;

    public MailInfo() {
    }

    public MailInfo(String from, String to, String subject, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public MailInfo(String from, String to, String cc, String bcc, String subject, String body, String files) {
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        this.files = files;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

}

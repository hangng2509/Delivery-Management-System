/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.service;

import com.delivery.pojo.BinhLuan;
import java.util.List;

/**
 *
 * @author PC
 */
public interface CommentService {

    List<BinhLuan> getCommentByShipperId(int shipId, int page);

    long countComment(int shipId);
}

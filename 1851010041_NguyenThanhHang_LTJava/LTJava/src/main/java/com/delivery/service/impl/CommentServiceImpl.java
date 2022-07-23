/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.service.impl;

import com.delivery.pojo.BinhLuan;
import com.delivery.repository.CommentRepository;
import com.delivery.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<BinhLuan> getCommentByShipperId(int shipId, int page) {
        return this.commentRepository.getCommentByShipperId(shipId, page);
    }

    @Override
    public long countComment(int shipId) {
        return this.commentRepository.countComment(shipId);
    }

}

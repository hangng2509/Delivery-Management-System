/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.repository.impl;

import com.delivery.pojo.BinhLuan;
import com.delivery.pojo.DonHang;
import com.delivery.pojo.Shipper;
import com.delivery.repository.CommentRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author PC
 */
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Transactional
    @Override
    public List<BinhLuan> getCommentByShipperId(int shipId, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<BinhLuan> query = builder.createQuery(BinhLuan.class);
        Root<BinhLuan> root = query.from(BinhLuan.class);
        Predicate p1 = builder.equal(root.get("shipperID").as(Shipper.class), shipId);
        query.where(p1);
        query.orderBy(builder.desc(root.get("id")));
        query.select(root);
        Query<BinhLuan> q = session.createQuery(query);

        int max = 3;
        //Mỗi lần sẽ lấy 3 phần tử
        q.setMaxResults(max);
        //Từ vị trí page đầu lấy 3 phần tử, page tiếp theo lấy tiếp 0-3,3-6,... phần tử
        q.setFirstResult((page - 1) * max);
        return q.getResultList();
    }

    @Transactional
    @Override
    public long countComment(int shipId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT (*) FROM BinhLuan WHERE shipperID.id =: id");
        q.setParameter("id", shipId);
        return Long.parseLong(q.getSingleResult().toString());
    }
}

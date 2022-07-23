/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.repository.impl;

import com.delivery.pojo.KhuyenMai;
import com.delivery.repository.KhuyenMaiRepository;
import java.util.List;
import org.hibernate.query.Query;
//import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author PC
 */
@Repository
@Transactional
public class KhuyenMaiRepositoryImpl implements KhuyenMaiRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<KhuyenMai> getDSKhuyenMai() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("From KhuyenMai");
        return q.getResultList();
    }

    @Override
    public boolean addOrUpdateKhuyenMai(KhuyenMai khuyenMai) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        if (khuyenMai.getId() > 0) {
            s.update(khuyenMai);
            return true;
        }
        try {
            s.save(khuyenMai);
            return true;
        } catch (Exception ex) {
            System.err.println("ADD FAIL BECAUSE: " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public KhuyenMai getKhuyenMaiById(int proId) {
        return this.sessionFactory.getObject().getCurrentSession().get(KhuyenMai.class, proId);
    }

    @Override
    public boolean deleteKhuyenMai(int i) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            KhuyenMai km = session.get(KhuyenMai.class, i);
            session.delete(km);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<KhuyenMai> getDsKhuyenMaiHoatDong() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<KhuyenMai> query = builder.createQuery(KhuyenMai.class);
        Root root = query.from(KhuyenMai.class);
        Predicate p1 = builder.equal(root.get("status"), false);
        query.where(p1);
        query.select(root);
        Query<KhuyenMai> q = session.createQuery(query);
        return q.getResultList();
    }

}

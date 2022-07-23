/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.repository.impl;

import com.delivery.pojo.BinhLuan;
import com.delivery.pojo.Shipper;
import com.delivery.pojo.Thich;
import com.delivery.pojo.User;
import com.delivery.repository.ShipperRepository;
import java.util.List;
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
import org.hibernate.query.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PC
 */
@Repository
public class ShipperReponsitoryImpl implements ShipperRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    @Transactional
    public List<Shipper> getListShippers(int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Shipper> query = builder.createQuery(Shipper.class);
        Root<Shipper> root = query.from(Shipper.class);
        query.select(root);
        Query<Shipper> q = session.createQuery(query);
        int max = 6;
        //Mỗi lần sẽ lấy 6 phần tử
        q.setMaxResults(max);
        //Từ vị trí page đầu lấy 6 phần tử, page tiếp theo lấy tiếp 6 phần tử
        q.setFirstResult((page - 1) * max);
        return q.getResultList();
    }

    @Transactional
    @Override
    public long countShip() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT (*) FROM Shipper");
        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    @Transactional
    public List<Shipper> getListShippers() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Shipper> query = builder.createQuery(Shipper.class);
        Root<Shipper> root = query.from(Shipper.class);
        query.select(root);
        Query<Shipper> q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    @Transactional
    public Shipper getShipperById(int shipId) {
        return this.sessionFactory.getObject().getCurrentSession().get(Shipper.class, shipId);
    }

    @Override
    @Transactional
    public BinhLuan addBinhLuan(BinhLuan binhLuan) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            s.save(binhLuan);
            return binhLuan;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public List<Thich> ktLuotThichShip(User userHienTai, Shipper ship) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Thich> query = builder.createQuery(Thich.class);
        Root<Thich> thichRoot = query.from(Thich.class);

        Predicate p1 = builder.equal(thichRoot.get("userThich").as(User.class), userHienTai);
        Predicate p2 = builder.equal(thichRoot.get("shipperIDThich").as(Shipper.class), ship);
        query.where(builder.and(p1, p2));
        query.select(thichRoot);
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    @Transactional
    public boolean likeShipper(Shipper shipDuocLike, Thich thich) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            session.save(thich);
            session.update(shipDuocLike);
            return true;
        } catch (HibernateException ex) {
            System.err.println("==Bug Like: " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public boolean unLikeShipper(Shipper shipDuocLike, Thich thich) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            session.delete(thich);
            session.update(shipDuocLike);
            return true;
        } catch (HibernateException ex) {
            System.err.println("==Bug unLike: " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.repository.impl;

import com.delivery.pojo.Payment;
import com.delivery.pojo.Shipper;
import com.delivery.repository.PaymentRepository;
import com.delivery.repository.ShipperRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
public class PaymentReponsitoryImpl implements PaymentRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Transactional
    @Override
    public List<Payment> getDSHinhThucThanhToan() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("From Payment");
        return q.getResultList();
    }

}

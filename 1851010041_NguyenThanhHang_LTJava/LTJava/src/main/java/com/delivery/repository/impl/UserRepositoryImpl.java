/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.repository.impl;

import com.delivery.pojo.Admin;
import com.delivery.pojo.Customer;
import com.delivery.pojo.Shipper;
import com.delivery.pojo.User;
import com.delivery.repository.UserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author PC
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean checkUsername(String username) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM User where username=:username");
        query.setParameter("username", username);
        if (query.getResultList().size() == 0) {
            return false;
        }
        User user = (User) query.getResultList().get(0);
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    //Mặc định add khách hàng đầu tiên
    @Override
    public boolean addUser(User user) {

        Session session = this.sessionFactory.getObject().getCurrentSession();
        if (user.getId() > 0) {
            session.update(user);
            return true;
        } else   
            try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            long millis = System.currentTimeMillis();
            java.sql.Date dateCreated = new java.sql.Date(millis);
            user.setJoinDate(dateCreated);
            user.setRole(user.getRole().ROLE_CUSTOMER);
            user.setActive(true);
            session.save(user);
            Customer customer = new Customer();
            customer.setIdCus(user);
            session.save(customer);
            return true;
        } catch (Exception ex) {
            System.err.println("ADD FAIL BECAUSE: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

//Thêm tài xế
    @Override
    public boolean addUserShipper(User user) {
        Admin adminXacNhan = new Admin();
        User adminUser = this.getUsersAuth();

        Session session = this.sessionFactory.getObject().getCurrentSession();

        adminXacNhan = session.get(Admin.class, adminUser.getId());

        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            long millis = System.currentTimeMillis();
            java.sql.Date dateCreated = new java.sql.Date(millis);
            user.setJoinDate(dateCreated);
            user.setActive(true);
            user.setRole(User.Role.ROLE_SHIPPER);

            session.save(user);

            Shipper shipper = new Shipper();

            shipper.setIdShipper(user);
            shipper.setAdminID(adminXacNhan);
            session.save(shipper);
            return true;
        } catch (Exception ex) {
            System.err.println("ADD FAIL BECAUSE: " + ex.getMessage());
            ex.printStackTrace();
        }

        return false;

    }

    //Lấy user có username là: ...?: Tìm kiếm username
    @Override
    public List<User> getUsers(String username
    ) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class
        );
        Root root = query.from(User.class
        );
        query.select(root);

        if (!username.isEmpty() && username != null) {
            Predicate p = builder.equal(root.get("username").as(String.class
            ), username.trim());
            query = query.where(p);
        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    //Đăng nhập với username nào đó và chứng thực quyền username
    @Override
    public User getUsersAuth() {
        //Chứng thực username hiện tại đang đăng nhập
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        //Lấy username 
        String username = loggedInUser.getName();

        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class
        );
        Root root = query.from(User.class
        );
        query.select(root);
        //Có username trong CSDL
        Predicate p = builder.equal(root.get("username").as(String.class
        ), username.trim());
        query = query.where(p);
        Query q = session.createQuery(query);
        List<User> users = q.getResultList();

        User u = users.get(0);
        return u;

    }

    @Override
    @Transactional
    public void xacnhantaixe(int shipperid) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Shipper shipper = session.get(Shipper.class,
                shipperid);
        Admin adminXacNhan = new Admin();
        User userAdmin = this.getUsersAuth();
        User shipperRole = session.get(User.class,
                shipperid);
        adminXacNhan = session.get(Admin.class,
                userAdmin.getId());
        shipperRole.setRole(User.Role.ROLE_SHIPPER);
        shipper.setAdminID(adminXacNhan);
        session.update(shipper);

    }

    @Override
    public User getUserById(int id) {
        return this.sessionFactory.getObject().getCurrentSession().get(User.class, id);
    }

    @Override
    public boolean updateUser(User user) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            session.update(user);
            return true;
        } catch (HibernateException ex) {
            System.err.println("== Update User Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(int shipperId) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            User ship = session.get(User.class, shipperId);
            Shipper shipDel = session.get(Shipper.class, shipperId);
            session.delete(ship);
            session.delete(shipDel);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Customer getCusById(int cusId) {
        return this.sessionFactory.getObject().getCurrentSession().get(Customer.class, cusId);
    }

    @Override
    public List<User> getCurrentLoggedUser(String username) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> uRoot = query.from(User.class);
        Predicate p = builder.equal(uRoot.get("username").as(String.class), username.trim());
        query.where(p);
        query.select(uRoot);
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<User> getListEmailOfShipperWinner(int bookingId) {

    }

    @Override
    public List<User> getListEmailOfShipperLoser(int bookingId) {

    }

}

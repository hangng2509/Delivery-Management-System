/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.repository.impl;

import com.delivery.pojo.Booking;
import com.delivery.pojo.Customer;
import com.delivery.pojo.DonHang;
import com.delivery.pojo.Shipper;
import com.delivery.pojo.User;
import com.delivery.repository.OrderRepository;
import com.delivery.service.UserService;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.query.Query;
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
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private UserService userService;

    //Hiển thị danh sách đơn hàng
    @Override
    @Transactional
    public List<DonHang> getOrdersByUserId(String kw, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        User user = this.userService.getUsersAuth();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DonHang> query = builder.createQuery(DonHang.class);
        Root<DonHang> root = query.from(DonHang.class);

        List<Predicate> predicates = new ArrayList<>();

        if (kw != null) {
            predicates.add(builder.like(root.get("orderName").as(String.class), String.format("%%%s%%", kw)));
        } else {
            predicates.add(builder.like(root.get("orderName").as(String.class), "%%"));
        }

        if (userService.getCusById(user.getId()) != null) {
            predicates.add(builder.equal(root.get("idKhachHangSoHuuDonHang").as(Customer.class), user));
        }

        //Dùng để bỏ tất cả điều kiện vào bên trong List sau đó parse tất cả dữ liệu predicates thành mảng bằng toArray
        query.where(predicates.toArray(new Predicate[]{}));

        query.select(root);
        Query<DonHang> q = session.createQuery(query);

        int max = 6;
        //Mỗi lần sẽ lấy 6 phần tử
        q.setMaxResults(max);
        //Từ vị trí page đầu lấy 6 phần tử, page tiếp theo lấy tiếp 6 phần tử
        q.setFirstResult((page - 1) * max);
        return q.getResultList();
    }

    @Override
    @Transactional
    public boolean addOrUpdateOrders(DonHang donHang) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        if (donHang.getId() > 0) {
            s.update(donHang);
            return true;
        } else {
            try {
                Customer cPost = new Customer();
                User user = this.userService.getUsersAuth();
                cPost = s.get(Customer.class, user.getId());
                donHang.setIdKhachHangSoHuuDonHang(cPost);
                donHang.setTrangThaiDonHang(donHang.getTrangThaiDonHang().CHUA_GIAO);
                s.save(donHang);
                return true;
            } catch (Exception ex) {
                System.err.println("ADD FAIL BECAUSE: " + ex.getMessage());
                ex.printStackTrace();
            }
            return false;
        }
    }

    //Đếm số lượng đơn hàng để phân trang
    @Transactional
    @Override
    public long countOrder() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT (*) FROM DonHang");
        return Long.parseLong(q.getSingleResult().toString()
        );
    }

    @Override
    @Transactional
    public DonHang getDonHangById(int orderId) {
        return this.sessionFactory.getObject().getCurrentSession().get(DonHang.class, orderId);
    }

    @Override
    @Transactional
    public List<DonHang> getOrders(String kw, int page, Date layHangDate, Date yeuCauGiaoDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DonHang> query = builder.createQuery(DonHang.class);
        Root root = query.from(DonHang.class);

        List<Predicate> predicates = new ArrayList<>();

        if (kw != null && !kw.isEmpty()) {
            predicates.add(builder.like(root.get("orderName").as(String.class), String.format("%%%s%%", kw)));
        } else {
            predicates.add(builder.like(root.get("orderName").as(String.class), "%%"));
        }
        if (layHangDate != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("ngayLayHang"), layHangDate));
        }
        if (yeuCauGiaoDate != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("ngayYeuCauGiao"), yeuCauGiaoDate));
        }

        //Dùng để bỏ tất cả điều kiện vào bên trong List sau đó parse tất cả dữ liệu predicates thành mảng bằng toArray
        query.where(predicates.toArray(new Predicate[]{}));

        query.select(root);
        Query q = session.createQuery(query);

        int max = 6;
        //Mỗi lần sẽ lấy 6 phần tử
        q.setMaxResults(max);
        //Từ vị trí page đầu lấy 6 phần tử, page tiếp theo lấy tiếp 6 phần tử
        q.setFirstResult((page - 1) * max);
        return q.getResultList();
    }

    @Override
    @Transactional
    public boolean deleteOrders(int orderId) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            DonHang dh = session.get(DonHang.class, orderId);
            session.delete(dh);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public List<Object[]> getOrdersByShip(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<DonHang> dhRoot = query.from(DonHang.class);
        Root<Booking> bRoot = query.from(Booking.class);

        Predicate p1 = builder.like(dhRoot.get("orderName").as(String.class), "%%");
        Predicate p2 = builder.equal(bRoot.get("donHangDauGia"), dhRoot.get("id"));
        Predicate p3 = builder.equal(dhRoot.get("trangThaiDonHang"), DonHang.TrangThai.DANG_GIAO);
        Predicate p4 = builder.equal(bRoot.get("tinhTrangBook"), true);
        if (!kw.isEmpty() && kw != null) {
            p1 = builder.like(dhRoot.get("orderName").as(String.class), String.format("%%%s%%", kw));
        }

        query.where(p1, p2, p3, p4);

        query.multiselect(
                dhRoot.get("id"),
                dhRoot.get("orderName").as(String.class),
                dhRoot.get("anh").as(String.class),
                dhRoot.get("pickupAddress").as(String.class),
                dhRoot.get("receiveAddress").as(String.class),
                bRoot.get("shipperDauGia").as(Shipper.class),
                bRoot.get("khachHangQuyetDinh").as(Customer.class),
                bRoot.get("tinhTrangBook"),
                dhRoot.get("trangThaiDonHang")
        );

        Query<Object[]> q = session.createQuery(query);

        return q.getResultList();
    }

    @Override
    @Transactional
    public List<DonHang> getOrdersWithoutShip(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DonHang> query = builder.createQuery(DonHang.class);
        Root<DonHang> root = query.from(DonHang.class);
        Predicate p1 = builder.like(root.get("orderName").as(String.class), "%%");
        Predicate p2 = builder.equal(root.get("trangThaiDonHang"), DonHang.TrangThai.CHUA_GIAO);
        if (!kw.isEmpty() && kw != null) {
            p1 = builder.like(root.get("orderName").as(String.class), String.format("%%%s%%", kw));
        }

        query.where(p1, p2);
        query.select(root);
        Query<DonHang> q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    @Transactional
    public List<Object[]> getOrderOfShipper(int idShiper, boolean trangThaiGiao) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Shipper> spRoot = query.from(Shipper.class);
        Root<DonHang> dhRoot = query.from(DonHang.class);
        Root<Booking> bRoot = query.from(Booking.class);
        Predicate p1 = builder.equal(spRoot.get("id"), idShiper);
        Predicate p2 = builder.equal(bRoot.get("shipperDauGia"), spRoot.get("id"));
        Predicate p3 = builder.equal(bRoot.get("donHangDauGia"), dhRoot.get("id"));
        if (trangThaiGiao == false) {
            Predicate p4 = builder.equal(dhRoot.get("trangThaiDonHang"), DonHang.TrangThai.CHUA_GIAO);
            query.where(p1, p2, p3, p4);
            query.multiselect(
                    dhRoot.get("id"),
                    dhRoot.get("orderName").as(String.class),
                    dhRoot.get("pickupAddress").as(String.class),
                    dhRoot.get("receiveAddress").as(String.class),
                    dhRoot.get("trangThaiDonHang")
            );

            Query<Object[]> q = session.createQuery(query);

            return q.getResultList();
        } else {
            Predicate p4 = builder.equal(dhRoot.get("trangThaiDonHang"), DonHang.TrangThai.GIAO_THANH_CONG);
            query.where(p1, p2, p3, p4);
            query.multiselect(
                    dhRoot.get("id"),
                    dhRoot.get("orderName").as(String.class),
                    dhRoot.get("pickupAddress").as(String.class),
                    dhRoot.get("receiveAddress").as(String.class),
                    dhRoot.get("trangThaiDonHang")
            );

            Query<Object[]> q = session.createQuery(query);

            return q.getResultList();
        }

    }

    @Override
    @Transactional
    public List<Object[]> getOrderOfShipper(int idShiper) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Shipper> spRoot = query.from(Shipper.class);
        Root<DonHang> dhRoot = query.from(DonHang.class);
        Root<Booking> bRoot = query.from(Booking.class);
        Predicate p1 = builder.equal(spRoot.get("id"), idShiper);
        Predicate p2 = builder.equal(bRoot.get("shipperDauGia"), spRoot.get("id"));
        Predicate p3 = builder.equal(bRoot.get("donHangDauGia"), dhRoot.get("id"));
        query.where(p1, p2, p3);
        query.multiselect(
                dhRoot.get("id"),
                dhRoot.get("orderName").as(String.class),
                dhRoot.get("pickupAddress").as(String.class),
                dhRoot.get("receiveAddress").as(String.class),
                dhRoot.get("trangThaiDonHang")
        );

        Query<Object[]> q = session.createQuery(query);

        return q.getResultList();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.repository.impl;

import com.delivery.Utils.Utils;
import com.delivery.pojo.Booking;
import com.delivery.pojo.Customer;
import com.delivery.pojo.DonHang;
import com.delivery.pojo.GioDauGia;
import com.delivery.pojo.Shipper;
import com.delivery.pojo.User;
import com.delivery.repository.BookingRepository;
import com.delivery.service.OrderService;
import com.delivery.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.query.Query;

/**
 *
 * @author PC
 */
@Repository
// Bật cờ: Lan truyền 2 thao tác cùng 1 lúc (propagation = Propagation.REQUIRED)
public class BookingRepositoryImpl implements BookingRepository {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    @Transactional
    public boolean addBook(Map<Integer, GioDauGia> gioDauGiaTaiXe) {
        long millis = System.currentTimeMillis();
        java.sql.Date ngayhientai = new java.sql.Date(millis);

        try {
            Session s = this.sessionFactory.getObject().getCurrentSession();
            User user = this.userService.getUsersAuth();

            Shipper taiXeDauGia = new Shipper();
            for (GioDauGia c : gioDauGiaTaiXe.values()) {
                Booking b = new Booking();
                DonHang dhTim = this.orderService.getDonHangById(c.getOrderId());
                dhTim.setTrangThaiDonHang(DonHang.TrangThai.DANG_DAU_GIA);
                b.setDonHangDauGia(dhTim);
                b.setKhachHangQuyetDinh(dhTim.getIdKhachHangSoHuuDonHang());
                b.setQuantity(c.getQuantity());
                taiXeDauGia = s.get(Shipper.class, user.getId());
                b.setShipperDauGia(taiXeDauGia);
                b.setBookingDay(new Date());
                b.setTinhTrangBook(false);
                if (c.getNgayYeuCauGiao().compareTo(ngayhientai) < 0) {
                    return false;
                }
                s.save(b);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public List<Object[]> getBooking(int orderId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);

        Root<Booking> bRoot = query.from(Booking.class);
        Root<DonHang> dhRoot = query.from(DonHang.class);
        Predicate p1 = builder.equal(bRoot.get("donHangDauGia"), dhRoot.get("id"));
        Predicate p2 = builder.equal(dhRoot.get("id"), orderId);
        query.where(builder.and(p1, p2));
        query.multiselect(
                bRoot.get("id"),
                dhRoot.get("orderName").as(String.class),
                bRoot.get("shipperDauGia").as(Shipper.class),
                bRoot.get("khachHangQuyetDinh").as(Customer.class),
                bRoot.get("bookingDay"),
                bRoot.get("tinhTrangBook"),
                bRoot.get("quantity"),
                bRoot.get("donHangDauGia").as(DonHang.class),
                dhRoot.get("idKhachHangSoHuuDonHang").as(Customer.class)
        );

        Query<Object[]> q = session.createQuery(query);

        return q.getResultList();

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void xacnhanbooking(int bookingid) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        User user = this.userService.getUsersAuth();
        Booking b = session.get(Booking.class,
                bookingid);
        b.setTinhTrangBook(true);
        DonHang dh = orderService.getDonHangById(b.getDonHangDauGia().getId());
        dh.setTrangThaiDonHang(dh.getTrangThaiDonHang().DANG_GIAO);
        session.update(b);
    }

    @Override
    @Transactional
    public List<Object[]> getBookingXacNhan(User u) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class
        );
        Root<DonHang> dhRoot = query.from(DonHang.class
        );
        Root<Booking> bRoot = query.from(Booking.class
        );

        Predicate p1 = builder.equal(bRoot.get("donHangDauGia"), dhRoot.get("id"));
        Predicate p2 = builder.equal(dhRoot.get("trangThaiDonHang"), DonHang.TrangThai.DANG_GIAO);
        Predicate p3 = builder.equal(bRoot.get("tinhTrangBook"), true);
        Predicate p4 = builder.equal(bRoot.get("shipperDauGia"), u.getId());
        query.where(p1, p2, p3, p4);

        query.multiselect(
                bRoot.get("id"),
                dhRoot.get("id"),
                dhRoot.get("orderName").as(String.class
                ),
                bRoot.get("khachHangQuyetDinh").as(Customer.class
                ),
                dhRoot.get("pickupAddress").as(String.class
                ),
                dhRoot.get("receiveAddress").as(String.class
                ),
                bRoot.get("bookingDay"),
                bRoot.get("tinhTrangBook"),
                dhRoot.get("trangThaiDonHang")
        );

        Query<Object[]> q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    @Transactional
    public boolean deleteBooking(int bookingid) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            Booking book = session.get(Booking.class,
                    bookingid);
            session.delete(book);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Booking getBookingById(int bookingId) {
        return this.sessionFactory.getObject().getCurrentSession().get(Booking.class,
                bookingId);
    }

    @Override
    @Transactional
    public void xacnhangiaohang(DonHang dh) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.update(dh);

        } catch (HibernateException ex) {
            System.err.println("==Bug xacNhan: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}

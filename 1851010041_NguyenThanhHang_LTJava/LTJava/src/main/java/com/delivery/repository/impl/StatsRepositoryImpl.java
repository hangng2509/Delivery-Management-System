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
import com.delivery.repository.StatsRepository;
import java.util.ArrayList;
import java.util.Date;
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
@Transactional
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Object[]> tanSuatGiaoHangStats() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);

        Root<DonHang> dhRoot = query.from(DonHang.class);
        Root<Booking> bRoot = query.from(Booking.class);

        Predicate p1 = builder.equal(bRoot.get("donHangDauGia"), dhRoot.get("id"));
        Predicate p2 = builder.equal(dhRoot.get("trangThaiDonHang"), DonHang.TrangThai.GIAO_THANH_CONG);
        Predicate p3 = builder.equal(bRoot.get("tinhTrangBook"), true);
        query.where(p1, p2, p3);

        query.multiselect(
                bRoot.get("id"),
                dhRoot.get("id"),
                bRoot.get("shipperDauGia").as(Shipper.class),
                builder.count(dhRoot.get("id"))
        );
        query.groupBy(bRoot.get("shipperDauGia").as(Shipper.class));

        Query q = session.createQuery(query);

        return q.getResultList();
    }

    @Override
    public List<Object[]> getDoanhThuKhachHangStats(Date fromDate, Date toDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root dhRoot = query.from(DonHang.class);
        Root bRoot = query.from(Booking.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(bRoot.get("donHangDauGia"), dhRoot.get("id")));
        predicates.add(builder.equal(dhRoot.get("trangThaiDonHang"), DonHang.TrangThai.GIAO_THANH_CONG));
        predicates.add(builder.equal(bRoot.get("tinhTrangBook"), true));

        if (fromDate != null) {
            predicates.add(builder.greaterThanOrEqualTo(dhRoot.get("ngayLayHang"), fromDate));
        }
        if (toDate != null) {
            predicates.add(builder.lessThanOrEqualTo(dhRoot.get("ngayYeuCauGiao"), toDate));
        }

        //Dùng để bỏ tất cả điều kiện vào bên trong List sau đó parse tất cả dữ liệu predicates thành mảng bằng toArray
        query.where(predicates.toArray(new Predicate[]{}));

        query.multiselect(
                dhRoot.get("id"),
                bRoot.get("khachHangQuyetDinh").as(Customer.class),
                builder.sum(dhRoot.get("price"))
        );

        query.groupBy(dhRoot.get("idKhachHangSoHuuDonHang").as(Customer.class));

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Object[]> getDoanhThuTaiXeStats(Date fromDate, Date toDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root dhRoot = query.from(DonHang.class);
        Root bRoot = query.from(Booking.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(bRoot.get("donHangDauGia"), dhRoot.get("id")));
        predicates.add(builder.equal(dhRoot.get("trangThaiDonHang"), DonHang.TrangThai.GIAO_THANH_CONG));
        predicates.add(builder.equal(bRoot.get("tinhTrangBook"), true));

        if (fromDate != null) {
            predicates.add(builder.greaterThanOrEqualTo(dhRoot.get("ngayLayHang"), fromDate));
        }
        if (toDate != null) {
            predicates.add(builder.lessThanOrEqualTo(dhRoot.get("ngayYeuCauGiao"), toDate));
        }

        //Dùng để bỏ tất cả điều kiện vào bên trong List sau đó parse tất cả dữ liệu predicates thành mảng bằng toArray
        query.where(predicates.toArray(new Predicate[]{}));

        query.multiselect(
                bRoot.get("shipperDauGia").as(Shipper.class),
                builder.sum(dhRoot.get("freeShip"))
        );

        query.groupBy(bRoot.get("shipperDauGia").as(Shipper.class));
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Object[]> getDoanhThuTatCaTaiXeStats(Date fromDate, Date toDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root dhRoot = query.from(DonHang.class);
        Root bRoot = query.from(Booking.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(bRoot.get("donHangDauGia"), dhRoot.get("id")));
        predicates.add(builder.equal(dhRoot.get("trangThaiDonHang"), DonHang.TrangThai.GIAO_THANH_CONG));
        predicates.add(builder.equal(bRoot.get("tinhTrangBook"), true));

        if (fromDate != null) {
            predicates.add(builder.greaterThanOrEqualTo(dhRoot.get("ngayYeuCauGiao"), fromDate));
        }
        if (toDate != null) {
            predicates.add(builder.lessThanOrEqualTo(dhRoot.get("ngayYeuCauGiao"), toDate));
        }

        //Dùng để bỏ tất cả điều kiện vào bên trong List sau đó parse tất cả dữ liệu predicates thành mảng bằng toArray
        query.where(predicates.toArray(new Predicate[]{}));

        query.multiselect(
                bRoot.get("shipperDauGia").as(Shipper.class),
                builder.sum(dhRoot.get("freeShip")),
                builder.function("MONTH", Integer.class, dhRoot.get("ngayYeuCauGiao")),
                builder.function("YEAR", Integer.class, dhRoot.get("ngayYeuCauGiao"))
        );

        query.groupBy(bRoot.get("shipperDauGia").as(Shipper.class),
                builder.function("MONTH", Integer.class, dhRoot.get("ngayYeuCauGiao")),
                builder.function("YEAR", Integer.class, dhRoot.get("ngayYeuCauGiao")));
        Query q = session.createQuery(query);
        return q.getResultList();
    }

}

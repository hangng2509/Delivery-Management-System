<%-- 
    Document   : content
    Created on : Sep 13, 2021, 3:34:36 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles"
           uri="http://tiles.apache.org/tags-tiles" %>
<main id="main">
    <!-- ======= Hero Section ======= -->
    <section id="hero" class="d-flex align-items-center">
        <div class="container">
            <div class="row">
                <div
                    class="
                    col-lg-6
                    d-flex
                    flex-column
                    justify-content-center
                    pt-4 pt-lg-0
                    order-2 order-lg-1
                    "
                    data-aos="fade-up"
                    data-aos-delay="200"
                    >
                    <h1>OU Delivery</h1>
                    <h2>Quản lý các đơn hàng thuận lợi - Hỗ trợ các Shipper hết mình</h2>
                    <div class="d-lg-flex">
                        <a href="#about" class="btn-get-started scrollto">Cùng nhau bắt đầu nào</a>
                        <a
                            href="https://www.youtube.com/watch?v=PTgLwfBHAm0"
                            class="venobox btn-watch-video"
                            data-vbtype="video"
                            data-autoplay="true"
                            >
                            Xem lịch sử hình thành của chúng tôi nhé <i class="icofont-play-alt-2"></i
                            ></a>
                    </div>
                </div>
                <div
                    class="col-lg-6 order-1 order-lg-2 hero-img"
                    data-aos="zoom-in"
                    data-aos-delay="200"
                    >
                    <img
                        src="<c:url value="/assets/img/ship.jpg"/>"
                        class="img-fluid animated"
                        alt=""
                        />
                </div>
            </div>
        </div>
    </section>
    <!-- End Hero -->
    <!-- ======= About Us Section ======= -->
    <section id="about" class="about">
        <div class="container" data-aos="fade-up">
            <div class="section-title">
                <h2>Về chúng tôi</h2>
            </div>

            <div class="row content">
                <div class="col-lg-12">
                    <p>
                        Được thành lập từ năm 2018, OU là sự tin dùng của các nhà cung cấp. 
                        Bạn có thể tìm đến chúng tôi bất kỳ lúc nào và luôn nhận được sự hỗ trợ 24/24. Ở chúng tôi có:
                    </p>
                    <ul>
                        <li>
                            <i class="ri-check-double-line"></i> Nhà cung cấp tạo các đơn hàng nhanh chóng, theo dõi tình hình giao hàng dễ dàng.
                        </li>
                        <li>
                            <i class="ri-check-double-line"></i> Shipper có thể đấu giá để chọn vận chuyển đơn hàng.
                        </li>
                        <li>
                            <i class="ri-check-double-line"></i> Các nhà quản trị có thể quản lý và theo dõi, kiểm soát và thống kê doanh thu rõ ràng.
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </section>
    <!-- End About Us Section -->

    <!-- ======= Team Section ======= -->
    <section id="team" class="team section-bg">
        <div class="container" data-aos="fade-up">
            <div class="section-title">
                <h2>Đội ngũ phát triển</h2>
            </div>
            <div class="d-flex justify-content-center">
                <div class="col-lg-6">
                    <div
                        class="member d-flex align-items-start"
                        data-aos="zoom-in"
                        data-aos-delay="100"
                        >
                        <div class="pic">
                            <img
                                src="<c:url value="/assets/img/customer.jpg"/>"
                                class="img-fluid"
                                alt=""
                                />
                        </div>
                        <div class="member-info">
                            <h4>Hang Nguyen</h4>
                            <span>
                                <p>Senior Student at Open University</p>
                                <p>From 2018 to 2022</p></span>
                            <p>
                                My Major: Computer Science
                            </p>
                            <div class="social">
                                <a href=""><i class="ri-twitter-fill"></i></a>
                                <a href=""><i class="ri-facebook-fill"></i></a>
                                <a href=""><i class="ri-instagram-fill"></i></a>
                                <a href=""> <i class="ri-linkedin-box-fill"></i> </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- End Team Section -->
</main>
<!-- End #main -->

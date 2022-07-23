<%-- 
    Document   : dsdonhang
    Created on : Sep 13, 2021, 10:31:44 PM
    Author     : PC
--%>

<%@page import="com.delivery.pojo.DonHang"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" 
           uri="http://www.springframework.org/security/tags" %> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<section id="about" class="about">
    <div class="container" data-aos="fade-up">
        <div class="section-title">
            <h2>DANH SÁCH CÁC BÀI ĐĂNG MÓN HÀNG CẦN GIAO</h2>
        </div>

        <sec:authorize access="hasRole('ROLE_SHIPPER')"> 
            <form action="">
                <div class="container">
                    <div class="row mb-5">
                        <div class="col-lg-8 mx-auto">
                            <div class="bg-white p-5 rounded shadow">
                                <div class="row">
                                    <div class="form-group col-md-12">
                                        <input id="exampleFormControlInput5" name="kw" placeholder="Tìm kiếm đơn hàng?" class="form-control form-control-underlined">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <div class="md-form">
                                            <label for="inputMDEx">Từ thời điểm</label>
                                            <input type="date" id="inputMDEx" class="form-control" name="ngayLayHang">

                                        </div>

                                    </div>
                                    <div class="form-group col-md-6">
                                        <div class="md-form">
                                            <label for="inputMDEx">Đến thời điểm</label>
                                            <input type="date" id="inputMDEx" class="form-control" name="ngayYeuCauGiao">

                                        </div>

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md">
                                        <button type="submit" class="btn btn-primary rounded-pill btn-block shadow-sm">Tìm kiếm</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')"> 
            <form action="">
                <div class="container">
                    <div class="row mb-5">
                        <div class="col-lg-8 mx-auto">
                            <div class="bg-white p-5 rounded shadow">
                                <div class="row">
                                    <div class="form-group col-md-12">
                                        <input id="exampleFormControlInput5" name="kw" placeholder="Tìm kiếm đơn hàng?" class="form-control form-control-underlined">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <div class="md-form">
                                            <label for="inputMDEx">Từ thời điểm</label>
                                            <input type="date" id="inputMDEx" class="form-control" name="ngayLayHang">

                                        </div>

                                    </div>
                                    <div class="form-group col-md-6">
                                        <div class="md-form">
                                            <label for="inputMDEx">Đến thời điểm</label>
                                            <input type="date" id="inputMDEx" class="form-control" name="ngayYeuCauGiao">

                                        </div>

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md">
                                        <button type="submit" class="btn btn-primary rounded-pill btn-block shadow-sm">Tìm kiếm</button>
                                    </div>
                                </div>
                            </div>



                        </div>
                    </div>
                </div>
            </form>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_CUSTOMER')"> 
            <form action="">
                <div class="container">
                    <div class="row mb-5">
                        <div class="col-lg-8 mx-auto">
                            <div class="bg-white p-5 rounded shadow">


                                <div class="row">
                                    <div class="form-group col-md-12">
                                        <input id="exampleFormControlInput5" name="kw" placeholder="Tìm kiếm đơn hàng?" class="form-control form-control-underlined">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <div class="md-form">
                                            <label for="inputMDEx">Từ thời điểm</label>
                                            <input type="date" id="inputMDEx" class="form-control" name="ngayLayHang">

                                        </div>

                                    </div>
                                    <div class="form-group col-md-6">
                                        <div class="md-form">
                                            <label for="inputMDEx">Đến thời điểm</label>
                                            <input type="date" id="inputMDEx" class="form-control" name="ngayYeuCauGiao">

                                        </div>

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md">
                                        <button type="submit" class="btn btn-primary rounded-pill btn-block shadow-sm">Tìm kiếm</button>
                                    </div>
                                </div>
                            </div>



                        </div>
                    </div>
                </div>
            </form>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')">  
            <ul class="nav nav-pills" style="margin-left: 10px">
                <li class="nav-item">
                    <a href="#tab1" data-toggle="tab" class="nav-link active">Tất cả đơn hàng</a>
                </li>
                <li class="nav-item">
                    <a href="#tab2" data-toggle="tab" class="nav-link">Đơn hàng đã nhận ship</a>
                </li>
                <li class="nav-item">
                    <a href="#tab3" data-toggle="tab" class="nav-link">Đơn hàng đang chờ ship </a>
                </li>
            </ul> 
        </sec:authorize>



        <div class="tab-content"> 

            <div class="tab-pane container active" id="tab1" style="margin-bottom: 10px">
                <div>

                    <ul class="pagination justify-content-end" style="margin:20px"
                        <c:forEach begin="1" end="${Math.ceil(counter/6)}" var="i">
                            <li class="page-item"><a class="page-link" href="<c:url value="/dsdonhang/"/>?page=${i}">${i}</a></li>
                        </c:forEach>
                    </ul>

                </div>

                <div class="row">


                    <c:forEach var="order" items="${orders}">

                        <div class="card col-md-4">
                            <a href="<c:url value="/order/${order.id}"/>">
                                <div class ="card-body">
                                    <c:if test="${order.anh != null && order.anh.startsWith('https') == true}">
                                        <img class="img-fluid"src="<c:url value="${order.anh}"/>"alt="${order.anh}" width="300" />
                                    </c:if>
                                    <c:if test="${order.anh ==  null || order.anh.startsWith('https') == false}">
                                        <img class="img-fluid"src="<c:url value="/assets/img/mypham.jpg"/>"alt="${order.anh}" width="300" />
                                    </c:if>
                                </div>
                            </a>
                            <div class="form-group">
                                <div class="card-footer">
                                    <h4 style="color: #161616;font-style: oblique">${order.orderName}</h4> 
                                    <p>Địa chỉ lấy hàng: ${order.pickupAddress}</p>
                                    <p>Địa chỉ giao hàng: ${order.receiveAddress}</p>
                                    <p>Ngày lấy hàng: ${order.ngayLayHang}</p>
                                    <p>Ngày mong muốn giao hàng: ${order.ngayYeuCauGiao}</p>
                                    <p>Tiền vận chuyển: ${order.freeShip} VNĐ</p>
                                    <p>Tiền hàng: ${order.price} VNĐ</p>
                                    <p>Trạng thái đơn hàng: <b style="color:black;font-size: 14px">${order.trangThaiDonHang}</b></p>
                                    <b><p style="color:black;font-size: 20px">Người đăng: ${order.idKhachHangSoHuuDonHang.idCus.name}</p></b>

                                </div>
                            </div>

                            <div class="card-footer">
                                <sec:authorize access="!hasRole('ROLE_SHIPPER')"> 

                                    <p style="color: red;font-style: italic">Chỉ có shipper mới có quyền đấu giá</p>
                                    <p><a style="color:black"href="<c:url value="/order/${order.id}"/>">Xem thêm chi tiết đơn hàng</a></p>

                                </sec:authorize>

                                <sec:authorize access="hasRole('ROLE_SHIPPER')"> 
                                    <c:if test="${order.trangThaiDonHang== 'CHUA_GIAO' || order.trangThaiDonHang== 'DANG_DAU_GIA'}">
                                        <br>
                                        <a style="color:green"href="javascript:;" onclick="addToCart(${order.id})">Đấu giá đơn hàng</a> 

                                        <p><a style="color:red"href="<c:url value="/order/${order.id}"/>">Xem thêm chi tiết đơn hàng</a></p>
                                    </c:if>
                                    <c:if test="${order.trangThaiDonHang  == 'DANG_GIAO'}">
                                        <p style="color: red;font-style: italic">Đơn hàng trong giai đoạn đang giao hàng</p>
                                        <p><a style="color:black"href="<c:url value="/order/${order.id}"/>">Xem thêm chi tiết đơn hàng</a></p>
                                    </c:if>
                                    <c:if test="${order.trangThaiDonHang  == 'GIAO_THANH_CONG'}">
                                        <p style="color: red;font-style: italic">Đơn hàng đã giao thành công</p>
                                        <p><a style="color:black"href="<c:url value="/order/${order.id}"/>">Xem thêm chi tiết đơn hàng</a></p>
                                    </c:if>
                                </sec:authorize>
                            </div>
                        </div>

                    </c:forEach>

                </div>
            </div>

            <div class="tab-pane container " id="tab2" style="margin-bottom: 10px">
                <div class="row"  style="margin-top: 20px">
                    <c:forEach var="OS" items="${orderShip}">

                        <div class="card col-md-4">
                            <a href="<c:url value="/order/${OS[0]}"/>">
                                <div class ="card-body">
                                    <c:if test="${OS[2] != null && OS[2].startsWith('https') == true}">
                                        <img class="img-fluid"src="<c:url value="${OS[2]}"/>"alt="${OS[2]}" width="300" />
                                    </c:if>
                                    <c:if test="${OS[2] ==  null || OS[2].startsWith('https') == false}">
                                        <img class="img-fluid"src="<c:url value="/assets/img/mypham.jpg"/>"alt="${OS[2]}" width="300" />
                                    </c:if>
                                </div>
                            </a>
                            <div class="form-group">
                                <div class="card-footer">
                                    <h4 style="color: #161616;font-style: oblique">${OS[1]}</h4> 
                                    <p>Mã đơn hàng: ${OS[0]}</p>
                                    <p>Địa chỉ lấy hàng: ${OS[3]}</p>
                                    <p>Địa chỉ giao hàng: ${OS[4]}</p>
                                    <p>Shipper giao hàng: ${OS[5].idShipper.name}</p>
                                    <p>Người đăng bài viết: ${OS[6].idCus.name}</p>
                                    <c:if test="${OS[7] == true}">
                                        <p>Tình trạng đấu giá: Thành công</p>
                                    </c:if>
                                    <c:if test="${OS[7] == false}">
                                        <p>Tình trạng đấu giá: Chưa thành công</p>
                                    </c:if>
                                    <p>Trạng thái đơn hàng: ${OS[8]}</p>
                                </div>
                            </div>
                            <div class="card-footer">
                                <p><a style="color:red"href="<c:url value="/order/${OS[0]}"/>">Xem thêm chi tiết đơn hàng</a></p>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>

            <div class="tab-pane container " id="tab3">
                <div class="row"  style="margin-top:20px">
                    <c:forEach var="ONS" items="${orderNotShip}">

                        <div class="card col-md-4">
                            <a href="<c:url value="/order/${ONS.id}"/>">
                                <div class ="card-body">
                                    <c:if test="${ONS.anh != null && ONS.anh.startsWith('https') == true}">
                                        <img class="img-fluid"src="<c:url value="${ONS.anh}"/>"alt="${ONS.anh}" width="300" />
                                    </c:if>
                                    <c:if test="${ONS.anh ==  null || ONS.anh.startsWith('https') == false}">
                                        <img class="img-fluid"src="<c:url value="/assets/img/mypham.jpg"/>"alt="${ONS.anh}" width="300" />
                                    </c:if>
                                </div>
                            </a>
                            <div class="form-group">
                                <div class="card-footer">
                                    <h4 style="color: #161616;font-style: oblique">${ONS.orderName}</h4> 
                                    <p>Mã đơn hàng: ${ONS.id}</p>
                                    <p>Địa chỉ lấy hàng: ${ONS.pickupAddress}</p>
                                    <p>Địa chỉ giao hàng: ${ONS.receiveAddress}</p>
                                    <p>Người đăng bài viết: ${ONS.idKhachHangSoHuuDonHang.idCus.name}</p>
                                    <p>Trạng thái đơn hàng: ${ONS.trangThaiDonHang}</p>
                                </div>
                            </div>
                            <div class="card-footer">
                                <p><a style="color:red"href="<c:url value="/order/${ONS.id}"/>">Xem thêm chi tiết đơn hàng</a></p>
                            </div>
                        </div>
                    </c:forEach>

                </div>

            </div>

        </div>


    </div>
</section>

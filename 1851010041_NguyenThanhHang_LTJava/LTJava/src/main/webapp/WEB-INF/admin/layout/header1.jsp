<%-- 
    Document   : header
    Created on : Sep 11, 2021, 9:00:33 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <nav class="navbar navbar-expand-lg navbar-light py-3" style="background-color: #e3f2fd">
        <div class="container"><a href="<c:url value="/admin"/>" class="navbar-brand d-flex align-items-center"> <i class="fa fa-snowflake-o fa-lg text-primary mr-2"></i><strong>Admin Page</strong></a>
            <button type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler"><span class="navbar-toggler-icon"></span></button>
            <div id="navbarSupportedContent" class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active"><a href="<c:url value="/admin"/>" class="nav-link font-italic">Trang chủ</a></li>
                    <li class="nav-item active"><a href="<c:url value="/admin/orders"/>" class="nav-link font-italic">Quản Lý Đơn Hàng</a></li>
                    <li class="nav-item active"><a href="<c:url value="/admin/quanlytaixe"/>" class="nav-link font-italic">Quản Lý Tài Xế</a></li>
                    <li class="nav-item active"><a href="<c:url value="/admin/quanlykhuyenmai"/>" class="nav-link font-italic">Quản Lý Khuyến Mãi</a></li>
                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle nav-link font-italic" href="#" id="navbardrop" data-toggle="dropdown" >
                            Thống kê 
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:url value="/admin/orderStats"/>" style="font-size: 15px">Thống kê tần suất giao hàng</a>
                            <a class="dropdown-item" href="<c:url value="/admin/cusStats"/>" style="font-size: 15px">Thống kê doanh thu khách hàng</a>
                            <a class="dropdown-item" href="<c:url value="/admin/shipStats"/>" style="font-size: 15px">Thống kê doanh thu tài xế</a>
                            <a class="dropdown-item" href="<c:url value="/admin/thongketatcataixe"/>" style="font-size: 15px">Thống kê doanh thu tất cả tài xế</a>
                        </div>
                    </li>
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name == null}">
                            <li class="nav-item active"><a href="<c:url value="/dangky"/>" class="nav-link font-italic"><i class="fas fa-user-plus" style="margin-right: 2px"></i>Đăng ký</a></li>
                            <li class="nav-item active"><a href="<c:url value="/login"/>" class="nav-link font-italic"><i class="fas fa-user-graduate" style="margin-right: 5px"></i>Đăng nhập</a></li>
                            </c:when>
                            <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <li class="nav-item active" style="margin-right: 20px"><a href="<c:url value="/" />" class="nav-link font-italic">Trang chính</a></li>
                            <li class="nav-item active"><a href="<c:url value="/admin" />" class="nav-link font-italic">
                                    <c:if test="${currentUser.avatar != null && currentUser.role !='ROLE_SHIPPER'}">
                                        <img style="width: 25px" src="${currentUser.avatar}" class="img-fluid rounded-circle"/>
                                    </c:if>
                                    <c:if test="${currentUser.role =='ROLE_SHIPPER'}">
                                        <i class="fas fa-user-graduate" style="margin-right: auto;margin-left: auto"></i>
                                    </c:if> 
                                    <c:if test="${currentUser.avatar == null}">
                                        <i class="fas fa-user-graduate" style="margin-right: auto;margin-left: auto"></i>
                                    </c:if>
                                    ${pageContext.request.userPrincipal.name}</a></li>
                            <li class="nav-item active"><a href="<c:url value="/logout"/>" class="nav-link font-italic"><i class="fas fa-sign-out-alt"style="margin-right: 2px"></i>Đăng xuất</a></li>
                            </c:when>
                        </c:choose>
                </ul>
            </div>
        </div>
    </nav>
</div>
<!-- End Header -->

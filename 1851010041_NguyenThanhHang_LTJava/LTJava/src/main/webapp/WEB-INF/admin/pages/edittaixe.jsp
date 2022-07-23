<%-- 
    Document   : dangky
    Created on : September 19, 2021, 2:29:15 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<link href="<c:url value="/assets/img/ou.png"/>" rel="icon" />
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">


<div class="container">
    <hr>
    <div class="alert alert-danger">${errAddShipper}</div>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <header class="card-header">
                    <h4 class="card-title mt-2">THAY ĐỔI THÔNG TIN TÀI XẾ</h4>
                </header>
                <article class="card-body">
                    <c:url value="/admin/quanlytaixe/edittaixe" var="profileEdit"/>
                    <form:form method="post" action="${profileEdit}" modelAttribute="shipperPro" enctype="multipart/form-data" >
                        <div class="form-row">
                            <div class="col-md-12 form-group">
                                <label>Mã tài xế</label>   
                                <form:input id="id" cssClass="form-control" path="id" readonly="true"/>
                                <form:errors path="id" cssClass="text-danger" />
                            </div> <!-- form-group end.// -->
                        </div>
                        <div class="form-row">
                            <div class="col form-group">
                                <label>Họ và Tên</label>   
                                <form:input id="name" cssClass="form-control" path="name" />
                                <form:errors path="name" cssClass="text-danger" />
                            </div> <!-- form-group end.// -->
                            <div class="col form-group">
                                <label>Địa chỉ</label>
                                <form:input id="address" cssClass="form-control" path="address" />
                                <form:errors path="address" cssClass="text-danger" />
                            </div> <!-- form-group end.// -->
                        </div>
                        <div class="form-row">
                            <div class="col form-group">
                                <label>Email</label>   
                                <form:input id="email" cssClass="form-control" path="email" />
                                <form:errors path="email" cssClass="text-danger" />
                            </div> <!-- form-group end.// -->

                            <div class="col form-group">
                                <label>Số điện thoại</label>
                                <form:input id="phone" cssClass="form-control" path="phone" />
                                <form:errors path="phone" cssClass="text-danger" />
                            </div> <!-- form-group end.// -->

                        </div> <!-- form-row end.// -->
                        <div class="form-group">
                            <label>Hình ảnh</label>
                            <c:if test="${shipperPro.avatar != null && shipperPro.avatar.startsWith('https') == true}">
                                <img class="img-fluid"src="<c:url value="${shipperPro.avatar}"/>"alt="${shipperPro.avatar}" width="100" style="margin-left: auto;margin-right: auto;display:block" />
                            </c:if>
                            <form:input type="file" path="img" cssClass="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>Ngày sinh</label>
                            <form:input type="date"  cssClass="form-control" id="dob" path="dob"/>
                        </div> <!-- form-group end.// -->
                        <div class="form-group">
                            <label class="form-check form-check-inline">
                                <form:radiobutton path="gender" value="true" cssclass="form-check-input" checked="checked"/>  
                                <span class="form-check-label"> Nam </span>
                            </label>
                            <label class="form-check form-check-inline">
                                <form:radiobutton path="gender" value="false" cssclass="form-check-input" /> 
                                <span class="form-check-label"> Nữ </span>
                            </label>
                        </div> <!-- form-group end.// -->
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <label>Tên đăng nhâp</label>
                                <form:input id="username" cssClass="form-control" path="username" readonly="true" />
                            </div> <!-- form-group end.// -->
                        </div> <!-- form-row.// -->
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>Nhập mật khẩu</label>
                                <form:input id="password" cssClass="form-control" path="password" type="password"/>
                                <form:errors path="password" cssClass="text-danger" />
                            </div>
                            <div class="form-group col-md-6">
                                <label>Nhập lại mật khẩu</label>
                                <form:input id="confirmPassword" cssClass="form-control" path="confirmPassword" type="password" />
                                <form:errors path="confirmPassword" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block">Cập nhật thông tin tài xế</button>
                        </div>     

                    </form:form>
                </article> 
            </div> 
        </div> 
    </div> 
</div> 
<br><br>

<%-- 
    Document   : login
    Created on : Sep 19, 2021, 5:05:06 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${param.error != null}">
    <div class="alert alert-danger">
        Chương trình đang xảy ra lỗi! Vui lòng quay lại sau!
    </div>
</c:if>
<c:if test="${param.accessDenied != null}">
    <div class="alert alert-danger">
        Bạn không có quyền truy cập trang web này
    </div>
</c:if>
<!-- Login Content -->
<div class="container-login">
    <div class="row justify-content-center">
        <div class="col-xl-6 col-lg-12 col-md-9">
            <div class="card shadow-sm my-5">
                <div class="card-body p-0">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="login-form">                                          
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Đăng nhập</h1>
                                </div>
                                <c:url value="/login" var="action"/>
                                <form class="form" action="${action}" method="post">
                                    <form:errors path="*" element="div" /> 
                                    <div class="form-group">
                                        <input type="text" id="username" name="username" class="form-control"
                                               placeholder="Nhập tài khoản">
                                        <form:errors path="username" cssClass="text-danger" />
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control" name="password" placeholder="Nhập mật khẩu">
                                        <form:errors path="password" cssClass="text-danger" />
                                    </div>
                                    <div class="form-group">
                                        <input type="submit" class="btn btn-primary btn-block" value="Đăng nhập"/>
                                    </div>
                                    <hr>
                                </form>
                                <hr>
                                <div class="text-center">
                                    <a class="font-weight-bold small" href="<c:url value="/dangky"/>">Tạo tài khoản không?</a>
                                </div>
                                <div class="text-center">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

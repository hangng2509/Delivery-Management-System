<%-- 
    Document   : themkhuyenmai
    Created on : Sep 25, 2021, 5:08:55 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">


<div class="container">
    <hr>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <header class="card-header">
                    <c:if test="${promotions.id > 0}">
                        <h4 class="card-title mt-2">CẬP NHẬT KHUYẾN MÃI</h4>
                    </c:if>
                    <c:if test="${promotions.id <= 0}">
                        <h4 class="card-title mt-2">THÊM KHUYẾN MÃI</h4>
                    </c:if>
                </header>
                <article class="card-body">
                    <form:form method="post" modelAttribute="promotions" enctype="multipart/form-data" >
                        <div class="row">
                            <c:if test="${promotions.id>0}">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">Mã khuyến mãi</label>
                                        <form:input id="id" cssClass="form-control" path="id" readonly="true"/>
                                        <form:errors path="id" cssClass="text-danger" />
                                    </div>
                                </div>
                            </c:if>
                        </div>
                        <div class="form-row">
                            <div class="col form-group">
                                <label>Tên Khuyến Mãi</label>   
                                <form:input id="tenKhuyenMai" cssClass="form-control" path="tenKhuyenMai" />
                                <form:errors path="tenKhuyenMai" cssClass="text-danger" />
                            </div> <!-- form-group end.// -->
                            <div class="col form-group">
                                <label> Giá Trị Khuyến Mãi</label>
                                <form:input id="giaTriKhuyenMai" cssClass="form-control" path="giaTriKhuyenMai" />
                                <form:errors path="giaTriKhuyenMai" cssClass="text-danger" />
                                <p class="text-danger">${errGiaTri}</p>
                            </div> <!-- form-group end.// -->
                        </div>
                        <div class="form-group">
                            <label>Ngày áp dụng</label>
                            <form:input type="date"  cssClass="form-control" id="ngayApDung" path="ngayApDung"/>
                        </div> <!-- form-group end.// -->
                        <div class="form-group">
                            <label>Ngày kết thúc</label>
                            <form:input type="date"  cssClass="form-control" id="ngayKetThuc" path="ngayKetThuc"/>
                        </div> <!-- form-group end.// -->
                        <div class="form-group">
                            <label class="form-check form-check-inline">
                                <form:radiobutton path="status" value="false" cssclass="form-check-input" checked="checked"/>  
                                <span class="form-check-label"> Đang hoạt động </span>
                            </label>
                            <label class="form-check form-check-inline">
                                <form:radiobutton path="status" value="true" cssclass="form-check-input" /> 
                                <span class="form-check-label"> Hết hoạt động </span>
                            </label>
                        </div> <!-- form-group end.// -->
                        <c:if test="${promotions.id>0}">
                            <div class="container mt-4 mb-4">
                                <button type="submit" class="btn btn-primary pull-right">Cập nhật khuyến mãi</button>
                                <div class="clearfix"></div>
                            </div>
                        </c:if>
                        <c:if test="${promotions.id<=0}">
                            <div class="container mt-4 mb-4">
                                <button type="submit" class="btn btn-primary pull-right">Thêm khuyến mãi</button>
                                <div class="clearfix"></div>
                            </div>
                        </c:if>
                    </form:form>
                </article> <!-- card-body end .// -->

            </div> <!-- card.// -->
        </div> <!-- col.//-->

    </div> <!-- row.//-->

</div> 
<!--container end.//-->

<br><br>


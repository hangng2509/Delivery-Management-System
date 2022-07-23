<%-- 
    Document   : dsshipper
    Created on : Oct 25, 2021, 12:13:25 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" 
           uri="http://www.springframework.org/security/tags" %> 
<section id="about" class="about">
    <div class="container" data-aos="fade-up">
        <div class="section-title">
            <h2>DANH SÁCH THÔNG TIN TÀI XẾ</h2>
        </div>
        <div>
            <ul class="pagination justify-content-end" style="margin:20px 0">
                <c:forEach begin="1" end="${Math.ceil(counter/6)}" var="i">
                    <li class="page-item"><a class="page-link" href="<c:url value="/dstaixe/"/>?page=${i}">${i}</a></li>
                    </c:forEach>
            </ul>
        </div>
        <div class="row">
            <c:forEach var="s" items="${shippers}">
                <div class="card">
                    <a href="<c:url value="/dstaixe/${s.id}"/>">
                        <div class ="card-body">
                            <img class="img-fluid"src="<c:url value="/assets/img/team/shipPic.jpg"/>"alt="${s.idShipper.avatar}" width="300" />
                        </div>
                    </a>
                    <div class="form-group">
                        <div class="card-footer">
                            <h4 style="font-style: oblique;color: navy">Tài xế: ${s.idShipper.name}</h4> 
                            <p>Ngày sinh: ${s.idShipper.dob}</p>
                            <c:if test="${s.idShipper.gender == true}">
                                <p>Giới tính: Nam</p>
                            </c:if>
                            <c:if test="${s.idShipper.gender == false}">
                                <p>Giới tính: Nữ</p>
                            </c:if>
                            <p>Số điện thoại liên lạc: ${s.idShipper.phone}</p>
                            <p>Email: ${s.idShipper.email}</p>
                        </div>
                    </div>
                    <div class="card-footer">
                        <p><a style="color:red"href="<c:url value="/dstaixe/${s.id}"/>">Xem thêm chi tiết thông tin</a></p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
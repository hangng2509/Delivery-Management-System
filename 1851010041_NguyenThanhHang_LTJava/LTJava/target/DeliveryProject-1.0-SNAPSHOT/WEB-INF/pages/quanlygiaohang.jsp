<%-- 
    Document   : quanlydaugia
    Created on : Sep 23, 2021, 12:13:06 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" 
           uri="http://www.springframework.org/security/tags" %> 
<br>
<h1 class="text-center text-danger" >QUẢN LÝ GIAO HÀNG</h1>
<div class="card-body">
    <div class="table-responsive">
        <table class="table">
            <tr>
                <th>Mã đấu giá</th>
                <th>Mã đơn hàng</th>
                <th>Tên đơn hàng</th>
                <th>Tên khách hàng sở hữu</th>
                <th>Email khách hàng</th>
                <th>Số điện thoại khách hàng</th>
                <th>Địa chỉ lấy hàng</th>
                <th>Địa chỉ nhận hàng</th>
                <th>Ngày đấu giá</th>
                <th>Tình trạng đấu giá</th>
                <th>Tình trạng giao hàng</th>
                <th class="text text-center" ></th>
            </tr>
            <c:forEach var="QLGH" items="${QLGH}">
                <tr>
                    <td>${QLGH[0]}</td>
                    <td>${QLGH[1]}</td>
                    <td>${QLGH[2]}</td>
                    <td>${QLGH[3].idCus.name}</td>
                    <td>${QLGH[3].idCus.email}</td>
                    <td>${QLGH[3].idCus.phone}</td>
                    <td>${QLGH[4]}</td>
                    <td>${QLGH[5]}</td>
                    <td>${QLGH[6]}</td>
                    <c:if test="${QLGH[7] == true}">
                        <td>Đấu giá thành công</td>
                    </c:if>
                    <c:if test="${QLGH[7] == false}">
                        <td>Đấu giá thất bại</td>
                    </c:if>
                    <td>${QLGH[8]}</td>
                    <c:if test="${QLGH[8]== 'DANG_GIAO'}">
                        <td>
                            <a href="javascript:;" class="btn btn-success" style="font-size: 15px" onclick="xacnhangiaohang(${QLGH[0]})">Xác nhận giao hàng thành công</a>
                        </td>
                    </c:if>
                    <c:if test="${QLGH[8]== 'GIAO_THANH_CONG'}">
                        <td><a style="color:red;font-size: 15px;font-style: italic">Bạn đã giao thành công đơn hàng này</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            <br>
        </table>
    </div>
</div>
<br>

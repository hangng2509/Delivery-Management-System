<%-- 
    Document   : tinhtrangdaugia
    Created on : Sep 13, 2021, 10:55:48 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" 
           uri="http://www.springframework.org/security/tags" %> 
<br>
<h1 class="text-center text-danger" >ĐẤU GIÁ ĐƠN HÀNG</h1>

<h3 class="text text-danger text-center" style="font-size: 30px;font-style: italic">${errNgay}</h3>
<div class="card-body">
    <div class="table-responsive">
        <table class="table">
            <tr>
                <th>Mã đơn hàng</th>
                <th>Tên đơn hàng</th>
                <th>Đơn giá</th>
                <th>Tiền vận chuyển</th>
                <th>Ngày mong muốn giao hàng</th>
                <th>Tổng giá trị</th>
                <th>Số lần đấu giá</th>
            </tr>
            <c:forEach var="c" items="${carts}">
                <tr>
                    <td>${c.orderId}</td>
                    <td>${c.orderName}</td>
                    <td>${c.price}</td>
                    <td>${c.freeShip}</td>
                    <c:if test="${c.ngayYeuCauGiao == null}">

                        <td><p class="text text-danger text-center" style="font-size: 10px;font-style: italic">Đơn hàng này chưa cập nhật ngày giao hàng</p></td>
                    </c:if>
                    <c:if test="${c.ngayYeuCauGiao != null}">
                        <td>${c.ngayYeuCauGiao}</td>
                    </c:if>

                    <td>${c.total}</td>
                    <td>${c.quantity}</td>
                    <td><input type="button" value="Xóa" class="btn btn-danger" onclick="deleteCart(${c.orderId})"/></td>
                </tr>
            </c:forEach>
            <br>

        </table>
    </div>
</div>
<div style="display:flex; justify-content:flex-end; width:100%; padding-right:10px;">

    <input type="button" class="btn btn-warning" onclick="pay()"value="Xác nhận đấu giá"/>



</div>
<br>
<p class="text text-danger text-sm-right" style="font-size: 15px;font-style: italic">Không đấu giá các đơn hàng chưa cập nhật ngày giao hàng</p>
<br><br>
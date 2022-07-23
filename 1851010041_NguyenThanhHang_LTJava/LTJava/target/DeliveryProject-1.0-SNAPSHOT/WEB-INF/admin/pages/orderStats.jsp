<%-- 
    Document   : orderStats
    Created on : Oct 31, 2021, 6:51:50 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" 
           uri="http://www.springframework.org/security/tags" %> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-danger">THỐNG KÊ TẦN SUẤT GIAO HÀNG THÀNH CÔNG THEO TỪNG TÀI XẾ</h1>

<div style="top:50px; width:500px; height:300px;margin-left: auto;margin-right: auto;bottom: 0px">
    <canvas id="tsStatsChart"></canvas>
</div>

<div class="card-body text-center">
    <div class="table-responsive">

        <table class="table">
            <tr>
                <th>
                    Mã đơn hàng
                </th>
                <th>
                    Mã nhân viên
                </th>
                <th>
                    Tên nhân viên
                </th>
                <th>
                    Số lượng đơn hàng giao thành công
                </th>
            </tr>

            <c:forEach items="${tanSuatChart}" var="ts">
                <tr>
                    <td>${ts[1]}</td>
                    <td>${ts[2].id}</td>
                    <td>${ts[2].idShipper.name}</td>
                    <td>${ts[3]}</td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>


<script>
    let shipLabel = [], shipDonHang = []

    <c:forEach items="${tanSuatChart}" var="ts">
    shipLabel.push('${ts[2].idShipper.name}')
    shipDonHang.push(${ts[3]})
    </c:forEach>
    window.onload = function () {
        tsChart("tsStatsChart", shipLabel, shipDonHang);
    }
</script>
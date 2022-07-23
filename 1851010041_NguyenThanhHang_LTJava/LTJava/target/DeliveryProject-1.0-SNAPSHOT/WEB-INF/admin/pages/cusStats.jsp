<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" 
           uri="http://www.springframework.org/security/tags" %> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-danger">THỐNG KÊ DOANH THU KHÁCH HÀNG</h1>


<form action="">

    <div class="form-group col-md-6" style="margin-left: auto;margin-right: auto;display: block">
        <div class="md-form">
            <label for="inputMDEx">Từ thời điểm</label>
            <input type="date" id="inputMDEx" class="form-control" name="fromDate">

        </div>

    </div>
    <div class="form-group col-md-6" style="margin-left: auto;margin-right: auto;display: block">
        <div class="md-form">
            <label for="inputMDEx">Đến thời điểm</label>
            <input type="date" id="inputMDEx" class="form-control" name="toDate">

        </div>

    </div>

    <div class="row col-md-6" style="margin-left: auto;margin-right: auto;display: block">
        <div class="col-md">
            <button type="submit" class="btn btn-primary rounded-pill btn-block shadow-sm">Báo cáo</button>
        </div>
    </div>
</form>



<div style="top:50px; width:500px; height:300px;margin-left: auto;margin-right: auto;bottom: 0px">
    <canvas id="cusStatsChart"></canvas>
</div>

<div class="card-body text-center">
    <div class="table-responsive">

        <table class="table">
            <tr>
                <th>
                    Mã khách hàng
                </th>
                <th>
                    Tên khách hàng
                </th>
                <th>
                    Doanh thu khách hàng
                </th>
            </tr>

            <c:forEach items="${doanhThuCusChart}" var="cus">
                <tr>
                    <td>${cus[1].id}</td>
                    <td>${cus[1].idCus.name}</td>
                    <td>${cus[2]}</td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>


<script>
    let cusLabel = [], cusSumDoanhThu = []

    <c:forEach items="${doanhThuCusChart}" var="cus">
    cusLabel.push('${cus[1].idCus.name}')
    cusSumDoanhThu.push(${cus[2]})
    </c:forEach>
    window.onload = function () {
        cusChart("cusStatsChart", cusLabel, cusSumDoanhThu);
    }
</script>
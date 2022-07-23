<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" 
           uri="http://www.springframework.org/security/tags" %> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-danger">THỐNG KÊ DOANH THU TÀI XẾ</h1>

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
    <canvas id="shipStatsChart"></canvas>
</div>

<div class="card-body text-center">
    <div class="table-responsive">

        <table class="table">
            <tr>
                <th>
                    Mã tài xế
                </th>
                <th>
                    Tên tài xế
                </th>
                <th>
                    Doanh thu tài xế
                </th>
            </tr>

            <c:forEach items="${doanhThuShipChart}" var="ship">
                <tr>
                    <td>${ship[0].id}</td>
                    <td>${ship[0].idShipper.name}</td>
                    <td>${ship[1]}</td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>


<script>
    let shipDTLabel = [], shipDT = []

    <c:forEach items="${doanhThuShipChart}" var="ship">
    shipDTLabel.push('${ship[0].idShipper.name}')
    shipDT.push(${ship[1]})
    </c:forEach>
    window.onload = function () {
        shipChart("shipStatsChart", shipDTLabel, shipDT);
    }
</script>
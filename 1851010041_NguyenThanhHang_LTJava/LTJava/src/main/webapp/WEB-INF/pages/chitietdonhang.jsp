<%-- 
    Document   : chitietdonhang
    Created on : Oct 28, 2021, 2:30:39 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" 
           uri="http://www.springframework.org/security/tags" %> 

<div class="row" style="padding: 10px">
    <div class="col-md-3" style="padding-top: 100px">
        <c:if test="${orderCT.anh != null && orderCT.anh.startsWith('https') == true}">
            <img class="img-fluid mx-auto d-block"src="<c:url value="${orderCT.anh}"/>"alt="${orderCT.anh}" width="200"/>
        </c:if>
        <c:if test="${orderCT.anh ==  null || orderCT.anh.startsWith('https') == false}">
            <img class="img-fluid mx-auto d-block"src="<c:url value="/assets/img/team/ou.png"/>"alt="${orderCT.anh}" width="200" />
        </c:if>
    </div>
    <div class="col-md-9">
        <h1 style="color: #002a80" class="font-weight-bolder">THÔNG TIN CHI TIẾT ĐƠN HÀNG</h1>
        <br>
        <p class="font-weight-bold font-italic">Mã đơn hàng: ${orderCT.id}</p>
        <p class="font-weight-bold font-italic">Người đăng bài viết:  ${orderCT.idKhachHangSoHuuDonHang.idCus.name}</p>
        <p class="font-weight-bold">Tên đơn hàng: ${orderCT.orderName}</p>
        <p>Mô tả đơn hàng: ${orderCT.description}</p>
        <hr  width="30%" align="center" />
        <p>Địa chỉ lấy hàng: ${orderCT.pickupAddress}</p>
        <p>Địa chỉ giao hàng: ${orderCT.receiveAddress}</p>
        <p>Ngày lấy hàng: ${orderCT.ngayLayHang}</p>
        <p>Ngày mong muốn giao hàng: ${orderCT.ngayYeuCauGiao}</p>
        <p>Trạng thái đơn hàng: ${orderCT.trangThaiDonHang}</p>
        <hr  width="30%" align="center" />
        <h2 style="color: #002a80" class="font-weight-bolder">THÔNG SỐ ĐƠN HÀNG</h2>
        <p>Số lượng hàng: ${orderCT.quantity}</p>
        <p>Cân nặng: ${orderCT.freight}</p>
        <hr  width="30%" align="center" />
        <h2 style="color: #002a80" class="font-weight-bolder">THÀNH PHẦN TIỀN</h2>
        <p>Phí trả cho tài xế: ${orderCT.freeShip}</p>
        <p>Tiền hàng: ${orderCT.price}</p>
        <p>Tổng giá trị đơn hàng (tiền vận chuyển cho tài xế và tiền đơn hàng): ${orderCT.total}</p>
        <p style="color: red;font-style: italic">Lưu ý: Tùy vào hàng hóa mà phí vận chuyển tài xế có thể do người đăng trả trước hoặc khách hàng nhận hàng trả (Xem kỹ: Người đăng bài viết sẽ viết rõ ngay phần mô tả) </p>
        <div>
            <p><a style="color:green; float: right;margin-right: 20px"href="<c:url value="/dsdonhang"/>">Trở về trang danh sách bài đăng</a></p>
        </div>
    </div>
</div>
<br>

<div class="p-3 mb-2 bg-success text-white"><h1 class="text-center text-dark" >QUẢN LÝ ĐẤU GIÁ</h1></div>

<div class="card-body">
    <div class="table-responsive">
        <table class="table">
            <tr>
                <th>Mã đấu giá</th>
                <th>Tên đơn hàng</th>
                <th>Tên tài xế</th>
                <th>Email tài xế</th>
                <th>Số điện thoại tài xế</th> 
                <th>Số lượt yêu thích của tài xế</th>
                <th>Địa chỉ tài xế</th>
                <th>Ngày đấu giá</th>
                <th>Số lần đấu giá</th>
                <th>Trạng thái giao hàng</th>
                <th>Tình trạng đấu giá</th>

            </tr>
            <c:forEach var="b" items="${booking}">
                <tr>
                    <td>${b[0]}</td>
                    <td>${b[1]}</td>
                    <td>${b[2].idShipper.name}</td>
                    <td>${b[2].idShipper.email}</td>
                    <td>${b[2].idShipper.phone}</td>
                    <td>${b[2].soLuotThich}</td>
                    <td>${b[2].idShipper.address}</td>
                    <td>${b[4]}</td>
                    <td>${b[6]}</td>
                    <td>${b[7].trangThaiDonHang}</td>
                    <c:if test="${b[5] == false}">
                        <td><p class="text text-danger text-left" style="font-size: 15px;font-style: italic">Chưa xác nhận đấu giá</p></td>
                    </c:if>
                    <c:if test="${b[5] == true}">
                        <td><p class="text text-success text-left" style="font-size: 15px;font-style: italic">Đã xác nhận shipper ${b[2].idShipper.name} vận chuyển</p></td>
                    </c:if>
                </tr>
            </c:forEach>
            <br>

        </table>
    </div>
</div>
<br>
<br><br>
<%-- 
    Document   : quanlydonhang
    Created on : Sep 18, 2021, 3:35:00 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" 
           uri="http://www.springframework.org/security/tags" %> 
<section id="about" class="about">
    <div class="container" data-aos="fade-up">
        <div class="section-title">
            <h2>QUẢN LÝ ĐƠN HÀNG CẦN GIAO</h2>
        </div>
        <form action="<c:url value="/admin/orders"/>">
            <div class="row" style="padding-bottom:10px">
                <div class="col-md-10" style="margin-left: 30px">
                    <input class="form-control" type="text" name="kw"placeholder="Nhập từ khóa tìm kiếm"/>
                </div>
                <div>
                    <input type="submit" value="Search"class="btn btn-danger" style="float: right">
                </div>
            </div>
        </form>
        <sec:authorize access="hasRole('ROLE_CUSTOMER')"> 
            <div class="card-header card-header-primary">
                <p style="font-style: italic" class="card-title">Bạn có thể thêm đơn hàng tại đây</p>
                <a href="<c:url value="/admin/orders/themdonhang"/>" class="btn btn-primary" >Thêm đơn hàng</a>
            </div>
        </sec:authorize>
        <sec:authorize access="!hasRole('ROLE_CUSTOMER')"> 
            <div class="card-header card-header-primary">
                <p style="font-style: italic" class="card-title">Bạn có thể thêm đơn hàng tại đây</p>
                <p style="color: red">Bạn không có quyền truy cập</p>
            </div>
        </sec:authorize>
        <div>

            <ul class="pagination justify-content-end" style="margin:10px"

                <c:forEach begin="1" end="${Math.ceil(counter/6)}" var="i">
                    <li class="page-item"><a class="page-link" href="<c:url value="/admin/orders/"/>?page=${i}">${i}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="row">

            <c:forEach var="order" items="${orders}">
                <div class="card col-md-4">
                    <a href="<c:url value="/admin/orders/${order.id}"/>">
                        <div class ="card-body">
                            <c:if test="${order.anh != null && order.anh.startsWith('https') == true}">
                                <img class="img-fluid"src="<c:url value="${order.anh}"/>"alt="${order.anh}" width="300" />
                            </c:if>
                            <c:if test="${order.anh ==  null || order.anh.startsWith('https') == false}">
                                <img class="img-fluid"src="<c:url value="/assets/img/mypham.jpg"/>"alt="${order.anh}" width="300" />
                            </c:if>
                        </div>
                    </a>
                    <div class="form-group">
                        <div class="card-footer">
                            <h4 style="color: #161616;font-style: oblique">${order.orderName}</h4> 
                            <p>Địa chỉ lấy hàng: ${order.pickupAddress}</p>
                            <p>Địa chỉ giao hàng: ${order.receiveAddress}</p>
                            <p>Ngày mong muốn giao hàng: ${order.ngayYeuCauGiao}</p>
                            <p>Tiền vận chuyển: ${order.freeShip} VNĐ</p>
                            <p>Trạng thái đơn hàng: <b style="color:black;font-size: 15px">${order.trangThaiDonHang}</b></p>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <b><p style="color:black;font-size: 20px">Người đăng: ${order.idKhachHangSoHuuDonHang.idCus.name}</p></b>
                            </sec:authorize>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div>
                            <c:if test="${order.trangThaiDonHang == 'GIAO_THANH_CONG'}">
                                <a  style="font-size: 15px; color: red;font-style: italic;margin-right: 20px">Đơn hàng đã giao thành công </a>
                                <br>
                            </c:if>
                            <c:if test="${order.trangThaiDonHang == 'DANG_GIAO'}">
                                <a  style="font-size: 15px; color: red;font-style: italic; margin-right: 20px">Đơn hàng đang trong quá trình giao hàng - Không thể cập nhật</a>
                                <br>
                            </c:if>
                            <c:if test="${order.trangThaiDonHang == 'CHUA_GIAO' || order.trangThaiDonHang == 'DANG_DAU_GIA' }">
                                <a href="<c:url value="/admin/orders/themdonhang?orderId=${order.id}" />" class="btn btn-warning" style="float: left;margin-right: 10px">Cập nhật</a>
                            </c:if>
                            <a href="javascript:;" class="btn btn-danger" onclick="deleteOrder(${order.id})">Xóa đơn hàng</a>
                            <p><a style="color:green"href="<c:url value="/admin/orders/${order.id}"/>">Xem thêm chi tiết đơn hàng</a></p>
                        </div>


                    </div>
                </div>
                <br>
            </c:forEach>
        </div>
    </div>
</section>
<script src="<c:url value="/assets/js/taikhoan.js" />"></script>

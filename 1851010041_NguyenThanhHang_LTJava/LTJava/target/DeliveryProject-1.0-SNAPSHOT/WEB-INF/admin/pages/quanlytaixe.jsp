<%-- 
    Document   : quanlytaixe
    Created on : Sep 20, 2021, 1:08:36 AM
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
            <h2>QUẢN LÝ NHÂN VIÊN</h2>
        </div>
        <div class="card-header" style="color:white;background-color: lightslategray">
            <p style="font-style: italic" class="card-title">Bạn có thể thêm tài xế tại đây</p>
            <a href="<c:url value="/admin/quanlytaixe/themtaixe"/>" class="btn btn-primary" >Thêm tài xế</a>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead class=" text-primary">
                    <th>
                        Mã tài xế
                    </th>
                    <th>
                        Tài khoản tài xế
                    </th>
                    <th>
                        Tên tài xế
                    </th>
                    <th>
                        Số điện thoại
                    </th>
                    <th>
                        Email
                    </th>
                    <th>
                        Trạng thái tài khoản
                    </th>
                    <th>
                        Quản lý tài khoản
                    </th>
                    <th class="text-center">
                        Cập nhật tài khoản
                    </th>
                    <th class="text-center">
                        Xóa tài khoản
                    </th>
                    </thead>
                    <tbody>
                        <c:forEach items="${shippers}" var="s">
                            <tr>
                                <td>
                                    ${s.id} 
                                </td>
                                <td>
                                    ${s.idShipper.username} 
                                </td>
                                <td>
                                    ${s.idShipper.name} 
                                </td>
                                <td>
                                    ${s.idShipper.phone}
                                </td>
                                <td>
                                    ${s.idShipper.email}
                                </td>
                                <td>
                                    <c:if test="${s.idShipper.active == true}">
                                        Đang hoạt động
                                    </c:if>
                                    <c:if test="${s.idShipper.active == false}">
                                        Hết hoạt động
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${s.adminID == null}">
                                        <p style="font-size: 15px;text-align: center;color: red">Tài khoản đã được admin xác nhận</p>
                                    </c:if>
                                    <c:if test="${s.adminID !=null}">
                                        <p style="font-size: 15px;text-align: center;color: green">Đã xác nhận tài khoản</p>
                                    </c:if>
                                </td>
                                <td class="text-center">
                                    <c:if test="${s.adminID == null}">
                                        <p style="font-size: 15px;text-align: center;color: red">Sau khi xác nhận tài khoản có thể thay đổi thông tin</p>
                                    </c:if>
                                    <c:if test="${s.adminID != null}">
                                        <a href="<c:url value="/admin/quanlytaixe/edittaixe?shipperId=${s.id}" />" class="btn btn-warning">Cập nhật</a>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${s.adminID == null}">
                                        <p style="font-size: 15px;text-align: center;color: red">Sau khi xác nhận tài khoản có thể thay đổi thông tin</p>
                                    </c:if>
                                    <c:if test="${s.adminID != null}">
                                        <a href="javascript:;" class="btn btn-info" onclick="deleteShipper(${s.id})">Xóa tài khoản</a>
                                    </c:if>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>
<script src="<c:url value="/assets/js/taikhoan.js" />"></script>
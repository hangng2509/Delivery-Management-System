<%-- 
    Document   : quanlykhuyenmai
    Created on : Sep 25, 2021, 5:09:09 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errAddKhuyenMai != null}">
    <div class="alert alert-danger">${errAddKhuyenMai}</div>
</c:if>
<div class="content" data-aos="fade-up">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title ">QUẢN LÝ KHUYẾN MÃI</h4>
                        <a href="<c:url value="/admin/quanlykhuyenmai/themkhuyenmai" />" > <button type="button"  class="btn btn-primary" >Thêm khuyến mãi mới</button></a>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class=" text-primary">
                                <th>
                                    Mã khuyến mãi
                                </th>
                                <th>
                                    Tên khuyến mãi
                                </th>
                                <th>
                                    Giá trị khuyến mãi
                                </th>
                                <th>
                                    Ngày áp dụng
                                </th>
                                <th>
                                    Ngày kết thúc
                                </th>
                                <th>
                                    Trạng thái
                                </th>
                                <th>
                                    Cập nhật khuyến mãi
                                </th>
                                <th>
                                    Xóa khuyến mãi
                                </th>
                                </thead>
                                <tbody>
                                    <c:forEach items="${promotions}" var="p">
                                        <tr>
                                            <td class="text-primary">
                                                ${p.id} 
                                            </td>
                                            <td>
                                                ${p.tenKhuyenMai} 
                                            </td>
                                            <td>
                                                ${p.giaTriKhuyenMai} 
                                            </td>
                                            <td>
                                                ${p.ngayApDung}
                                            </td>
                                            <td>
                                                ${p.ngayKetThuc}
                                            </td>
                                            <td>
                                                <c:if test="${p.status==false}">
                                                    Đang hoạt động
                                                </c:if>
                                                <c:if test="${p.status==true}">
                                                    Hết hoạt động
                                                </c:if>
                                            </td>
                                            <td>
                                                <a href="<c:url value="/admin/quanlykhuyenmai/themkhuyenmai?proId=${p.id}" />" class="btn btn-warning" style="margin-left: auto;margin-right: auto;display: block">Cập nhật</a>
                                            </td>
                                            <td>
                                                <a href="javascript:;" class="btn btn-info" onclick="deletePro(${p.id})">Xóa khuyến mãi</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/assets/js/taikhoan.js" />"></script>


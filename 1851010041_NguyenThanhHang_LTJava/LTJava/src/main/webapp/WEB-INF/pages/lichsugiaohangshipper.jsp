<%-- 
    Document   : test
    Created on : Dec 19, 2021, 1:09:48 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="container">
    <div>

        <h1 class="text-center my-3">Lịch Sử Giao Hàng Shipper</h1>
        <div class="action-group my-2">
            <a href="<c:url value="/lichsugiaohangshipper/${currentUser.id}?trangThai=true"/>" class="btn btn-success">Các Đơn Hàng Đã Hoàn Thành</a>
            <a href="<c:url value="/lichsugiaohangshipper/${currentUser.id}?trangThai=false"/>"class="btn btn-danger">Các Đơn Hàng Chưa Giao</a>
            <a href="<c:url value="/lichsugiaohangshipper/${currentUser.id}/all"/>" class="btn btn-info">Tất cả các đơn hàng</a>
        </div>
        <c:if test="${listOrder.size()==0}">
            <p>Cha co gi</p>
        </c:if>
        <c:if test="${err!=null}">
            <div class="alert alert-danger">
                ${err}
            </div>
        </c:if>

        <table class="table table-hover">
            <tr>
                <th>Mã đơn hàng</th>
                <th>Tên Đơn Hàng</th>
                <th>Địa chỉ lấy hàng</th>
                <th>Địa chỉ nhận hàng</th>
                <th>Tình trạng giao hàng</th>
                <th class="text text-center" ></th>
            </tr>
            <tbody>
                <c:forEach items="${listOrder}" var="item">
                    <tr>
                        <td class="text-left">${item[0]}</td>
                        <td class="text-left">${item[1]}</td>
                        <td class="text-left">${item[2]}</td>
                        <td class="text-left">${item[3]}</td>
                        <c:choose>
                            <c:when test="${item[4]== 'GIAO_THANH_CONG'}">
                                <td class="text-left text-success">${item[4]}</td>
                            </c:when>
                            <c:otherwise>
                                <td class="text-left text-danger">${item[4]}</td>
                            </c:otherwise>
                        </c:choose>

                    </tr>
                </c:forEach> 
            </tbody>

        </table>
    </div>

</div>

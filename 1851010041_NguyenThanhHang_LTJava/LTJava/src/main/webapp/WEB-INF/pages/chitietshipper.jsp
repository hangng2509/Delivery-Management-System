<%-- 
    Document   : chitietshipper
    Created on : Oct 25, 2021, 12:13:35 AM
    Author     : PC
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" 
           uri="http://www.springframework.org/security/tags" %> 

<div class="row" style="padding: 10px">
    <div class="col-md-3" style="padding-top: 100px">
        <c:if test="${ships.idShipper.avatar != null && ships.idShipper.avatar.startsWith('https') == true}">
            <img class="img-fluid mx-auto d-block"src="<c:url value="${ships.idShipper.avatar}"/>"alt="${ships.idShipper.avatar}" width="200"/>
        </c:if>
        <c:if test="${ships.idShipper.avatar ==  null || ships.idShipper.avatar.startsWith('https') == false}">
            <img class="img-fluid mx-auto d-block"src="<c:url value="/assets/img/team/shipPic.jpg"/>"alt="${ships.idShipper.avatar}" width="200" />
        </c:if>
    </div>
    <div class="col-md-9">
        <h1 style="color: #002a80" class="font-weight-bolder">THÔNG TIN CHI TIẾT TÀI XẾ</h1>
        <br>
        <p class="font-weight-bold font-italic">Mã tài xế: ${ships.id}</p>
        <p class="font-weight-bold">Tên tài xế: ${ships.idShipper.name}</p>
        <p>Ngày sinh: ${ships.idShipper.dob}</p>
        <c:if test="${ships.idShipper.gender == true}">
            <p>Giới tính: Nam</p>
        </c:if>
        <c:if test="${ships.idShipper.gender == false}">
            <p>Giới tính: Nữ</p>
        </c:if>
        <p>Số điện thoại liên hệ: ${ships.idShipper.phone}</p>
        <p>Email liên hệ: ${ships.idShipper.email}</p>
        <p>Địa chỉ: ${ships.idShipper.address}</p>
        <p>Ngày vào làm: ${ships.idShipper.joinDate}</p>
        <c:if test="${ships.idShipper.active == true}">
            <p>Trạng thái: Đang hoạt động</p>
        </c:if>
        <c:if test="${ships.idShipper.active == false}">
            <p>Trạng thái: Hết hoạt động</p>
        </c:if>
        <div>
            <sec:authorize access="hasRole('ROLE_CUSTOMER')">
                <button type="submit" onclick="thichOrKhongThich(${ships.id}, '${pageContext.request.userPrincipal.name}')"><i class="fas fa-hand-holding-heart"></i><a> Số lượt yêu thích: <span>${ships.soLuotThich}</span></a></button>
                    </sec:authorize>
                    <sec:authorize access="!hasRole('ROLE_CUSTOMER')">
                        <c:if test="${ships.soLuotThich == 0}">
                    Số lượt yêu thích: Tài xế chưa nhận được lượt yêu thích</c:if>
                <c:if test="${ships.soLuotThich > 0}">
                    Số lượt yêu thích: <span>${ships.soLuotThich}</span> </c:if>
            </sec:authorize>
        </div>
    </div>
</div>
<br><br>
<form style="padding: 10px">
    <div class="form-group">
        <textarea class="form-control" id="commentId" placeholder="Nhập bình luận của bạn"></textarea>
        <br>
        <sec:authorize access="hasRole('ROLE_CUSTOMER')">
            <input type="button" value="Gửi đánh giá" class="btn btn-danger" onclick="addComment(${ships.id})"/>
        </sec:authorize>
        <sec:authorize access="!hasRole('ROLE_CUSTOMER')">
            <p style="color: red;font-style: italic">Chỉ có khách hàng mới có quyền bình luận</p>
        </sec:authorize>
    </div>

</form>
<div>

    <ul class="pagination justify-content-end" style="margin:20px"

        <c:forEach begin="1" end="${Math.ceil(commentsCounter/3)}" var="i">
            <li class="page-item"><a class="page-link" href="<c:url value="/dstaixe/${shipId}/"/>?page=${i}">${i}</a></li>
        </c:forEach>
    </ul>
</div>
<div id="commentArea">

    <c:forEach items="${comments}" var="c">
        <div class="row" style="padding: 10px">
            <div class="col-md-2">
                <img class="rounded-circle"src="${c.userCom.avatar}"alt="${c.userCom.avatar}" width="200" />
            </div>
            <div class="col-md-9 my-date" style="margin-top: 20px;margin-left: 40px">
                <p><a class="font-weight-bold">Nội dung bình luận:</a> ${c.noiDung}</p>
                <a class="font-weight-bold">Thời gian bình luận: </a><i>${c.ngayBinhLuan}</i>
                <p style="margin-top: 15px"><a class="font-weight-bold">Khách hàng bình luận:</a> ${c.userCom.name}</p>
            </div> 
        </div>
        <br><br>

    </c:forEach>
</div>
<script>
    window.onload = function () {
        tinhNgayBinhLuan();
    }
</script>
<script src="<c:url value="/assets/js/binhluan.js"/>"></script>


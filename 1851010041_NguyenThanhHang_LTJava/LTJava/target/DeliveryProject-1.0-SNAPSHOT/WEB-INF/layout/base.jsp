<%-- 
    Document   : base1
    Created on : Sep 11, 2021, 9:05:17 PM
    Author     : PC
--%>

<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles"
           uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <title><tiles:insertAttribute name="title"/></title>
        <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Jost:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
            rel="stylesheet"
            />

        <link rel="stylesheet" href="<c:url value="/assetsUI/fonts/ionicons/css/ionicons.min.css"/>"/>
        <link href="<c:url value="/assets/img/ou.png"/>" rel="icon" />
        <link href="<c:url value="/assets/img/apple-touch-icon.png"/>" rel="apple-touch-icon" />
        <link href="<c:url value="/assets/css/style.css"/>" rel="stylesheet" />
        <link href="<c:url value="/vendor_login/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/vendor_login/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/assets/css/ruang-admin.min.css"/>" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment-with-locales.min.js"></script>
    </head>
    <body>
        <tiles:insertAttribute name="header"/>
        <tiles:insertAttribute name="content"/>
        <tiles:insertAttribute name="footer"/>
        <!-- Template Main JS File -->

        <script src="<c:url value="/vendor_login/jquery/jquery.min.js"/>"></script>
        <script src="<c:url value="/vendor_login/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
        <script src="<c:url value="/vendor_login/jquery-easing/jquery.easing.min.js"/>"></script>

        <script src="<c:url value="/assets/js/tinhTrangDauGia.js"/>"></script>
        <script src="<c:url value="/assets/js/taikhoan.js"/>"></script>
        <script src="<c:url value="/assets/js/binhluan.js"/>"></script>

    </body>
</html>

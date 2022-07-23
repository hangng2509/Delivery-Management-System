<%-- 
    Document   : themdonhang
    Created on : Sep 18, 2021, 3:35:09 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:if test="${errAddOrders != null}">
    <div class="alert alert-danger">${errAddShipper}</div>
</c:if>
<c:if test="${errNgay != null}">
    <div class="alert alert-danger">${errNgay}</div>
</c:if>
<c:if test="${err != null}">
    <div class="alert alert-danger">${err}</div>
</c:if>

<div class="content">
    <c:url value="/admin/orders/themdonhang" var="action"/>
    <div class="row justify-content-center">
        <div class="col-md-">
            <div class="card">
                <header class="card-header">
                    <c:if test="${order.id > 0}">
                        <h4 class="card-title mt-2">CẬP NHẬT THÔNG TIN ĐƠN HÀNG</h4>
                    </c:if>
                    <c:if test="${order.id <= 0}">
                        <h4 class="card-title mt-2">THÊM THÔNG TIN ĐƠN HÀNG</h4>
                    </c:if>
                </header>
                <div class="card-body">
                    <form:form method="POST" action="${action}" modelAttribute="order" enctype="multipart/form-data" >
                        <div class="row">
                            <c:if test="${order.id>0}">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">Mã đơn hàng</label>
                                        <form:input id="id" cssClass="form-control" path="id" readonly="true"/>
                                        <form:errors path="id" cssClass="text-danger" />
                                    </div>
                                </div>
                            </c:if>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="bmd-label-floating">Tên đơn hàng</label>
                                    <form:input id="orderName" cssClass="form-control" path="orderName" />
                                    <form:errors path="orderName" cssClass="text-danger" />
                                </div>
                            </div>
                            <div class="col-md">
                                <div class="form-group">
                                    <label for="soluong">Số lượng</label>
                                    <div class="probootstrap_select-wrap">
                                        <label for="soluong" style="width: 100%;">
                                            <form:select path="quantity" class="js-example-basic-single js-states form-control" id="soluong" style="width: 100%;" onchange='soLuongChange(this)'>
                                                <c:if test="${order.quantity !=null}">
                                                    <option value="${order.quantity}" selected="selected">${order.quantity}</option>
                                                </c:if>
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                                <option value="6">6</option>
                                                <option value="7">7</option>
                                                <option value="8">8</option>
                                                <option value="9">9</option>
                                                <option value="10">10</option>
                                            </form:select>
                                            <form:errors path="quantity" cssClass="text-danger" />
                                            <p class="text-danger">${errQuan}</p>
                                        </label>

                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="id_label_single2">Khối lượng của 1 đơn vị (kg)</label>
                                    <div class="probootstrap_select-wrap">
                                        <label for="freight" style="width: 100%;">
                                            <form:select path="freight" class="js-example-basic-single js-states form-control" id="freight" style="width: 100%;">
                                                <c:if test="${order.freight !=null}">
                                                    <option value="${order.freight}" selected="selected">${order.freight}</option>
                                                </c:if>

                                                <option value="0.00">0.00</option>
                                                <option value="0.05">0.05</option>
                                                <option value="0.10">0.10</option>
                                                <option value="0.15">0.15</option>
                                                <option value="0.20">0.20</option>
                                                <option value="0.25">0.25</option>
                                                <option value="0.30">0.30</option>
                                                <option value="0.35">0.35</option>
                                                <option value="0.40">0.40</option>
                                                <option value="0.45">0.45</option>
                                                <option value="0.50">0.50</option>
                                                <option value="0.55">0.55</option>
                                                <option value="0.60">0.60</option>
                                                <option value="0.65">0.65</option>
                                                <option value="0.70">0.70</option>
                                                <option value="0.75">0.75</option>
                                                <option value="0.80">0.80</option>
                                                <option value="0.85">0.85</option>
                                                <option value="0.90">0.90</option>
                                                <option value="0.95">0.95</option>
                                                <option value="1.00">1.00</option>
                                                <option value="1.05">1.05</option>
                                                <option value="1.10">1.10</option>
                                                <option value="1.15">1.15</option>
                                                <option value="1.20">1.20</option>
                                                <option value="1.25">1.25</option>
                                                <option value="1.30">1.30</option>
                                                <option value="1.35">1.35</option>
                                                <option value="1.40">1.40</option>
                                                <option value="1.45">1.45</option>
                                                <option value="1.50">1.50</option>
                                                <option value="1.55">1.55</option>
                                                <option value="1.60">1.60</option>
                                                <option value="1.65">1.65</option>
                                                <option value="1.70">1.70</option>
                                                <option value="1.75">1.75</option>
                                                <option value="1.80">1.80</option>
                                                <option value="1.85">1.85</option>
                                                <option value="1.90">1.90</option>
                                                <option value="1.95">1.95</option>
                                                <option value="2.00">2.00</option>
                                                <option value="2.05">2.05</option>
                                                <option value="2.10">2.10</option>
                                                <option value="2.15">2.15</option>
                                                <option value="2.20">2.20</option>
                                                <option value="2.25">2.25</option>
                                                <option value="2.30">2.30</option>
                                                <option value="2.35">2.35</option>
                                                <option value="2.40">2.40</option>
                                                <option value="2.45">2.45</option>
                                                <option value="2.50">2.50</option>
                                                <option value="2.55">2.55</option>
                                                <option value="2.60">2.60</option>
                                                <option value="2.65">2.65</option>
                                                <option value="2.70">2.70</option>
                                                <option value="2.75">2.75</option>
                                                <option value="2.80">2.80</option>
                                                <option value="2.85">2.85</option>
                                                <option value="2.90">2.90</option>
                                                <option value="2.95">2.95</option>
                                                <option value="3.00">3.00</option>
                                                <option value="3.05">3.05</option>
                                                <option value="3.10">3.10</option>
                                                <option value="3.15">3.15</option>
                                                <option value="3.20">3.20</option>
                                                <option value="3.25">3.25</option>
                                                <option value="3.30">3.30</option>
                                                <option value="3.35">3.35</option>
                                                <option value="3.40">3.40</option>
                                                <option value="3.45">3.45</option>
                                                <option value="3.50">3.50</option>
                                                <option value="3.55">3.55</option>
                                                <option value="3.60">3.60</option>
                                                <option value="3.65">3.65</option>
                                                <option value="3.70">3.70</option>
                                                <option value="3.75">3.75</option>
                                                <option value="3.80">3.80</option>
                                                <option value="3.85">3.85</option>
                                                <option value="3.90">3.90</option>
                                                <option value="3.95">3.95</option>
                                                <option value="4.00">4.00</option>
                                                <option value="4.05">4.05</option>
                                                <option value="4.10">4.10</option>
                                                <option value="4.15">4.15</option>
                                                <option value="4.20">4.20</option>
                                                <option value="4.25">4.25</option>
                                                <option value="4.30">4.30</option>
                                                <option value="4.35">4.35</option>
                                                <option value="4.40">4.40</option>
                                                <option value="4.45">4.45</option>
                                                <option value="4.50">4.50</option>
                                                <option value="4.55">4.55</option>
                                                <option value="4.60">4.60</option>
                                                <option value="4.65">4.65</option>
                                                <option value="4.70">4.70</option>
                                                <option value="4.75">4.75</option>
                                                <option value="4.80">4.80</option>
                                                <option value="4.85">4.85</option>
                                                <option value="4.90">4.90</option>
                                                <option value="4.95">4.95</option>
                                                <option value="5.00">5.00</option>
                                            </form:select>
                                        </label>
                                        <p class="text-danger">${errFr}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="khuyenMai">Khuyến Mãi</label>
                                    <div class="probootstrap_select-wrap">
                                        <label for="khuyenMai" style="width: 100%;">
                                            <form:select path="khuyenMaiApDung" class="js-example-basic-single js-states form-control" id="khuyenMai" style="width: 100%;" onchange="test(this)">
                                                <c:forEach var="p" items="${promos}">
                                                    <c:choose>
                                                        <c:when test="${order.khuyenMaiApDung == null}">
                                                            <c:choose>
                                                                <c:when test="${p.tenKhuyenMai == 'Không Áp Dụng Mã Khuyến Mãi'}">
                                                                    <option value="${p.id}" valueSale="${p.giaTriKhuyenMai}" selected="selected">${p.tenKhuyenMai}-sale: <span>${p.giaTriKhuyenMai*100}</span>%</option>
                                                                </c:when> 
                                                                <c:otherwise>
                                                                    <option value="${p.id}" valueSale="${p.giaTriKhuyenMai}">${p.tenKhuyenMai}-sale: <span>${p.giaTriKhuyenMai*100}</span>%</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:choose>
                                                                <c:when test="${p.id == order.khuyenMaiApDung.id}">
                                                                    <option value="${p.id}" selected="selected" valueSale="${p.giaTriKhuyenMai}">${p.tenKhuyenMai}-sale: <span>${p.giaTriKhuyenMai*100}</span>%</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${p.id}" valueSale="${p.giaTriKhuyenMai}">${p.tenKhuyenMai}-sale: <span>${p.giaTriKhuyenMai*100}</span>%</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </form:select>
                                        </label>
                                        <p class="text-danger">${errFr}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="bmd-label-floating">Ngày lấy hàng</label>
                                    <form:input type="date" value="${order.ngayLayHang}" cssClass="form-control" id="ngayLayHang" path="ngayLayHang"/>
                                    <form:errors path="ngayLayHang" cssClass="text-danger" />
                                    <p Class="text-danger">${errNgayLayHang}</p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="bmd-label-floating">Ngày mong muốn giao hàng</label>
                                    <form:input type="date" value="${order.ngayYeuCauGiao}" cssClass="form-control" id="ngayYeuCauGiao" path="ngayYeuCauGiao"/>
                                    <form:errors path="ngayYeuCauGiao" cssClass="text-danger" />
                                    <p Class="text-danger">${errNgayYeuCauGiao}</p>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="bmd-label-floating">Địa chỉ lấy hàng</label>
                                    <form:input id="pickupAddress" cssClass="form-control" path="pickupAddress" />
                                    <form:errors path="pickupAddress" cssClass="text-danger" />
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="bmd-label-floating">Địa chỉ giao hàng</label>
                                    <form:input id="receiveAddress" cssClass="form-control" path="receiveAddress" />
                                    <form:errors path="receiveAddress" cssClass="text-danger" />
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="bmd-label-floating">Hình ảnh đơn hàng</label>
                            <form:input type="file" path="imgUploadFile" cssClass="form-control"/>
                            <p Class="text-danger">${mess}</p>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <div class="form-group">
                                        <label class="bmd-label-floating">Nhập mô tả</label>
                                        <form:textarea class="form-control" path="description" id ="description" rows="10" /> 
                                        <form:errors path="description" cssClass="text-danger" />
                                    </div>
                                </div>
                                <p class="text text-danger" style="font-size: 15px;font-family: monospace;font-style: italic">
                                    Ghi rõ: Thông tin chi tiết hàng hóa và hình thức thanh toán (hàng hóa và phí vận chuyển) cho tài xế dễ dàng theo dõi.
                                </p>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="bmd-label-floating">Giá đơn hàng</label>
                                    <form:input id="price" type= "number"  cssClass="form-control" path="price" onblur="donGiaChange(this)"/>
                                    <form:errors path="price" cssClass="text-danger" />
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="bmd-label-floating">Phí vận chuyển</label>
                                    <label id ="tienVanChuyen" style="font-size: 20px; color:red">${order.freeShip}</label>
                                    <form:errors path="freeShip" cssClass="text-danger" />
                                </div>
                            </div>
                        </div>

                        <div class="row-cols-md-2">
                            <div class="form-group">
                                <label class="bmd-label-floating">Tổng cộng đơn hàng:</label>
                                <label id ="tienTong" style="font-size: 20px; color:red">${order.total}</label>
                                <form:errors path="total" cssClass="text-danger" />
                            </div>
                        </div>
                        <c:if test="${order.id>0 && order.trangThaiDonHang == 'GIAO_THANH_CONG'}">
                            <a  style="font-size: 20px; color: black;font-style: italic;float: right">Đơn hàng đã giao thành công </a>
                        </c:if>
                        <c:if test="${order.id>0 && order.trangThaiDonHang == 'DANG_GIAO'}">
                            <a  style="font-size: 20px; color: black;font-style: italic;float: right">Đơn hàng đang trong quá trình giao hàng - Không thể cập nhật</a>
                        </c:if>
                        <c:if test="${order.id>0 && (order.trangThaiDonHang == 'CHUA_GIAO' || order.trangThaiDonHang == 'DANG_DAU_GIA')}">
                            <button type="submit" class="btn btn-outline-success" style="float: right">Cập nhật đơn hàng</button>
                        </c:if>
                        <c:if test="${order.id<=0}">
                            <button type="submit"  class="btn btn-outline-success" style="float: right">Thêm đơn hàng</button
                        </c:if>
                    </form:form>
                </div>

            </div>
        </div>

    </div>

</div>
<script>

    const tienkhoiluonghang = 10000;
    var tongtienkhoiluong = 0;
    var tienVanChuyen = 0;
    var tongTienPhaiThu = 0;
    var tienAppDungKhuyenMai = 0;
    var price = 0;
    var sl = 0;
    var dg = 0;
    var kl = 0;
    var valueSale = 0;

    function hienThiTien() {
        document.getElementById("tienVanChuyen").innerHTML = tienVanChuyen + " VNĐ";
        document.getElementById("tienTong").innerHTML = tongTienPhaiThu + " VNĐ";
    }
    function tinhTongTien() {
        tienAppDungKhuyenMai = Number((tienkhoiluonghang + sl * tongtienkhoiluong) * valueSale);
        tongTienPhaiThu = Number(price) * sl + Number(tienkhoiluonghang + sl * tongtienkhoiluong) - tienAppDungKhuyenMai;
        tienVanChuyen = (Number(tienkhoiluonghang + sl * tongtienkhoiluong) - tienAppDungKhuyenMai);
    }
    function test(e) {
        const options = e.options;
        valueSale = options[e.selectedIndex].getAttribute("valuesale");
        tinhTongTien();
        hienThiTien();

    }
    document.getElementById("price").onchange = function () {
        price = document.getElementById("price").value;
        tinhTongTien();
        hienThiTien();
    }
    document.getElementById("soluong").onchange = function (e) {
        sl = Number(document.getElementById("soluong").value);
        if (sl > 5) {
            tienkhoiluonghang = 20000;
        }
        tinhTongTien();
        hienThiTien();
    };
    document.getElementById("freight").onchange = function () {
        kl = document.getElementById("freight").value;
        if (kl > 0 && kl <= 0.2) {
            tongtienkhoiluong = 4000;
        } else if (kl > 0.2 && kl <= 1) {
            tongtienkhoiluong = 4500;
        } else if (kl > 1 && kl <= 2) {
            tongtienkhoiluong = 5000;
        } else if (kl > 2 && kl <= 3) {
            tongtienkhoiluong = 5500;
        } else if (kl > 3 && kl <= 4) {
            tongtienkhoiluong = 6000;
        } else if (kl > 4 && kl <= 5) {
            tongtienkhoiluong = 7000;
        } else {
            tongtienkhoiluong = 9000;
        }
        tinhTongTien();
        hienThiTien();
    }
    console.log(document.getElementById("tienTong").innerHTML)
    console.log(document.getElementById("tienVanChuyen").innerHTML)
    if (document.getElementById("tienTong").innerHTML == null && document.getElementById("tienVanChuyen").innerHTML == null) {
        hienThiTien();
    } else {
//        tienVanChuyen = Number(document.getElementById("tienVanChuyen").innerHTML)
//        tongTienPhaiThu = Number(document.getElementById("tienTong").innerHTML)
        let promo = document.getElementById("khuyenMai");
        price = document.getElementById("price").value;
        console.log(price);
        valueSale = promo.options[promo.selectedIndex].getAttribute("valuesale");
        sl = document.getElementById("soluong").value;
        kl = document.getElementById("freight").value;
        if (kl > 0 && kl <= 0.2) {
            tongtienkhoiluong = 4000;
        } else if (kl > 0.2 && kl <= 1) {
            tongtienkhoiluong = 4500;
        } else if (kl > 1 && kl <= 2) {
            tongtienkhoiluong = 5000;
        } else if (kl > 2 && kl <= 3) {
            tongtienkhoiluong = 5500;
        } else if (kl > 3 && kl <= 4) {
            tongtienkhoiluong = 6000;
        } else if (kl > 4 && kl <= 5) {
            tongtienkhoiluong = 7000;
        } else {
            tongtienkhoiluong = 9000;
        }
        if (sl > 5) {
            tienkhoiluonghang = 20000;
        }

        tinhTongTien();
        hienThiTien();
    }


</script>

<!--<script type="text/javascript">
    bkLib.onDomLoaded(function () {
        nicEditors.allTextAreas()
    });
</script>-->
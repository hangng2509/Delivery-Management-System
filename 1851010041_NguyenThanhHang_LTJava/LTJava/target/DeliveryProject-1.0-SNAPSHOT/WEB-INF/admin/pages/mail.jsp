<%-- 
    Document   : mail
    Created on : Oct 19, 2021, 4:57:17 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- The Modal -->
<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">THÔNG BÁO</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <div class="form-group">
                    <label> Email người gửi</label>
                    <input id="sender" class="form-control"/>
                </div>
                <div class="form-group">
                    <label> Email người nhận</label>
                    <input id="email" class="form-control"/>
                </div>
                <div class="form-group">
                    <label>Nội dung gửi</label>
                    <textarea id="nd" class="form-control" rows="3"></textarea>
                </div>
            </div>
            <input type="hidden" id="id" class="form-control">

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-success btn-send-to-cus">Gửi</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>

            </div>

        </div>
    </div>
</div>

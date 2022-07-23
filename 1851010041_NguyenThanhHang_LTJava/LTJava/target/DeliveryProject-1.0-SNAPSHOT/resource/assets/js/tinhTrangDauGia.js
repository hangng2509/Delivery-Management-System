/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function addToCart(orderId) {
    if (confirm("Bạn quyết định chọn đấu giá đơn hàng không?") == true) {
        fetch(`/DeliveryProject/api/daugia/${orderId}`).then(res => res.json()).then(data => {
            var d = document.getElementById("cart-counter")
            if (d !== null)
                d.innerText = data;
        })
    }
}

function deleteCart(orderId) {
    if (confirm("Bạn chắc chắn xóa đơn hàng đấu giá này không?") == true) {
        fetch(`/DeliveryProject/api/daugia/${orderId}`, {
            method: "delete"
                    //Không cần body,header do không gửi gì lên ngoài orderId

        }).then(function (res) {
            return res.json()
        }).then(function (data) {
            var d = document.getElementById("cart-counter")
            //Số lương đơn hàng đấu giá trong giỏ updata lên giao diện
            d.innerText = data;
            //reload lại trang
            location.reload();
        })
    }
}
function pay() {
    if (confirm("Bạn chắc chắn đấu giá?") == true) {
        fetch("/DeliveryProject/api/pay", {
            method: "post"
        }).then(res => {
            if (res.status == 200) {
                location.reload();
                return res.json();
            } else
                alert("Đấu giá thất bại!");
        })

    }
}
function xacnhanbooking(bookingid) {
    if (confirm("Bạn xác nhận tài xế này giao hàng nhé?") == true) {
        fetch(`/DeliveryProject/api/admin/xacnhandaugia/${bookingid}`, {
            method: "get",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.status == 200) {
                location.reload();
            } else
                alert("Xác nhận thất bại!");
        })
    }
}

function xacnhangiaohang(bookingid) {
    if (confirm("Bạn xác nhận giao hàng thành công?") == true) {
        fetch(`/DeliveryProject/api/xacnhangiaohang/${bookingid}`, {
            method: "get",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.status == 200) {
                location.reload();
            } else
                alert("Xác nhận thất bại!");
        })
    }
}
//Dùng để kích hoạt hàm
$(document).ready(function () {
//click vào btn - thực hiện hàm lấy nội dung đơn hàng để gửi cho shipper
    $(".btn-open-mail").click(function () {
        var id = $(this).closest("td").attr("data-id");
        $("#myModal #id").val(id);
    })
    //click vào btn tiến hành gửi mail từ client -> server
    $(".btn-send-to-cus").click(function () {
        var form = {
            //lấy giá trị từ textbox chuyển thành dữ liệu json
            id: $("#myModal #id").val(),
            from: $("#myModal #sender").val(),
            to: $("#myModal #email").val(),
            body: $("#myModal #nd").val()
        }
        $.ajax({
            url: "/DeliveryProject/admin/send-mail-to-shipper",
            //gửi dữ liệu lên server
            data: form,
            //Kết quả server phản hồi về
            success: function (response) {
                if (response) {
                    //Giả lập nút click bằng việc truy xuất thuộc tính data-dismiss để tắt hộp thoại mail sau khi gửi mail thành công
                    $("[data-dismiss]").click();
                    alert("Gửi mail thành công")
                } else
                    alert("Gửi mail thất bại")
            }
        })
    })
})

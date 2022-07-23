/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function deleteShipper(shipperId) {
    if (confirm("Bạn chắc chắn xóa thông tin tài xế không?") == true) {
        fetch(`/DeliveryProject/api/admin/xoataixe/${shipperId}`, {
            method: "delete",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.status == 200) {
                location.reload();
                //Ẩn tài khoản bằng JS === Xóa
                let d = document.getElementById(`shipperId ${shipperId}`);
                d.style.display = "none";
            } else
                alert("Xóa thất bại");
        })
    }
}

//function xacnhantaixe(shipperid) {
//    if (confirm("Bạn xác nhận tài khoản nhé?") == true) {
//        fetch(`/DeliveryProject/api/admin/xacnhantaixe/${shipperid}`, {
//            method: "get",
//            headers: {
//                "Content-Type": "application/json"
//            }
//        }).then(res => {
//            if (res.status == 200) {
//                location.reload();
//            } else
//                alert("Xác nhận tài xế thất bại!");
//        })
//    }
//}

function deletePro(proId) {
    if (confirm("Bạn chắc chắn xóa thông tin khuyến mãi không?") == true) {
        fetch(`/DeliveryProject/api/admin/xoakhuyenmai/${proId}`, {
            method: "delete",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.status == 200) {
                location.reload();
                //Ẩn khuyến mãi bằng JS === Xóa
//                let d = document.getElementById(`proId ${proId}`);
//                d.style.display = "none";
            } else
                alert("Xóa thất bại");
        })
    }
}
function deleteOrder(orderId) {
    if (confirm("Bạn chắc chắn xóa bài đăng này không?") == true) {
        fetch(`/DeliveryProject/api/admin/xoadonhang/${orderId}`, {
            method: "delete",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.status == 200) {
                location.reload();
                //Ẩn khuyến mãi bằng JS === Xóa
                let d = document.getElementById(`orderId ${orderId}`);
                d.style.display = "none";
            } else
                alert("Xóa thất bại");
        })
    }
}
function deleteBooking(bookingId) {
    if (confirm("Bạn chắc chắn xóa lượt đấu giá này không?") == true) {
        fetch(`/DeliveryProject/api/admin/xoadaugia/${bookingId}`, {
            method: "delete",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.status == 200) {
                location.reload();
                //Ẩn khuyến mãi bằng JS === Xóa
                let d = document.getElementById(`bookingId ${bookingId}`);
                d.style.display = "none";
            } else
                alert("Xóa thất bại");
        })
    }
}


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function addComment(shipId) {
    fetch("/DeliveryProject/api/add-comment", {
        method: 'post',
        body: JSON.stringify(
                {
                    "nd": document.getElementById("commentId").value,
                    "shipId": shipId
                }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (res) {
        console.info(res);

        return res.json();
    }).then(function (data) {
        console.info(data);


        let area = document.getElementById("commentArea");

        area.innerHTML = `
                 <div class="row" style="padding: 10px">
                    <div class="col-md-2">
                        <img class="rounded-circle"src="${data.userCom.avatar}"alt="${data.userCom.avatar}" width="200" />
                    </div>
                    <div class="col-md-9 my-date" style="margin-top: 20px;margin-left: 40px">
                        <p><a class="font-weight-bold">Nội dung bình luận:</a> ${data.noiDung}</p>
                        <a class="font-weight-bold">Thời gian bình luận: </a><i>${moment(data.ngayBinhLuan).fromNow()}</i>
                        <p style="margin-top: 15px"><a class="font-weight-bold">Khách hàng bình luận:</a> ${data.userCom.name}</p>
                    </div>
                </div>
                <br><br>
            ` + area.innerHTML
    })
}
function tinhNgayBinhLuan() {
    moment.locale('vi');
    let dates = document.querySelectorAll(".my-date > i")
    for (let i = 0; i < dates.length; i++)
    {
        let d = dates[i]
        d.innerText = moment(d.innerText).fromNow()
    }
}

function thichOrKhongThich(shipId, username) {
    fetch("/DeliveryProject/api/thichOrKhongThich", {
        method: 'post',
        body: JSON.stringify({
            "shipId": shipId,
            "username": username
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        console.info(data);
        location.reload()
    })
}

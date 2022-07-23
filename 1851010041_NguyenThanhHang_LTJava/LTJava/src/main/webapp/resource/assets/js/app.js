document.querySelectorAll('.sidebar-submenu').forEach((e, index) => {
    e.querySelector('.sidebar-menu-dropdown').onclick = (event) => {
        event.preventDefault();
        e.querySelector('.sidebar-menu-dropdown .dropdown-icon').classList.toggle('active');

        let dropdown_content = e.querySelector('.sidebar-menu-dropdown-content');
        let dropdown_content_lis = dropdown_content.querySelectorAll('li');

        let active_height = dropdown_content_lis[0].clientHeight * dropdown_content_lis.length;

        dropdown_content.classList.toggle('active');

        dropdown_content.style.height = dropdown_content.classList.contains('active') ? active_height + 'px' : '0';
    };

});

// DARK MODE TOGGLE
let darkmode_toggle = document.querySelector('#darkmode-toggle');

darkmode_toggle.onclick = (e) => {
    e.preventDefault();
    document.querySelector('body').classList.toggle('dark');
    darkmode_toggle.querySelector('.darkmode-switch').classList.toggle('active');
};

//let overlay = document.querySelector('.overlay');
//let sidebar = document.querySelector('.sidebar');
//
//
//document.querySelector('#sidebar-close').onclick = function () {
//    sidebar.classList.toggle('active');
//    overlay.classList.toggle('active');
//};

// set active của setbar
//window.location.pathname
let pathname = new URL(document.location).pathname;
switch (pathname) {
    case "/DeliveryProject/adminv2":
        document.getElementById("home").classList.add("active");
        break;
    case "/DeliveryProject/adminv2/orders":
    {
        document.getElementById("qldh").classList.add("active");
        const e = document.querySelectorAll('.sidebar-submenu')[0];
        e.querySelector('.sidebar-menu-dropdown .dropdown-icon').classList.toggle('active');

        let dropdown_content = e.querySelector('.sidebar-menu-dropdown-content');
        let dropdown_content_lis = dropdown_content.querySelectorAll('li');

        let active_height = dropdown_content_lis[0].clientHeight * dropdown_content_lis.length;

        dropdown_content.classList.toggle('active');

        dropdown_content.style.height = dropdown_content.classList.contains('active') ? active_height + 'px' : '0';
        break;
    }

    case "/DeliveryProject/adminv2/orders/":
    {
        document.getElementById("qldh").classList.add("active");
        const e = document.querySelectorAll('.sidebar-submenu')[0];
        e.querySelector('.sidebar-menu-dropdown .dropdown-icon').classList.toggle('active');

        let dropdown_content = e.querySelector('.sidebar-menu-dropdown-content');
        let dropdown_content_lis = dropdown_content.querySelectorAll('li');

        let active_height = dropdown_content_lis[0].clientHeight * dropdown_content_lis.length;

        dropdown_content.classList.toggle('active');

        dropdown_content.style.height = dropdown_content.classList.contains('active') ? active_height + 'px' : '0';
        break;
    }
    case "/DeliveryProject/adminv2/quanlykhuyenmai":
    {
        document.getElementById("qlkm").classList.add("active");
        const e = document.querySelectorAll('.sidebar-submenu')[2];
        e.querySelector('.sidebar-menu-dropdown .dropdown-icon').classList.toggle('active');

        let dropdown_content = e.querySelector('.sidebar-menu-dropdown-content');
        let dropdown_content_lis = dropdown_content.querySelectorAll('li');

        let active_height = dropdown_content_lis[0].clientHeight * dropdown_content_lis.length;

        dropdown_content.classList.toggle('active');

        dropdown_content.style.height = dropdown_content.classList.contains('active') ? active_height + 'px' : '0';
        break;
    }
    case "/DeliveryProject/adminv2/quanlykhuyenmai/themkhuyenmai":
    {
        document.getElementById("themKm").classList.add("active");
        const e = document.querySelectorAll('.sidebar-submenu')[2];
        e.querySelector('.sidebar-menu-dropdown .dropdown-icon').classList.toggle('active');

        let dropdown_content = e.querySelector('.sidebar-menu-dropdown-content');
        let dropdown_content_lis = dropdown_content.querySelectorAll('li');

        let active_height = dropdown_content_lis[0].clientHeight * dropdown_content_lis.length;

        dropdown_content.classList.toggle('active');

        dropdown_content.style.height = dropdown_content.classList.contains('active') ? active_height + 'px' : '0';
        break;
    }
    default :
        break;
}
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
            } else
                alert("Xóa thất bại");
        })
    }
}
// Xử lý active phân trang
let param = new URL(document.location).searchParams;
const page = document.querySelectorAll(".pagination .page-item");
if (param.get("page")) {
    page[Number(param.get("page")) - 1].classList.add("active");
} else {
    page[0].classList.add("active");
}


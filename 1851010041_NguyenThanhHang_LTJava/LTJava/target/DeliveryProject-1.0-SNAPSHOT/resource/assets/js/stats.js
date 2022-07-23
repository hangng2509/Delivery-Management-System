/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function generateColor() {
    let r = parseInt(Math.random() * 255);
    let g = parseInt(Math.random() * 255);
    let b = parseInt(Math.random() * 255);
    return `rgb(${r},${g},${b})`
}

function tsChart(id, shipLabel = [], shipDonHang = []) {

    let color = [];

    for (let i = 0; i < shipDonHang.length; i++) {

        color.push(generateColor());

    }

    const data = {
        labels: shipLabel,
        datasets: [{
                label: 'Thống kê tần suất giao hàng thành công theo từng tài xế',
                data: shipDonHang,
                backgroundColor: color,
                hoverOffset: 4
            }]
    };
    const config = {
        type: 'bar',
        data: data,
    };

    let idTsChart = document.getElementById(id).getContext("2d")
    new Chart(idTsChart, config);
}

function cusChart(id, cusLabel = [], cusSumDoanhThu = []) {

    let color = [];

    for (let i = 0; i < cusSumDoanhThu.length; i++) {

        color.push(generateColor());

    }

    const data = {
        labels: cusLabel,
        datasets: [{
                label: 'Thống kê doanh thu khách hàng',
                data: cusSumDoanhThu,
                backgroundColor: color,
                hoverOffset: 4
            }]
    };
    const config = {
        type: 'bar',
        data: data,
    };

    let idKHChart = document.getElementById(id).getContext("2d")
    new Chart(idKHChart, config);
}

function shipChart(id, shipDTLabel = [], shipDT = []) {

    let color = [];

    for (let i = 0; i < shipDT.length; i++) {

        color.push(generateColor());

    }

    const data = {
        labels: shipDTLabel,
        datasets: [{
                label: 'Thống kê doanh thu tài xế',
                data: shipDT,
                backgroundColor: color,
                hoverOffset: 4
            }]
    };
    const config = {
        type: 'bar',
        data: data,
    };

    let idShipChart = document.getElementById(id).getContext("2d")
    new Chart(idShipChart, config);
}
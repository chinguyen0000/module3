<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Trang Chủ Hóa Đơn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        h1 {
            background-color: #f1f1f1;
            padding: 20px;
            text-align: center;
        }
    </style>

</head>
<body>
    <h1>QUẢN LÝ HÓA ĐƠN BÁN ĐIỆN THOẠI </h1>

    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Quản Lý Hóa Đơn</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="product_servlet">Điện Thoại</a>
        </li>
    </ul>

    <div class="container mt-4">
        <div class="row ">
            <div class="col">
                Chọn giá tiền tối đa
                <input type="range" class="form-range" id="priceRange" min="0" max="50000000" step="500000">
                <span id="max-value-display"></span> đ
            </div>
            <!-- Thanh tìm kiếm -->
            <div class="col">
                <input type="text" class="form-control" placeholder="Tìm kiếm theo tên khách hàng ..." id="input">
            </div>
            <!-- Nút thêm mới -->
            <div class="col text-md-end mt-2 mt-md-0 align-items-left">
                <button class="btn btn-outline-success" onclick="doFilter()">Tìm Kiếm</button>
            </div>
        </div>
    </div>
    <button class="btn btn-outline-dark m-10" onclick="location.href='/invoice_servlet?action=show-add'">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2"/>
        </svg>
        Thêm Mới
    </button>


    <table class="table table-striped" id="invoiceTable">
        <thead>
        <tr>
            <th>STT</th>
            <th>Tên Khách Hàng</th>
            <th>Ngày Lập Hóa Đơn</th>
            <th>Tổng Tiền</th>
            <th>Xem Chi Tiết</th>
            <th>Chỉnh Sửa</th>
            <th>Xóa</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${invoices}" var="inv" varStatus="loop">
            <tr>
                <td>${loop.count}</td>
                <td>${inv.getCustomerByID(inv.customerID).getFullName()}</td>
                <td>
                    <fmt:parseDate value="${inv.date}" pattern="y-M-dd'T'H:m" var="myParseDate" />
                    <fmt:formatDate value="${myParseDate}" pattern="dd-MM-yyyy HH:mm:ss" />
                </td>
                <td><fmt:formatNumber type="currency" value="${inv.totalAmount}" /></td>
                <td>
                    <button type="button" class="btn btn-primary" onclick="location.href='/invoice_servlet?action=show-detail&invoiceID=${inv.invoiceID}'">Xem</button>
                </td>
                <td>
                    <button type="button" class="btn btn-success" onclick="location.href='/invoice_servlet?action=show-edit&invoiceID=${inv.invoiceID}'">Sửa</button>
                </td>
                <td>
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" onclick="showDeleteModal(${inv.invoiceID}, ${loop.count})">
                        Xóa
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Modal chung -->
    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="deleteModalLabel">Cảnh Báo</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Bạn có chắc chắn muốn xóa hóa đơn số <span id="deleteModalCount"></span> ?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Đóng</button>
                    <button type="button" class="btn btn-danger" id="confirmDeleteButton">
                        Chắc Chắn
                    </button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        const maxRange = document.getElementById("priceRange");
        const maxValueDisplay = document.getElementById("max-value-display");

        maxRange.addEventListener("input", () => {
            maxValueDisplay.textContent = parseInt(maxRange.value).toLocaleString("vi-VN");
        });

        function showDeleteModal(invoiceID, count) {
            // Cập nhật nội dung modal
            document.getElementById('deleteModalCount').textContent = count;
            // Cập nhật hành động của nút "Chắc Chắn"
            const confirmButton = document.getElementById('confirmDeleteButton');
            confirmButton.onclick = function () {
                location.href = '/invoice_servlet?action=remove-invoice&invoiceID='+invoiceID;
            };
            // Hiển thị modal
            const deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
            deleteModal.show();
        }
        function doFilter() {
            const minValue = 0.0;
            const maxValue = parseFloat(maxRange.value);
            const table = document.getElementById("invoiceTable");
            const rows = table.getElementsByTagName("tbody")[0].getElementsByTagName("tr");

            var filter = document.getElementById("input").value.toUpperCase();
            var txtValue;

            for (let i = 0; i < rows.length; i++) {
                const cellValue = rows[i].getElementsByTagName("td")[3].textContent;
                const cellName = rows[i].getElementsByTagName("td")[1];
                const numericValue = parseFloat(cellValue.replace(/[^\d]/g, ""));
                txtValue = cellName.textContent || cellName.innerText;
                if ((numericValue >= minValue) && (numericValue <= maxValue) && (txtValue.toUpperCase().indexOf(filter) > -1)) {
                    rows[i].style.display = "";
                } else {
                    rows[i].style.display = "none";
                }
            }
        }
    </script>
</body>
</html>
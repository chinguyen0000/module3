<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
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
            <a class="nav-link" href="#">Điện Thoại</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
            <a class="nav-link disabled" aria-disabled="true">Disabled</a>
        </li>
    </ul>
    <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
    </form>

    <table class="table table-striped">
        <tr>
            <th>STT</th>
            <th>Tên Khách Hàng</th>
            <th>Ngày Lập Hóa Đơn </th>
            <th>Tổng Tiền </th>
            <th>Xem Chi Tiết</th>
            <th>Chỉnh Sửa</th>
            <th>Xóa</th>
        </tr>
        <c:forEach items="${invoices}" var="inv" varStatus="loop">
            <tr>
                <td>${loop.count}</td>
                <td>${inv.getCustomerByID(inv.customerID).getFullName()}</td>
                <td>
                    <fmt:parseDate value="${inv.date}" pattern="y-M-dd'T'H:m" var="myParseDate"/>
                    <fmt:formatDate value="${myParseDate}" pattern="dd-MM-yyyy HH:mm:ss"/>
                </td>
                <td><fmt:formatNumber type="currency" value="${inv.totalAmount}"/></td>
                <td><button type="button" class="btn btn-primary" onclick="location.href='/invoice_servlet?action=show-detail&invoiceID=${inv.invoiceID}'">Xem</button></td>
                <td><button type="button" class="btn btn-success" onclick="location.href='/invoice_servlet?action=show-edit&invoiceID=${inv.invoiceID}'">Sửa</button></td>
                <td>
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${loop.count}">
                        Xóa
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal${loop.count}" tabindex="-1" aria-labelledby="exampleModalLabel${loop.count}" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel${loop.count}">Cảnh Báo</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    Bạn có chắc chắn muốn xóa hóa đơn này ? <span>${loop.count}</span>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Đóng</button>
                                    <button type="button" class="btn btn-danger" onclick="location.href='/invoice_servlet?action=remove-invoice&invoiceID=${inv.invoiceID}'">Chắc Chắn</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
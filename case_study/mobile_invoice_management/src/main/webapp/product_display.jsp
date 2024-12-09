<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Trang Chủ Sản Phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        h1 {
            background-color: #f1f1f1;
            padding: 20px;
            text-align: center;
        }
        table {
            margin-top: 30px;
        }
        td {
            padding-left: 10px;
        }
    </style>

</head>
<body>
    <h1>ĐIỆN THOẠI </h1>

    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link" aria-current="page" href="invoice_servlet">Quản Lý Hóa Đơn</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="#">Điện Thoại</a>
        </li>
    </ul>
    <table class="table table-striped" >
        <thead>
        <tr>
            <th>STT</th>
            <th>Tên Sản Phẩm</th>
            <th>Hãng</th>
            <th>Giá</th>
            <th>Tồn Kho</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="pro" varStatus="loop">
            <tr>
                <td>${loop.count}</td>
                <td>${pro.productName}</td>
                <td>${pro.brand}</td>
                <td><fmt:formatNumber type="currency" value="${pro.price}"/></td>
                <td>${pro.stock}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/7/2024
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tạo Hóa Đơn Mới</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        td {
            padding: 5px;
        }
    </style>

</head>
<body>
    <h1>Tạo Hóa Đơn Mới</h1>

    <form action="invoice_servlet?action=add-invoice" method="post">
        <table>
            <tr>
                <td>Tên Khách Hàng</td>
                <td>
                    <input class="form-control" list="customerOptions" placeholder="Nhập để tìm kiếm ..." name="customerName" required>
                    <datalist id="customerOptions">
                        <c:forEach items="${customers}" var="cus">
                            <option value="${cus.fullName}">
                        </c:forEach>
                    </datalist>
                </td>
            </tr>

            <tr>
                <td>Sản Phẩm</td>
                <td>
                    <c:forEach items="${products}" var="pro" varStatus="loop">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" value="${pro.productID}" id="check${loop.count}" name="product" required>
                            <label class="form-check-label" for="check${loop.count}">
                                    ${pro.productName}
                            </label>
                        </div>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>Số Lượng</td>
                <td><input type="number" name="quantity" min="1" max="3" placeholder="Tối đa 3 sản phẩm" required class="form-control"></td>
            </tr>
            <tr>
                <td></td>
                <td><button type="submit" class="btn btn-primary">Tạo Mới</button></td>
            </tr>
        </table>
    </form>
</body>
</html>

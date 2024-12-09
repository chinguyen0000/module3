<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chỉnh sửa hóa đơn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        td {
            padding: 10px;
        }
    </style>
</head>
<body>
    <h1>Chỉnh Sửa Hóa Đơn</h1>
    <form action="/invoice_servlet?action=edit-invoice&invoiceID=${invoice.invoiceID}" method="post">
        <table>
            <tr>
                <td>Ngày Lập Hóa Đơn</td>
                <td><input class="form-control" type="datetime-local" name="datetime" value="${invoice.date}"></td>
            </tr>
            <tr>
                <td>Khách Hàng</td>
                <td>
                    <select class="form-select" name="newCustomerID">
                        <c:forEach items="${customers}" var="cus">
                            <option value="${cus.customerID}">${cus.fullName}</option>
                        </c:forEach>
                    </select
                </td>
            </tr>
            <tr>
                <td>Tổng Tiền</td>
                <td>
                    <input class="form-control" type="number" name="total" required placeholder="<fmt:formatNumber type="currency" value="${invoice.totalAmount}"/>" min="1000000" max="50000000">
                </td>
            </tr>
            <tr>
                <td></td>
                <td><button class="btn btn-primary">Cập Nhật</button></td>
            </tr>
        </table>
    </form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
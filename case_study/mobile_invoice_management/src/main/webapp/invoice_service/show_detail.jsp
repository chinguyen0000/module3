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
    <title>${invoice.date}</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <h1>Hóa đơn số ${invoice.invoiceID}+${invoice.customerID}</h1>
    <table class="table">
        <tr>
            <td>Ngày lập</td>
            <td><fmt:parseDate value="${invoice.date}" pattern="y-M-dd'T'H:m" var="myParseDate"/>
                <fmt:formatDate value="${myParseDate}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
        </tr>
        <tr>
            <TD>Khách hàng</TD>
            <td><b>${invoice.getCustomerByID(invoice.customerID).getFullName()}</b></td>
        </tr>
        <tr>
            <td>Tổng tiền</td>
            <td><b><fmt:formatNumber value="${invoice.totalAmount}" type="currency"/></td>
        </tr>
        <tr>
            <td>Sản phẩm</td>
            <td>
                <c:forEach items="${details}" var="detail" varStatus="loop">
                    <p>${loop.count}. ${detail.getProductByID(detail.productID).getProductName()}</p>
                    <p>Số lượng: <b>${detail.quantity}</b></p>
                    <p>Thành tiền: <b><fmt:formatNumber value="${detail.subTotal}" type="currency"/></b></p>
                </c:forEach>
            </td>
        </tr>
    </table>


</body>
</html>

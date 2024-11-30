<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11/30/2024
  Time: 2:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Xóa Sản Phẩm</title>
</head>
<body>
<h1>Chỉnh sửa sản phẩm</h1>
<form action="products?action=remove-product&id=${id}" method="post">
     <h3>Bạn có chắc chắn muốn xóa?</h3>
    <fieldset>
        <legend>Thông tin sản phẩm</legend>
        <table>
            <tr>
                <td>Tên: </td>
                <td>${requestScope["product"].getName()}</td>
            </tr>
            <tr>
                <td>Giá: </td>
                <td>${requestScope["product"].getPrice()}</td>
            </tr>
            <tr>
                <td>Trạng thái: </td>
                <td>
                    <c:if test="${product.status == true}">Còn hàng</c:if>
                    <c:if test="${product.status == false}">Đã hết</c:if>
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="Xóa"></td>
                <td><a href="/products">Quay về danh sách sản phẩm</a></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>

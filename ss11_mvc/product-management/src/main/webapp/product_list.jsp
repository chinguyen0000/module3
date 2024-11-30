<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11/30/2024
  Time: 1:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Danh Sách Sản Phẩm</title>
    <style>
      table, th, td {
        border: solid black 1px;
        border-collapse: collapse;
      }
    </style>
</head>
<body>
<h1>Danh sách sản phẩm</h1>
<a href="/products?action=show-add">Thêm mới sản phẩm</a>
<table>
  <tr>
    <th>ID</th>
    <th>Tên</th>
    <th>Trạng thái</th>
    <th>Giá</th>
    <th></th>
    <th></th>
  </tr>
  <c:forEach items="${productList}" var = "pro" varStatus = "loop">
    <tr>
      <td>${loop.count}</td>
      <td>${pro.name}</td>
      <td>
        <c:if test="${pro.status == true}">Còn hàng</c:if>
        <c:if test="${pro.status == false}">Đã hết</c:if>
      </td>
      <td><fmt:formatNumber value="${pro.price}" type="currency"/></td>
      <td><a href="/products?action=show-edit&id=${productList.indexOf(pro)}">Edit</a></td>
      <td><a href="/products?action=show-remove&id=${productList.indexOf(pro)}">Remove</a></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>

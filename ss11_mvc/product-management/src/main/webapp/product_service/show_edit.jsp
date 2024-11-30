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
    <title>Chỉnh Sửa Sản Phẩm</title>
</head>
<body>
<h1>Chỉnh sửa sản phẩm</h1>
<form action="/products?action=edit-product&id=${id}" method="post">
    Tên mới <input type="text" name="name"> <br>
    Trạng thái <input type="radio" name="status" value="true">Còn
    <input type="radio" name="status" value="false">Hết <br>
    Giá <input type="number" name="price"> <br>
    <button type="submit">Cập nhật</button>
</form>
</body>
</html>

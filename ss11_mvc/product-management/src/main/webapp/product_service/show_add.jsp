<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11/30/2024
  Time: 2:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm sản phẩm</title>
</head>
<body>
<h1>Thêm Sản Phẩm</h1>
<form action="/products?action=add-product" method="post">
    Tên <input type="text" name="name"> <br>
    Trạng thái <input type="radio" name="status" value="true">Còn
     <input type="radio" name="status" value="false">Hết <br>
    Giá <input type="number" name="price"> <br>
    Thêm mới<input type="submit" >
</form>
</body>
</html>

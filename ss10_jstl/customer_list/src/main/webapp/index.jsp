<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <title>JSTL - Customer List Display</title>

</head>
<body>
<a href="/customer_list">Click here to reload list</a>
<h1 class="text-center">Danh sách khách hàng</h1>

<table class="table">
    <tr>
        <th>Tên</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
        <th>Ảnh</th>
    </tr>

    <c:forEach items="${customerList}" var="cus" varStatus="loop">
        <tr>
            <td><c:out value="${cus.getFull_name()}"/></td>
            <td>${cus.getDate_of_birth()}</td>
            <td>${cus.getAddress()}</td>
            <td><img src="${cus.getImage()}" class="img-thumbnail" alt="${cus.getFull_name()}" width="100px" height="100px"></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
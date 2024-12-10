<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Đăng Nhập</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        h1 {
            background-color: #f1f1f1;
            padding: 20px;
            text-align: center;
        }
    </style>
</head>
<body>

    <h1>ĐĂNG NHẬP </h1>
    <form action="login_servlet" method="post" style="width: 40%; margin:auto;">

        <label for="inputUserName" class="form-label">Tài Khoản</label>
        <input type="text" id="inputUsername" class="form-control" name="username">

        <label for="inputPassword" class="form-label">Mật Khẩu</label>
        <input type="password" id="inputPassword" class="form-control" name="password" required>

        <br>
        <button class="btn btn-primary" type="submit">Đăng Nhập</button>
        <c:if test="${param.error == 'true'}">
            <p style="color: red;" class="form-text">Sai tên đăng nhập hoặc mật khẩu!</p>
        </c:if>
    </form>


</body>
</html>
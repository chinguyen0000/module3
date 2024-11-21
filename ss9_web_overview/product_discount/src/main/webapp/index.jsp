<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Product Discount Calculator</h1>
<form action="/display-discount" method="get">
    <table>
        <tr>
            <td>Product Description</td>
            <td><input type="text" name="description"></td>
        </tr>
        <tr>
            <td>List Price</td>
            <td><input type="number" name="price"></td>
        </tr>
        <tr>
            <td>Discount Percent</td>
            <td><input type="number" name="discount"></td>
        </tr>
        <tr>
            <td><button type="submit">Calculate Discount</button></td>
        </tr>
    </table>
</form>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>
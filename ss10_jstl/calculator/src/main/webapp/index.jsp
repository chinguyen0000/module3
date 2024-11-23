<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>JSTL - Calculator</title>
</head>
<body>
<h1>Simple Calculator</h1>
<form action="/calculator" method="get" title="Calculator">
  <table>
    <tr>
      <td>First operand:</td>
      <td><input type="number" name="firstOperand"></td>
    </tr>
    <tr>
      <td>Operator:</td>
      <td><select name="operators" id="operators">
        <option value="+">Addition</option>
        <option value="-">Subtraction</option>
        <option value="*">Multiplication</option>
        <option value="/">Division</option>
      </select></td>
    </tr>
    <tr>
      <td>Second operand:</td>
      <td><input type="number" name="secondOperand"></td>
    </tr>
    <tr>
      <td></td>
      <td><button type="submit">Calculate</button></td>
    </tr>
  </table>
</form>
</body>
</html>
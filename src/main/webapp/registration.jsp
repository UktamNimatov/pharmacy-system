<%--
  Created by IntelliJ IDEA.
  User: uktamnimatov
  Date: 5/25/22
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
</head>
<h2>Registration Page</h2>
<body>
<form action="controller" <%--method="post"--%>>
    <input type="hidden" name="command" value="registration">
        <input type="text" name="login" placeholder="login" value="">
    <br>
        <input type="password"  name="password" placeholder="password" value="">
    <br>
        <input type="text" name="firstName" placeholder="first name" value="">
    <br>
        <input type="text" name="lastName" placeholder="last name" value="">
    <br>
        <input type="email" name="email" placeholder="email" value="">
    <br>
        <input type="hidden" name="role" value="client">
    <br>
    <input type="submit" value="Register">
</form>
</body>
</html>

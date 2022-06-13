
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Password Change</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="update_password">
    <input type="hidden" name="login" value="${username}">
    <input type="password" name="new_password" placeholder="password">
    <input type="submit" value="Update Password">
</form>
${password_change_message}
</body>
</html>

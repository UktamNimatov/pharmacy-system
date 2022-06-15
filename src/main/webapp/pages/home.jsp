
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
Hello, ${username}
<form action="controller">
    <input type="hidden" name="command" value="find_all_users">
    <input type="submit" value="Find All Users">
</form>

<form action="controller">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="logout">
</form>

<a href="${pageContext.request.contextPath}/pages/password-update.jsp">Update Password</a>

<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="find_all_medicine">
    <input type="submit" value="Find All Medicines">
</form>

<form action="${pageContext.request.contextPath}/controller">
    <div>
        <a href="${pageContext.request.contextPath}/pages/add-medicine.jsp" class="btn btn-primary" role="button" aria-pressed="true">Add New Medicine</a>
    </div>
</form>

${password_change_message}
</body>
</html>

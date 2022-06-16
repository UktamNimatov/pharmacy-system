
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"   %>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<fmt:message key="label.welcome" />
${username} <br>
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
<fmt:message key="footer.copyright" />
${password_change_message}
</body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add medicine</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="add_medicine">
    <input type="text" name="title" placeholder="title">
    <br>
    <input type="text" name="price" placeholder="price">
    <br>
    <input type="text" name="description" placeholder="description">
    <br>
    <input type="radio" name="with_prescription">
    <br>
    <input type="submit" name="submit" value="Add medicine">
</form>
<form action="controller">
    <div>
        <a href="${pageContext.request.contextPath}/pages/home.jsp" class="btn btn-primary" role="button" aria-pressed="true">Back home</a>
    </div>
</form>
</body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Medicine Created</title>
</head>
<body>
<h2>Medicine successfully registered</h2>
<hr>
<h4>Added Medicine</h4>
${medicine_created}
<form action="controller">
    <div>
        <a href="${pageContext.request.contextPath}/pages/home.jsp" class="btn btn-primary" role="button" aria-pressed="true">Back home</a>
    </div>
</form>
</body>
</html>

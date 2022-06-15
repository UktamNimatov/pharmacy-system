<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of medicines by query</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>List of medicines by query</h2>
    </div>
</div>
<div id="container">
    <div id="content">
<table>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Price</th>
        <th>Description</th>
        <th>Is Prescribed</th>
    </tr>
    <c:forEach var="tempMedicine" items="${medicine_list_by_query}">

    <c:url var="updateLink" value="/medicine/showFormForUpdate">
        <c:param name="medicineId" value="${tempMedicine.id}"/>
    </c:url>

    <c:url var="deleteLink" value="/medicine/delete">
        <c:param name="medicineId" value="${tempMedicine.id}"/>
    </c:url>
        <tr>
            <td>${tempMedicine.id}</td>
            <td>${tempMedicine.title}</td>
            <td>${tempMedicine.price}</td>
            <td>${tempMedicine.description}</td>
            <td>${tempMedicine.withPrescription}</td>
            <td>
                <a href="${updateLink}">Update</a>
                <a href="${deleteLink}" onclick="if (!(confirm('Are you sure to delete this medicine'))) return false">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
    </div>
</div>
</body>
</html>

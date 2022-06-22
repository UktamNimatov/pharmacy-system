<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>List of Medicines (new page)</title>
    <%@ include file="../sources/parts/linksDataTable.jsp"%>
</head>
<body>

<table id="new-medicine-list" class="table table-striped" style="width:100%">
    <thead>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Price</th>
        <th>Description</th>
        <th>Is Prescribed</th>
        <th>Action</th>
    </tr>
    </thead>
    <c:forEach var="tempMedicine" items="${medicine_list}">

<%--        <c:url var="updateLink" value="${pageContext.request.contextPath}/controller?command=update_medicine">--%>
<%--            <c:param name="medicineId" value="${tempMedicine.id}"/>--%>
<%--        </c:url>--%>

<%--        <c:url var="deleteLink" value="${pageContext.request.contextPath}/controller?command=delete_medicine">--%>
<%--            <c:param name="medicineId" value="${tempMedicine.id}"/>--%>
<%--        </c:url>--%>
        <tr>
            <td>${tempMedicine.id}</td>
            <td>${tempMedicine.title}</td>
            <td>${tempMedicine.price}</td>
            <td>${tempMedicine.description}</td>
            <td>${tempMedicine.withPrescription}</td>
            <td>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="medicineId" value="${tempMedicine.id}">
                    <input type="hidden" name="command" value="update_medicine">
                    <input type="submit" value="Update">
<%--                <a href="${updateLink}">Update</a>--%>
                </form>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="medicineId" value="${tempMedicine.id}">
                    <input type="hidden" name="command" value="delete_medicine">
                    <input type="submit" value="Delete" onclick="if (!(confirm('Are you sure to delete this medicine'))) return false">
<%--                <a href="${deleteLink}" onclick="if (!(confirm('Are you sure to delete this medicine'))) return false">Delete</a>--%>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<script>
    $(document).ready(function () {
        $('#new-medicine-list').DataTable();
    });
</script>
<%@ include file="../sources/parts/sidebar.jsp"%>
</body>
<%@ include file="../sources/parts/footer.jsp"%>
</html>

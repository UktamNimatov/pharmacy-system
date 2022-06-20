<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>List of Orders</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap4.min.css">
</head>
<body>
<table id="all-orders" class="table table-striped table-bordered" style="width:100%">
    <thead>
    <tr>
        <th>Id</th>
        <th>User Id</th>
        <th>Status</th>
        <th>Ordered Time</th>
        <th>Confirmed Time</th>
        <th>Completed Time</th>
        <th>Canceled Time</th>
        <th>Action</th>
    </tr>
    </thead>
    <c:forEach var="tempOrder" items="${orders}">

    <c:url var="updateLink" value="/customer/showFormForUpdate">
        <c:param name="customerId" value="${tempOrder.id}"/>
    </c:url>

    <c:url var="deleteLink" value="/customer/delete">
        <c:param name="customerId" value="${tempOrder.id}"/>
    </c:url>

    <tr>
        <td>${tempOrder.id}</td>
        <td>${tempOrder.user_id}</td>
        <td>${tempOrder.status}</td>
        <td>${tempOrder.ordered_time}</td>
        <td>${tempOrder.confirmed_time}</td>
        <td>${tempOrder.completed_time}</td>
        <td>${tempOrder.canceled_time}</td>

        <td>
            <a href="${updateLink}">Update</a>
            <a href="${deleteLink}" onclick="if (!(confirm('Are you sure to delete this customer'))) return false">Delete</a>
        </td>
    </tr>
    </c:forEach>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap4.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#all-orders').DataTable();
        });
    </script>
</body>
</html>

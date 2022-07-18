<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="message"/>
<!DOCTYPE html>
<html lang="${sessionScope.language}">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Tables</title>

    <!-- Custom fonts for this template -->
    <link href="${pageContext.request.contextPath}/startbootstrap/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/startbootstrap/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Medicine table css -->
    <link href="${pageContext.request.contextPath}/startbootstrap/css/usertable.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="${pageContext.request.contextPath}/startbootstrap/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/startbootstrap/home.jsp">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3"><fmt:message key="label.welcome" /> ${username}</div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/startbootstrap/home.jsp">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span><fmt:message key="dashboard" /></span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            <fmt:message key="interface" />
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
               aria-expanded="true" aria-controls="collapseTwo">
                <i class="fas fa-fw fa-cog"></i>
                <span><fmt:message key="make.orders" /></span>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header"><fmt:message key="available.actions" /></h6>
                    <a class="collapse-item" href="${pageContext.request.contextPath}/startbootstrap/order-medicine.jsp"><fmt:message key="order.medicine" /></a>
                    <a class="collapse-item" href="${pageContext.request.contextPath}/startbootstrap/ask-receipt.jsp"><fmt:message key="illness.complaint" /></a>
                </div>
            </div>
        </li>


        <!-- Nav Item - Utilities Collapse Menu -->
        <!-- Nav Item - Utilities Collapse Menu -->
        <c:if test="${role.toString().equals('ADMIN') || role.toString().equals('DOCTOR')}">
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                   aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span><fmt:message key="additional.functions" /></span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                     data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header"><fmt:message key="user.services" /></h6>
                        <c:if test="${role.toString().equals('ADMIN')}">
                            <a class="collapse-item" href="${pageContext.request.contextPath}/controller?command=find_all_orders"><fmt:message key="your.orders" /></a>
                        </c:if>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/controller?command=find_all_receipts"><fmt:message key="given.receipts" /></a>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/controller?command=find_all_request_receipts"><fmt:message key="find.all.complaints"/> </a>
                    </div>
                </div>
            </li>
        </c:if>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            <fmt:message key="addons"/>
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
               aria-expanded="true" aria-controls="collapsePages">
                <i class="fas fa-fw fa-folder"></i>
                <span><fmt:message key="pharmacy.data" /></span>
            </a>
            <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header"><fmt:message key="data.tables" /></h6>
                    <c:if test="${role.toString().equals('ADMIN')}">
                        <a class="collapse-item" href="${pageContext.request.contextPath}/controller?command=find_all_users"><fmt:message key="table.users" /></a>
                    </c:if>
                    <a class="collapse-item" href="${pageContext.request.contextPath}/controller?command=find_all_medicine"><fmt:message key="table.medicines" /></a>
                    <div class="collapse-divider"></div>
                    <h6 class="collapse-header"><fmt:message key="other.pages" /></h6>
                </div>
            </div>
        </li>

        <!-- Nav Item - Charts -->
        <c:if test="${role.toString().equals('ADMIN') || role.toString().equals('PHARMACIST')}">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/startbootstrap/add-medicine.jsp">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span><fmt:message key="add.medicine"/> </span></a>
            </li>
            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">
        </c:if>

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>


    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- Topbar Search -->
                <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="<fmt:message key="search.for" />"
                               aria-label="Search" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">
                                <i class="fas fa-search fa-sm"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-search fa-fw"></i>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                             aria-labelledby="searchDropdown">
                            <form class="form-inline mr-auto w-100 navbar-search">
                                <div class="input-group">
                                    <input type="text" class="form-control bg-light border-0 small"
                                           placeholder="Search for..." aria-label="Search"
                                           aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>
                    <c:if test="${not empty medicine_not_created}">
                        <strong>${medicine_not_created}</strong>
                    </c:if>
                    <c:if test="${not empty operation_message}">
                        <a href="#" class="btn btn-success btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-check"></i>
                                        </span>
                                ${operation_message}
                        </a>
                    </c:if>

                    <!-- Nav Item - Alerts -->
                    <li class="nav-item dropdown font-weight-bolder">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink"
                           role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <fmt:message key="language"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="change_locale">
                                <input type="hidden" name="locale" value="uz_UZ">
                                <input class="dropdown-item" data-toggle="modal" data-target="#logoutModal"
                                       type="submit" value="O'zbek tilida">
                            </form>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="change_locale">
                                <input type="hidden" name="locale" value="ru_RU">
                                <input class="dropdown-item" data-toggle="modal" data-target="#logoutModal"
                                       type="submit" value="На русском">
                            </form>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="change_locale">
                                <input type="hidden" name="locale" value="en_US">
                                <input class="dropdown-item" data-toggle="modal" data-target="#logoutModal"
                                       type="submit" value="In English">
                            </form>
                        </div>
                    </li>

                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">${username}</span>
                            <img class="img-profile rounded-circle"
                                 src="${pageContext.request.contextPath}/startbootstrap/img/undraw_profile.svg" alt="Avatar">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/startbootstrap/profile.jsp">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                <fmt:message key="edit.profile" />
                            </a>
                            <div class="dropdown-divider"></div>
                            <form action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command" value="logout">
                                <input class="dropdown-item" data-toggle="modal" data-target="#logoutModal"
                                       type="submit" value="<fmt:message key="logout" />">
                            </form>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800"><fmt:message key="request.receipt.table"/> </h1>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary"><fmt:message key="request.receipt.list"/> </h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" >
                                <thead>
                                <tr>
                                    <th><fmt:message key="id" /></th>
                                    <th><fmt:message key="illness.description" /></th>
                                    <th><fmt:message key="user.id" /></th>
                                    <th><fmt:message key="request.status" /></th>
                                    <th><fmt:message key="ordered.time" /></th>
                                    <th><fmt:message key="manage" /></th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th><fmt:message key="id" /></th>
                                    <th><fmt:message key="illness.description" /></th>
                                    <th><fmt:message key="user.id" /></th>
                                    <th><fmt:message key="request.status" /></th>
                                    <th><fmt:message key="ordered.time" /></th>
                                    <th><fmt:message key="manage" /></th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach var="tempRequest" items="${request_receipts}">
                                    <tr>
                                        <td>${tempRequest.id}</td>
                                        <td>${tempRequest.illnessDescription}</td>
                                        <td>${tempRequest.userId}</td>
                                        <td>${tempRequest.status}</td>
                                        <td>${tempRequest.orderedTime}</td>
                                        <td>
                                            <ul class="list-unstyled mb-0 d-flex justify-content-center">
                                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                                    <input type="hidden" name="request_receipt_id" value="${tempRequest.id}">
                                                    <input type="hidden" name="command" value="find_request_receipt_info">
                                                    <button type="submit" class="btn btn-outline-primary"
                                                            name="submit" ><fmt:message key="post.receipt"/> </button>
                                                </form>
                                            </ul>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span><fmt:message key="footer.copyright" /> &copy;</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>


    <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath}/startbootstrap/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/startbootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/startbootstrap/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/startbootstrap/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="${pageContext.request.contextPath}/startbootstrap/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/startbootstrap/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="${pageContext.request.contextPath}/startbootstrap/js/demo/datatables-demo.js"></script>

</body>

</html>
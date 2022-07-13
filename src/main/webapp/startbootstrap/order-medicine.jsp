<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

    <title><fmt:message key="homepage.title"/></title>

    <!-- Custom fonts for this template -->
    <link href="${pageContext.request.contextPath}/startbootstrap/vendor/fontawesome-free/css/all.min.css"
          rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/startbootstrap/css/sb-admin-2.min.css" rel="stylesheet">

    <%--Flag icon--%>
    <link href="${pageContext.request.contextPath}/startbootstrap/css/flag-icon.css" rel="stylesheet">

    <%--Order medicine css--%>
    <link href="${pageContext.request.contextPath}/startbootstrap/css/ordermedicine.css" rel="stylesheet">

    <%--Basket css--%>
    <link href="${pageContext.request.contextPath}/startbootstrap/css/shoppingbasket1.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="${pageContext.request.contextPath}/startbootstrap/vendor/datatables/dataTables.bootstrap4.min.css"
          rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center"
           href="${pageContext.request.contextPath}/startbootstrap/home.jsp">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3"><fmt:message key="label.welcome"/> ${username}</div>
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
            <fmt:message key="interface"/>
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
               aria-expanded="true" aria-controls="collapseTwo">
                <i class="fas fa-fw fa-cog"></i>
                <span><fmt:message key="make.orders"/></span>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header"><fmt:message key="available.actions"/></h6>
                    <a class="collapse-item"
                       href="${pageContext.request.contextPath}/startbootstrap/buttons.jsp"><fmt:message
                            key="order.medicine"/></a>
                    <a class="collapse-item"
                       href="${pageContext.request.contextPath}/startbootstrap/cards.jsp"><fmt:message
                            key="illness.complaint"/></a>
                </div>
            </div>
        </li>

        <!-- Nav Item - Utilities Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
               aria-expanded="true" aria-controls="collapseUtilities">
                <i class="fas fa-fw fa-wrench"></i>
                <span><fmt:message key="additional.functions"/></span>
            </a>
            <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header"><fmt:message key="user.services"/></h6>
                    <a class="collapse-item"
                       href="${pageContext.request.contextPath}/controller?command=find_all_orders"><fmt:message
                            key="your.orders"/></a>
                    <a class="collapse-item"
                       href="${pageContext.request.contextPath}/startbootstrap/utilities-border.jsp"><fmt:message
                            key="given.receipts"/></a>
                    <a class="collapse-item"
                       href="${pageContext.request.contextPath}/startbootstrap/utilities-animation.jsp"><fmt:message
                            key="medicine.with.prescription"/></a>
                    <a class="collapse-item"
                       href="${pageContext.request.contextPath}/startbootstrap/utilities-other.jsp"><fmt:message
                            key="medicine.without.prescription"/></a>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Addons
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
               aria-expanded="true" aria-controls="collapsePages">
                <i class="fas fa-fw fa-folder"></i>
                <span><fmt:message key="pharmacy.data"/></span>
            </a>
            <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header"><fmt:message key="data.tables"/></h6>
                    <a class="collapse-item"
                       href="${pageContext.request.contextPath}/controller?command=find_all_users"><fmt:message
                            key="table.users"/></a>
                    <a class="collapse-item"
                       href="${pageContext.request.contextPath}/controller?command=find_all_medicine"><fmt:message
                            key="table.medicines"/></a>
                    <a class="collapse-item"
                       href="${pageContext.request.contextPath}/startbootstrap/forgot-password.jsp">Forgot Password</a>
                    <div class="collapse-divider"></div>
                    <h6 class="collapse-header"><fmt:message key="other.pages"/></h6>
                    <a class="collapse-item" href="${pageContext.request.contextPath}/startbootstrap/404.jsp">404
                        Page</a>
                    <a class="collapse-item" href="${pageContext.request.contextPath}/startbootstrap/blank.jsp">Blank
                        Page</a>
                </div>
            </div>
        </li>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/startbootstrap/add-medicine.jsp">
                <i class="fas fa-fw fa-chart-area"></i>
                <span><fmt:message key="add.medicine"/> </span></a>
        </li>

        <!-- Nav Item - Tables -->
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/startbootstrap/user-table.jsp">
                <i class="fas fa-fw fa-table"></i>
                <span>Tables</span></a>
        </li>
        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

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
                <form action="${pageContext.request.contextPath}/controller" method="post"
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" name="medicine_search_query"
                               placeholder="<fmt:message key="search.for" />"
                               aria-label="Search" aria-describedby="basic-addon2">
                        <input type="hidden" name="command" value="find_medicine_by_query">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="submit">
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

                    <!-- Nav Item - Alerts -->
                    <li class="nav-item dropdown font-weight-bolder">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink"
                           role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Language
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=change_locale&locale=uz_UZ">O'zbek tilida</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru_RU">На русском</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=change_locale&locale=en_US">In English</a>
                        </div>
                    </li>

                    <li class="nav-item dropdown no-arrow mx-1">
                        <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-bell fa-fw"></i>
                            <!-- Counter - Alerts -->
                            <span class="badge badge-danger badge-counter">3+</span>
                        </a>
                        <!-- Dropdown - Alerts -->
                        <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="alertsDropdown">
                            <h6 class="dropdown-header">
                                Change Locale Center
                            </h6>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-primary">
                                        <i class="fas fa-file-alt text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 12, 2019</div>
                                    <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-success">
                                        <i class="fas fa-donate text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 7, 2019</div>
                                    $290.29 has been deposited into your account!
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-warning">
                                        <i class="fas fa-exclamation-triangle text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 2, 2019</div>
                                    Spending Alert: We've noticed unusually high spending for your account.
                                </div>
                            </a>
                            <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                        </div>
                    </li>

                    <!-- Nav Item - Messages -->
                    <li class="nav-item dropdown no-arrow mx-1">
                        <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-envelope fa-fw"></i>
                            <!-- Counter - Messages -->
                            <span class="badge badge-danger badge-counter">7</span>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="messagesDropdown">
                            <h6 class="dropdown-header">
                                <fmt:message key="message.center"/>
                            </h6>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle"
                                         src="${pageContext.request.contextPath}/startbootstrap/img/undraw_profile_1.svg"
                                         alt="...">
                                    <div class="status-indicator bg-success"></div>
                                </div>
                                <div class="font-weight-bold">
                                    <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                        problem I've been having.
                                    </div>
                                    <div class="small text-gray-500">Emily Fowler · 58m</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle"
                                         src="${pageContext.request.contextPath}/startbootstrap/img/undraw_profile_2.svg"
                                         alt="...">
                                    <div class="status-indicator"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">I have the photos that you ordered last month, how
                                        would you like them sent to you?
                                    </div>
                                    <div class="small text-gray-500">Jae Chun · 1d</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle"
                                         src="${pageContext.request.contextPath}/startbootstrap/img/undraw_profile_3.svg"
                                         alt="...">
                                    <div class="status-indicator bg-warning"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">Last month's report looks great, I am very happy with
                                        the progress so far, keep up the good work!
                                    </div>
                                    <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                         alt="...">
                                    <div class="status-indicator bg-success"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                        told me that people say this to all dogs, even if they aren't good...
                                    </div>
                                    <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                </div>
                            </a>
                            <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                        </div>
                    </li>

                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">${username}</span>
                            <img class="img-profile rounded-circle"
                                 src="${pageContext.request.contextPath}/startbootstrap/img/undraw_profile.svg"
                                 alt="Avatar">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/startbootstrap/profile.jsp">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                <fmt:message key="edit.profile" />
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                <fmt:message key="settings" />
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                <fmt:message key="activity.log" />
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
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Dashboard   ${no_medicine_with_this_name}</h1>
                    <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                </div>

                <!-- Content Row -->

                <div class="row">

                    <!-- Area Chart -->
                    <div class="col-xl-8 col-lg-7">
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div class="container mt-5 mb-5">
                                <div class="d-flex justify-content-center row">
                                    <div class="col-md-10">
                                        <c:forEach var="temp_medicine" items="${medicine_list}">
                                            <div class="row p-2 bg-white border rounded">
                                                <div class="col-md-3 mt-1"><img
                                                        class="img-fluid img-responsive rounded product-image"
                                                        src="${pageContext.request.contextPath}/startbootstrap/img/shop-medicine-avatar.webp">
                                                </div>
                                                <div class="col-md-6 mt-1">
                                                    <h5>${temp_medicine.title}</h5>
                                                    <div class="d-flex flex-row">
                                                        <div class="ratings mr-2"><i class="fa fa-star"></i><i
                                                                class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                                class="fa fa-star"></i></div>
                                                        <span>310</span>
                                                    </div>
                                                    <p class="text-justify text-truncate para mb-0">${temp_medicine.description}<br><br>
                                                    </p>
                                                    <c:if test="${temp_medicine.withPrescription}">
                                                        <span class="dot"></span><fmt:message key="only.with.prescription"/>
                                                    </c:if>
                                                </div>
                                                <div class="align-items-center align-content-center col-md-3 border-left mt-1">
                                                    <div class="d-flex flex-row align-items-center">
                                                        <h4 class="mr-1">$${temp_medicine.price}</h4><%--<span
                                                            class="strike-text">$20.99</span>--%>
                                                    </div>
                                                    <h6 class="text-success"><fmt:message key="free.delivery"/></h6>

                                                    <div class="d-flex flex-column mt-4">
                                                        <form action="${pageContext.request.contextPath}/controller"
                                                              method="post">
                                                            <input type="hidden" name="medicine_id"
                                                                   value="${temp_medicine.id}">
                                                            <input type="hidden" name="command"
                                                                   value="find_medicine_info">
                                                            <button class="btn btn-primary btn-sm" type="submit">
                                                                <fmt:message key="details"/>
                                                            </button>
                                                        </form>
                                                        <form action="${pageContext.request.contextPath}/controller"
                                                              method="post">
                                                            <input type="hidden" name="medicine_id"
                                                                   value="${temp_medicine.id}">
                                                            <input type="hidden" name="command"
                                                                   value="add_medicine_to_basket">
                                                            <button class="btn btn-outline-primary btn-sm mt-2"
                                                                    type="submit"><fmt:message key="add.to.wishlist"/>
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Pie Chart -->
                    <div class="col-xl-4 col-lg-5">
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">Medicine Shopping Cart <h6
                                        class="mb-1">${already_in_basket}</h6></h6>
                            </div>
                            <div class="col">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">Medicine Item</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Price</th>
                                            <th scope="col"></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <c:forEach var="entry" items="${medicine_quantity_map}">
                                            <th scope="row">
                                                <div class="d-flex align-items-center">
                                                        <%--                                                        <img src="${pageContext.request.contextPath}/startbootstrap/img/shop-medicine-avatar.webp" class="img-fluid rounded-3"--%>
                                                        <%--                                                             style="width: 120px;" alt="Book">--%>
                                                    <div class="flex-column ms-4">
                                                            <%--                                                            <p class="mb-2">Thinking, Fast and Slow</p>--%>
                                                        <p class="mb-1">${entry.key.title}</p>
                                                    </div>
                                                </div>
                                            </th>
                                            <td class="align-middle">
                                                <div class="d-flex flex-row">
                                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                                        <input type="hidden" name="medicine_id" value="${entry.key.id}">
                                                        <input type="hidden" name="command"
                                                               value="change_medicine_quantity">
                                                        <button class="btn btn-link px-2" type="submit"
                                                                onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
                                                            <i class="fas fa-plus"></i>
                                                        </button>
                                                        <input id="quantity" min="0" name="quantity" type="number"
                                                               value="${entry.value}"
                                                               class="form-control form-control-sm"
                                                               style="width: 45px;"/>
                                                        <button class="btn btn-link px-2" type="submit"
                                                                onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
                                                            <i class="fas fa-minus"></i>
                                                        </button>
                                                    </form>
                                                </div>
                                            </td>
                                            <td class="align-middle">
                                                <p class="mb-0" style="font-weight: 500;">&dollar;${entry.key.price}</p>
                                            </td>
                                            <td class="align-middle">
                                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                                    <input type="hidden" name="medicine_id" value="${entry.key.id}">
                                                    <input type="hidden" name="command"
                                                           value="remove_medicine_from_basket">
                                                    <button type="submit"
                                                            class="btn btn-outline-info btn-circle <%--btn-lg--%> btn-circle ml-2 text-danger">
                                                        <i class="fas fa-trash fa-lg"></i></button>
                                                        <%--<a href="${pageContext.request.contextPath}/controller?command=remove_medicine_from_basket" class="text-danger">--%><%--</a>--%>
                                                </form>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>

                                <div class="card shadow-2-strong mb-5 mb-lg-0" style="border-radius: 16px;">
                                    <div class="card-body p-4">
                                        <div class="row">
                                            <div class=" col-lg-8 col-xl-7">
                                                <div class="d-flex justify-content-between" style="font-weight: 500;">
                                                    <p class="mb-1">Subtotal</p>
                                                    <p class="mb-1">&dollar; ${sum}</p>
                                                </div>

                                                <div class="d-flex justify-content-between" style="font-weight: 500;">
                                                    <p class="mb-0">Transaction Cost</p>
                                                    <p class="mb-0">&dollar; ${transaction_cost}</p>
                                                </div>

                                                <hr class="my-1">

                                                <div class="d-flex justify-content-between mb-4"
                                                     style="font-weight: 500;">
                                                    <p class="mb-1">Total </p>
                                                    <p class="mb-1">&dollar; ${total_cost}</p>
                                                </div>
                                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                                    <input type="hidden" name="command" value="order_medicine">
                                                <button type="submit" class="btn btn-primary btn-block">
                                                    <div class="d-flex justify-content-between">
                                                        <span>Order </span>
                                                    </div>
                                                </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--{}{}{}{}{}{}{}--%>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span><fmt:message key="footer.copyright"/> &copy;</span>
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

<!-- Logout Modal-->
<form action="${pageContext.request.contextPath}/controller">
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>

                    <input type="hidden" name="command" value="logout">
                    <input class="btn btn-primary" type="submit" value="Logout">
                    <%--                        <a class="btn btn-primary" href="../login.jsp">Logout</a>--%>
                </div>
            </div>
        </div>
    </div>
</form>

<!-- Bootstrap core JavaScript-->
<script src="${pageContext.request.contextPath}/startbootstrap/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/startbootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="${pageContext.request.contextPath}/startbootstrap/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="${pageContext.request.contextPath}/startbootstrap/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="${pageContext.request.contextPath}/startbootstrap/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="${pageContext.request.contextPath}/startbootstrap/js/demo/chart-area-demo.js"></script>
<script src="${pageContext.request.contextPath}/startbootstrap/js/demo/chart-pie-demo.js"></script>

</body>

</html>
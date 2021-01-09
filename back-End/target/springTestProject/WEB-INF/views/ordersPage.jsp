<%--
  Created by IntelliJ IDEA.
  User: luxa4
  Date: 20.08.2020
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Shop of Wardrobes </title>
    <link href="<c:url value="/static/css/bootstrap.css" />" rel="stylesheet"  type="text/css" />
    <link href="<c:url value="/static/css/bootstrap-grid.css" />" rel="stylesheet"  type="text/css" />
    <link href="<c:url value="/static/css/myapp.css" />" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/static/css/all.css" />" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/static/css/jquery-ui.css" />" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Mulish&display=swap" rel="stylesheet">
</head>
<body>

<%@include file="fragments/header.jsp" %>
<div class="container-fluid mt-4">
    <div class="row">
        <%@include file="fragments/searchfilter.jsp" %>
        <main class="col-12 col-sm-6 col-md-6 col-lg-10 col-xl-10">
            <div class="container-fluid">
                <div id="content" class="row">
                    <div class="alert alert-dark container-fluid" role="alert">
                        You have : ${countOrders} orders
                    </div>
                    <table class="table">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col">Order Id</th>
                            <th scope="col">Created</th>
                            <th scope="col">Delivery address</th>
                            <th scope="col">Execution status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="order" items="${orderList}">
                            <tr id="${item.product.id}">
                                <td><a href="<c:url value="/orders/${order.id}"/>">Order #${order.id}</a></td>
                                <td>${order.created}</td>
                                <td>${order.address.index}, ${order.address.region}, ${order.address.city}, ${order.address.street} str., ${order.address.homeNumber}, ${order.address.flat}
                                </td>
                                <td>${order.executionStatus}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
    </div>
</div>
<%@include file="fragments/footer.jsp" %>
<script src="<c:url value="/static/js/jquery.js"/>" ></script>
<script src="<c:url value="/static/js/bootstrap.js"/>" ></script>
<script src="<c:url value="/static/js/application.js"/>" ></script>
<script src="<c:url value="/static/js/jquery-ui.js"/>" ></script>
</body>
</html>

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
                    <div>
                       <h4>Order № ${order.id}</h4>
                    </div>
                    <table class="table">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">Product</th>
                            <th scope="col">Count</th>
                            <th scope="col">Price</th>
                            <th scope="col">Sum</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${order.orderItemsList}">
                            <tr id="${item.product.id}">
                                <td><img src="${item.product.imageUrl}" style="width: 100px" /></td>
                                <td>${item.product.name}</td>
                                <td>${item.count}</td>
                                <td>${item.product.price}</td>
                                <td>${item.product.price*item.count}</td>
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
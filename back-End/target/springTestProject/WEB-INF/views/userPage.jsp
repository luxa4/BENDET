<%--
  Created by IntelliJ IDEA.
  User: luxa4
  Date: 20.08.2020
  Time: 9:58
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
            <div class="container">
                <div id="content" class="row">
                    <table class="table">
                        <thead class="thead-light">
                        <tr>
                            <th colspan="3" scope="col">User information</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td rowspan="3"><img src="/static/img/user-anonymous.png" style="width: 100px" /></td>
                                <td><b>Name:</b> ${user.name}</td>
                            </tr>
                            <tr>
                                <td><b>Email:</b> ${user.email}</td>
                            </tr>
                            <tr>
                                <td><b>Orders:</b> <a href="/orders"> ${countOrders} </a></td>
                            </tr>
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

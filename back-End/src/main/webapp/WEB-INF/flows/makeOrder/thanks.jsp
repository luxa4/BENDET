<%--
  Created by IntelliJ IDEA.
  User: luxa4
  Date: 30.07.2020
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Thanks</title>
    <link href="<c:url value="/static/css/bootstrap.css" />" rel="stylesheet"  type="text/css" />
    <link href="<c:url value="/static/css/bootstrap-grid.css" />" rel="stylesheet"  type="text/css" />
    <link href="<c:url value="/static/css/myapp.css" />" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/static/css/all.css" />" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/static/css/jquery-ui.css" />" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Mulish&display=swap" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row justify-content-center align-items-center h-100">
         <main class="col-12 col-sm-6 col-md-6 col-lg-10 col-xl-10">
                <div>
                    <h2 class="text-center">Your order has been created successfully! Thank you!</h2>
                </div>
                <div class="mt-3 text-center">
                    <a href="/" class="btn btn-success">Back to main page</a>
                </div>
        </main>
    </div>
</div>
<%@include file="../../views/fragments/footer.jsp" %>
<script src="<c:url value="/static/js/jquery.js"/>" ></script>
<script src="<c:url value="/static/js/bootstrap.js"/>" ></script>
<script src="<c:url value="/static/js/application.js"/>" ></script>
<script src="<c:url value="/static/js/jquery-ui.js"/>" ></script>
</body>
</html>
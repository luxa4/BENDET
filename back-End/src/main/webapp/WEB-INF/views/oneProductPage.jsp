
<%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 25.06.2020
  Time: 15:13
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
                            <th colspan="3" scope="col">Product information</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td rowspan="5" style="width:auto"><img src="${product.imageUrl}" style="height:100%;width: auto" class="card-img-top" alt="..."></td>
                            <td> <input id="productId" type="hidden" name="productId" value="${product.id}" />
                                <p class="card-text"><b>Name: </b>${product.name}</p></td>
                        </tr>
                        <tr>
                            <td><p class="card-text marginCartBottom-1"><b>Price:</b> ${product.price} €</p></td>
                        </tr>
                        <tr>
                            <td><p class="card-text marginCartBottom-1"><b>Size:</b> ${product.length}x${product.width}x${product.height} cm</p></td>
                        </tr>
                        <tr>
                            <td><p class="card-text marginCartBottom-1"><b>Status:</b> ${product.status}</p></td>
                        </tr>
                        <tr>
                            <td><p class="card-text marginCartBottom-1"><b>Count: </b><input id="count" type="number" name="count"  value="1" min="1" max="2" /></td>
                        </tr>
                        <tr>
                            <td colspan="2"><c:choose>
                                <c:when test="${user != null}">
                                    <button id="addToCart" type="submit" class="btn btn-warning w-100"><i class="fas fa-dolly-flatbed normal"></i> Add to shopping cart </button>
                                </c:when>
                                <c:otherwise>
                                    <a href="/login" class="btn btn-warning w-100"><i class="fas fa-user normal"></i> You need to login to add to Cart </a>
                                </c:otherwise>
                            </c:choose>
                            </td>
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
<%--
  Created by IntelliJ IDEA.
  User: luxa4
  Date: 30.07.2020
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Order information</title>
    <link href="<c:url value="/static/css/bootstrap.css" />" rel="stylesheet"  type="text/css" />
    <link href="<c:url value="/static/css/bootstrap-grid.css" />" rel="stylesheet"  type="text/css" />
    <link href="<c:url value="/static/css/myapp.css" />" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/static/css/all.css" />" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/static/css/jquery-ui.css" />" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Mulish&display=swap" rel="stylesheet">
</head>
<body>

<%@include file="../../views/fragments/header.jsp" %>
<div class="container-fluid mt-4">
    <div class="row">
        <%@include file="../../views/fragments/searchfilter.jsp" %>

        <main class="col-12 col-sm-6 col-md-6 col-lg-10 col-xl-10">
            <div class="container-fluid">
                <div class="row justify-content-around">
                    <h2>Full information about your order!</h2>
                </div>
                <div class="row justify-content-center">
                    <div>
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
                            <c:forEach var="item" items="${shoppingCart.shoppingCartItems}">
                            <tr id="${item.product.id}">
                                <td><img src="${item.product.imageUrl}" style="width: 100px" /></td>
                                <td>${item.product.name}</td>
                                <td>${item.count}</td>
                                <td>${item.product.price}</td>
                                <td>${item.product.price*item.count}</td>
                            </tr>
                            </c:forEach>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><br/></td>
                                <td><b>Total cost: ${shoppingCart.totalCost}</b></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>


                    <div class="ml-3">
                        <table class="table">
                            <thead class="thead-light">
                            <tr>
                                <th scope="col">User information</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><b>Name:</b> ${user.name}</td>
                                </tr>
                                <tr>
                                    <td><b>Email:</b> ${user.email}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="ml-3">
                        <table class="table">
                            <thead class="thead-light">
                            <tr>
                                <th scope="col">Delivery address</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><b>Recipient:</b> ${address.recipient}</td>
                            </tr>
                            <tr>
                                <td><b>Index:</b> ${address.index}</td>
                            </tr>
                            <tr>
                                <td><b>Region:</b> ${address.region}</td>
                            </tr>
                            <tr>
                                <td><b>City:</b> ${address.city}</td>
                            </tr>
                            <tr>
                                <td><b>Street:</b> ${address.street}</td>
                            </tr>
                            <tr>
                                <td><b>House number:</b> ${address.homeNumber}</td>
                            </tr>
                            <tr>
                                <td><b>Flat number:</b> ${address.flat}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <a href="${flowExecutionUrl}&_eventId=cancel" class="btn btn-primary">Cancel</a>
                    <a href="${flowExecutionUrl}&_eventId=back" class="btn btn-primary ml-1">Back</a>
                    <a href="${flowExecutionUrl}&_eventId=makeOrder" class="btn btn-primary ml-1">Confirm</a>
                </div>
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





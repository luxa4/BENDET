<%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 23.06.2020
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Shop of Wardrobes </title>
    <link href="<c:url value="/static/css/bootstrap.css" />" rel="stylesheet"  type="text/css" />
    <link href="<c:url value="/static/css/bootstrap-grid.css" />" rel="stylesheet"  type="text/css" />
    <link href="<c:url value="/static/css/myapp.css" />" rel="stylesheet" type="text/css">
    <link href="<c:url value="/static/css/all.css" />" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container-fluid">
    <div class="d-flex flex-column h-100 justify-content-center align-items-center">
        <div><h3>Registration</h3></div>
        <div class="col-10 col-sm-8 col-md-6 col-lg-4 col-xl-3">
            <div class="card" >
                <div class="card-body">
                    <spring:form method="post" action="/registration" modelAttribute="UserFromRegistrationForm" >
                        <div class="form-group">
                            <label>Login: </label>
                            <c:if test="${mymsg !=null}">
                                <span class="text-danger"> ${mymsg}</span>
                            </c:if>
                            <spring:errors path="name" cssClass="text-danger"/>
                            <spring:input path="name" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>Email: </label>
                            <spring:errors path="email" cssClass="text-danger" />
                            <spring:input path="email" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>Password: </label>
                            <spring:errors path="password" cssClass="text-danger"/>
                            <spring:input path="password" type="password" class="form-control"/>
                        </div>
                        <div class="container-fluid" >
                            <div class="row align-items-center justify-content-between" >
                                <div>
                                    <a href="/login" class="text-primary text-left">Sign in instead</a>
                                </div>
                                <div>
                                    <button type="submit" class="btn btn-primary ">Registration</button>
                                </div>
                            </div>
                        </div>
                    </spring:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

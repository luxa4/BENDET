
<%--
  Created by IntelliJ IDEA.
  User: luxa4
  Date: 30.07.2020
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Shopping Address</title>
    <link href="<c:url value="/static/css/bootstrap.css" />" rel="stylesheet"  type="text/css" />
    <link href="<c:url value="/static/css/bootstrap-grid.css" />" rel="stylesheet"  type="text/css" />
    <link href="<c:url value="/static/css/myapp.css" />" rel="stylesheet" type="text/css">
    <link href="<c:url value="/static/css/all.css" />" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container-fluid">
    <div class="d-flex flex-column h-100 justify-content-center align-items-center">
        <div><h3>Shipping details</h3></div>
        <div class="col-10 col-sm-8 col-md-6 col-lg-4 col-xl-3">
            <div class="card x">
                <div class="card-body">
                    <spring:form modelAttribute="address">
                        <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
                        <div class="form-group">
                            <label>Recipient</label>
                            <c:if test="${flowRequestContext.messageContext.hasErrorMessages()}" >
                                <c:forEach items="${flowRequestContext.messageContext.getMessagesBySource('recipient')}" var="message" >
                                    <span class="text-danger" ><b>(${message.text})</b></span>
                                </c:forEach>
                            </c:if>
                            <spring:input path="recipient"  type="text" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label>Index</label>
                                <c:if test="${flowRequestContext.messageContext.hasErrorMessages()}" >
                                   <c:forEach items="${flowRequestContext.messageContext.getMessagesBySource('index')}" var="message" >
                                       <span class="text-danger" ><b>(${message.text})</b></span>
                                   </c:forEach>
                                </c:if>
                            <spring:input path="index"  type="text" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label>Region</label>
                            <c:if test="${flowRequestContext.messageContext.hasErrorMessages()}" >
                                <c:forEach items="${flowRequestContext.messageContext.getMessagesBySource('region')}" var="message" >
                                    <span class="text-danger" ><b>(${message.text})</b></span>
                                </c:forEach>
                            </c:if>
                            <spring:input path="region" type="text" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>City</label>
                            <c:if test="${flowRequestContext.messageContext.hasErrorMessages()}" >
                                <c:forEach items="${flowRequestContext.messageContext.getMessagesBySource('city')}" var="message" >
                                    <span class="text-danger" ><b>(${message.text})</b></span>
                                </c:forEach>
                            </c:if>
                            <spring:input path="city" type="text" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>Street</label>
                            <c:if test="${flowRequestContext.messageContext.hasErrorMessages()}" >
                                <c:forEach items="${flowRequestContext.messageContext.getMessagesBySource('street')}" var="message" >
                                    <span class="text-danger" ><b>(${message.text})</b></span>
                                </c:forEach>
                            </c:if>
                            <spring:input path="street" type="text" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>House</label>
                            <c:if test="${flowRequestContext.messageContext.hasErrorMessages()}" >
                                <c:forEach items="${flowRequestContext.messageContext.getMessagesBySource('house')}" var="message" >
                                    <span class="text-danger" ><b>(${message.text})</b></span>
                                </c:forEach>
                            </c:if>
                            <spring:input path="homeNumber" type="text" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>Flat</label>
                            <c:if test="${flowRequestContext.messageContext.hasErrorMessages()}" >
                                <c:forEach items="${flowRequestContext.messageContext.getMessagesBySource('flat')}" var="message" >
                                    <span class="text-danger" ><b>(${message.text})</b></span>
                                </c:forEach>
                            </c:if>
                            <spring:input path="flat" type="text" class="form-control" />
                        </div>
                        <a href="${flowExecutionUrl}&_eventId=cancel" class="btn btn-primary">Cancel</a>
                        <a href="${flowExecutionUrl}&_eventId=back" class="btn btn-primary">Back</a>
                        <button type="submit" name="_eventId_next" class="btn btn-primary">Next</button>
                    </spring:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 8/4/16
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Register</title>
</head>
<body>

<h1>Register</h1>
<br>
<a href="<c:url value="/"/>">Home</a><br>

<form:form commandName="registerPassengerForm" method="POST">

    <form:input type="text" placeholder="Write your name" path="name"/>
    <span class="error">
            <form:errors path="name"/>
    </span>
    <br>
    <br>

    <input type="submit" value="Submit"/>
</form:form>

<c:if test="${not empty createdPassengerName}">
    User with name
    <c:out value="${createdPassengerName}"/>
    was registered.
</c:if>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 8/3/16
  Time: 4:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Create flight</title>
</head>
<body>
<h1>Create flight</h1><br><br>


<form:form commandName="createFlightForm" method="POST">

    <select name="departureAirport">
        <c:forEach items="${departureAirports}" var="airport">
            <option value="${airport.id}">
                <c:out value="${airport.id}"/>
                <c:out value="${airport.title}"/>
            </option>
        </c:forEach>
    </select>

    <br>
    <br>

    <select name="arrivalAirport">
        <c:forEach items="${arrivalAirports}" var="airport">
            <option value="${airport.id}">
                <c:out value="${airport.id}"/>
                <c:out value="${airport.title}"/>
            </option>
        </c:forEach>
    </select>

    <br>
    <br>

    <input type="text" name="aircraftCapacity" required="required" pattern="[0-9]{1,2}"
           placeholder="Number of tickets"/>

    <br>
    <br>

    <br>
    <br>

    <input type="submit" value="Submit"/>
</form:form>

<br><br>

<c:out value="${result}"/>

</body>
</html>

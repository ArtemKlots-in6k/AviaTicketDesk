<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 8/1/16
  Time: 9:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Title$</title>
</head>
<style>
    table {
        border-collapse: collapse;
        width: 100%;
    }
</style>
<body>
<h1>Flights </h1>
<br>
<form action="<c:url value="/ticketDesk"/>" method="POST">
    <select name="flight">
        <option value="${null}">Choose the flight</option>

        <c:forEach items="${flights}" var="flight">
            <option value="${flight.id}">
                <c:out value="${flight.id}"/>
                <c:out value="${flight.departureAirport.title}"/>
                <c:out value="${flight.destinationAirport.title}"/>
                <c:out value="${flight.departureDateTime}"/>
            </option>
        </c:forEach>
    </select>
    <br>
    <br>
    <input type="text" name="numberOfTickets" placeholder="Number of tickets">
    <br>
    <br>
    <input type="text" name="passenger" placeholder="Write your name">
    <br>
    <br>

    <input type="submit" value="Submit"/>
</form>
</table>
</body>
</html>

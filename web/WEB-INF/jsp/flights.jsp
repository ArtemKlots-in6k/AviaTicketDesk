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
<a href="<c:url value="/"/>">Home</a><br>
<table>
    <tr>
        <td>Flight id</td>
        <td>Departure Airport</td>
        <td>Destination Airport</td>
        <td>Aircraft Capacity</td>
        <td>Free Seats</td>
        <td>Departure Time</td>
    </tr>
    <br>
    <c:forEach items="${flights}" var="flight">
        <tr>

            <td><c:out value="${flight.id}"/></td>

            <td><c:out value="${flight.departureAirport.title}"/></td>

            <td><c:out value="${flight.destinationAirport.title}"/></td>

            <td><c:out value="${flight.aircraftCapacity}"/></td>

            <td><c:out value="${flight.freeSeats}"/></td>

            <td><c:out value="${flight.departureDateTime}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

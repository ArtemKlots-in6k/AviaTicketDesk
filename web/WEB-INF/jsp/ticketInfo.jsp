<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 8/3/16
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ticket Info</title>
</head>
<body>

<table>
    <tr>
        <td><c:out value="${flight.id}"/></td>
    </tr>

    <tr>
        <td><c:out value="${flight.departureAirport.title}"/></td>
    </tr>
    <tr>
        <td><c:out value="${flight.destinationAirport.title}"/></td>
    </tr>
    <tr>
        <td><c:out value="${flight.departureDateTime}"/></td>
    </tr>

    <tr>
        <td><c:out value="${numberOfTickets}"/></td>
    </tr>

    <tr>
        <td><c:out value="${passengerName}"/></td>
    </tr>
</table>
</body>
</html>

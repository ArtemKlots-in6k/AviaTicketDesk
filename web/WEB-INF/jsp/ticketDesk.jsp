<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 8/1/16
  Time: 9:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<form:form commandName="buyTicketForm" method="POST">
    <form:select path="flight">

        <c:forEach items="${flights}" var="flight">
            <option value="${flight.id}">
                <c:out value="${flight.id}"/>
                <c:out value="${flight.departureAirport.title}"/>
                <c:out value="${flight.destinationAirport.title}"/>
                <c:out value="${flight.departureDateTime}"/>
            </option>
        </c:forEach>
    </form:select>
    <br>
    <br>
    <form:input type="text" required="required" pattern="[0-9]{0-40}" placeholder="Number of tickets"
                path="numberOfTickets"/>
    <br>
    <br>

    <form:input type="text" placeholder="Write your name" path="passengerName"/>
    <form:errors path="passengerName"/>
    <br>
    <br>

    <input type="submit" value="Submit"/>
</form:form>
</table>
</body>
</html>

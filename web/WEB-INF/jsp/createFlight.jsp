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
<a href="<c:url value="/"/>">Home</a><br><br>


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

    <input type="text" name="aircraftCapacity" required="required" pattern="[0-9]{1,3}"
           placeholder="Number of tickets"/>

    <br>
    <br>

    <input type="datetime-local" name="departureDateTime">

    <br>
    <br>

    <input type="submit" value="Submit"/>
</form:form>

<br><br>

<c:if test="${result != null}">
    <table>
        Done:
        <tr>
            <td>Id: <c:out value="${result.id}"/></td>
        </tr>
        <tr>
            <td>Departure Airport: <c:out value="${result.departureAirport.id}"/> <c:out
                    value="${result.departureAirport.title}"/></td>
        </tr>
        <tr>
            <td>Destination Airport: <c:out value="${result.destinationAirport.id}"/> <c:out
                    value="${result.destinationAirport.title}"/></td>
        </tr>
        <tr>
            <td>Aircraft Capacity: <c:out value="${result.aircraftCapacity}"/></td>
        </tr>
        <tr>
            <td>Departure Date & Time: <c:out value="${result.departureDateTime}"/></td>
        </tr>
    </table>
</c:if>

</body>
</html>

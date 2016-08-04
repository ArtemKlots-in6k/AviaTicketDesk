<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 8/1/16
  Time: 9:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<h1>Hello World</h1>

<a href="<c:url value="/flights"/>">Flights</a><br>
<a href="<c:url value="/ticketDesk"/>">Ticket Desk</a><br>
<br>
<a href="<c:url value="/createFlight"/>">Create Flight</a><br>

</body>
</html>

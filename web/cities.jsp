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
<body>
<h1>1 1 </h1>

<table>
    <c:forEach items="${cities}" var="city">
        <tr>
            <td><c:out value="${city.id}"/></td>
        </tr>

        <tr>
            <td><c:out value="${city.title}"/></td>
        </tr>

    </c:forEach>
</table>
</body>
</html>

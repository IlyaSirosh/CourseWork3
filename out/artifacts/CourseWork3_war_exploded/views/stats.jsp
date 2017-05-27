<%--
  Created by IntelliJ IDEA.
  User: Illya
  Date: 5/27/17
  Time: 03:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Stats</title>
</head>
<body>
    <h1>Stats</h1>
    <h3>Countries</h3>
    <ul>
        <c:forEach items="${countryRating}" var="x">
            <li>
                ${x.key} - ${x.value}
            </li>
        </c:forEach>
    </ul>
    <h3>Pages - Visits</h3>
    <ol>
        <c:forEach items="${pageVisitInfo}" var="y">
            <li>
                    ${y.key} - ${y.value}
            </li>
        </c:forEach>
    </ol>
    <button onclick="window.location.href='/main'">Main</button>
</body>
</html>

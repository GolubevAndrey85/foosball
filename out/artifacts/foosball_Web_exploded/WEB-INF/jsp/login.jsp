<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 26/01/2018
  Time: 12:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>${someAttribute}</h1>
<div>HI!</div>
<div>
    <c:forEach var="usr" items="${someAttribute2}">
    <tr>
        <td width="160px"><c:out value="${usr}"/></td>
    </tr>
</c:forEach>
</div>


</body>
</html>

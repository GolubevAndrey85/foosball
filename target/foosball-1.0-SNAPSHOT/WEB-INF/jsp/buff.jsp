<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 28/01/2018
  Time: 10:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <c:forEach var="usr" items="${someAttribute2}">
        <tr>
            <td width="160px"><c:out value="${usr}"/></td>
        </tr>
    </c:forEach>

    <c:forEach var="usr" items="${someAttribute3}">
        <tr>
            <td width="160px"><c:out value="${usr}"/></td>
        </tr>
    </c:forEach>
</div>

<!--img src="${pageContext.request.contextPath}/WEB-INF/img/ico.gif"-->

<style>
    textarea {
        background: #fce9c0; /* Цвет фона */
        border: 2px solid #a9c358; /* Параметры рамки */
        padding: 10px; /* Поля */
        width: 100%; /* Ширина */
        height: 200px; /* Высота */
        box-sizing: border-box; /* Алгоритм расчёта ширины */
        font-size: 14px; /* Размер шрифта */
    }
</style>
</body>
</html>

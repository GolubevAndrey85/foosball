<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 26/01/2018
  Time: 6:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Match</title>
    <title>Текстовое поле</title>
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
</head>
<body>
<div>MATCH</div>
<form action="/saveMatch" method="post">
<div>
    <input id=MyDate name="date" type=text readonly>
    <script>
        var dt=new Date();
        var month = dt.getMonth()+1;
        if (month<10) month='0'+month;
        var day = dt.getDate();
        if (day<10) day='0'+day;
        var year = dt.getFullYear();
        var hour = dt.getHours();
        if (hour<10) hour='0'+hour;
        var minute = dt.getMinutes();
        if (minute<10) minute='0'+minute;
        var sec = dt.getSeconds();
        if (sec<10) sec='0'+sec;
        MyDate.value=year+'-'+month+'-'+day+' '+hour+':'+minute+':'+sec;
    </script>
</div>
    <p><b>${message}</b></p>
    <p><b>TEAM 1:</b></p>
    <p><textarea name="team1">${team1Attribute}</textarea></p>

    <p><b>TEAM 2:</b></p>
    <p><textarea name="team2">${team2Attribute}</textarea></p>

    <p><b>TEAM 1 Score:</b></p>
    <p><textarea name="team1score">${team1scoreAttribute}</textarea></p>

    <p><b>TEAM 2 Score:</b></p>
    <p><textarea name="team2score">${team2scoreAttribute}</textarea></p>
    <p><input type="submit" value="Save Match"></p>

</form>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Info</title>
</head>
<body>

<div class="container">
    <h2 class="form-signin-heading">${mainUserNameAttr}</h2><br>
    <h4 id="winsNum" class="text-success">Your total number of WINS is: ${mUscoreAttr}</h4>
    <h4 id="loosNum" class="text-danger">Your total number of LOOSES is: ${mUloosesAttr}</h4><br>
    <h4 id="anotherWinsNum" class="text-success">Your total number of WINS over ${anotherUNameAttr}
        is: ${anotherUloosesAttr}</h4>
    <h4 id="anotherLoosNum" class="text-danger">Your total number of LOOSES to ${anotherUNameAttr}
        is: ${anotherUscoreAttr}</h4>

    <div id="myDiv"></div>
    <script>
        <c:set var="message" value="Hello"/>
        var mUstatSates = '<c:out value="${mUstatSatesAttr}"/>';
        <c:set var="message" value="Hello"/>
        var mUstatScore = '<c:out value="${mUstatScoreAttr}"/>';
        <c:set var="message" value="Hello"/>
        var anotherUName = '<c:out value="${anotherUNameAttr}"/>';
        <c:set var="message" value="Hello"/>
        var anotherUpresence = '<c:out value="${anotherUpresenceAttr}"/>';
        var b = mUstatScore.split(';').map(Number);
        var c = mUstatSates.split(';').map(String);

        if ((mUstatSates.length === 0)) {
            document.getElementById("myDiv").style.display = "none";
            document.getElementById("anotherWinsNum").style.display = "none";
            document.getElementById("anotherLoosNum").style.display = "none";
            document.getElementById("winsNum").style.display = "none";
            document.getElementById("loosNum").style.display = "none";
        }

        if ((anotherUName.length !== 0) && (mUstatSates.length !== 0)) {
            document.getElementById("myDiv").style.display = "block";
            document.getElementById("anotherWinsNum").style.display = "block";
            document.getElementById("anotherLoosNum").style.display = "block";
            document.getElementById("winsNum").style.display = "block";
            document.getElementById("loosNum").style.display = "block";
        }

        if ((anotherUName.length === 0) && (mUstatSates.length !== 0)) {
            document.getElementById("myDiv").style.display = "block";
            document.getElementById("anotherWinsNum").style.display = "none";
            document.getElementById("anotherLoosNum").style.display = "none";
            document.getElementById("winsNum").style.display = "block";
            document.getElementById("loosNum").style.display = "block";
        }

        if ((anotherUpresence.localeCompare('false') === 0)) {
            document.getElementById("anotherWinsNum").style.display = "none";
            document.getElementById("anotherLoosNum").style.display = "none";
        }

        var data = [
            {
                x: c,
                y: b,
                type: 'scatter'
            }
        ];
        Plotly.newPlot('myDiv', data);
    </script>
    <button class="btn btn-lg btn-primary btn-block" onclick='location.href="/"'>Back home</button>
</div>
</body>
</html>

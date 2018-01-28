<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>New Match</title>

    <script>
        function checkUserNames() {
            var team1 = document.getElementById("team1ID").value.split(";");//.replace(/^\s*!/,'').replace(/\s*$/,'');
            var team2 = document.getElementById("team2ID").value.split(";");
            var namesEquality = false;
            for (var i = 0; i < team1.length-1; i++) {
                for (var j = 0; j < team2.length-1; j++) {
                    if ( (team1[i].replace(/^\s*!/, '').replace(/\s*$/, '').localeCompare(team2[j].replace(/^\s*/, '').replace(/\s*$/, ''))) === 0 ) {
                        namesEquality = true;}
                }
            }
            if (namesEquality) {
                document.getElementById("warning").style.display = 'block';
            } else document.getElementById("warning").style.display = 'none';
            return !namesEquality;}
    </script>

</head>
<body>
<div class="container">
<form id="saveMatch" onsubmit="return checkUserNames()" action="/saveMatch" method="post">
<div>
    <h4 class="form-signin-heading">Data and time of the Match</h4>
    <input class="form-control" id=MyDate name="date" type=text readonly>
    <script>
        function getDataTime() {
            var data= '';
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
        data = year+'-'+month+'-'+day+' '+hour+':'+minute+':'+sec;
        return data;
        }
        document.getElementById("MyDate").setAttribute("value", getDataTime());
    </script>

</div>

    <p><b>${message}</b></p>
    <p><b>TEAM 1:</b></p>
    <p><input class="form-control" id="team1ID" name="team1" value="${team1Attribute}" placeholder="name1; name2; ...; nameN;" pattern="^[_A-Za-z0-9;\s]{1,};$">${team1Attribute}</input></p>

    <p><b>TEAM 2:</b></p>
    <p><input class="form-control" id="team2ID" name="team2" value="${team2Attribute}" placeholder="name1; name2; ...; nameX;" pattern="^[_A-Za-z0-9;\s]{1,};$">${team2Attribute}</input></p>

    <div id="warning" class="alert-warning">
        <strong>Warning!</strong> There are the same players in the bots teams!
    </div>

    <script>
        document.getElementById("warning").style.display = 'none';
    </script>

    <p><b>TEAM 1 Score:</b></p>
    <p><input class="form-control" name="team1score" placeholder=" " pattern="^[0-9]{1,3}$">${team1scoreAttribute}</input></p>

    <p><b>TEAM 2 Score:</b></p>
    <p><input class="form-control" name="team2score" placeholder=" " pattern="^[0-9]{1,3}$" >${team2scoreAttribute}</input></p>

    <p><b>Match's details:</b></p>
    <p><input class="form-control" name="matchDetails" placeholder="It was amazing match!">${matchDetails}</input></p>
    <p><input id="btn" class="btn btn-lg btn-primary btn-block" type="submit" value="Save the Match"></p>
</form>
</div>
</body>
</html>

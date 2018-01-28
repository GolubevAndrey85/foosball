<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Welcome!</title>

    <script>
        function checkUserNames() {
            if ((document.getElementById("mainPlayer").value.toLowerCase().localeCompare(document.getElementById("anotherPlayer").value.toLowerCase()) === 0)||
                (document.getElementById("mainPlayer").value === '')||(document.getElementById("mainPlayer").value === ' ')) {
                document.getElementById("warning").style.display = 'block';
                return false;
            }
            else {
                document.getElementById("warning").style.display = 'none';
                return true;
            }
        }
    </script>

</head>
<body>
<div class="container">
    <form name='newMatch' action="/newMatch" method='POST'>
        <h2 class="form-signin-heading">Please, choose the action</h2><br>
        <input class="btn btn-lg btn-primary btn-block" name="createNewMatch" type="submit"
               value="create new match"/>
    </form>
    <form class="form-signin" name='loginForm' onsubmit="return checkUserNames()" action="/login" method='POST'>
        <input id="mainPlayer" class="form-control" type='text' name='username' placeholder="Player's name"
               pattern="^[_A-Za-z0-9\s]{1,}$">
        <input id="anotherPlayer" class="form-control" type='text' name='anotherUsername'
               placeholder="Player's name for comparison (leave empty for individual statistics)"
               pattern="^[_A-Za-z0-9\s]{1,}$">

        <div id="warning" class="alert-warning">
            <strong>Warning!</strong> Wrong names!
        </div>

        <script>
            document.getElementById("warning").style.display = 'none';
        </script>

        <input class="btn btn-lg btn-primary btn-block" name="showPlayerInfo" type="submit"
               value="show player info"/>
    </form>

</div>
</body>
</html>

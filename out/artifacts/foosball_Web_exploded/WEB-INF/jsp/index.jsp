<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 24/12/2017
  Time: 7:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<div>HI!</div>

<form name='loginForm'
      action="/login" method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username'></td>
        </tr>
            <td colspan='2'><input name="showPlayeInfo" type="submit"
                                   value="show player info" /></td>

    </table>

</form>

<form name='newMatch' action="/newMatch" method='POST'>
    <input name="createNewMatch" type="submit"
           value="create new match" />
</form>
</body>
</html>

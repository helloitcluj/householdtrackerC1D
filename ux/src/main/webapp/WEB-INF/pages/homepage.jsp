
<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 5/21/2016
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>Homepage</title>
</head>
<body>

    <div class="row">
        <div class=" col-md-6">
            ${message}
        </div>
        <div class=" col-md-6" >
            <span class="pull-right" style="margin-right: 10px" >
                <a href="logoutController">Logout</a>
            </span>
        </div>
    </div>
    <div>
        Logged in user: ${loggedInUser}
    </div>
    <div>
        <a href="changePasswordNavigationController">Change password</a>
    </div>
</body>
</html>

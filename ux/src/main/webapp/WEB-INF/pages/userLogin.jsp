<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>Login Page</title>
</head>
<body>


<div align="center" style="margin-top:15%">
    <form action="userLoginController" method="post" style="width: 300px">

        <th><h1>Login</h1></th>
        <div>
            <div class="col-xs-6">Username</div>
            <div class="col-xs-6"><input type="text" name="Uname"></div>
        </div>
        <div>
            <div class="col-xs-6">Password</div>
            <div class="col-xs-6"><input type="password" name="Pasword"></div>
        </div>
        <p></p>
        <div>${message}</div>
        <p></p>
        <input type="submit" value="Submit" class="btn btn-default" style="margin-top: 20px">
    </form>
    <br/>
    <div><a href="redirect">Create account</a></div>
</div>
</body>
</html>

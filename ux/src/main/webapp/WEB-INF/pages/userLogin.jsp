<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>Login Page</title>
</head>
<body>


<div align="center" style="margin-top:15%">
    <form action="userLoginController" class="panel panel-default" method="post" style="width: 400px; padding: 10px; background-color: #f2f2f2"  >
        <h1>Login</h1>
        <div class="input-group" style="margin: 10px">
            <span class="input-group-addon" id="basic-addon1">@</span>
            <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1" name="Uname">
        </div>
        <div class="input-group" style="margin: 10px">
            <span class="input-group-addon" id="basic-addon2">%</span>
            <input type="password" class="form-control" placeholder="Password" aria-describedby="basic-addon1" name="Pasword">
        </div>
        <p></p>
        <div>${message}</div>
        <p></p>
        <input type="submit" value="Submit" class="btn btn-default" style="margin-top: 20px">
        <br/>
        <div><a href="redirect">Create account</a></div>
    </form>


</div>
</body>
</html>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login Page</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
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

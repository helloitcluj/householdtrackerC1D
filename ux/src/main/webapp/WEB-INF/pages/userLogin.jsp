<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Login Page</title>
    <link rel="icon" href="../images/favicon.ico">
    <link href="../css/vendor/bootstrap.min.css" rel="stylesheet">
    <link href="../css/loginpage.css" rel="stylesheet">
</head>
<body>

<div class="container" align="center" style="margin-top:15%; ">
    <form id="mainForm" class="form-login" action="loginAjax" class="panel panel-default" method="post" style="width: 400px; background-color:aliceblue; padding: 10px">
        <h2 class="form-signin-heading" style="margin-bottom: 30px; font-family: Calibri" >Log in...</h2>
        <label for="userName" class="sr-only">User name</label><input type="text" id="userName" name="Uname"
                                                                      placeholder="Type your username" class="form-control"
                                                                      required autofocus>
        <label for="password" class="sr-only">Password</label><input type="password" id="password" name="Pasword"
                                                                     placeholder="Type your password" class="form-control"
                                                                     required>
        <p></p>
        <p></p>
        <button class="btn btn-lg btn-success btn-block" type="submit">Login</button>

        <div id="errorMessage">${message}</div>
        Don't have an account?<div><a href="userRegistration">Create account</a></div>
    </form>
</div>



<%--</div>--%>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" type="text/javascript"></script>
<script type="javascript">window.jQuery || document.write('<script src="../js/vendor/jquery.min.js" type="text/javascript"><\/script>')</script>
<script src="..//js/vendor/bootstrap.min.js" type="text/javascript"></script>
<script src="..//js/vendor/ie10-viewport-bug-workaround.js" type="text/javascript"></script>

<script type="text/javascript" >
    $(function(){


    $("#mainForm").submit(function (event){

        event.preventDefault();
        var url = $(this).attr("action");
        var posting = $.post(url,{Uname: $("#userName")[0].value, Pasword: $("#password")[0].value})

        posting.done(function(loginResult){
            if(loginResult.kind ==="SUCCESS"){
                window.location.href = "../";
            }
            else{
                var $messageDiv = $('<div class="alert alert-danger" role="alert"></div>');
                $messageDiv.append(loginResult.message);
                $("#errorMessage").empty().append($messageDiv);
            }
        });

        posting.fail(function(loginResult){
            $("#errorMessage").empty().append("Could not find server!");
        });
    });
});

</script>

</body>
</html>

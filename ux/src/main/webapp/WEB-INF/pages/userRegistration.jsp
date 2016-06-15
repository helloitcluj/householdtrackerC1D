<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>servlets hello world!</title>
</head>
<body>


<div align="center" style="margin-top:15%; ">
    <form id="userRegistrationAjax" action="registerAjax" class="panel panel-default" method="post"
          style="width: 400px; background-color:aliceblue; padding: 10px">

        <h1 style="margin-bottom: 30px; font-family: Calibri;${displayMetod}">Register</h1>
        <div class="input-group" style="margin: 10px;${displayMetod}">
            <span class="input-group-addon" id="basic-addon1">@</span>
            <input type="text" class="form-control" placeholder="Choose a username" aria-describedby="basic-addon1"
                   name="uname">
        </div>
        <div class="input-group" style="margin: 10px;${displayMetod}">
            <span class="input-group-addon" id="basic-addon2">#</span>
            <input type="password" class="form-control" placeholder="Type password" aria-describedby="basic-addon2"
                   name="pasword">
        </div>
        <div class="input-group" style="margin: 10px;${displayMetod}" >
            <span class="input-group-addon" id="basic-addon3">#</span>
            <input type="password" class="form-control" placeholder="Re-type password" aria-describedby="basic-addon2"
                   name="retype">
        </div>
        <div id="login" style="${displayMetod}"><input type="submit" value="Register" class="btn-default" style="margin-top: 20px"></div>
        <div style="margin-top: 20px; color:red">${message}</div>
        <div><a href="userLoginController">Login</a></div>
        <div id="errorMessage"></div>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" type="text/javascript"></script>
<script type="javascript">window.jQuery || document.write('<script src="../js/vendor/jquery.min.js" type="javascript"><\/script>')</script>
<script src="..//js/vendor/bootstrap.min.js" type="javascript"></script>
<script src="..//js/vendor/ie10-viewport-bug-workaround.js" type="javascript"></script>

<script type="text/javascript">

    $(function(){

        $('#userRegistrationAjax').submit(function(event){
            event.preventDefault();

            var url = $(this).attr("action");
            var $form = $(this);
            var data = {};
            $form.find('input').each(function(){
               data[this.name] = this.value;
            });

            var posting = $.post(url, data);

            posting.done(function(message){

                $('#errorMessage').empty().append(message);

            });

        });
    });

</script>
</body>

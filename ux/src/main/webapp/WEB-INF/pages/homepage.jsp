<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Homepage</title>

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
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

/**
 * Created by Student on 6/15/2016.
 */
$(function(){
    $('#adminMenuItem').click(function(){
        ajaxGetAllUsers()
    });

    $('#testButton').click(function(){
        ajaxGetAllUsers()
    });


    $('body').on('click','#userRow', function(){
        var id = $(this).find('td').first().text();

        //alert(id);

        $.ajax({
            type : "POST",
            url : "account/getUserProfile",
            data: {id : id},

            success : function(data) {
                //console.log("SUCCESS: ", data);

                result = "";


                result += "<table class=\"table table-bordered table-hover table-striped\" id=\"tableToRemove\">";
                result +="<thead><tr><th>ID</th><th>Username</th><th>Password</th></tr></thead><tbody>";


                result += "<tr id=\"userRow\"><td>"+ data.id + "</td><td>"+ data.username + "</td><td>" + data.password + "</td></tr>";


                result += "</tbody><table>";
                //console.log("SUCCESS: ", data);
                $('#targetElement').html(result);
                $('h1.page-header').empty().text(data.username);
            }
        });
    });
});


function ajaxGetAllUsers() {
    $('#tableToRemove').remove();
    $.ajax({
        type: "POST",
        url: "account/testAjax",
        data: 'json',

        success: function (data) {
            result = "";
            result += "<table class=\"table table-bordered table-hover table-striped\" id=\"tableToRemove\">";
            result += "<thead><tr><th>ID</th><th>Username</th><th>Password</th></tr></thead><tbody>";

            $.each(data, function (index, user) {
                //console.log("SUCCESS: ", user.username);
                result += "<tr id=\"userRow\"><td>" + user.id + "</td><td>" + user.username + "</td><td>" + user.password + "</td></tr>";

            });
            result += "</tbody><table>";
            //console.log("SUCCESS: ", data);
            $('#targetElement').html(result);
            $('h1.page-header').empty().text('Manage users');
        }
    });
}
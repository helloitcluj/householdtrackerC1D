/**
 * Created by Student on 6/15/2016.
 */
$(function(){
    $('#button').click(function(){
        //alert("Hello world");
        $('#tableToRemove').remove();
        $.ajax({
            type : "POST",
            url : "account/testAjax",
            data: 'json',

            success : function(data) {
                result = "";
                result += "<table class=\"table table-bordered table-hover table-striped\" id=\"tableToRemove\">";
                result +="<thead><tr><th>ID</th><th>Username</th><th>Password</th></tr></thead><tbody>";

                $.each(data, function(index, user){
                    //console.log("SUCCESS: ", user.username);
                    result += "<tr id=\"userRow\"><td>"+ user.id + "</td><td>"+ user.username + "</td><td>" + user.password + "</td></tr>";

                });
                result += "</tbody><table>";
                //console.log("SUCCESS: ", data);
                $('#targetElement').html(result);
            }
        });
    });

    $('#userRow').click(function(){
        console.log("SUCCESS");
      alert('Row clicked');
    });
});

/**
 * Created by Student on 6/15/2016.
 */
$(function(){
    window.UserPage = {};

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

                UserPage.value = data;
                result = "";


                result += "<table class=\"table table-bordered table-hover table-striped\" id=\"tableToRemove\">";
                result +="<thead><tr><th>ID</th><th>Username</th><th>Password</th><th>Disabled</th></tr></thead><tbody>";
                result += "<tr id=\"userRow\"><td>"+ data.id + "</td><td>"+ data.username + "</td><td>" + data.password + "</td><td>" + data.disabled + "</td></tr>";
                result += "</tbody><table>";

                var $disableSelector = $('<div>Disabled: <input id="disabled" type="checkbox"/></div></br>');
                var $saveButton = $('<div><input type="button" value="Save" id="saveUserButton"</div>');

                //console.log("SUCCESS: ", data);

                $('#targetElement').html(result);
                $('#targetElement').append($disableSelector);
                $('#targetElement').append($saveButton);
                $('h1.page-header').empty().text(data.username);
            }
        });
    });

    $('body').on('click', '#saveUserButton', function(){
        var disabled = $('body').find('#disabled')[0].checked;


        //$.ajax({
        //    type : "POST",
        //    url : "account/saveUserDetails",
        //    contentType: 'application/json',
        //    data: JSON.stringify({user: window.UserPage.value, disabled:disabled}),
        //    //dataType : 'json',
        //    success : function(data) {
        //        alert(data);
        //        ajaxGetAllUsers();
        //        //console.log("SUCCESS: ", data);
        //    }
        //})  ;

        var posting = $.post("account/saveUserDetails", {user: window.UserPage.value, disabled:disabled});

        posting.done(function(result){
            alert(result);

            ajaxGetAllUsers();
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
            result += "<thead><tr><th>ID</th><th>Username</th><th>Password</th><th>Disabled</th></tr></thead><tbody>";

            $.each(data, function (index, user) {
                //console.log("SUCCESS: ", user.username);
                result += "<tr id=\"userRow\"><td>" + user.id + "</td><td>" + user.username + "</td><td>" + user.password + "</td><td>" + user.disabled + "</td></tr>";

            });
            result += "</tbody><table>";
            //console.log("SUCCESS: ", data);
            $('#targetElement').html(result);
            $('h1.page-header').empty().text('Manage users');
        }
    });
}
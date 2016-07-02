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

                var $table = $('<table class=\"table table-bordered table-hover table-striped\" id=\"tableToRemove\"></table>');
                var $tHeader  = $('<thead><tr><th>ID</th><th>Username</th><th>Password</th><th>Disabled</th></tr></thead>');
                var $tBody = $('<tbody></tbody>');
                var $row = $('<tr id=\"userRow\"></tr>');
                var $cellId = $('<td></td>').append(data.id);
                var $cellUserName = $('<td></td>').append(data.username);
                var $cellPassword = $('<td></td>').append(data.password);
                var $cellDisabled = $('<td></td>').append(data.disabledUser);

                $row.append($cellId).append($cellUserName).append($cellPassword).append($cellDisabled);

                $tBody.append($row);
                $table.append($tHeader);
                $table.append($tBody);

                var $disableSelector = $('<div>Disabled: <input id="disabled" type="checkbox"/></div></br>');
                var $saveButton = $('<div><input type="button" value="Save" id="saveUserButton"</div>');

                $('#targetElement').html($table);
                $('#targetElement').append($disableSelector);
                $('#targetElement').append($saveButton);
                $('h1.page-header').empty().text(data.username);

                $('body').find('#disabled').prop('checked', UserPage.value.disabledUser);
            }
        });
    });

    $('body').on('click', '#saveUserButton', function(){
        var disabled = $('body').find('#disabled')[0].checked;

        var userObject = window.UserPage.value;
        userObject.disabledUser = disabled;

        var posting = $.ajax({
            type : "POST",
            url : "account/saveUserDetails",
            contentType: 'application/json; charset=utf-8',
            //dataType: 'json',
            data: JSON.stringify(userObject),
            success : function(response) {
                //alert(response);
                ajaxGetAllUsers();
                //console.log("SUCCESS: ", response);
            },
            error : function(){
              alert("Error");
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
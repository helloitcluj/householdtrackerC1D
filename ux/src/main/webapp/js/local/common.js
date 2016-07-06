/**
 * Created by Student on 7/2/2016.
 */

var helloIt = helloIt || {};

helloIt.createNavbar = function() {

    var $navbar =
    $('\
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">\
    <div class="navbar-header">\
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">\
    <span class="sr-only">Toggle navigation</span>\
<span class="icon-bar"></span>\
    <span class="icon-bar"></span>\
    <span class="icon-bar"></span>\
    </button>\
    <a class="navbar-brand" href="index.html">Hello IT Household Tracker</a>\
</div>\
    <!-- /.navbar-header -->\
\
<ul class="nav navbar-top-links navbar-right">\
\
    <li class="dropdown">\
    <a class="dropdown-toggle" data-toggle="dropdown" href="#">\
        <span id="currentUser">${loggedInUser}</span> <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>\
    </a>\
    <ul class="dropdown-menu dropdown-user">\
    <li><a href="account/changePasswordNavigationController"><i class="fa fa-user fa-fw"></i> User Profile</a>\
</li>\
<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>\
</li>\
<li class="divider"></li>\
    <li><a id = "logoutLink"><i class="fa fa-sign-out fa-fw"></i> Logout</a>\
</li>\
</ul>\
    <!-- /.dropdown-user -->\
</li>\
    <!-- /.dropdown -->\
</ul>\
    <!-- /.navbar-top-links -->\
\
<div class="navbar-default sidebar" role="navigation">\
    <div class="sidebar-nav navbar-collapse">\
    <ul class="nav" id="side-menu">\
    <li class="sidebar-search">\
    <div class="input-group custom-search-form">\
    <input type="text" class="form-control" placeholder="Search...">\
    <span class="input-group-btn">\
    <button class="btn btn-default" type="button">\
    <i class="fa fa-search"></i>\
    </button>\
    </span>\
    </div>\
        <!-- /input-group -->\
    </li>\
    <li>\
    <a href="/ux/"><i class="fa fa-dashboard fa-fw"></i> Home</a>\
</li>\
<li>\
<a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Reports<span class="fa arrow"></span></a>\
<ul class="nav nav-second-level">\
    <li>\
    <a href="flot.html">Monthly reports</a>\
</li>\
<li>\
<a href="morris.html">Yearly reports</a>\
</li>\
</ul>\
    <!-- /.nav-second-level -->\
</li>\
<li>\
<a href="expense.html"><i class="fa fa-table fa-fw"></i> Expenses</a>\
</li>\
<li>\
<a href="tables.html"><i class="fa fa-table fa-fw"></i> Income</a>\
</li>\
<li>\
<a href="tables.html"><i class="fa fa-table fa-fw"></i> Exchange rates</a>\
</li>\
<li>\
<a href="#"><i class="fa fa-files-o fa-fw"></i> Admin<span class="fa arrow"></span></a>\
<ul class="nav nav-second-level">\
    <li>\
    <a href="#" id="adminMenuItem">Manage users</a>\
</li>\
<li>\
<a href="#">Other settings</a>\
</li>\
</ul>\
    <!-- /.nav-second-level -->\
</li>\
</ul>\
</div>\
    <!-- /.sidebar-collapse -->\
</div>\
    <!-- /.navbar-static-side -->\
</nav>\
');

   return $navbar;

}


helloIt.createMainContainer = function() {
    var $mainContainer = $('\
        <div id="page-wrapper">\
        </div>\
    ')

    return $mainContainer;
}


helloIt.createMainContainerTitle = function() {
    var $mainContainerTitle = $('\
        <div class="row">\
            <div class="col-lg-12">\
                <h1 class="page-header">Homepage</h1>\
            </div>\
            <!-- /.col-lg-12 -->\
        </div>\
    ')
    return $mainContainerTitle;
}

helloIt.createMainContainerContent = function() {
    var $mainContainerTitleContent = $('\
        <div class="table-responsive" id="targetElement">\
           \
        </div>\
    ')
    return $mainContainerTitleContent;
}


    helloIt.createTableContainer = function() {
    var $tableContainer = $('\
        <div class="table-responsive" id="expensesElement">\
           \
        </div>\
    ')
    return $tableContainer;
}

helloIt.createAddButton = function() {
    var $addButton = $('\
<button id="addExpense" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#expenseModal">Add expense</button>\
    ')
    return $addButton;
}

helloIt.createExpenseModal = function() {
    var $expenseModal = $('\
    <div class="modal fade" id="expenseModal" tabindex="-1" role="dialog">\
    <div class="modal-dialog">\
    <div class="modal-content">\
    <div class="modal-header">\
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>\
    <h4 class="modal-title">Add expense</h4>\
    </div>\
    <div class="modal-body">\
        <form>\
        <div class="form-group">\
            <label for="expense-date" class="control-label">Date:</label>\
            <input type="datetime-local" class="form-control" id="expenseDate">\
        </div>\
        <div class="form-group">\
            <label for="expense-amount" class="control-label">Amount:</label>\
            <input type="number" class="form-control" id="expenseAmount">\
        </div>\
        <div class="form-group">\
            <label for="expense-description" class="control-label">Description:</label>\
            <input type="text" class="form-control" id="expenseDescription">\
        </div>\
        </form>\
    </div>\
    <div class="modal-footer">\
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>\
        <button type="button" id="saveExpenseButton" class="btn btn-primary">Save changes</button>\
    </div>\
    </div><!-- /.modal-content -->\
    </div><!-- /.modal-dialog -->\
    </div><!-- /.modal -->\
    ')
    return $expenseModal;
}


helloIt.createExpensesTable = function ajaxGetAllExpenses() {

    var myNode = document.getElementById("expensesElement");

    if (myNode != null) {
        while (myNode.firstChild) {
            myNode.removeChild(myNode.firstChild);
        }
    }

    $.ajax({
        type: "POST",
        url: "account/expensesAjax",
        data: 'json',

        success: function (data) {
            result = "";
            result += "<table class=\"table table-bordered table-hover table-striped\" id=\"expenseTable\">";
            result += "<thead><tr><th>ID</th><th>Date</th><th>Amount</th><th>Description</th></tr></thead><tbody>";

            $.each(data, function (index, expense) {

                result += "<tr id=\"expenseRow\"><td>" + expense.id + "</td><td>" + expense.date.year +" - "+ expense.date.month+" - "+ expense.date.dayOfMonth+"</td><td>" + expense.amount + "</td><td>" + expense.description + "</td>";

            });
            result += "</tbody><table>";
            $('#expensesElement').append(result);
            $('h1.page-header').empty().text('Existing expenses');
        }
    });
};

$(function() {
    $("#logoutLink").click(function(){
        console.log('click');
        var getting = $.get("account/logoutController");
        getting.done(function(){
            console.log('done');
            window.location.href = "account/userLoginController";
        })
    })
})
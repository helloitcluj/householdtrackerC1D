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
        ${loggedInUser} <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>\
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
                <p>${message}</p>\
            </div>\
            <!-- /.col-lg-12 -->\
        </div>\
    ')
    return $mainContainerTitle;
}

helloIt.createMainContainerContent = function() {
    var $mainContainerTitleContent = $('\
        <div class="table-responsive" id="targetElement">\
            <input type="button" id="testButton" value="back">\
        </div>\
    ')
    return $mainContainerTitleContent;
}

helloIt.createAddButton = function() {
    var $addButton = $('\
    <div class="modal fade" tabindex="-1" role="dialog">\
    <div class="modal-dialog">\
    <div class="modal-content">\
    <div class="modal-header">\
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>\
<h4 class="modal-title">Modal title</h4>\
</div>\
<div class="modal-body">\
    <p>One fine body&hellip;</p>\
</div>\
<div class="modal-footer">\
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>\
    <button type="button" class="btn btn-primary">Save changes</button>\
</div>\
</div><!-- /.modal-content -->\
</div><!-- /.modal-dialog -->\
</div><!-- /.modal -->\
')
return $addButton;
}

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
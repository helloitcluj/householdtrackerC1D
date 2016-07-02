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
<a href="tables.html"><i class="fa fa-table fa-fw"></i> Expenses</a>\
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
        <div class="row">\
        <div class="col-lg-12">\
        <h1 class="page-header">Homepage</h1>\
        <p>${message}</p>\
        </div>\
            <!-- /.col-lg-12 -->\
        </div>\
\
            <!-- /.row -->\
        <div class="col-lg-10">\
        <div class="panel panel-default">\
        <div class="panel-heading">\
        <i class="fa fa-bar-chart-o fa-fw"></i> Summary table for current month\
    <div class="pull-right">\
        <div class="btn-group">\
        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">\
        Actions\
        <span class="caret"></span>\
        </button>\
        <ul class="dropdown-menu pull-right" role="menu">\
        <li><a href="#">Export to excel</a>\
    </li>\
    </ul>\
    </div>\
    </div>\
    </div>\
        <!-- /.panel-heading -->\
    <div class="panel-body">\
        <div class="row">\
        <div class="col-lg-4" id="tableContainer">\
        <input type="button" id="testButton" value="back">\
        <div class="table-responsive" id="targetElement">\
\
    </div>\
        <!-- /.table-responsive -->\
    </div>\
        <!-- /.col-lg-4 (nested) -->\
    <div class="col-lg-8">\
        <div id="morris-bar-chart"></div>\
        </div>\
            <!-- /.col-lg-8 (nested) -->\
        </div>\
            <!-- /.row -->\
        </div>\
            <!-- /.panel-body -->\
        </div>\
            <!-- /.panel -->\
        </div>\
            <!-- /.col-lg-8 -->\
        </div>\
            <!-- /.row -->\
        </div>\
    ')

    return $mainContainer;
}



<!DOCTYPE html>
<html lang="en">
<head>
<title>HR Application : login</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="http://trirand.com/blog/jqgrid/themes/ui.jqgrid.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="css/inbox.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js">
</script>
<script src="http://trirand.com/blog/jqgrid/js/i18n/grid.locale-en.js"></script>
<script src="http://trirand.com/blog/jqgrid/js/jquery.jqGrid.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script defer src="js/inbox.js"></script>


</head>
<body>
	<br>
	<br>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="dashboard" style="color:#1a4791">HR Portal</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="dashboard">Dashboard</a></li>
				<li class="active"><a href="inbox">Inbox</a></li>
				<li><a href="myworkflow">My workflows</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="login"><span class="glyphicon glyphicon-log-in"></span>
						Log out</a></li>
			</ul>
		</div>
	</nav>
	<h1 class="text-center">
		<b>Notifications</b>
	</h1>
	<div class="container">
		<table id="notificationTable"></table>
	</div>
		<div id="notificationTablePager" style="text-align:center; height: 60px;" > </div>
</body>
</html>

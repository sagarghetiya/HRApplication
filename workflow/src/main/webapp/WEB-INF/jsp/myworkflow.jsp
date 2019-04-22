<!DOCTYPE html>
<html lang="en">
<head>
<title>HR Application : Dashboard</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<link rel="stylesheet" href="css/myworkflow.css">
<script defer src="js/myworkflow.js"></script>

</head>
<body onload="listworkflows()">
	<br>
	<br>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="dashboard" style="color: #1a4791">HR
					Portal</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="dashboard">Dashboard</a></li>
				<li><a href="inbox">Inbox</a></li>
				<li class="active"><a id="activeBar" href="myworkflow">My Workflows</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="login"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<h5 style="color: #1a4791">Running Workflows</h5>
		<input type="text" id="workflowrunning" onkeyup="searchRunning()"
			placeholder="Search for workflow" style="margin-left: 500px">
	</div>

	<div class="scrollmenu">
		<ul class="list-group" id="runningdisplay">
		</ul>
	</div>

	<div class="container">
		<h5 style="color: #1a4791">Completed/Incomplete Workflows</h5>
		<input type="text" id="workflowcomplete" onkeyup="searchCompleted()"
			placeholder="Search for workflow" style="margin-left: 470px">
	</div>
	<div class="scrollmenu">
		<ul class="list-group" id="completedisplay">
		</ul>
	</div>
	<table id="workflowatble">
	</table>
</body>
</html>
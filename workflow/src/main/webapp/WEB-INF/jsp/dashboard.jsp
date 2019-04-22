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
<link rel="stylesheet" href="css/dashboard.css">
<script defer src="js/dashboard.js"></script>

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
				<li class="active"><a id="activeBar" href="dashboard">Dashboard</a></li>
				<!-- <li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Menu <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">option 1</a></li>
						<li><a href="#">option 2</a></li>
						<li><a href="#">option 3</a></li>
					</ul></li> -->
				<li><a href="inbox">Inbox</a></li>
				<li><a href="myworkflow">My Workflows</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="login"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			</ul>
		</div>
	</nav>
	
	<input type="text" id="workflowname" onkeyup="searchWorkflow()" placeholder="Search for workflow" style="margin-left:120px">
	<ul class="list-group" id="uldisplay">
		</ul>
	<table id="workflowatble">
	</table>
	<div id="sucess_Modal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Workflow initiated successfully!!!... :)</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal"
						onclick="clearFields()" style="background-color: #4174c6">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div id="failure_Modal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Sorry an unexpected error occured
						while initiating workflow!!!... :(</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal" style="background-color: #4174c6">Close</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
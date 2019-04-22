<!DOCTYPE html>
<html lang="en">
<head>
<title>HR Application : Registration</title>
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
<link rel="stylesheet" href="css/registration.css">
<script defer src="js/registration.js"></script>

</head>
<body>
	<br>
	<br>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="home" style="color: #1a4791">HR
					Portal</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="home">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Menu <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">option 1</a></li>
						<li><a href="#">option 2</a></li>
						<li><a href="#">option 3</a></li>
					</ul></li>
				<li><a href="inbox">Inbox</a></li>
				<li><a href="contactus">Contact Us</a></li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="registration"><span
						class="glyphicon glyphicon-user"></span> Sign Up</a></li>
				<li><a href="login"><span
						class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div>
			<img src="/images/hrimg.png" alt="IMG">
		</div>
		<div class="incontainer">
			<form method="post" id="loginform">
				<label for="username">User Name</label> <input type="text"
					id="username" name="username" required> <label
					for="firstname">First Name</label> <input type="text"
					id="firstname" name="firstname" required> <label
					for="lastname">Last Name</label> <input type="text" id="lastname"
					name="lastname" required> <label for="email">Email</label>
				<input type="email" id="email" name="email" required> <label
					for="password">Password</label> <input type="password"
					id="password" name="password"
					required> <input type="reset" value="Reset" id="reset">
				<input type="button" value="Submit" id="register_submit">
			</form>
		</div>
	</div>
	<div id="failure_Modal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Ooops!! Username or Email is already
						taken :(</h4>
				</div>
				<div class="modal-footer">
					<input type="button" data-dismiss="modal" value="close">
				</div>
			</div>
		</div>
	</div>
	<!-- <div id="message">
		<h3>Password must contain the following:</h3>
		<p id="letter" class="invalid">
			A <b>lowercase</b> letter
		</p>
		<p id="capital" class="invalid">
			A <b>capital (uppercase)</b> letter
		</p>
		<p id="number" class="invalid">
			A <b>number</b>
		</p>
		<p id="length" class="invalid">
			Minimum <b>8 characters</b>
		</p>
	</div> -->
</body>
</html>
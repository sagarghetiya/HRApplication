<!DOCTYPE html>
<html lang="en">
<head>
<title>HR Application : Contact Us</title>
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
<link rel="stylesheet" href="css/contactus.css">
<script defer src="js/contactus.js"></script>

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
				<li><a href="contactus">Contact Us</a></li>
				
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="login"><span
						class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="incontainer1">
			<!-- <img src="/images/hrimg.png" alt="IMG"> -->

			<h3>Contact Us</h3>
			<div class="imgcontainer" style="float: left">
				<img src="/images/phone.png" height="40px" width="40px"
					style="margin-top: 15px">
				<h4 style="margin-left: 20px">981281822</h4>
			</div>
			<br> <br> <br>
			<div class="imgcontainer" style="float: left">
				<img src="/images/email.png" height="40px" width="40px"
					style="margin-top: 15px">
				<h4 style="margin-left: 20px">hrapplication@gmail.com</h4>
			</div>

		</div>
		<div class="incontainer">
			<form method="post" id="contactUsForm">
				<label for="firstname">First Name</label> <input type="text"
					id="firstname" name="firstname" required> <label
					for="lastname">Last Name</label> <input type="text" id="lastname"
					name="lastname" required> <label for="email">Email</label>
				<input type="email" id="email" name="email" required> <label
					for="subject">Subject</label>
				<textarea id="subject" name="subject"
					style="height: 100px width:200px"></textarea>
				<input type="reset" value="Reset" id="reset"> <input
					type="button" value="Contact Us" id="contactus">
			</form>
		</div>
	</div>
	<div id="sucess_Modal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Email sent successfully. Keep in touch.. :)
					</h4>
				</div>
				<div class="modal-footer">
					<input
					type="button" value="Close" data-dismiss="modal" id="close">
				</div>
			</div>
		</div>
	</div>
	<div id="failure_Modal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Ooops!! We could not send you an email!(</h4>
				</div>
				<div class="modal-footer">
					<input type="button" data-dismiss="modal" value="Close">
				</div>
			</div>
		</div>
	</div>
</body>
</html>
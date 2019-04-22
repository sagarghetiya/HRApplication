$("#login_submit").click(function(){
	$('#failure_p').hide();
	$('#success_p').hide();
	var isvalidate = true;
	if (isvalidate) {
		$.ajax({
			type : 'POST',
			contentType : "application/json",
			url : "http://localhost:9090/login",
			data : formToJSON(),
			statusCode : {
				200 : function() {
					getUserID();
				},
				201 : function() {
					console.log("error");
					$('#failure_Modal').modal('show');
				}
			}
		});
	}
});

function formToJSON() {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;

	var eqn = JSON.stringify({
		"username" : username,
		"password" : password

	});
	return eqn;
}

function getUserID(){
	var username = document.getElementById("username").value;
	$.ajax({
		type : 'GET',
		contentType : "application/json",
		url : "http://localhost:9090/getuserdetails/"+username,
		success : function(data) {
			var userId = data[0].userId;
			console.log(userId);
			sessionStorage.setItem("userId",userId);
			window.location.href = "/dashboard";
		},
		error : function(data) {
		}
		
	});
}
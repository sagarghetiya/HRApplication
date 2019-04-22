$("#register_submit").click(function() {
	$('#failure_p').hide();
	$('#success_p').hide();
	var isvalidate = true;
	if (isvalidate) {
		$.ajax({
			type : 'POST',
			contentType : "application/json",
			url : "http://localhost:9090/api/register",
			dataType : "json",
			data : formToJSON(),
			statusCode : {
				200 : function() {
					window.location.href = "/login";
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
	var firstname = document.getElementById("firstname").value;
	var lastname = document.getElementById("lastname").value;
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	
	var eqn = JSON.stringify({
		"username" : username,
		"firstname" : firstname,
		"lastname" : lastname,
		"email" : email,
		"password" : password

	});
	return eqn;
}


/*var password_validation = document.getElementById("password");

password_validation.onfocus = function() {
	document.getElementById("message").style.display = "block";
}

//When the user clicks outside of the password field, hide the message box
password_validation.onblur = function() {
	document.getElementById("message").style.display = "none";
}

//When the user starts to type something inside the password field
password_validation.onkeyup = function() {
	// Validate lowercase letters
	var lowerCaseLetters = /[a-z]/g;
	if (password_validation.value.match(lowerCaseLetters)) {
		letter.classList.remove("invalid");
		letter.classList.add("valid");
	} else {
		letter.classList.remove("valid");
		letter.classList.add("invalid");
	}

	// Validate capital letters
	var upperCaseLetters = /[A-Z]/g;
	if (password_validation.value.match(upperCaseLetters)) {
		capital.classList.remove("invalid");
		capital.classList.add("valid");
	} else {
		capital.classList.remove("valid");
		capital.classList.add("invalid");
	}

	// Validate numbers
	var numbers = /[0-9]/g;
	if (password_validation.value.match(numbers)) {
		number.classList.remove("invalid");
		number.classList.add("valid");
	} else {
		number.classList.remove("valid");
		number.classList.add("invalid");
	}

	// Validate length
	if (password_validation.value.length >= 8) {
		length.classList.remove("invalid");
		length.classList.add("valid");
	} else {
		length.classList.remove("valid");
		length.classList.add("invalid");
	}
}*/
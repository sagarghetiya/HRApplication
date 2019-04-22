$("#contactus").click(function(){
	console.log("inside method");
	var isvalidate = true;
	if (isvalidate) {
		$.ajax({
			type : 'POST',
			contentType : "application/json",
			url : "http://localhost:9090/sendEmail",
			data : formToJSON(),
			statusCode : {
				200: function(){
	        		$('#sucess_Modal').modal('show');
	        	},
	    		500: function(){
	    			$('#failure_Modal').modal('show');
	    		}
			}
		});
	}
});

function formToJSON() {
	var firstname = document.getElementById("firstname").value;
	var email = document.getElementById("email").value;
	var subject = document.getElementById("subject").value;

	var eqn = JSON.stringify({
		"firstname" : firstname,
		"email" : email,
		"subject" : subject

	});
	return eqn;
}

$("#close").click(function(){

	document.getElementById("contactUsForm").reset();
});
function listworkflows() {
	var userId = sessionStorage.getItem("userId");
	var isvalidate = true;
	if (isvalidate) {
		$(document)
				.ready(
						function() {
							$
									.getJSON(
											"http://localhost:9090/getWorkflows/"+userId,
											function(json) {

												$("#uldisplay").empty();
												var uldisplay = $("#uldisplay");
												for (var i = 0; i < json.length; i++) {
													uldisplay
															.append("<li class='list-group-item liclass'><ul style='margin-left:50px' role='listbox' tabindex='0' aria-label='email list'>"
																	+ json[i].workflowName
																	+"<input type='button' class='rightalign' value='Initiate' id='"+json[i].workflowId+"' onClick=startWorkflow("+json[i].workflowId+")>" 	
																	+ "</ul></li>");
													console.log(json[i]);
													console.log(uldisplay);
												}
											});
						});

	}
}

function searchWorkflow() {
	// Declare variables
	var input, filter, ul, li, a, i, txtValue;
	input = document.getElementById('workflowname');
	filter = input.value.toUpperCase();
	ul = document.getElementById("uldisplay");
	li = ul.getElementsByTagName('li');

	// Loop through all list items, and hide those who don't match the search
	// query
	for (i = 0; i < li.length; i++) {
		a = li[i].getElementsByTagName("ul")[0];
		txtValue = a.textContent || a.innerText;
		if (txtValue.toUpperCase().indexOf(filter) > -1) {
			li[i].style.display = "";
		} else {
			li[i].style.display = "none";
		}
	}
}

function startWorkflow(workflowId){
	console.log("Workflow ID::"+workflowId);
	var userId = sessionStorage.getItem("userId");
	$.ajax({
		type : 'POST',
		contentType : "application/json",
		url : "http://localhost:9090/api/createWorkflowInstance/"+workflowId+"_"+userId,
		statusCode : {
			200 : function() {
				console.log("success");
				$('#sucess_Modal').modal('show');
			},
			201 : function() {
				console.log("error");
				$('#failure_Modal').modal('show');
			}
		}
	});
}
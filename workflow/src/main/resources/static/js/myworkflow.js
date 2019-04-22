function listworkflows() {
	console.log("inside this");
	var userId = sessionStorage.getItem("userId");
	$(document)
			.ready(
					function() {
						$
								.getJSON(
										"http://localhost:9090/api/showMyWorkflowInstance/"
												+ userId,
										function(json) {

											console.log("test");
											$("#runningdisplay").empty();
											var runningdisplay = $("#runningdisplay");
											$("#completedisplay").empty();
											var completedisplay = $("#completedisplay");
											for (var i = 0; i < json.length; i++) {
												if (json[i].status == "Completed" || json[i].status == "Rejected") {
													completedisplay
															.append("<li class='list-group-item liclass'><ul style='margin-left:50px' role='listbox' tabindex='0' aria-label='email list'>"
																	+ json[i].workflowName + "_" + json[i].workflowInstanceId
																	+ "<input class='rightalign' type='button' value='View Details' id='"
																	+ json[i].workflowInstanceId
																	+ "' onClick=viewDetails("
																	+ json[i].workflowInstanceId
																	+ ")>"
																	+ "</ul></li>");

												} else if(json[i].status == "Running"){
													runningdisplay
															.append("<li class='list-group-item liclass'><ul style='margin-left:50px' role='listbox' tabindex='0' aria-label='email list'>"
																	+ json[i].workflowName + "_" + json[i].workflowInstanceId
																	+ "<input class='rightalign' type='button' value='View Details' id='"
																	+ json[i].workflowInstanceId
																	+ "' onClick=viewDetails("
																	+ json[i].workflowInstanceId
																	+ ")>"
																	+ "</ul></li>");

												}

											}

										});
					});

}

function searchRunning() {
	// Declare variables
	var input, filter, ul, li, a, i, txtValue;
	input = document.getElementById('workflowrunning');
	filter = input.value.toUpperCase();
	ul = document.getElementById("runningdisplay");
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

function searchCompleted() {
	// Declare variables
	var input, filter, ul, li, a, i, txtValue;
	input = document.getElementById('workflowcomplete');
	filter = input.value.toUpperCase();
	ul = document.getElementById("completedisplay");
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

function viewDetails(workflowInstanceId) {
	sessionStorage.setItem("workflowStatusFlag","workflow");
	var userId = sessionStorage.getItem("userId");
	window.location.href = "/workflowInstanceStatus/"+workflowInstanceId+"/"+userId;
}
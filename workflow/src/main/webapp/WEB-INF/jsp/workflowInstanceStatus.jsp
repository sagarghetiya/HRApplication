<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="http://trirand.com/blog/jqgrid/themes/ui.jqgrid.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="../../css/workflowInstanceStatus.css">
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


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
<script src="../../js/workflowInstanceStatus.js"></script>


<body>
	<br>
	<br>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/dashboard" style="color: #1a4791">HR
				Portal</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="/home">Home</a></li>
			<li><a href="/inbox">Inbox</a></li>
			<li><a href="/myworkflow">My Workflows</a>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="/login"><span
					class="glyphicon glyphicon-log-in"></span> Logout</a></li>
		</ul>
	</div>
	</nav>
	<h1 class="text-center">
		<b>Workflow Status</b>
	</h1>
	<div class="container">
		<input type="hidden" id="workflowInstanceId"
			value='${workflowInstanceId}'>
		<form method="post" id="viewWorkflowInstanceForm">
			<label for="workflowname">Workflow Name</label> <input type="text"
				id="workflowname" name="workflowname" readonly="readonly"> <label
				for="workflowdescription">Workflow description</label> <input
				type="text" id="workflowdescription" name="workflowdescription"
				readonly="readonly" placeholder="No description">
			<table id="taskTable"></table>
			<!-- <input type="button" value="Done" id="workflow_submit"> -->
		</form>
	</div>
	<div id="taskTablePager" style="text-align:center; height: 65px;" > </div>
	<div class="modal fade" id="taskActionModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Action</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">What do you want to do ?</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" id="approveTaskBtn" onclick="approveTask()"
						class="btn btn-success">Approve</button>
					<button type="button" id="rejectTaskBtn" onclick="rejectTask()"
						class="btn btn-danger">Reject</button>
				</div>

			</div>
		</div>
	</div>
	
<div class="modal fade" id="taskRejectModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Action</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">Task rejected successfully!!...</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>
	<div class="modal fade" id="taskApprovalModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Action</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">Task approved successfully!!...</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>
	
	
	<div class="modal fade" id="taskAlreadyApprovedModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Action</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">Either you have already approved this task, A task can be approved only once. OR you do not have enough rights to approve this task</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>
		<div class="modal fade" id="errorModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Action</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body">Sorry an unexpected error occurred while performing the action :( !!!</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

</body>
</html>
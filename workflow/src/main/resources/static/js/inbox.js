$(document).ready(function() {
	var userId = sessionStorage.getItem("userId");
	loadJQGrid(userId);
	$('#notificationTable').jqGrid('navGrid', '#notificationTablePager', { edit: false, add: false, del: false, paging:true, search: false });
	$('#notificationTable').jqGrid('filterToolbar');
	setTimeout(function(){ 
		jQuery("#notificationTable").trigger("reloadGrid"); 						
	}, 50);
});
function loadJQGrid(userId) {
	jQuery("#notificationTable").jqGrid(
			{
				url : '/getNotifications/' + userId,
				datatype : "json",
				loadonce : true,
				colNames : [ "Notification ID", 'Message', 'Date', "View",
						"Workflow Instance Id", "Task Instance Id" ],
				colModel : [ {
					name : 'notificationId',
					hidden : true
				}, {
					name : 'message',
					width:380
				}, {
					name : 'createdDate',
					width:100,
					formatter: 'date', formatoptions: { srcformat: 'd/m/Y h:i:s A', newformat: 'd/m/Y h:i:s A'},
					sortable: true
				}, {
					name : 'View',
					search : false,
					sortable : false,
					width:30,
					formatter : viewFormatter
				}, {
					name : 'workflowInstanceId',
					hidden : true
				}, {
					name : 'taskInstanceId',
					hidden : true
				} ],
				autowidth : true,
				rownumbers: true,
				height : '280',
				rowNum: 8,
				sortname:'createdDate',
				sorttype:'date',
				sortorder:'desc',
				viewrecords : true,
				caption : 'Notifications',
				pager: '#notificationTablePager',
				emptyrecords: 'No Records To Display',
				viewrecords : true
			});
}
function viewFormatter(cellValue, opts, rowObject) {
	return "<div class='text-center'><div id='actionDiv'  onClick='viewDetails(" + rowObject.workflowInstanceId + ")'><i class='fa fa-eye ' style='font-size:30px'></i></div></div>";
}

function viewDetails(workflowInstanceId) {
	sessionStorage.setItem("workflowStatusFlag","notification");
	var userId = sessionStorage.getItem("userId");
	window.location.href = "/workflowInstanceStatus/"+workflowInstanceId+"/"+userId;
}

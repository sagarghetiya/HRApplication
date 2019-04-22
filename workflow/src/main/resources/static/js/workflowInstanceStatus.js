var taskInstanceId; 
var workflowInstanceStatus;
var isAllowed;
$(document).ready(function() {
	loadOptions();
});
function loadOptions(){
	var userId = sessionStorage.getItem("userId");
	var id = $('#workflowInstanceId').val();
	$.ajax({
		type : "POST",
		url : "/getWorkflowInstanceDetails/"+userId,
		contentType : "application/json",
		data : id,
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : function(response){
			workflowInstanceStatus = response.workflowStatus;
			$('#workflowname').val(response.workflowName);
			$('#workflowdescription').val(response.workflowdescription);
			isAllowed = response.isAllowed;
			loadJQGrid(response.taskInstanceList);
			colorRows();
		}
	});
}
function loadJQGrid(data) {
	jQuery("#taskTable").jqGrid(
					{
						datatype: "jsonstring",
				        datastr: data,
				        jsonReader: { repeatitems: false },
						loadonce:true,
						colNames : ['Task Id' , 'Task name', 'Task description',
             "Notificaton Status",
								"Deadline", "Status", "Action","Owner","isAllowed"],
						colModel : [
								{
									name : 'taskInstanceId',
									hidden:true,
									sortable: false
								},
								{
									name : 'taskName',
									width:60,
									sortable: false
								},
								{
									name : 'taskDescription',
									width:80,
									sortable: false
								},
								{
									name : 'notificationStatus',
									width:60,
									sortable: false
								},
								{
									name : "deadline",
									width:80,
									sortable: false
                },
								{
									name : "userName",
									width: 60
								},
								{
									name : "taskStatus",
									width:60,
									sortable: false
								},
								{
									name : "action",
									formatter: actionFormatter,
									width:30,
									sortable: false
								},
								{	name : "isAllowed",
									hidden : true
								}],
						autowidth : true,
						height : '200',
						rowNum:10,
						viewrecords : true,
						caption : 'Task List',
						pager: '#taskTablePager',
						emptyrecords: 'No Records To Display',
						rownumbers: true
					});
	}
function actionFormatter(cellValue, opts, rowObject) {
	/*var flag = sessionStorage.getItem("workflowStatusFlag");
	if (flag == "workflow") {
		if (rowObject.taskStatus == "Pending" && workflowInstanceStatus !== "Rejected") {
			return "<div class='text-center'><i class='fa fa-clock-o ' style='font-size:22px' ></i></div>";
		} else if (rowObject.taskStatus == "Running") {
			return "<div class='text-center'><i class='fa fa-cogs' style='font-size:22px' ></i></div>";
		} else if (rowObject.taskStatus == "Completed") {
			return "<div class='text-center'><i class='fa fa-check-square' style='font-size:22px' ></i></div>";
		} else if (rowObject.taskStatus == "Rejected") {
			return "<div class='text-center'><i class='fa fa-times-rectangle' style='font-size:22px' ></i></div>";
		} else if (rowObject.taskStatus == "Pending" && workflowInstanceStatus =="Rejected") {
			return "<div class='text-center'><i class='fa fa-warning' style='font-size:22px' ></i></div>";
		}
	} else {*/
		if (rowObject.taskStatus == "Pending" && workflowInstanceStatus !== "Rejected") {
			return "<div class='text-center'><i class='fa fa-clock-o ' style='font-size:22px' ></i></div>";
		} else if (rowObject.taskStatus == "Running") {
			if(isAllowed == true){
				return "<div class='text-center'><div id='actionDiv' onclick='performTaskAction("
					+ rowObject.taskInstanceId
					+ ")'><i class='fa fa-cogs' style='font-size:22px' ></i></div></div>";
			}else{
				return "<div class='text-center'><i class='fa fa-cogs' style='font-size:22px' ></i></div>";
			}
		} else if (rowObject.taskStatus == "Completed") {
			return "<div class='text-center'><i class='fa fa-check-square' style='font-size:22px' ></i></div>";
		} else if (rowObject.taskStatus == "Rejected") {
			return "<div class='text-center'><i class='fa fa-times-rectangle' style='font-size:22px' ></i></div>";
		} else if (rowObject.taskStatus == "Pending" && workflowInstanceStatus =="Rejected") {
			return "<div class='text-center'><i class='fa fa-warning' style='font-size:22px' ></i></div>";
		}
	//}
}

function colorRows(){
	var localGridData = $("#taskTable").jqGrid('getRowData');
	for(var i=0;i<localGridData.length; i++)
	{
		if(localGridData[i].taskStatus == "Running"){
			$("#"+(i+1)).children().css("background-color","#42ffff");
		}else if(localGridData[i].taskStatus == "Completed"){
			$("#"+(i+1)).children().css("background-color","#beff82");
		}else if(localGridData[i].taskStatus ==  "Rejected"){
			$("#"+(i+1)).children().css("background-color","#ff9696");
		}else if(localGridData[i].taskStatus ==  "Pending" && workflowInstanceStatus !== "Rejected"){
			$("#"+(i+1)).children().css("background-color","#ffd641");
		}else if(localGridData[i].taskStatus ==  "Pending" && workflowInstanceStatus == "Rejected"){
			$("#"+(i+1)).children().css("background-color","#ff9696");
		}
	}
}	

function performTaskAction(id){
	taskInstanceId = id;
	$("#taskActionModal").modal('show');
}

function approveTask(){
	var userId = sessionStorage.getItem("userId");
	$.ajax({
		type : "POST",
		url : "/approveTask/"+taskInstanceId+"/"+userId,
		contentType : "application/json", 
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		statusCode: {
			200:function (){
				$("#taskActionModal").modal('hide');
				$("#taskApprovalModal").modal('show');
				$('#taskTable').jqGrid('GridUnload');
				loadOptions();
			},
			208:function (){
				$("#taskActionModal").modal('hide');
				$("#taskAlreadyApprovedModal").modal('show'); 
			},
			500:function(){
				$("#taskActionModal").modal('hide');
				$("#errorModal").modal('show');
			}
		}
	});
}

function rejectTask(){
	var userId = sessionStorage.getItem("userId");
	$.ajax({
		type : "POST",
		url : "/rejectTask/"+taskInstanceId+"/"+userId,
		contentType : "application/json",
		data : taskInstanceId,
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		statusCode : {
			200:function(){
				$("#taskActionModal").modal('hide');
				$("#taskRejectModal").modal('show');
				$('#taskTable').jqGrid('GridUnload');
				loadOptions();
			},
			208:function(){
				$("#taskActionModal").modal('hide');
				$("#errorModal").modal('show');
				$('#taskTable').jqGrid('GridUnload');
				loadOptions();
			}
		}
	});
}
package com.dm.hrapplication.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dm.hrapplication.dao.UserDao;
import com.dm.hrapplication.model.TaskInstance;
import com.dm.hrapplication.model.TaskInstanceWrapper;
import com.dm.hrapplication.model.WorkflowInstance;
import com.dm.hrapplication.model.WorklfowInstanceStatusWrapper;
import com.dm.hrapplication.service.NotificationService;
import com.dm.hrapplication.service.TaskInstanceService;
import com.dm.hrapplication.service.WorkflowInstanceService;

@Controller
public class WorkflowInstanceStatusController {

	@Autowired
	UserDao userDao;

	@Autowired
	WorkflowInstanceService workflowInstanceService;

	@Autowired
	TaskInstanceService taskInstanceService;

	@Autowired
	NotificationService notificationService;

	@GetMapping({ "/workflowInstanceStatus/{workflowInstanceId}/{userId}" })
	public String workflowInstanceStatus(@PathVariable("workflowInstanceId") String workflowInstanceId,
			@PathVariable("userId") String userId, Model model) {
		model.addAttribute("workflowInstanceId", workflowInstanceId);
		model.addAttribute("userId", userId);
		return "workflowInstanceStatus";
	}

	@PostMapping(value = "/getWorkflowInstanceDetails/{userId}")
	public ResponseEntity<WorklfowInstanceStatusWrapper> getWorkflowInstanceDetails(
			@Valid @RequestBody String workflowInstanceId, @PathVariable(value = "userId") Long userId) {
		try {
			WorkflowInstance workflowInstance = workflowInstanceService
					.getWorkflowInstance(Long.parseLong(workflowInstanceId));
			if (workflowInstance == null) {
				return new ResponseEntity<WorklfowInstanceStatusWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				WorklfowInstanceStatusWrapper workflowInstanceStatusWrapper = new WorklfowInstanceStatusWrapper();
				workflowInstanceStatusWrapper.workflowName = workflowInstance.getWorkflow().getWorkflowName();
				workflowInstanceStatusWrapper.workflowDescription = workflowInstance.getWorkflow()
						.getWorkflowDescription();
				workflowInstanceStatusWrapper.workflowStatus = workflowInstance.getStatus();
				List<TaskInstance> taskInstanceList = workflowInstance.getTaskInstanceList();
				Collections.sort(taskInstanceList, new Comparator<TaskInstance>() {
					public int compare(TaskInstance t1, TaskInstance t2) {
						return Integer.compare(t1.getTask().getSequence(), t2.getTask().getSequence());
					}
				});
				for (TaskInstance taskInstance : taskInstanceList) {
					TaskInstanceWrapper taskInstanceWrapper = new TaskInstanceWrapper();
					taskInstanceWrapper.taskInstanceId = taskInstance.getTaskInstanceId();
					taskInstanceWrapper.taskName = taskInstance.getTask().getTaskName();
					taskInstanceWrapper.taskDescription = taskInstance.getTask().getTaskDescription();
					taskInstanceWrapper.deadline = taskInstance.getTaskInstaceDeadLine();
					taskInstanceWrapper.notificationStatus = taskInstance.getTask().getNotificationStatus().toString();
					taskInstanceWrapper.taskStatus = taskInstance.getStatus();
					if (taskInstance.getFile() == null) {
						taskInstanceWrapper.isFileExist = "false";
					} else {
						taskInstanceWrapper.isFileExist = "true";
					}
					if (taskInstance.getUsername().split("_").length > 1)
						taskInstanceWrapper.userName = taskInstance.getUsername().split("_")[0] + "_Group";
					else
						taskInstanceWrapper.userName = taskInstance.getUsername();
					workflowInstanceStatusWrapper.taskInstanceList.add(taskInstanceWrapper);

					// User Permission Check Code
					if (taskInstance.getStatus().equals("Running")) {
						if (taskInstance.getUser() != null) {
							if (taskInstance.getUser().getUserId() == userId) {
								workflowInstanceStatusWrapper.isAllowed = true;
							} else {
								workflowInstanceStatusWrapper.isAllowed = false;
							}
						} else {
							workflowInstanceStatusWrapper.isAllowed = notificationService
									.checkUserPermission(taskInstance, userId);
						}
					}
				}
				return ResponseEntity.ok().body(workflowInstanceStatusWrapper);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping(value = "/approveTask/{taskInstanceId}/{userId}")
	public ResponseEntity<Void> approveTaskInstance(@PathVariable("taskInstanceId") String taskInstanceId,
			@PathVariable("userId") String userId, @RequestParam(required = false) MultipartFile file,
			@RequestParam(required = false) String commentsOwner, @RequestParam(required = false) String commentsUser) {

		int returnCode = taskInstanceService.approveTaskInstance(Long.parseLong(taskInstanceId), Long.parseLong(userId),
				file, commentsUser, commentsOwner);
		if (returnCode == 1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else if (returnCode == 2) {
			return new ResponseEntity<Void>(HttpStatus.ALREADY_REPORTED);
		}
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/rejectTask/{taskInstanceId}/{userId}")
	public ResponseEntity<Void> rejectTaskInstance(@PathVariable("taskInstanceId") String taskInstanceId,
			@PathVariable("userId") String userId, @RequestParam(required = false) MultipartFile file,
			@RequestParam(required = false) String commentsOwner, @RequestParam(required = false) String commentsUser) {

		int returnCode = taskInstanceService.rejectTaskInstance(Long.parseLong(taskInstanceId), Long.parseLong(userId), commentsUser, commentsOwner);
		if (returnCode == 1) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/downloadFile/{taskInstanceId}")
	public String downloadFile(@PathVariable("taskInstanceId") String taskInstanceId, HttpServletResponse response) {
		TaskInstance taskInstance = taskInstanceService.getTaskInstanceById(Long.parseLong(taskInstanceId));
		try {
			response.setHeader("Content-Disposition", "inline;filename=\"" + taskInstance.getFileName() + "\"");
			OutputStream out = response.getOutputStream();
			response.setContentType(taskInstance.getContentType());
			IOUtils.copy(taskInstance.getFile().getBinaryStream(), out);
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

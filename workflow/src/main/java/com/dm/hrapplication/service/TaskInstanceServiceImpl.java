package com.dm.hrapplication.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dm.hrapplication.dao.TaskInstanceDao;
import com.dm.hrapplication.dao.UserDao;
import com.dm.hrapplication.dao.WorkflowInstanceDao;
import com.dm.hrapplication.model.Task;
import com.dm.hrapplication.model.TaskInstance;

@Service
public class TaskInstanceServiceImpl implements TaskInstanceService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	TaskInstanceDao taskInstanceDao;
	
	@Autowired
	WorkflowInstanceDao workflowInstanceDao;

	@Autowired
	NotificationService notificationService;

	public TaskInstance getTaskInstanceById(Long taskInstanceId) {
		return taskInstanceDao.getTaskInstanceById(taskInstanceId);
	}

	public int approveTaskInstance(Long taskInstanceId, Long userId) {
		TaskInstance taskInstance = getTaskInstanceById(taskInstanceId);
		if (taskInstance.getTask().getNotificationStatus().toString().equals("ANY")) {
			return approveTaskWithNotificationStatusAny(taskInstance, userId);
		} else {
			return approveTaskWithNotificationStatusAll(taskInstance, userId);
		}
	}

	private int approveTaskWithNotificationStatusAll(TaskInstance taskInstance, Long userId) {
		if(taskInstance.getUserIdList().remove(userId)) {
			taskInstanceDao.saveTaskInstance(taskInstance);
			if(taskInstance.getUserIdList().isEmpty()) {
				return approveTaskWithNotificationStatusAny(taskInstance, userId);
			}else {
				List<TaskInstance> taskInstanceList = taskInstance.getWorkflowInstance().getTaskInstanceList();
				Collections.sort(taskInstanceList, new Comparator<TaskInstance>() {
					public int compare(TaskInstance t1, TaskInstance t2) {
						return Integer.compare(t1.getTask().getSequence(), t2.getTask().getSequence());
					}
				});
				notificationService.createNotificationForUser(taskInstanceList.get(0).getUser(),
						"Task with name " + taskInstance.getTask().getTaskName()+" has been approved by "+userDao.getUser(userId).getUserName(),
						taskInstance.getWorkflowInstance().getWorkflowInstanceId(), taskInstance.getTaskInstanceId());
			}
			//success case
			return 1;
		}else {
			//Generate alert as (You cannot approve/ reject a task twice)
			return 2;
		}
	}
	
	
	
	
	private int approveTaskWithNotificationStatusAny(TaskInstance taskInstance, Long userId) {
		// Set current task status to completed and update the taskinstance
		taskInstance.setStatus("Completed");
		taskInstanceDao.saveTaskInstance(taskInstance);
		// get next task and mark it as running
		List<Task> taskList = taskInstance.getTask().getWorkflow().getTaskList();
		int currentSequenceNumber = taskInstance.getTask().getSequence();
		if (taskList.size() > currentSequenceNumber) {
			Collections.sort(taskList, new Comparator<Task>() {
				public int compare(Task t1, Task t2) {
					return Integer.compare(t1.getSequence(), t2.getSequence());
				}
			});
			// Since the indexing starts with 0
			List<TaskInstance> listTaskInstance = taskList.get(currentSequenceNumber).getTaskInstanceList();
			TaskInstance nextTaskInstance = null;
			for (TaskInstance taskInstance2 : listTaskInstance) {
				if (taskInstance2.getWorkflowInstance().getWorkflowInstanceId() == taskInstance.getWorkflowInstance()
						.getWorkflowInstanceId()) {
					nextTaskInstance = taskInstance2;
					break;
				}
			}
			if (nextTaskInstance == null) {
				//Problem occurred
				System.out.println("Unexpected error occurred in TaskInstanceService");
				return 0;
			} else {
				// marking next task instance as running
				nextTaskInstance.setStatus("Running");
				taskInstanceDao.saveTaskInstance(nextTaskInstance);
				//send notification to user who initiated the workflow
				List<TaskInstance> taskInstanceList2 = taskInstance.getWorkflowInstance().getTaskInstanceList();
				Collections.sort(taskInstanceList2, new Comparator<TaskInstance>() {
					public int compare(TaskInstance t1, TaskInstance t2) {
						return Integer.compare(t1.getTask().getSequence(), t2.getTask().getSequence());
					}
				});
				notificationService.createNotificationForUser(taskInstanceList2.get(0).getUser(),
						"Your task with name: " + taskInstance.getTask().getTaskName()+" belonging to Workflow: "+ taskInstance.getWorkflowInstance().getWorkflow().getWorkflowName() +" has been approved by User: "+userDao.getUser(userId).getUserName(),
						taskInstance.getWorkflowInstance().getWorkflowInstanceId(), taskInstance.getTaskInstanceId());
				// send notification to concerned users
				if (nextTaskInstance.getUser() == null) {
					notificationService.createNotificationForUserGroup(nextTaskInstance.getTask().getUserGroup(),
							"Please Approve/Reject Task:"+nextTaskInstance.getTask().getTaskName()+" of Workflow: "+nextTaskInstance.getWorkflowInstance().getWorkflow().getWorkflowName()+" intitated by User:"+taskInstanceList2.get(0).getUser().getUserName(),
							nextTaskInstance.getWorkflowInstance().getWorkflowInstanceId(), taskInstance.getTaskInstanceId());
				} else {
					notificationService.createNotificationForUser(nextTaskInstance.getUser(),
							"Please Approve/Reject Task:"+nextTaskInstance.getTask().getTaskName()+" of Workflow: "+nextTaskInstance.getWorkflowInstance().getWorkflow().getWorkflowName()+" intitated by User:"+taskInstanceList2.get(0).getUser().getUserName(),
							nextTaskInstance.getWorkflowInstance().getWorkflowInstanceId(), nextTaskInstance.getTaskInstanceId());
				}
				return 1;
			}
		} else {
			List<TaskInstance> taskInstanceList2 = taskInstance.getWorkflowInstance().getTaskInstanceList();
			Collections.sort(taskInstanceList2, new Comparator<TaskInstance>() {
				public int compare(TaskInstance t1, TaskInstance t2) {
					return Integer.compare(t1.getTask().getSequence(), t2.getTask().getSequence());
				}
			});
			notificationService.createNotificationForUser(taskInstanceList2.get(0).getUser(),
					"Task with name: " + taskInstance.getTask().getTaskName()+" has been approved by User: "+userDao.getUser(userId).getUserName(),
					taskInstance.getWorkflowInstance().getWorkflowInstanceId(), taskInstance.getTaskInstanceId());
			//set workflowinstance to completed
			taskInstance.getWorkflowInstance().setStatus("Completed");
			taskInstanceDao.saveTaskInstance(taskInstance);
			notificationService.createNotificationForUser(taskInstanceList2.get(0).getUser(),
					"Your workflow with name: " + taskInstance.getWorkflowInstance().getWorkflow().getWorkflowName()+" has been completed successfully on "+(new Date()),
					taskInstance.getWorkflowInstance().getWorkflowInstanceId(), taskInstance.getTaskInstanceId());
			return 1;
		}
	}

	@Override
	public int rejectTaskInstance(long taskInstanceId, long userId) {
		TaskInstance taskInstance = getTaskInstanceById(taskInstanceId);
		taskInstance.getWorkflowInstance().setStatus("Rejected");
		taskInstance.setStatus("Rejected");
		taskInstanceDao.saveTaskInstance(taskInstance);
		List<TaskInstance> taskInstanceList2 = taskInstance.getWorkflowInstance().getTaskInstanceList();
		Collections.sort(taskInstanceList2, new Comparator<TaskInstance>() {
			public int compare(TaskInstance t1, TaskInstance t2) {
				return Integer.compare(t1.getTask().getSequence(), t2.getTask().getSequence());
			}
		});
		notificationService.createNotificationForUser(taskInstanceList2.get(0).getUser(),
				"Task with name " + taskInstance.getTask().getTaskName()+" was rejected by "+userDao.getUser(userId).getUserName(),
				taskInstance.getWorkflowInstance().getWorkflowInstanceId(), taskInstance.getTaskInstanceId());
		notificationService.createNotificationForUser(taskInstanceList2.get(0).getUser(),
				"Your workflow with name " + taskInstance.getWorkflowInstance().getWorkflow().getWorkflowName()+" was stopped abruptly on "+(new Date())+" because User: "+userDao.getUser(userId).getUserName()+" rejected a task",
				taskInstance.getWorkflowInstance().getWorkflowInstanceId(), taskInstance.getTaskInstanceId());
		return 1;
	}
	
	
}

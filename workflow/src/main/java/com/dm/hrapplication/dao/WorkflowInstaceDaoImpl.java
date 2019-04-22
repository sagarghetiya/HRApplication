package com.dm.hrapplication.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dm.hrapplication.model.Task;
import com.dm.hrapplication.model.TaskInstance;
import com.dm.hrapplication.model.User;
import com.dm.hrapplication.model.UserGroup;
import com.dm.hrapplication.model.Workflow;
import com.dm.hrapplication.model.WorkflowInstance;
import com.dm.hrapplication.model.WorkflowInstanceNameWrapper;
import com.dm.hrapplication.service.NotificationService;

@Transactional
@Repository
public class WorkflowInstaceDaoImpl implements WorkflowInstanceDao {

	@Autowired
	NotificationService notificationService;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<WorkflowInstanceNameWrapper> showMyWorkflowInstance(Long userId) {
		String userQuery = "From User as user where user.userId = ?1";
		
		User user =  (User) entityManager.createQuery(userQuery).setParameter(1, userId)
				.getSingleResult();
		List<WorkflowInstanceNameWrapper> workflowInstanceNamesList = new ArrayList<WorkflowInstanceNameWrapper>();
		for(WorkflowInstance workflowInstanceList : user.getWorkflowInstanceList()) {
			WorkflowInstanceNameWrapper workflowInstanceNameWrapper = new WorkflowInstanceNameWrapper();
			workflowInstanceNameWrapper.setWorkflowInstanceId(workflowInstanceList.getWorkflowInstanceId());
			workflowInstanceNameWrapper.setWorkflowName(workflowInstanceList.getWorkflow().getWorkflowName());
			workflowInstanceNameWrapper.setStatus(workflowInstanceList.getStatus());
			workflowInstanceNamesList.add(workflowInstanceNameWrapper);
		}
		
		return workflowInstanceNamesList;

	}

	@Override
	public void createWorkflowInstance(String w) {
		String[] tmp = w.split("_");
		Long workflowId = Long.parseLong(tmp[0]);
		Long userId = Long.parseLong(tmp[1]);

		String userQuery = "From User as user where user.userId = ?1";
		User user = (User) entityManager.createQuery(userQuery).setParameter(1, userId).getSingleResult();

		String workflowQuery = "From Workflow as workflow where workflow.workflowId = ?1";
		Workflow workflow = (Workflow) entityManager.createQuery(workflowQuery).setParameter(1, workflowId)
				.getSingleResult();

		WorkflowInstance workflowInstance = new WorkflowInstance();
		workflowInstance.setUser(user);
		workflowInstance.setUsername(user.getUserName());
		workflowInstance.setUser(user);
		workflowInstance.setWorkflow(workflow);

		List<UserGroup> userGroupsOfUser = user.getGroupList();
		List<TaskInstance> taskInstanceList = new ArrayList<TaskInstance>();
		int i = 0;
		Comparator<Task> compareBySequence = new Comparator<Task>() {
			@Override
			public int compare(Task t1, Task t2) {
				return Integer.compare(t1.getSequence(), t2.getSequence());
			}
		};
		List<Task> taskList2 = workflow.getTaskList();
		Collections.sort(taskList2, compareBySequence);
		for (Task task : taskList2) {
			TaskInstance taskInstance = new TaskInstance();
			taskInstance.setTask(task);
			if(task.getNotificationStatus().toString().equals("ALL")) {
				List<Long> userIdList = new ArrayList<Long>();
				for(User u : task.getUserGroup().getUserList()) {
					userIdList.add(u.getUserId());
				}
				taskInstance.setUserIdList(userIdList);
			}
			taskInstance.setWorkflowInstance(workflowInstance);

			// Check what is a userGroup of any particular task
			String userGroupName = task.getUserGroup().getUserGroupName();

			// set default status of tasks
			if (i == 0) {
				System.out.println("Name::" + userGroupName);
				taskInstance.setStatus("Completed");
				taskInstance.setUsername(user.getUserName());
				taskInstance.setUser(user);
			} else {
				if (i == 1) {
					taskInstance.setStatus("Running");
				} else {
					taskInstance.setStatus("Pending");
				}

				int flagExists = 0;
				for (UserGroup userGroupOfUser : userGroupsOfUser) {
					System.out.println("List::" + userGroupOfUser.getUserGroupName() + "Name::" + userGroupName);
					if (userGroupOfUser.getUserGroupName().equals(userGroupName)) {
						flagExists = 1;
						taskInstance.setUser(user);
						taskInstance.setUsername(user.getUserName());
					}
				}
				if (flagExists == 0) {
					taskInstance.setUsername(userGroupName + ("_NA"));
				}
			}
			taskInstanceList.add(taskInstance);

			String deadLineValue = task.getDeadLine();
			try {
				int days = Integer.parseInt(deadLineValue);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				Calendar c = Calendar.getInstance();
				if (i > 0) {
					Date previousDate = taskInstanceList.get(i - 1).getTaskInstanceDeadLineInDateFormat();
					c.setTime(previousDate);
					c.add(Calendar.DATE, days);
					String output = sdf.format(c.getTime());
					try {
						Date currentDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(output);
						System.out.println("Current Date::" + currentDate);
						taskInstance.setTaskInstaceDeadLine(currentDate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("Days ::" + days);

					c.setTime(new Date()); // Now use today date.
					c.add(Calendar.DATE, days); // Adding days
					String output = sdf.format(c.getTime());
					try {
						Date currentDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(output);
						System.out.println("Current Date::" + currentDate);
						taskInstance.setTaskInstaceDeadLine(currentDate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (NumberFormatException e) {
				try {
					Date deadLineDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(task.getDeadLine());
					System.out.println("Deadline date from task::" + task.getDeadLine());
					System.out.println("Original Date::" + deadLineDate);
					taskInstance.setTaskInstaceDeadLine(deadLineDate);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}

			// Adding taskInstance to Task
			task.addTaskInstance(taskInstance);
			i++;
		}

		// Set workflowinstance status:
		if (taskInstanceList.get(taskInstanceList.size() - 1).getStatus().equals("Completed")) {
			workflowInstance.setStatus("Completed");
		} else {
			workflowInstance.setStatus("Running");
		}
		workflowInstance.setTaskInstanceList(taskInstanceList);
		user.addWorkflowInstance(workflowInstance);
		workflow.addWorkflowInstance(workflowInstance);
		entityManager.persist(workflowInstance);
		notificationService.createNotificationForUser(user, "Workflow with name "+workflow.getWorkflowName()+" has been initiated ", workflowInstance.getWorkflowInstanceId(), taskInstanceList.get(0).getTaskInstanceId());
		TaskInstance nextTaskInstance = taskInstanceList.get(1);
		if(nextTaskInstance.getUser() == null) {
			notificationService.createNotificationForUserGroup(nextTaskInstance.getTask().getUserGroup(), "Please Approve/Reject "+nextTaskInstance.getTask().getTaskName()+" of Workflow: "+nextTaskInstance.getWorkflowInstance().getWorkflow().getWorkflowName()+" intitated by User:"+user.getUserName(), workflowInstance.getWorkflowInstanceId(), nextTaskInstance.getTaskInstanceId());
		}else {
			notificationService.createNotificationForUser(user, "Please Approve/Reject Task: "+nextTaskInstance.getTask().getTaskName()+" of Workflow: "+nextTaskInstance.getWorkflowInstance().getWorkflow().getWorkflowName()+" intitated by User:"+user.getUserName(), workflowInstance.getWorkflowInstanceId(), nextTaskInstance.getTaskInstanceId());
		}
	}

	@Override
	public WorkflowInstance getWorkflowInstance(Long workflowInstanceId) {
		String query = "From WorkflowInstance as workflowInstance where workflowInstance.workflowInstanceId = ?1";
		WorkflowInstance workflowInstance = (WorkflowInstance) entityManager.createQuery(query)
				.setParameter(1, workflowInstanceId).getSingleResult();
		return workflowInstance;
	}
}

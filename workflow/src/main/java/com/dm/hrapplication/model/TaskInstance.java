package com.dm.hrapplication.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "taskinstance")
public class TaskInstance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskInstanceId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date taskInstaceDeadLine;

	private String status;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "task_id")
	private Task task;

	@ElementCollection
	private List<Long> userIdList = new ArrayList<Long>();
	
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "workflow_instance_id")
	private WorkflowInstance workflowInstance;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getTaskInstanceId() {
		return taskInstanceId;
	}

	public Task getTask() {
		return task;
	}

	public WorkflowInstance getWorkflowInstance() {
		return workflowInstance;
	}

	public void setTaskInstanceId(Long taskInstanceId) {
		this.taskInstanceId = taskInstanceId;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public void setWorkflowInstance(WorkflowInstance workflowInstance) {
		this.workflowInstance = workflowInstance;
	}

	public String getTaskInstaceDeadLine() {
		SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		String strDate = sm.format(this.taskInstaceDeadLine);
		return strDate;
	}

	public Date getTaskInstanceDeadLineInDateFormat() {
		return this.taskInstaceDeadLine;
	}
	
	public void setTaskInstaceDeadLine(Date taskInstaceDeadLine) {
		this.taskInstaceDeadLine = taskInstaceDeadLine;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Long> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<Long> userIdList) {
		this.userIdList = userIdList;
	}
	
}

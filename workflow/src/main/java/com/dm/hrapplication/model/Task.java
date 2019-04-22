package com.dm.hrapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "task")
public class Task implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskId;
	
	@NotBlank
	private String taskName;
	
	/*
	 * @NotBlank private String message;
	 */
	
	@NotBlank
	private String taskDescription;
	
	private String deadLine;
	
	@NotNull
	private Integer sequence;
	
	@Enumerated(EnumType.STRING)
	private NotificationStatusEnum notificationStatus;
	
	@ManyToOne
	@JsonIgnore 
    @JoinColumn(name = "workflow_id")
    private Workflow workflow;

	@OneToMany(mappedBy = "task", orphanRemoval = true, cascade=CascadeType.ALL)
	private List<TaskInstance> taskInstanceList = new ArrayList<TaskInstance>();
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="user_group_id")
	private UserGroup userGroup;
	
	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public Long getTaskId() {
		return taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public String getDeadLine() {
		return deadLine;
	}

	public Integer getSequence() {
		return sequence;
	}

	public NotificationStatusEnum getNotificationStatus() {
		return notificationStatus;
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public void setNotificationStatus(NotificationStatusEnum notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	/*
	 * public String getMessage() { return message; }
	 */

	public List<TaskInstance> getTaskInstanceList() {
		return taskInstanceList;
	}

	/*
	 * public void setMessage(String message) { this.message = message; }
	 */

	public void setTaskInstanceList(List<TaskInstance> taskInstanceList) {
		this.taskInstanceList = taskInstanceList;
	}
	
	public void addTaskInstance(TaskInstance taskinstance) {
		taskInstanceList.add(taskinstance);
	}
	
}
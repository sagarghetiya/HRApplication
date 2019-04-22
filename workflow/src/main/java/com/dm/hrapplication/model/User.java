package com.dm.hrapplication.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@NotNull
	private String userName;
	private String designation;
	private String password;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private NotificationBox notificationBox;
	
	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<WorkflowInstance> workflowInstanceList = new ArrayList<>();

	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<TaskInstance> taskInstanceList = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "user_group_map", joinColumns = @JoinColumn(name = "fk_user"), inverseJoinColumns = @JoinColumn(name = "fk_group"))
	@JsonIgnoreProperties("userList")
	private List<UserGroup> groupList = new ArrayList<>();

	public List<WorkflowInstance> getWorkflowInstanceList() {
		return workflowInstanceList;
	}

	public void setWorkflowInstanceList(List<WorkflowInstance> workflowInstanceList) {
		this.workflowInstanceList = workflowInstanceList;
	}

	public List<TaskInstance> getTaskInstanceList() {
		return taskInstanceList;
	}

	public void setTaskInstanceList(List<TaskInstance> taskInstanceList) {
		this.taskInstanceList = taskInstanceList;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserGroup> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<UserGroup> groupList) {
		this.groupList = groupList;
	}

	public void addWorkflowInstance(WorkflowInstance workflowInstance) {
		workflowInstanceList.add(workflowInstance);
	}

	public NotificationBox getNotificationBox() {
		return notificationBox;
	}

	public void setNotificationBox(NotificationBox notificationBox) {
		this.notificationBox = notificationBox;
	}
	
}

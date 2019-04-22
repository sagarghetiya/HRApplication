package com.dm.hrapplication.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usergroup")
public class UserGroup {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userGroupId;
	@NotNull
	private String userGroupName;
	private String description;
	
	@ManyToMany(mappedBy = "groupList")
	@JsonIgnoreProperties("groupList")
	private List<User> userList = new ArrayList<>();
	
	@OneToMany(mappedBy="userGroup", orphanRemoval=true, cascade=CascadeType.ALL)
	private List<Task> taskList = new ArrayList<>();
	
	public List<Task> getTaskList() {
		return taskList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public void addUser(User u) {
		this.userList.add(u);
		u.getGroupList().add(this);
	}

	public void removeUser(User u) {
		this.userList.remove(u);
		u.getGroupList().remove(this);
	}
	
	public Long getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Long userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUsers(List<User> userList) {
		this.userList = userList;
	}
	
	
}

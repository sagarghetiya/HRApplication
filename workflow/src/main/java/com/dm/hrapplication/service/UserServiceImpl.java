package com.dm.hrapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dm.hrapplication.dao.UserDao;
import com.dm.hrapplication.model.User;
import com.dm.hrapplication.model.UserGroup;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public synchronized boolean doLogin(String username, String password) {
		if (userDao.loginExists(username, password)) {
			System.out.println("User is already there");
			return true;

		} else {
			System.out.println("Username and password does not match");
			return false;
		}
	}
	
	@Override
	public List<User> getUserDetails(String username){
		return userDao.getUserDetails(username);
	}
	@Override
	public User getUser(Long userid) {
		return userDao.getUser(userid);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public void addUserGroup(UserGroup grp) {
		 userDao.addUserGroup(grp);
		/*
		 * try { UserGroup ugrp = new UserGroup(); ugrp.setUsers(grp.getUserList());
		 * ugrp.setUserGroupName(grp.getUserGroupName());
		 * ugrp.setDescription(grp.getDescription()); //Integer sequence = 1; // Since
		 * it is a bi-directional mapping we need to set workflow in task and task // in
		 * workflow for (Task task : workflowWrapper.getTaskList()) {
		 * task.setWorkflow(workflow); task.setSequence(sequence++); }
		 * userDao.addUserGroup(ugrp); } catch (Exception e) { e.printStackTrace(); return
		 * false; } return true;
		 */
	}

	@Override
	public UserGroup getGroup(Long grpid) {
		return userDao.getGroup(grpid);
	}

	@Override
	public List<UserGroup> getAllGroups() {
		return userDao.getAllGroups();
	}



}

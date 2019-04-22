package com.dm.hrapplication.service;

import java.util.List;

import com.dm.hrapplication.model.User;
import com.dm.hrapplication.model.UserGroup;

public interface UserService {
	
	boolean doLogin(String username, String password);
	List<User> getUserDetails(String username);
	User getUser(Long userid);
	List<User> getAllUsers();
	
	void addUserGroup(UserGroup grp);
	//List<User> getUserFromGroup(Long grpid);
		UserGroup getGroup(Long grpid);
		List<UserGroup> getAllGroups();
}

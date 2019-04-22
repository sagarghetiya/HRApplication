package com.dm.hrapplication.dao;

import java.util.List;

import com.dm.hrapplication.model.TaskInstance;
import com.dm.hrapplication.model.User;
import com.dm.hrapplication.model.UserGroup;

public interface UserDao {
	
	boolean loginExists(String username, String password);
	List<User> getUserDetails(String username);
	User getUser(Long userid);
	List<User> getAllUsers();
	
	void addUserGroup(UserGroup grp);
	//List<User> getUserFromGroup(Long grpid);
	UserGroup getGroup(Long grpid);
	List<UserGroup> getAllGroups();
	void addUser(User user);
	boolean checkUserPermission(TaskInstance taskInstance,Long userId);
}

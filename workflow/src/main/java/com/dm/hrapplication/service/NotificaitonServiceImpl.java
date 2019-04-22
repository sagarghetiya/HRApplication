package com.dm.hrapplication.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dm.hrapplication.dao.UserDao;
import com.dm.hrapplication.model.Notification;
import com.dm.hrapplication.model.NotificationBox;
import com.dm.hrapplication.model.TaskInstance;
import com.dm.hrapplication.model.User;
import com.dm.hrapplication.model.UserGroup;

@Service
public class NotificaitonServiceImpl implements NotificationService{

		
	@Autowired
	private UserDao userDao;
	

	@Override
	public int createNotificationForUser(User user, String message, Long workflowInstanceId, Long taskInstanceId) {
		NotificationBox notificationBox = user.getNotificationBox();
		if(notificationBox == null) {
			notificationBox = new NotificationBox();
			user.setNotificationBox(notificationBox);
			notificationBox.setUser(user);
		}
		Notification notification = new Notification();
		notification.setCreatedDate(new Date());
		notification.setMessage(message);
		notification.setTaskInstanceId(taskInstanceId);
		notification.setWorkflowInstanceId(workflowInstanceId);
		notification.setNotificationBox(notificationBox);
		user.getNotificationBox().getNotificationList().add(notification);
		userDao.addUser(user);
		return 1;
	}

	@Override
	public int createNotificationForUserGroup(UserGroup userGroup, String message, Long workflowInstanceId, Long taskInstanceId) {
		try {
			for(User user: userGroup.getUserList()) {
				NotificationBox notificationBox = user.getNotificationBox();
				if(notificationBox == null) {
					notificationBox = new NotificationBox();
					user.setNotificationBox(notificationBox);
					notificationBox.setUser(user);
				}
				Notification notification = new Notification();
				notification.setCreatedDate(new Date());
				notification.setMessage(message);
				notification.setTaskInstanceId(taskInstanceId);
				notification.setWorkflowInstanceId(workflowInstanceId);
				notification.setNotificationBox(notificationBox);
				user.getNotificationBox().getNotificationList().add(notification);
				userDao.addUser(user);
			}
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public List<Notification> getAllNotifications(Long userId){
		return userDao.getUser(userId).getNotificationBox().getNotificationList();
	}
	
	@Override
	public boolean checkUserPermission(TaskInstance taskInstance,Long userId) {
		return userDao.checkUserPermission(taskInstance,userId);
	}
}

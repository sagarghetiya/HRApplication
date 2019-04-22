package com.dm.hrapplication.service;

import java.util.List;

import com.dm.hrapplication.model.Notification;
import com.dm.hrapplication.model.TaskInstance;
import com.dm.hrapplication.model.User;
import com.dm.hrapplication.model.UserGroup;

public interface NotificationService {
	public int createNotificationForUser(User user, String message, Long workflowInstanceId, Long taskInstanceId);
	public int createNotificationForUserGroup(UserGroup userGroup, String message, Long workflowInstanceId, Long taskInstanceId);
	public List<Notification> getAllNotifications(Long userId);
	public boolean checkUserPermission(TaskInstance taskInstance,Long userId);
}

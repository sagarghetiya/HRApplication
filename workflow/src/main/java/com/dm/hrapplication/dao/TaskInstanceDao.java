package com.dm.hrapplication.dao;

import com.dm.hrapplication.model.TaskInstance;

public interface TaskInstanceDao {
	
	public TaskInstance getTaskInstanceById(Long taskInstanceId);
	public void saveTaskInstance(TaskInstance taskInstance);
}

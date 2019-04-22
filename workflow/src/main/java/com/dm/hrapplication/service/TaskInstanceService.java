package com.dm.hrapplication.service;

import com.dm.hrapplication.model.TaskInstance;

public interface TaskInstanceService {
	public TaskInstance getTaskInstanceById(Long taskInstanceId);
	public int approveTaskInstance(Long taskIntanceId, Long userId);
	public int rejectTaskInstance(long parseLong, long parseLong2);
}

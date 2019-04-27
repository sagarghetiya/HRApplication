package com.dm.hrapplication.service;

import org.springframework.web.multipart.MultipartFile;

import com.dm.hrapplication.model.TaskInstance;

public interface TaskInstanceService {
	public TaskInstance getTaskInstanceById(Long taskInstanceId);
	public int approveTaskInstance(Long taskIntanceId, Long userId, MultipartFile file, String commentsUser, String commentsOwner);
	public int rejectTaskInstance(long parseLong, long parseLong2, String commentsUser, String commentsOwner);
}

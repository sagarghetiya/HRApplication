package com.dm.hrapplication.dao;

import java.util.List;

import com.dm.hrapplication.model.WorkflowInstance;
import com.dm.hrapplication.model.WorkflowInstanceNameWrapper;

public interface WorkflowInstanceDao {

	List<WorkflowInstanceNameWrapper> showMyWorkflowInstance(Long userId);
	void createWorkflowInstance(String workflowId);

	WorkflowInstance getWorkflowInstance(Long workflowInstanceId);
}

package com.dm.hrapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dm.hrapplication.dao.WorkflowInstanceDao;
import com.dm.hrapplication.model.WorkflowInstance;
import com.dm.hrapplication.model.WorkflowInstanceNameWrapper;

@Service
public class WorkflowInstaceServiceImpl implements WorkflowInstanceService {

	@Autowired
	private WorkflowInstanceDao workflowInstanceDao;
	
	public List<WorkflowInstanceNameWrapper> showMyWorkflowInstance(Long userId){
		return workflowInstanceDao.showMyWorkflowInstance(userId);
	}
	
	public void createWorkflowInstance(String workflowId) {
		workflowInstanceDao.createWorkflowInstance(workflowId);
	}
	
	public WorkflowInstance getWorkflowInstance(Long workflowInstanceId) {
		return workflowInstanceDao.getWorkflowInstance(workflowInstanceId);
	}
	
}

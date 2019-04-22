package com.dm.hrapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dm.hrapplication.dao.WorkflowDao;
import com.dm.hrapplication.model.Workflow;

@Service
public class WorkflowServiceImpl implements WorkflowService {
	@Autowired
	private WorkflowDao workflowDao;
		
	@Override
	public List<Workflow> getWorkflows(Long userId) {
		List<Workflow> workflowList = null;
		try {
			workflowList = workflowDao.getWorkflows(userId);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		if(workflowList!=null && !workflowList.isEmpty()) {
			return workflowList;
		}
		return null;
	}
}

package com.dm.hrapplication.dao;

import java.util.List;

import com.dm.hrapplication.model.Workflow;

public interface WorkflowDao {
	List<Workflow> getWorkflows(Long userId);
} 
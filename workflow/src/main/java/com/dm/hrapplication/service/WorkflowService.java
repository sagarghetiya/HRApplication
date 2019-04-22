package com.dm.hrapplication.service;

import java.util.List;

import com.dm.hrapplication.model.Workflow;

public interface WorkflowService {
	List<Workflow> getWorkflows(Long userId);
} 
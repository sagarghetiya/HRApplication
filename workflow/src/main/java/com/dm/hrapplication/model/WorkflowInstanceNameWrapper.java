package com.dm.hrapplication.model;

public class WorkflowInstanceNameWrapper {

	private String workflowName;
	private Long workflowInstanceId;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWorkflowName() {
		return workflowName;
	}
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
	public Long getWorkflowInstanceId() {
		return workflowInstanceId;
	}
	public void setWorkflowInstanceId(Long workflowInstanceId) {
		this.workflowInstanceId = workflowInstanceId;
	}
	
	
	
}

package com.dm.hrapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "workflow")
public class Workflow implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long workflowId;
	
	@NotBlank
	private String workflowName;
	
	@NotBlank
	private String workflowDescription;
	
	@OneToMany(mappedBy = "workflow", orphanRemoval = true, cascade=CascadeType.ALL)
    private List<Task> taskList = new ArrayList<>();
	
	@OneToMany(mappedBy = "workflow", orphanRemoval = true, cascade=CascadeType.ALL)
	private List<WorkflowInstance> workflowInstances = new ArrayList<>();
	
	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean isDeleted;
	
	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public String getWorkflowDescription() {
		return workflowDescription;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public void setWorkflowDescription(String workflowDescription) {
		this.workflowDescription = workflowDescription;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public List<WorkflowInstance> getWorkflowInstances() {
		return workflowInstances;
	}

	public void setWorkflowInstances(List<WorkflowInstance> workflowInstances) {
		this.workflowInstances = workflowInstances;
	}
	
	public void addWorkflowInstance(WorkflowInstance workflowInstance) {
		workflowInstances.add(workflowInstance);
	}
}

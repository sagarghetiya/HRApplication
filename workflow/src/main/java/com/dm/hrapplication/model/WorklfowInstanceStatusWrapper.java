package com.dm.hrapplication.model;

import java.util.ArrayList;
import java.util.List;

public class WorklfowInstanceStatusWrapper {
	public String workflowName;
	public String workflowStatus;
	public String workflowDescription;
	public List<TaskInstanceWrapper> taskInstanceList = new ArrayList<TaskInstanceWrapper>();
	public boolean isAllowed;
	
}

package com.dm.hrapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dm.hrapplication.model.Workflow;
import com.dm.hrapplication.service.WorkflowService;

@Controller
public class WorkflowController {
	@Autowired
	private WorkflowService workflowService;
	
	/**************
	 * This Method is used for listing all workflows
	 *************************/
	@GetMapping({"/getWorkflows/{userId}"})
	public ResponseEntity<List<Workflow>> getWorkflows(@PathVariable(value = "userId") Long userId){
		List<Workflow> workflowList = workflowService.getWorkflows(userId);
		if(workflowList == null) {
			return new ResponseEntity<List<Workflow>>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}else {
			return new ResponseEntity<List<Workflow>>(workflowList, HttpStatus.OK);
		}
	}
}

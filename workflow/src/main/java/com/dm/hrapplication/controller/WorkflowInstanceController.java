package com.dm.hrapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dm.hrapplication.model.WorkflowInstanceNameWrapper;
import com.dm.hrapplication.service.WorkflowInstanceService;

@Controller
@RequestMapping(value = "/api")
public class WorkflowInstanceController {

	@Autowired
	WorkflowInstanceService workflowInstanceService;
	
	@GetMapping("/showMyWorkflowInstance/{userId}")
	public ResponseEntity<List<WorkflowInstanceNameWrapper>> showMyWorkflowInstance(@PathVariable(value = "userId")Long userId){
		List<WorkflowInstanceNameWrapper> myWorkflowInstanceList = workflowInstanceService.showMyWorkflowInstance(userId);
		return new ResponseEntity<List<WorkflowInstanceNameWrapper>>(myWorkflowInstanceList, HttpStatus.OK);
	}
	@PostMapping("/createWorkflowInstance/{workflowId}")
	public ResponseEntity<Void> createWorkflowInstance(@PathVariable(value = "workflowId") String workflowId) {
		workflowInstanceService.createWorkflowInstance(workflowId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}

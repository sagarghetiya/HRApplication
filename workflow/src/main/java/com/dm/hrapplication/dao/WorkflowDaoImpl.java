package com.dm.hrapplication.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dm.hrapplication.model.Task;
import com.dm.hrapplication.model.User;
import com.dm.hrapplication.model.UserGroup;
import com.dm.hrapplication.model.Workflow;

@Transactional
@Repository
public class WorkflowDaoImpl implements WorkflowDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<Workflow> getWorkflows(Long userId) {
		String hql = "FROM Workflow as workflow";
		List<Workflow> allWorkflowList = (List<Workflow>) entityManager.createQuery(hql).getResultList();
		List<Workflow> userWorkflow = new ArrayList<Workflow>();
		String userQuery = "FROM User as user WHERE user.userId = ?1";
		User user = (User) entityManager.createQuery(userQuery).setParameter(1, userId).getSingleResult();

		for (Workflow workflow : allWorkflowList) {
			int i = 0;
			for (Task task : workflow.getTaskList()) {
				if (task.getSequence() == 1) {
					for (UserGroup userGroup : user.getGroupList()) {
						System.out.println(
								"Group name::" + workflow.getTaskList().get(i).getUserGroup().getUserGroupName());
						System.out.println("Group name 2::" + userGroup.getUserGroupName());
						if (workflow.getTaskList().get(i).getUserGroup().getUserGroupName()
								.equals(userGroup.getUserGroupName())) {
							if (workflow.getIsDeleted() != true) {
								userWorkflow.add(workflow);
							}

						}
					}
				}
				i++;
			}

		}

		return userWorkflow;
	}
}
package com.dm.hrapplication.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.dm.hrapplication.model.TaskInstance;

@Transactional
@Repository
public class TaskInstanceDaoImpl implements TaskInstanceDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public TaskInstance getTaskInstanceById(Long taskInstanceId) {
		String query = "From TaskInstance as taskInstance where taskInstance.taskInstanceId = ?1";
		TaskInstance taskInstance = (TaskInstance) entityManager.createQuery(query).setParameter(1, taskInstanceId).getSingleResult();
		return taskInstance;
	}

	@Override
	public void saveTaskInstance(TaskInstance taskInstance) {
		entityManager.persist(taskInstance);
	}
	
}

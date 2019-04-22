package com.dm.hrapplication.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class NotificationDaoImpl implements NotificationDao {

	@PersistenceContext
	private EntityManager entityManager;
}

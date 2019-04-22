package com.dm.hrapplication.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.dm.hrapplication.controller.PassEncryption;
import com.dm.hrapplication.model.TaskInstance;
import com.dm.hrapplication.model.User;
import com.dm.hrapplication.model.UserGroup;

@Transactional
@Repository
public class UserDaoImpl implements UserDao{
	@PersistenceContext
	private EntityManager entityManager;
	private PassEncryption passEncryption = new PassEncryption("himsagabcdprijay");

	@Override
	public boolean loginExists(String username, String password) {
		String encryptPassword;
		try {
			encryptPassword = passEncryption.encrypt(password);
			String hql = "FROM User as user WHERE user.userName = ?1 and user.password = ?2";

			int count = entityManager.createQuery(hql).setParameter(1, username).setParameter(2, encryptPassword)
					.getResultList().size();
			System.out.println("count" + count);
			return count > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserDetails(String username){
		String userQuery = "From User as user WHERE user.userName = ?1";
		return (List<User>)entityManager.createQuery(userQuery).setParameter(1, username).getResultList();
	}

	@Override
	public User getUser(Long userid) {
		String hql = "from User as u where u.userId =?1";
		return (User) entityManager.createQuery(hql).setParameter(1, userid).getSingleResult();
	}

	@Override
	public List<User> getAllUsers() {
		String hql = "from User";
		@SuppressWarnings("unchecked")
		List<User> resultList = (List<User>) entityManager.createQuery(hql).getResultList();
		return resultList;
	}

	@Override
	public void addUserGroup(UserGroup grp) {
		UserGroup g = new UserGroup();
		g.setUserGroupName(grp.getUserGroupName());
		g.setDescription(grp.getDescription());
		for(User u : grp.getUserList()) {
			User ob = new User();
			ob = getUser(u.getUserId());
			g.addUser(ob);
		}
		entityManager.persist(g);
	}
	
	public void addUser(User user) {
		entityManager.persist(user);
	}
	
	@Override
	public UserGroup getGroup(Long grpid) {
		String hql ="from UserGroup where userGroupId=?1";
		return (UserGroup) entityManager.createQuery(hql).setParameter(1, grpid).getSingleResult();
		/*TypedQuery<Object[]> query= entityManager.createQuery("FROM UserGroup g WHERE g.userGroupId = ?1",
                Object[].class);
		List<Object[]> results = query.setParameter(1, grpid).getResultList();
		
		 List<User> s=new ArrayList<>();
		 for (Object[] result : results) {
			 s = (List<User>) result[0];
		  }
		return s;*/
	}

	@Override
	public List<UserGroup> getAllGroups() {
		String hql = "from UserGroup";
		@SuppressWarnings("unchecked")
		List<UserGroup> resultList = (List<UserGroup>) entityManager.createQuery(hql).getResultList();
		return resultList;
	}

	@Override
	public boolean checkUserPermission(TaskInstance taskInstance, Long userId) {

		boolean isExist = false;
		String userQuery = "From User as user where user.userId = ?1";
		User user = (User) entityManager.createQuery(userQuery).setParameter(1, userId).getSingleResult();
		for (UserGroup usergroup : user.getGroupList()) {
			if (usergroup.getUserGroupName().concat("_NA").equals(taskInstance.getUsername())) {
				isExist = true;
			}
		}
		return isExist;
	}

}

package vn.whoever.server.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import vn.whoever.server.dao.UserDao;

@Transactional
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String getRoleUser(String username) {
		
		return "ROLE_ADMIN";
	}

	public boolean validateUser(String username, String password) {
	
		return true;
	}
	
}

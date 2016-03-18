package vn.whoever.dao.impl;

import org.hibernate.SessionFactory;

public class UserDAOImpl {

	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

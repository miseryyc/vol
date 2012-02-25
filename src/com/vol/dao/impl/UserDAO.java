package com.vol.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vol.dao.IUserDAO;
import com.vol.entity.User;

public class UserDAO extends HibernateDaoSupport implements IUserDAO {

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByName(String userName) {
		// TODO Auto-generated method stub
		List<Object> results = getHibernateTemplate().find("select id, userName, password, " +
				"email from User where userName = '" + userName +"'");
		return this.convertToUser(results);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByNameAndPwd(User user) {
		List<Object> results = getHibernateTemplate().find("select id, userName, password, " +
				"email from User where userName = '" + user.getUserName() + "' and " +
				"password = '" + user.getPassword() + "'");		
		return this.convertToUser(results);
	}
	
	private List<User> convertToUser(List results) {
		List<User> users = new ArrayList<User>();
		for(Object result : results) {
			User user = new User();
			Object [] obj = (Object[])result;
			user.setId((Integer)obj[0]);
			user.setUserName((String)obj[1]);
			user.setPassword((String)obj[2]);
			user.setEmail((String)obj[3]);
			users.add(user);
		}
		return users;
	}

}

/**
 * 
 */
package com.vol.service.impl;

import java.util.List;

import com.vol.dao.impl.UserDAO;
import com.vol.entity.User;
import com.vol.service.UserService;

/**
 * @author miseryyc
 *
 */
public class UserServiceImpl implements UserService{

	private UserDAO userDAO;
	private final static int USER_EXIST = 0;
	private final static int USER_NOT_EXIST = 1;
	
	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userDAO.saveUser(user);
	}

	/**
	 * @return the userDAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public int checkUserByName(String userName) {
		// TODO Auto-generated method stub
		List<User> users = userDAO.findUserByName(userName);
		if(users != null && users.size() > 0)
			return USER_EXIST;
		else return USER_NOT_EXIST;
	}

	@Override
	public boolean checkNameAndPwd(User user) {
		// TODO Auto-generated method stub
		List<User> users = this.userDAO.findUserByNameAndPwd(user);
		if(users != null && users.size() > 0) {
			return true;
		}
		return false;
	}

}

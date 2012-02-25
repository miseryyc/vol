/**
 * 
 */
package com.vol.dao;

import java.util.List;

import com.vol.entity.User;

/**
 * @author miseryyc
 *
 */
public interface IUserDAO {
	public void saveUser(User user);
	public List<User> findUserByName(String userName);
	public List<User> findUserByNameAndPwd(User user);
}

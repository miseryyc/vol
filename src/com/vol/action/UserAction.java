/**
 * 
 */
package com.vol.action;

import com.opensymphony.xwork2.ActionSupport;
import com.vol.entity.User;
import com.vol.service.UserService;
import com.vol.util.JsonUtil;
import com.vol.util.SessionUtil;

/**
 * @author miseryyc
 *
 */
public class UserAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private User user;
	
	public void saveUser() {
		String result = "isOk";
		try {
			userService.saveUser(user);
			JsonUtil.sendJsonMsg(result);
		}catch(Exception e) {
			result = "error";
			JsonUtil.sendJsonMsg(result);
		}
	}
	
	public void checkUser() {
		int result = userService.checkUserByName(user.getUserName());
		JsonUtil.sendJsonMsg(String.valueOf(result));
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void login() {
		SessionUtil.addToSession(user);
		String result = "fail";
		if(this.userService.checkNameAndPwd(user)) {
			result = "ok";
		}
		JsonUtil.sendJsonMsg(result);
	}
}

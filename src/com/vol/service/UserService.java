package com.vol.service;

import com.vol.entity.User;

public interface UserService {
	void saveUser(User user);
	int checkUserByName(String userName);
	boolean checkNameAndPwd(User user);
}

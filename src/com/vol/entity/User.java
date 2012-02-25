/**
 * 
 */
package com.vol.entity;

import java.io.Serializable;

/**
 * @author miseryyc
 *
 */
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5407005471857341908L;
	private int id;
	private String userName;
	private String password;
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

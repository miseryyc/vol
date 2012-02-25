/**
 * 
 */
package com.vol.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.vol.entity.User;

/**
 * @author miseryyc
 *
 */
public class SessionUtil {
	
	public static void addToSession(User user) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		session.setAttribute("username", user.getUserName());
	}
	
	public static HttpSession getSession() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getSession();
	}
	
	public static void process( ) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		HttpServletResponse response = ServletActionContext.getResponse();
		if(user == null || user.getUserName() == null) {
			try {
				response.sendRedirect(request.getContextPath() + "/welcome.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

/**
 * 
 */
package com.vol.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.vol.entity.User;
import com.vol.service.RunValidatorService;
import com.vol.util.JsonUtil;
import com.vol.util.SessionUtil;

/**
 * @author miseryyc
 *
 */
public class FileDownloadAction extends ActionSupport {
	private RunValidatorService runValidatorService;
	
	public void getDownloadFiles() {
		HttpSession session = SessionUtil.getSession();
		User user = (User)session.getAttribute("user");
		List<File> files = runValidatorService.getDirFiles(user.getUserName());
		String json = JsonUtil.ListToStr(files);
		JsonUtil.sendJsonMsg(json);
	}
	
	public void downloadFiles() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String filePath = request.getParameter("path");
		FileInputStream in = new FileInputStream(filePath);
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
		ServletActionContext.getResponse().setHeader("Content-Disposition","attachment; filename="
				+ new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
		PrintWriter out = response.getWriter();
		int b = 0;
		while((b = in.read()) != -1) {
			out.write(b);
		}
		
		in.close();
		out.close();
	}

	/**
	 * @return the runValidatorService
	 */
	public RunValidatorService getRunValidatorService() {
		return runValidatorService;
	}

	/**
	 * @param runValidatorService the runValidatorService to set
	 */
	public void setRunValidatorService(RunValidatorService runValidatorService) {
		this.runValidatorService = runValidatorService;
	}
}

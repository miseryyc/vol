package com.vol.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.vol.dao.impl.FileUploadDAO;
import com.vol.dao.impl.RunValidatorDAO;
import com.vol.dao.impl.UserDAO;
import com.vol.entity.FileUpload;
import com.vol.entity.RunValidator;
import com.vol.entity.User;
import com.vol.service.RunValidatorService;
import com.vol.thread.pool.ThreadPoolManager;
import com.vol.thread.task.ThreadTask;
import com.vol.util.PropertiesConfig;
import com.vol.util.SessionUtil;

public class RunValidatorServiceImpl implements RunValidatorService{

	private FileUploadDAO fileUploadDAO;
	private RunValidatorDAO runValidatorDAO;
	private UserDAO userDAO;
	
	@Override
	public void runValidator(String filePath, String dataFileName) {
		// TODO Auto-generated method stub
		ThreadPoolManager poolManager = ThreadPoolManager.getInstance();
		RunValidator runValidator = new RunValidator();
		HttpSession session = SessionUtil.getSession();
		User user = (User)session.getAttribute("user");
		List<User> users = userDAO.findUserByName(user.getUserName());
		
		if(users == null || users.size() == 0) {
			return;
		}
		
		User newUser = (User)users.get(0);
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		runValidator.setUserId(newUser.getId());
		runValidator.setRunTime(now);
		
		ThreadTask task = new ThreadTask(filePath, dataFileName, runValidator, runValidatorDAO);
		poolManager.addTask(task);
	}

	@Override
	public String getValidatorLog() {
		// TODO Auto-generated method stub
		String filePath = "/home/miseryyc/Jin/log.txt";
		StringBuilder log = new StringBuilder();
		try {
			File logFile = new File(filePath);
			if(!logFile.exists()) {
				return null;
			}
			BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
			String line;
			while((line = reader.readLine()) != null) {
				log.append(line);
				log.append("\n");
			}
			return log.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveFileUpload(FileUpload fileUpload) {
		// TODO Auto-generated method stub
		fileUploadDAO.saveFileUpload(fileUpload);
	}

	public FileUploadDAO getFileUploadDAO() {
		return fileUploadDAO;
	}

	public void setFileUploadDAO(FileUploadDAO fileUploadDAO) {
		this.fileUploadDAO = fileUploadDAO;
	}

	/**
	 * @return the runValidatorDAO
	 */
	public RunValidatorDAO getRunValidatorDAO() {
		return runValidatorDAO;
	}

	/**
	 * @param runValidatorDAO the runValidatorDAO to set
	 */
	public void setRunValidatorDAO(RunValidatorDAO runValidatorDAO) {
		this.runValidatorDAO = runValidatorDAO;
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
	public List<File> getDirFiles(String userName) {
		// TODO Auto-generated method stub
		List<User> users = userDAO.findUserByName(userName);
		if(users == null || users.size() == 0) {
			return null;
		}
		
		User user = users.get(0);
		
		List<RunValidator> validators = runValidatorDAO.findRunByTimeAndUser(user.getId());
		if(validators == null || validators.size() == 0) {
			return null;
		}
		
		RunValidator validator = validators.get(0);
		
		String rexPath = PropertiesConfig.getValue("OUT_PUT_PATH");
		
		File file = new File(rexPath + validator.getOutputPath());
		
		try {
			if(!file.exists() || !file.isDirectory()) {
				return null;
			}
			
			File [] files = file.listFiles();
			return Arrays.asList(files);
		}catch(Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
}

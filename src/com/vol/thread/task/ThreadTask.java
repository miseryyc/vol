/**
 * 
 */
package com.vol.thread.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.vol.dao.impl.RunValidatorDAO;
import com.vol.entity.RunValidator;


/**
 * @author miseryyc
 *
 */
public class ThreadTask implements Runnable {
	private String dataFilePath;
	private String dataFileName;
	private RunValidator runValidator;
	private RunValidatorDAO runValidatorDAO;
	
	public ThreadTask(String dataFilePath, String dataFileName, RunValidator runValidator, RunValidatorDAO runValidatorDAO) {
		this.dataFilePath = dataFilePath;
		this.dataFileName = dataFileName;
		this.runValidator = runValidator;
		this.runValidatorDAO = runValidatorDAO;
	}
	
	@Override
	public void run(){
		try {
			String cmds[] = new String[] {"/home/miseryyc/Desktop/test.sh",
					dataFilePath, dataFileName};
			
			Runtime.getRuntime().exec(cmds);	 
			while(true) {
				if(getPath() != null) {
					synchronized(runValidator) {
						this.runValidator.setOutputPath(getPath());
						this.runValidatorDAO.saveRunValidator(runValidator);
						break;
					}
				}else {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getPath() {
		String filePath = "/home/miseryyc/Jin/log.txt";
		try {
			File logFile = new File(filePath);
			if(!logFile.exists()) {
				return null;
			}
			BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
			String line;
			while((line = reader.readLine()) != null) {
				if(line.contains("output_directory")) {
					String [] strs = line.split(" ");
					for(String str : strs) {
						if(str.contains("output/")) {
							return str;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

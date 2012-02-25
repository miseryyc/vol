package com.vol.service;

import java.io.File;
import java.util.List;

import com.vol.entity.FileUpload;
import com.vol.entity.RunValidator;

public interface RunValidatorService {
	
	void runValidator(String filePath, String dataFileName);
	String getValidatorLog();
	void saveFileUpload(FileUpload fileUpload);
	List<File> getDirFiles(String userName);
}

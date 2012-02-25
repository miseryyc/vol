package com.vol.dao;

import java.util.List;

import com.vol.entity.FileUpload;

public interface IFileUploadDAO {

	void saveFileUpload(FileUpload fileUpload);
	List<FileUpload> findFileUpload();
}

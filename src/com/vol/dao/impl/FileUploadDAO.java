/**
 * 
 */
package com.vol.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vol.dao.IFileUploadDAO;
import com.vol.entity.FileUpload;

/**
 * @author miseryyc
 *
 */
public class FileUploadDAO extends HibernateDaoSupport implements IFileUploadDAO{

	@Override
	public void saveFileUpload(FileUpload fileUpload) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(fileUpload);
	}

	@Override
	public List<FileUpload> findFileUpload() {
		// TODO Auto-generated method stub
		String querySql = "select id, name, path, save_path, save_time from t_upload_file";
		List<FileUpload> files = this.getHibernateTemplate().find(querySql);
		return files;
	}
	
	

}

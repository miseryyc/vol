/**
 * 
 */
package com.vol.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vol.dao.IRunValidatorDAO;
import com.vol.entity.RunValidator;

/**
 * @author miseryyc
 *
 */
public class RunValidatorDAO extends HibernateDaoSupport 
	implements IRunValidatorDAO{

	@Override
	public void saveRunValidator(RunValidator runValdator) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(runValdator);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RunValidator> findRunByTimeAndUser(int userId) {
		// TODO Auto-generated method stub
		String findSql = "select id, userId, runTime, outputPath from RunValidator where userId = '"+ userId +"' order by id desc";
		List<Object> results = this.getHibernateTemplate().find(findSql);
		return this.convertToRunValidator(results);
	}
	
	private List<RunValidator> convertToRunValidator(List<Object> results) {
		List<RunValidator> validators = new ArrayList<RunValidator>();
		for(Object result : results) {
			RunValidator validator = new RunValidator();
			Object [] obj = (Object[])result;
			validator.setId((Integer)obj[0]);
			validator.setUserId((Integer)obj[1]);
			validator.setRunTime((Timestamp)obj[2]);
			validator.setOutputPath((String)obj[3]);
			validators.add(validator);
		}
		return validators;
	}
}

/**
 * 
 */
package com.vol.dao;

import java.util.List;

import com.vol.entity.RunValidator;

/**
 * @author miseryyc
 *
 */
public interface IRunValidatorDAO {
	void saveRunValidator(RunValidator runValidator);
	List<RunValidator> findRunByTimeAndUser(int userId);
}

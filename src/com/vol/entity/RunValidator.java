/**
 * 
 */
package com.vol.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author miseryyc
 *
 */
public class RunValidator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3108243654449009329L;
	private int id;
	private int userId;
	private Timestamp runTime;
	private String outputPath;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the runTime
	 */
	public Timestamp getRunTime() {
		return runTime;
	}
	/**
	 * @param runTime the runTime to set
	 */
	public void setRunTime(Timestamp runTime) {
		this.runTime = runTime;
	}
	/**
	 * @return the outputPath
	 */
	public String getOutputPath() {
		return outputPath;
	}
	/**
	 * @param outputPath the outputPath to set
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
}

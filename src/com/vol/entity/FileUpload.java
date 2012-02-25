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
public class FileUpload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7861921394481692010L;

	private String name;
	private int id;
	private String path;
	private String saveName;
	private Timestamp saveTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public Timestamp getSaveTime() {
		return saveTime;
	}
	public void setSaveTime(Timestamp saveTime) {
		this.saveTime = saveTime;
	}
}

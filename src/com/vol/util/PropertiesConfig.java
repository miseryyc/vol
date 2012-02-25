/**
 * 
 */
package com.vol.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

/**
 * @author miseryyc
 *
 */
public class PropertiesConfig {

	public static String getValue(String key){
		FileInputStream in;
		String value = "";
		try{
			in = new FileInputStream(ServletActionContext.getServletContext()
					.getRealPath("/WEB-INF/classes/resConfig.properties"));
			Properties p = new Properties();
			p.load(in);
			in.close();
			value = p.getProperty(key);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return value;
	}
}

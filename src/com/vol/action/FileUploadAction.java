/**
 * 
 */
package com.vol.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.vol.beans.FileUploadPath;
import com.vol.entity.FileUpload;
import com.vol.service.RunValidatorService;
import com.vol.util.JsonUtil;

/**
 * @author miseryyc
 *
 */
public class FileUploadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int BUFFER_SIZE = 2 * 1024;

	private int id = -1;

	private File upload;
	private String name;
	private List<String> names;
	private String uploadFileName;
	private String uploadContentType;
	private int chunk;
	private int chunks;
	private RunValidatorService runValidatorService;
	
	private String result;

	private void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			if (dst.exists()) {
				out = new BufferedOutputStream(new FileOutputStream(dst, true),
						BUFFER_SIZE);
			} else {
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
			}
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);

			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String upload() throws Exception {
		String dstPath = ServletActionContext.getServletContext().getRealPath(
				this.getSavePath())
				+ "/" + this.getName();
		File dstFile = new File(dstPath);

		// 文件已存在（上传了同名的文件）
		if (chunk == 0 && dstFile.exists()) {
			dstFile.delete();
			dstFile = new File(dstPath);
		}

		copy(this.upload, dstFile);
		System.out.println("upload file:" + uploadFileName + "temp file：" + uploadContentType + " "
				+ chunk + " " + chunks);
		if (chunk == chunks - 1) {
			// 完成一整个文件;
		}

		return SUCCESS;
	}

	public String submit() throws IOException {
		String filePath = ServletActionContext.getServletContext().getRealPath(
				this.getSavePath());
		System.out.println("file save path： " + filePath);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		result =  ""; 	
		int count = Integer.parseInt(request.getParameter("uploader_count"));
		
		boolean valid = false;
		
		if(count != 1) valid = true;
		for (int i = 0; i < count; i++) {
			uploadFileName = request.getParameter("uploader_" + i + "_name");
			name = request.getParameter("uploader_" + i + "_tmpname");
			System.out.println(uploadFileName + " " + name);
			
			try {
				//do something with file;
				result += uploadFileName + "loading finished. <br />";  
			} catch(Exception e) {
				result += uploadFileName + "loading failed:" + e.getMessage() + ". <br />";
				e.printStackTrace();
			}
		}

		if(valid == true) {
			//log = "please upload a dat file and txt file";
			return ERROR;
		}
		
		FileUpload fileUpload = new FileUpload();
		fileUpload.setName(uploadFileName);
		fileUpload.setSaveName(name);
		fileUpload.setPath(filePath);
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		fileUpload.setSaveTime(now);
		
		runValidatorService.saveFileUpload(fileUpload);
		runValidatorService.runValidator(filePath, name);
		return SUCCESS;
	}
	
	public void getValidatorLog() throws IOException {
		String log = runValidatorService.getValidatorLog();
		if(log == null) {
			log = "fail";
		}
		HttpServletResponse response = ServletActionContext.getResponse();      
        response.setCharacterEncoding("UTF-8");   
        response.setContentType("text/html");
		response.getWriter().write(log); 
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public List<String> getNames() {
		return names;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getSavePath() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		FileUploadPath fileUploadPath = (FileUploadPath)context.getBean("fileUploadPath");
		return fileUploadPath.getPath();
	}

	public int getChunk() {
		return chunk;
	}

	public void setChunk(int chunk) {
		this.chunk = chunk;
	}

	public int getChunks() {
		return chunks;
	}

	public void setChunks(int chunks) {
		this.chunks = chunks;
	}


	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public RunValidatorService getRunValidatorService() {
		return runValidatorService;
	}

	public void setRunValidatorService(RunValidatorService runValidatorService) {
		this.runValidatorService = runValidatorService;
	}
}

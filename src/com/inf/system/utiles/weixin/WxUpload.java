package com.inf.system.utiles.weixin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.inf.system.utiles.PropertyConfUtils;

/**
 * 文件下载
 * @author zl
 *
 */
public class WxUpload {
	private String savePath, saveName, fileName, extensionName, allows,error;
	
	private long maxSize;
	
	private HttpServletRequest request;
	
	public WxUpload (HttpServletRequest request,String allows, long maxSize){
		this.request = request;
		this.allows = allows;
		this.maxSize = maxSize;
	}

	public String getSavePath() {
		return savePath;
	}

	public String getSaveName() {
		return saveName;
	}

	public String getExtensionName() {
		return extensionName;
	}
	
	public String getSaveFile(){
		return savePath + saveName + extensionName;
	}

	public String getFileName() {
		return fileName;
	}
	
	public String getError(){
		return error;
	}
	
	public synchronized boolean save(String fieldName){
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
		
		Iterator<String> iter = multipartRequest.getFileNames();
		while (iter.hasNext()) {
			// 取得上传文件
			MultipartFile files = multipartRequest.getFile(iter.next());
			if (files != null) {
				System.out.println(files.getOriginalFilename());
			}
		}
		MultipartFile file  =  multipartRequest.getFile(fieldName);
		return save(file);
	}

	public synchronized boolean save(String fieldName ,String savePath,String saveName){
		
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
		MultipartFile file  =  multipartRequest.getFile(fieldName);
		this.savePath = savePath;
		this.saveName = saveName;
		return save(file);
	}
	

	public synchronized boolean save(MultipartFile file){
		// String savePath = "/upload/files/" + Calendar.getInstance().get(Calendar.YEAR) + "/";
		String savePath =PropertyConfUtils.getProperty("user.header.path").toString()+ "/";
		//String saveName = "" + System.currentTimeMillis();
		
		Date date = new Date();// 获取当前时间
		SimpleDateFormat sdfFileName = new SimpleDateFormat("yyyyMMddHHmmss");
		String newfileName = sdfFileName.format(date);
		String saveName = newfileName + UUID.randomUUID().toString();
		
		return this.save(file,savePath,saveName);
	}
	
	public synchronized boolean save(MultipartFile file ,String savePath,String saveName){
		this.savePath = savePath;
		this.saveName = saveName;
		if(null != file && file.isEmpty()==false ){
			//文件名
			String fileName = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
			this.fileName=(fileName);
			
			//扩展名
			String extensionName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
			this.extensionName=("."+extensionName);
			
			if(("".equals(allows) || allows != null ) == false && this.allows.toLowerCase().contains(extensionName.toLowerCase()) == false){
				error += ",标题图片不符合要求:"+allows;
			}
			
			long size = file.getSize();
			if(size>this.maxSize){
				error += ",标题图片不能大于"+ Math.floor(maxSize/1024/1024)+"MB";
			}
			
            //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\savePath\\文件夹中  
          //  String realPath = request.getSession().getServletContext().getRealPath(this.getSavePath());
           // File targetFile = new File(realPath, this.saveName +"."+extensionName);
			File targetFile = new File(this.saveName +"."+extensionName);
    		if (!targetFile.exists()) {
    			targetFile.mkdirs();
    		}
    		// 保存
    		try {
				file.transferTo(targetFile);
				
				if("".equals(allows) || allows != null ){
	    			return true;
	    		} else {
	    			return false;
	    		}
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return false;
		
	}
	
}

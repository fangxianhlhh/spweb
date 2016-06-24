package com.inf.system.utiles;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


/**
 * 文件上传指定目录下，视屏，音频，文件,图片
 * @author Administrator
 *
 */
public class FileUploadUtil {

	//文件上传
	public static  String uploadFiles(HttpServletRequest request) throws IllegalStateException, Exception{

		String jsonString = "";
		List<FileUploadUtil.rebakFile> fileList=new ArrayList<FileUploadUtil.rebakFile>();

		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						// System.out.println(myFileName);
						// 重命名上传后的文件名
						Date date = new Date();// 获取当前时间
						SimpleDateFormat sdfFileName = new SimpleDateFormat("yyyyMMddHHmmss");
						String newfileName = sdfFileName.format(date);

						String originalFileName = file.getOriginalFilename().trim();
						if(originalFileName.indexOf(",")>=0){
							originalFileName=originalFileName.replace(",", "");
						}
						if(originalFileName.indexOf("，")>=0){
							originalFileName=originalFileName.replace("，", "");
						}
						//获取后缀名
						String suffixNmae=originalFileName.substring(originalFileName.lastIndexOf(".")+1,originalFileName.length());

						//新命名文件名
						String fileName = newfileName + UUID.randomUUID().toString()+"."+suffixNmae;

						//获取商专文件的地址和别名，类型
						Map<String, String> fileObj=getUploadPath(originalFileName);

						String  uploadPath=fileObj.get("uploadFile");

						if(StringUtils.isNotBlank(uploadPath)){

							//别名
							String 	uploadUrl=fileObj.get("uploadUrl");

							// 定义上传路径
							String path = uploadPath +"/"+ fileName;

							File localFile = new File(path);

							String fileu=String.format("%s%s",
									request.getContextPath(),
									uploadUrl
									);

							String fileUrl=path.replace(uploadPath, fileu);

							file.transferTo(localFile);

							FileUploadUtil.rebakFile refFile=getRebakFile();

							refFile.setFileName(originalFileName);
							refFile.setFileurl(fileUrl);

							fileList.add(refFile);

						}
					}
				}
			}
			Map<String, Object> jsonMap= new HashMap<String, Object>();
			jsonMap.put("message", "服务端处理正常");
			jsonMap.put("success", true);
			jsonMap.put("filePath", fileList);

			jsonString = JsonBean.toJson(jsonMap);
		}
		return jsonString;
	}



	//根据文件后缀名获取文件的上传路径，和别名
	public static Map<String,String> getUploadPath(String fileName){

		//获取后缀名
		String fileSuffer=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());

		String uploadPath=null;
		String uploadUrl=null;
		Integer encloType=0;

		String videoSuffer=PropertyConfUtils.getProperty(Constant.VIDEO_SUFFER);
		String audioSuffer=PropertyConfUtils.getProperty(Constant.AUDIO_SUFFER);
		String docSuffer=PropertyConfUtils.getProperty(Constant.DOC_SUFFER);
		String imgSuffer=PropertyConfUtils.getProperty(Constant.IMG_SUFFER);

		//视频
		if(videoSuffer.indexOf(fileSuffer.toLowerCase())>=0){
			uploadPath=PropertyConfUtils.getProperty(Constant.UPLOAD_VIDEO_PATH);
			uploadUrl=PropertyConfUtils.getProperty(Constant.UPLOAD_VIDEO_URL);
			encloType=Constant.ENCLO_MANG_TYPE_SP;
		}

		//音频
		if(audioSuffer.indexOf(fileSuffer.toLowerCase())>=0){
			uploadPath=PropertyConfUtils.getProperty(Constant.UPLOAD_AUDIO_PATH);
			uploadUrl=PropertyConfUtils.getProperty(Constant.UPLOAD_AUDIO_URL);
			encloType=Constant.ENCLO_MANG_TYPE_YP;
		}

		//文件
		if(docSuffer.indexOf(fileSuffer.toLowerCase())>=0){
			uploadPath=PropertyConfUtils.getProperty(Constant.UPLOAD_DOC_PATH);
			uploadUrl=PropertyConfUtils.getProperty(Constant.UPLOAD_DOC_URL);
			encloType=Constant.ENCLO_MANG_TYPE_WD;
		}
		//图片      
		if(imgSuffer.indexOf(fileSuffer.toLowerCase())>=0){
			uploadPath=PropertyConfUtils.getProperty(Constant.CASE_IMAGE_PATH);
			uploadUrl=PropertyConfUtils.getProperty(Constant.CASE_IMAGE_URL);
			encloType=Constant.ENCLO_MANG_TYPE_TP;
		}

		File uploadFile = new File(uploadPath);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		Map<String, String> map=new HashMap<String, String>();

		map.put("uploadFile", uploadPath);
		map.put("uploadUrl", uploadUrl);
		map.put("encloType", String.valueOf(encloType));

		return map;
	}

	public static rebakFile getRebakFile() { 
		return new rebakFile(); 
	} 

	
	//多文件上传返回结果
	public static  class  rebakFile{
		String fileName;  //文件名称
		String fileurl;  //文件地址
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFileurl() {
			return fileurl;
		}
		public void setFileurl(String fileurl) {
			this.fileurl = fileurl;
		}


	}



	//删除文件
	public static Boolean distoryFiles(HttpServletRequest request){

		String fileName=request.getParameter("fileName");
		if(StringUtils.isNotBlank(fileName)){
			//根据文件名获取文件的上传路径和别名地址
			Map<String, String> fileObj=getUploadPath(fileName);
			if(!CollectionUtils.isEmpty(fileObj)){

				String fileAddress=fileObj.get("uploadFile")+"\\"+fileName;

				File file = new File(fileAddress);
				// 判断目录或文件是否存在  
				if (!file.exists()) {  // 不存在返回 true  
					return true;  
				} else {  
					// 判断是否为文件  
					if (file.isFile()) {  // 为文件时调用删除文件方法  
						return deleteFile(fileAddress);  
					} 
				}  

			}

		}
		return true;
	}



	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {  
		boolean     flag = false;  
		File    file = new File(sPath);  
		// 路径为文件且不为空则进行删除  
		if (file.isFile() && file.exists()) {  
			file.delete();  
			flag = true;  
		}  
		return flag;  
	}  


	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public boolean deleteDirectory(String sPath) {  
		//如果sPath不以文件分隔符结尾，自动添加文件分隔符  
		if (!sPath.endsWith(File.separator)) {  
			sPath = sPath + File.separator;  
		}  
		File dirFile = new File(sPath);  
		//如果dir对应的文件不存在，或者不是一个目录，则退出  
		if (!dirFile.exists() || !dirFile.isDirectory()) {  
			return false;  
		}  
		boolean     flag = true;  
		//删除文件夹下的所有文件(包括子目录)  
		File[] files = dirFile.listFiles();  
		for (int i = 0; i < files.length; i++) {  
			//删除子文件  
			if (files[i].isFile()) {  
				flag = deleteFile(files[i].getAbsolutePath());  
				if (!flag) break;  
			} //删除子目录  
			else {  
				flag = deleteDirectory(files[i].getAbsolutePath());  
				if (!flag) break;  
			}  
		}  
		if (!flag) return false;  
		//删除当前目录  
		if (dirFile.delete()) {  
			return true;  
		} else {  
			return false;  
		}  
	}  
	
	
	/**
	 * 转换文件大小
	 * @param fileS
	 * @return
	 */
	public static String formetFileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

}

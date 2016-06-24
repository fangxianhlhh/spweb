/*
 * 文 件 名:  FaceImgServlet.java
 * 版    权:  Ysten Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  bo.chen
 * 修改时间:  2015年7月15日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.inf.base.system.aop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * <文件访问Servlet>
 */
public class FileServlet extends HttpServlet
{
    
    private static final long serialVersionUID = -1305670105049727873L;
    
    private static final Log logger = LogFactory.getLog(FileServlet.class);
    
    private static Properties props;
    
    static
    {
        try
        {
            props = PropertiesLoaderUtils.loadAllProperties("config.properties");
        }
        catch (IOException e)
        {
            logger.error("init properties error!",e);
        }
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
    	String requestUri=request.getRequestURI().trim();
    	
    	//案例图片
    //	String imagepath=String.valueOf(props.get("upload.image.path"));
    	//String imagUrl=String.valueOf(props.get("file.image.url"));
    	//用户图像
    //	String headPath=String.valueOf(props.get("user.header.path"));
    //	String headerUrl=String.valueOf(props.get("header.image.url"));
    	// 用户名片
    //	String cardPath=String.valueOf(props.get("user.card.path"));
    //	String cardUrl=String.valueOf(props.get("card.image.url"));
    	
    	String voidpath=String.valueOf(props.get("codes.void.path"));
    	String voidurl=String.valueOf(props.get("file.codes.void.url"));
    	 // 获取文件绝对路径
        String realPath = null;
        
        	if(requestUri.indexOf(voidurl)>=0){
        		realPath=voidpath + requestUri.replaceFirst(request.getContextPath()+ voidurl,StringUtils.EMPTY);
        	}
        	/*else if(requestUri.indexOf(headerUrl)>=0){
        		realPath=headPath + requestUri.replaceFirst(request.getContextPath()+ headerUrl,StringUtils.EMPTY);
        	}else if(requestUri.indexOf(cardUrl)>=0){
        		realPath=cardPath + requestUri.replaceFirst(request.getContextPath()+ cardUrl,StringUtils.EMPTY);
        	}*/
        	else{
        		return;
        	}
       
           /* String.valueOf(props.get("upload.image.path"))
                + requestUri.replaceFirst(request.getContextPath()
                + String.valueOf(props.get("file.image.url")),StringUtils.EMPTY);*/
    	
        File file = new File(realPath);
        // 判断文件是否存在如果不存在就返回默认图标
        if (!(file.exists() && file.canRead()))
        {
            return;
        }
        
        try
        {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[(int)file.length()];
           // int size= inputStream.read(data);
            logger.debug("file size is {} bytes");
            inputStream.close();
           // response.setContentType("image/jpeg");
            response.setContentType("video/x-flv");
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        }
        catch (FileNotFoundException e)
        {
            logger.error("FileNotFoundException", e);
        }
        catch (IOException e)
        {
            logger.error("IOException", e);
        }
        
    }
}

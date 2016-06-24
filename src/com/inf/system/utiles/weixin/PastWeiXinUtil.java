package com.inf.system.utiles.weixin;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.lang.jstl.parser.Token;

import com.inf.system.utiles.PropertyUtils;

public class PastWeiXinUtil {
    public static Token token = null;
    public static String time = null;
    public static String jsapi_ticket = null;
    /**
     * 
     * @param appId   公账号appId
     * @param appSecret
     * @param url    当前网页的URL，不包含#及其后面部分
     * @return
     */
    public static Map<String, String> getParam(HttpServletRequest request){
       
    	String   appId=PropertyUtils.getProperty("appId").toString();
    	String   appSecret=PropertyUtils.getProperty("appsecret").toString();
         
        String url = getUrl(request);
        jsapi_ticket=WeixinUtil.getTickets(appId,appSecret);
        Map<String, String> params = sign(jsapi_ticket, url);
        params.put("appid", appId);
         
        return params;
    }
     
    private static String getUrl(HttpServletRequest request){
      
        StringBuffer requestUrl = request.getRequestURL();
         
        String queryString = request.getQueryString();
        
        String url = requestUrl.toString();
       
        if(StringUtils.isNotBlank(queryString)){
        	 url = requestUrl +"?"+queryString;
        }
        
        return url;
    }
     
    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String str;
        String signature = "";
 
        //注意这里参数名必须全部小写，且必须有序
        str = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
 
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
 
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
 
        return ret;
    }
 
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
 
    
    //产生随机字符串
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }
 
    //获取当前时间
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
     
    //获取当前系统时间 用来判断access_token是否过期
    public static String getTime(){
        Date dt=new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }
}
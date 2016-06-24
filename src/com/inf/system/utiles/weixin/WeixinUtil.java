package com.inf.system.utiles.weixin;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 微信公众号图片上传和下载
 */
public class WeixinUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeixinUtil.class);
	/**
	 * 获取接口访问凭证
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static String getAccess_token(String appid, String appsecret) {

		String access_token = "";
		String time="";
		String mapaccesstoken="";

		WxinTokenSingleton singleton = WxinTokenSingleton.getInstance();

		Map<String, String> map = singleton.getMap();

		if(!CollectionUtils.isEmpty(map)){
			time = map.get("time");

			mapaccesstoken = map.get("access_token");
		}
		Long nowDate =System.currentTimeMillis();

		//这里设置过期时间 7000*1000就好了   有效期 2小时
		if (StringUtils.isNotBlank(mapaccesstoken) && StringUtils.isNotBlank(time) && nowDate - Long.parseLong(time) < 7000 * 1000){
			access_token = mapaccesstoken;
		} else {
			//凭证获取(GET)
			String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
			String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);

			// 发起GET请求获取凭证
			JSONObject jsonObject = httpsRequest(requestUrl, "UTF-8");
			if (null != jsonObject) {
				try {
					access_token = jsonObject.getString("access_token");
					map.put("time", nowDate + "");
					map.put("access_token", access_token);
				} catch (JSONException e) {
					// 获取token失败
					LOGGER.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
				}
			}
		}
		return access_token;
	}



	/**
	 * 调用微信JS接口的临时票据
	 * 
	 * @param access_token 接口访问凭证
	 * @return
	 */
	public static String getJsApiTicket(String access_token) {
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		String requestUrl = url.replace("ACCESS_TOKEN", access_token);
		// 发起GET请求获取凭证
		JSONObject jsonObject = httpsRequest(requestUrl, "UTF-8");
		String ticket = null;
		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
			} catch (JSONException e) {
				// 获取token失败
			LOGGER.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		System.out.println(ticket);
		return ticket;
	}
	
	
	//http访问 返回 json
	public static JSONObject httpsRequest(String urlAll, String charset) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		try {
			URL url = new URL(urlAll);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(30000);
			connection.setConnectTimeout(30000);
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, charset));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.fromObject(result);
	}



	// 获取tickets
	public static  String getTickets(String appid, String appsecret){
		String 	access_token=getAccess_token(appid, appsecret);
		String ticket =getJsApiTicket(access_token);
		return  ticket;
	}

	
	
	//图片上传
	@SuppressWarnings("rawtypes")
	public static String UploadImg(String appid, String appsecret, Map<String, String> textMap, Map<String, String> fileMap)
	{
		String accessToken = getAccess_token(appid,appsecret);

		String postUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + accessToken + "&type=image";

		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "----------参数分隔";
		try {
			URL url = new URL(postUrl);
			conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());

			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry)iter.next();
					String inputName = (String)entry.getKey();
					String inputValue = (String)entry.getValue();

					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}

			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry)iter.next();
					String inputName = (String)entry.getKey();
					String inputValue = (String)entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();
					String contentType = new MimetypesFileTypeMap().getContentType(file);

					if (filename.endsWith(".png")) {
						contentType = "image/png";
					}
					if ((contentType == null) || (contentType.equals(""))) {
						contentType = "application/octet-stream";
					}
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
					out.write(strBuf.toString().getBytes());
					DataInputStream in = new DataInputStream(new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + postUrl);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}


	//图片下载
	public static String downloadImg(String mediaId, String access_token, String filePath)
	{
		String result="";
		String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + access_token + "&media_id=" + mediaId;
		try
		{
			URL urlGet = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)urlGet.openConnection();
			conn.setRequestMethod("GET");
			// conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setDoOutput(true);
			conn.setDoInput(true);

			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");

			//设置请求方式为"GET"  
	        conn.setRequestMethod("GET");  
	        //超时响应时间为5秒  
	        conn.setConnectTimeout(5 * 1000); 
	        
			conn.connect();

			Date time = new Date();// 获取当前时间
			SimpleDateFormat sdfFileName = new SimpleDateFormat("yyyyMMddHHmmss");
			String newfileName = sdfFileName.format(time);
			String saveName = newfileName + UUID.randomUUID().toString();

			String fileExt =getFileEndWitsh(conn.getHeaderField("Content-Type"));

			filePath=filePath+"/"+saveName+fileExt;
			

			//byte[] data = new byte[2056];
			FileOutputStream fileOutputStream = null;
			
			//通过输入流获取图片数据  
	        InputStream inStream = conn.getInputStream();  
	        //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
	        byte[] data = readInputStream(inStream);  
	        //new一个文件对象用来保存图片，默认保存当前工程根目录  
	        File imageFile = new File(filePath);  
	        //创建输出流  
	         fileOutputStream = new FileOutputStream(imageFile);  
	         System.out.println("写入图片");
	        //写入数据  
	        fileOutputStream.write(data);  
	        fileOutputStream.close();
	        
	        result=saveName+fileExt;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error------"+e);
		}
			
		return result;
	}

	public static String getFileEndWitsh(String contentType)
	{
		String fileEndWitsh = "";
		if ("image/jpeg".equals(contentType))
			fileEndWitsh = ".jpg";
		else if("image/gif".endsWith(contentType))
			fileEndWitsh = ".gif";
		else if ("audio/mpeg".equals(contentType))
			fileEndWitsh = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileEndWitsh = ".amr";
		else if ("video/mp4".equals(contentType))
			fileEndWitsh = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileEndWitsh = ".mp4";

		return fileEndWitsh;
	}
	
	
	public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }

	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		WeixinUtil uxUserWeixin=new WeixinUtil();

		String appid="wxef6d3baa73ed6008";
		String  appsecret="942babfcef4d52cd64be3a1533990558";
		String access_token=uxUserWeixin.getAccess_token(appid, appsecret);
		System.out.println("-----"+access_token);
		uxUserWeixin.getJsApiTicket(access_token);
	} 	


	
}

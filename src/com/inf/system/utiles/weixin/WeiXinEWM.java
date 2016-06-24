package com.inf.system.utiles.weixin;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class WeiXinEWM{
	
	/**
	 * 生成二维码并下载到服务器
	 * @throws Exception 
	 */
	@SuppressWarnings({ "deprecation", "static-access", "unused" })
	public static void uploadWXcode(HttpServletRequest request,String docId) throws Exception {
		//--------------------以下获取access_token--------------------
		String AppID = "wx9d879345d6ed29e2";
		String AppSecret = "078aa9b09b96a46a1c2bf99e1a0aa0de";
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ AppID +"&secret="+AppSecret;
		System.out.println("33333333333333333333333333333");
		String jsonstring = request(url,"UTF-8");
		JSONObject jsonObject = null;
		jsonObject = JSONObject.fromObject(new String(jsonstring));
		String access_token = jsonObject.getString("access_token");
		
		
		//--------------------以下获取ticket--------------------
		String url2 = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+access_token;
		HttpClient client2 = new HttpClient();
		PostMethod method2 = new PostMethod(url2);//post请求
		String body = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \""+ docId +"\"}}}";
		method2.setRequestBody(body);
		method2.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
		System.out.println("4444444444444444444444444444444");
		int statusCode2 = client2.executeMethod(method2);
		byte[] responseBody2 = method2.getResponseBody();
		jsonObject = jsonObject.fromObject(new String(responseBody2));
		String ticket = jsonObject.getString("ticket");
		
		String wxUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;//(二维码图片src路径)
		// String uploadPath = "yjq\\upload\\app\\"; // 上传文件的目录
		String uploadPath = "yjq" + File.separator + "upload" + File.separator + "app" + File.separator; // 上传文件的目录
		File uploadFile = new File(uploadPath);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		System.out.println("55555555555555"+wxUrl);
        URL wxUrl2 = new URL(wxUrl);  
        //打开链接  
        HttpURLConnection conn = (HttpURLConnection)wxUrl2.openConnection();  
        //设置请求方式为"GET"  
        conn.setRequestMethod("GET");  
        //超时响应时间为5秒  
        conn.setConnectTimeout(5 * 1000);  
        //通过输入流获取图片数据  
        InputStream inStream = conn.getInputStream();  
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
        byte[] data = readInputStream(inStream);  
        //new一个文件对象用来保存图片，默认保存当前工程根目录  
        File imageFile = new File(request.getSession().getServletContext().getRealPath(File.separator) +  uploadPath + docId + ".jpg");  
        //创建输出流  
        FileOutputStream outStream = new FileOutputStream(imageFile);  
        System.out.println("66666666666666666666666666666"+wxUrl);
        System.out.println("写入图片");
        //写入数据  
        outStream.write(data);
        outStream.close();
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
	
	/**
	 * @param urlAll :请求接口
	 * @param charset :字符编码
	 * @return 返回json结果
	 */
	public static String request(String urlAll, String charset) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";// 模拟浏览器
		try {
			URL url = new URL(urlAll);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(30000);
			connection.setConnectTimeout(30000);
			connection.setRequestProperty("User-agent", userAgent);
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
		return result;
	}

	public static void main(String[] args) {

	}

}

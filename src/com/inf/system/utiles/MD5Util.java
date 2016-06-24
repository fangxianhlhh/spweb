package com.inf.system.utiles;


import java.security.MessageDigest;  
import java.text.DecimalFormat;
  
/**
 * MD5加解密  
 * @author zongxin
 * 下午1:40:58
 */
public class MD5Util {  
    /*** 
     * MD5加码 生成32位md5码 
     * @throws Exception 
     */  
    public static String convertMD5(String desStr) throws Exception{  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = desStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }  
    
    public static void main(String[] args) throws Exception {
//		System.out.println(MD5Util.convertMD5("123456"));
		String s = "0.8394";
		Double fristRate = Double.parseDouble(s);
		DecimalFormat df = new DecimalFormat("0.00%");
		System.out.println(df.format(fristRate));
	}
}  

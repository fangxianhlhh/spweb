package com.inf.system.utiles;
import javax.crypto.Cipher;   
import javax.crypto.SecretKey;   
import javax.crypto.SecretKeyFactory;   
import javax.crypto.spec.DESKeySpec;   
import javax.crypto.spec.IvParameterSpec;   


/**
 * 数据加解密
 * @author zongxin
 * 上午9:44:23
 */
public class DesEncrypt {
	

	/**
     * 解密方式   
     * @param message
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt(String message,String key) throws Exception {   
		byte[] bytesrc =convertHexString(message);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");       
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));      
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");      
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);      
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));   
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);         
		byte[] retByte = cipher.doFinal(bytesrc);        
		return new String(retByte);    
    }   
   
    
    /**
     * 加密方式
     * @param message
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(String message, String key) throws Exception {   
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");   
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));   
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");   
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);   
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));   
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);   
        return cipher.doFinal(message.getBytes("UTF-8"));   
    }   
        
    
    /**
     * 十六进制转十进制
     * @param ss
     * @return
     */
    public static byte[] convertHexString(String ss) {    
	    byte digest[] = new byte[ss.length() / 2];    
	    for(int i = 0; i < digest.length; i++) {    
	    String byteString = ss.substring(2 * i, 2 * i + 2);    
	    int byteValue = Integer.parseInt(byteString, 16);    
	    digest[i] = (byte)byteValue;    
	    }    
	    return digest;    
    }    
    
    
    /**
     * 十进制转十六进制
     * @param b
     * @return
     */
    public static String toHexString(byte b[]) {   
        StringBuffer hexString = new StringBuffer();   
        for (int i = 0; i < b.length; i++) {   
            String plainText = Integer.toHexString(0xff & b[i]);   
            if (plainText.length() < 2)   
                plainText = "0" + plainText;   
            hexString.append(plainText);   
        }   
        return hexString.toString();   
    }
    
    
    /**
     * 获取解密后数据
     * @param message
     * @param key
     * @return
     */
	public static String getDecrypt(String message,String key)throws Exception {
			return java.net.URLDecoder.decode(decrypt(message, key),"utf-8");
    }
    
    
    /**
     * 获取加密后数据
     * @param str
     * @return
     * @throws Exception 
     */
    public static String getEncrypt(String message,String key) throws Exception {
    	String JsonString = null;
    	try {
    		String enCodeString = java.net.URLEncoder.encode(message, "utf-8");
    		JsonString = toHexString(encrypt(enCodeString, key)).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonString;
    }
   
}  
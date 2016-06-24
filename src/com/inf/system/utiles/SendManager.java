package com.inf.system.utiles;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.bcloud.msg.http.HttpSender;

/**
 * 发送信息
 * @author Administrator
 *
 */
public class SendManager {
	
	// 发送手机验证码
		public static Integer sendVerifyCode(HttpServletRequest request,String userPhone){


			if(StringUtils.isBlank(userPhone)){
				return  Constant.SEND_YZM_ERROR;
			}

			String sendBefore=PropertyUtils.getProperty(Constant.SEND_CODE_BEFORE);

			String sendmesafter=PropertyUtils.getProperty(Constant.SEND_CODE_AFTER);

			Integer codeLength=Integer.valueOf(PropertyUtils.getProperty(Constant.CODE_SIZE));

			 //获取验证码
			String  code=MatchsUtil.getLengthNums(codeLength);
			

			String CodeContext=sendBefore+code+sendmesafter;
			
			String  timenow=String.valueOf(System.currentTimeMillis());
			
			System.out.println(CodeContext);

			// String count =	SendManager.send(userPhone, CodeContext);
			String count ="0";
			
			if(count.equals("0")){
				request.getSession().setAttribute(Constant.VERF_COD, code+','+timenow);

				return Constant.SEND_YZM_SUCCESS;
			}
			else {
				return Constant.SEND_YZM_ERROR;
			}
		}
		
		//发送手机验证码方法
		public static String send(String phone, String content) {
			String uri = PropertyUtils.getProperty(Constant.SEND_MANAGER_ADDRESS);//应用地址
			String account = PropertyUtils.getProperty(Constant.SEND_MANAGER_USERNAME);//账号
			String pswd = PropertyUtils.getProperty(Constant.SEND_MANAGER_PASSWD);//密码
			String mobiles = phone;//手机号码，多个号码使用","分割
			boolean needstatus = true;//是否需要状态报告，需要true，不需要false
			String product = null;//产品ID
			String extno = null;//扩展码
			String returnString = "";
			try {
				 returnString = HttpSender.batchSend(uri, account, pswd, mobiles, content, needstatus, product, extno);
				 
				 return returnString.substring(15, 16);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}

}

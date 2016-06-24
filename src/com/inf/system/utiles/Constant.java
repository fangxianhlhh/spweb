package com.inf.system.utiles;

public class Constant {
	
	/*----------------- 系统定义----------------------- */
	
	/*-------------------------------------默认的系统日期格式---------*/
	
	public static final String FORMAT_DATE="yyyy-MM-dd";
	public static final String FORMAT_DATE_TIME="yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_YEAR="yyyy";
	public static final String FORMAT_MONTH="MM";
	public static final String FORMAT_DAY="dd";
	public static final String FORMAT_YEAT_MONTH="yyyy-MM";
	public static final String FORMAT_CH_TIME="yyyy年MM月dd日 HH:mm";

	
	
	//默认分页长度
	public static final int PageSize = 20;
	
	public static final int PageSizeForStock = 30;
	
	
	public static  final String PAGE="page";
	public static final String ROWS="rows";
	//默认起始页
   public static  final int BEGIN_PAGE=1;
	
	
	public final static String DELETE_FALG_DELETED = "1";
	public final static String DELETE_FALG_EXISTED = "0";
	
	
	public final static String OPERA_SUCCESS = "true";
	public final static String OPERA_FAILED = "false";
	
	
	
	//返回常数结果
	public final static  int DEFAULT_RESULT_0=0;
	public final static  int DEFAULT_RESULT_1=1;
	
	//LONG 型常数
	public final static Long DEFAULT_LONG_0=0L;
	public final static Long DEFAULT_LONG_1=1L;
	public final static Long DEFAULT_LONG_2=2L;
	public final static Long DEFAULT_LONG_3=3L;
	public final static Long DEFAULT_LONG_4=4L;
	public final static Long DEFAULT_LONG_5=5L;
	
	
	//验证码发送结果 1 -发送成功  0- 失败
	public final static  Integer SEND_YZM_SUCCESS=1;
	public final static  Integer SEND_YZM_ERROR=0;
	
	//成功
	public final static  String  SUCCESS="1";	
	//失败
	public final static  String  ERROR="0";
	
	//超级管理员用户名称
	public  final static  String SYSTEN_USER_ROLENAME="超级管理员";
	
	
	//系统管理员登录
	public final static String SYSUSER_LOGIN_MANAGER="系统管理员登录";
	
	//code前缀
	public final static  String  VERF_COD="code";
	//发送验证码前缀
	public final static  String  SEND_CODE_BEFORE="send_code_before";
	// 发送验证码后缀
	public final static  String  SEND_CODE_AFTER="send_code_after";
	//验证码有效时间
	public final static  String  CODE_VALID_TIMES="code_valid_times";
	//验证码长度
	public final static  String  CODE_SIZE="code_size";
	
	//发送短信服务地址
	public final static String SEND_MANAGER_ADDRESS="send_manager_address";
	//发送短信账户
	public final static String SEND_MANAGER_USERNAME="send_manager_username";
	// 发送短信账户密码
	public final static String SEND_MANAGER_PASSWD="send_manager_passwd";
	
	
	
	//----------------用户的字段------
	public final static String USER_ID="userId";
	public final static String USER_LOGIN_NAME="loginName";
	public final static String USER_PHONE="phone";
	public final static String USER_PASSWORD="password";
	
	public final static String USER_ROLE_USERID="userId";
	
	//角色字段名称
	public final static String ROLE_SYS_ROLENAME="roleName";
	//角色id字段名称
	public final static String ROLE_SYS_ROLID="roleId";
	
	//图像验证码code
	public final static String  PIC_CODE_SESSION="CodeRandSession";
	
	//用户角色数量字段名称
	public final static String USER_ROLE_COUNT="roleCount";
	
	
	
	//系统操作说明字段
	 //用户模块
	 public final static String  USER_SYS_ADDUSER="添加管理员用户";
	 public final static String  USER_SYS_UPDATEUSER="修改管理员用户";
	 public final static String  USER_SYS_DELETEUSER="冻结管理员用户";
	 public final static String  USER_SYS_UPDATEPSWD="修改管理员密码";
	 
	 //系统资源
	 public final static String  RES_SYS_ADDRES="添加系统资源";
	 public final static String  RES_SYS_UPDATERES="修改系统资源";
	 public final static String  RES_SYS_DELETERES="删除系统资源";

	
	 //角色资源
	 public final static String  ROLE_SYS_ADDROLE="添加系统角色";
	 public final static String  ROLE_SYS_UPDATEROLE="修改系统角色";
	 public final static String  ROLE_SYS_DELETEROLE="修改系统角色";
	
	
		//user session key
		public final static  String  USER_SESSION="userSessionInfo";
		
		//图像路径  
		public final static  String CASE_IMAGE_PATH="upload.image.path";
		//图像别名
		public final static  String CASE_IMAGE_URL="file.image.url";
				
		//附件视屏路径
		public final static  String UPLOAD_VIDEO_PATH="upload.video.path";
		//附件视屏别名
		public final static  String UPLOAD_VIDEO_URL="upload.video.url";
		
		//附件音频路径
		public final static  String UPLOAD_AUDIO_PATH="upload.audio.path";
		//附件音频别名
		public final static  String UPLOAD_AUDIO_URL="upload.audio.url";
				
		//附件文件路径
		public final static  String UPLOAD_DOC_PATH="upload.doc.path";
		//附件文件别名
		public final static  String UPLOAD_DOC_URL="upload.doc.url";
		
		//视频后缀
		public final static String VIDEO_SUFFER="video.suffer";
		
		//音频后缀
		public final static String AUDIO_SUFFER="audio.suffer";
			
		//文件后缀
		public final static String DOC_SUFFER="doc.suffer";
		
		//图片后缀
		public final static String IMG_SUFFER="image.suffer";
		
	
		//附件类型 1-文档，2-音频，3-视频，4-图片
		public  final static Integer ENCLO_MANG_TYPE_WD=1;
		public  final static Integer ENCLO_MANG_TYPE_YP=2;
		public  final static Integer ENCLO_MANG_TYPE_SP=3;
		public  final static Integer ENCLO_MANG_TYPE_TP=4;

	
	


	
		

	

	
	

	

	

	

		
}


#初始化一个超级管理员角色
SET @roleId=UUID();
INSERT INTO `tb_sys_role`(`ROLE_ID`,`ROLE_NAME`,`REMARK_DESC`,`CREATE_TIME`)
 VALUES(@roleId,"超级管理员","系统超级管理员",NOW());

#初始化一个超级用户 密码 1111111
SET @userId=UUID();
INSERT INTO `tb_sys_user`(`USER_ID`,`LOGIN_NAME`,`PASSWORD`,`USER_NAME`,`PHONE`,`EMAIL`,`CREATE_TIME`,`DESCS`)
VALUES(@userId,'admin','7fa8282ad93047a4d6fe6111c93b308a','hh','18500316076','736775275@qq.com',NOW(),"超级管理员");

#初始化一个系统管理菜单
SET @resId=UUID();
SET @resId2=UUID();

SET @resUser=UUID();
SET @resrole=UUID();
SET @resres=UUID();

SET @resaction=UUID();
SET @reslog=UUID();

INSERT INTO `tb_sys_resource`(`RES_ID`,`RES_NAME`,`RES_TYPE`,`RES_URL`,`PAREND_RES_ID`,`RES_LEVEL`,`RES_ORDER`,`REMARK_DESC`,`CREATE_TIME`)
VALUES(@resId,"系统管理",1,"",NULL,1,1,"管理系统功能",NOW()),

	(@resUser,"管理员管理",1,"/spweb/sysusers/sysuser/sysUserList.jsp",@resId,2,1,"管理管理员",NOW()),
	(@resrole,"角色管理",1,"/spweb/sysusers/sysrole/sysRoleList.jsp",@resId,2,2,"管理角色",NOW()),
	(@resres,"资源管理",1,"/spweb/sysusers/resource/resourceList.jsp",@resId,2,3,"管理资源",NOW()),
      
      (@resId2,"日志管理",1,"",NULL,1,2,"管理系统相关日志",NOW()),
          (@resaction,"操作日志管理",1,"/spweb/log/systemLog/systemLogList.jsp",@resId2,2,1,"管理操作日志功能",NOW()),
          (@reslog,"登录日志管理",1,"/spweb/log/loginLog/loginLogList.jsp",@resId2,2,2,"管理登录日志功能",NOW());
      
#给用户赋角色
INSERT INTO `tb_user_role`(`TUR_ID`,`USER_ID`,`ROLE_ID`,`CREATE_USER`,`CREATE_TIME`)
                  VALUES(UUID(),@userId,@roleId,@userId,NOW());
       
#给角色赋权限
INSERT INTO `tb_sys_privilege`(`PRIV_ID`,`ROLE_ID`,`RES_ID`,`CREATE_USER`,`CREATE_TIME`,`REMARK_DESC`)
VALUES(UUID(),@roleId,@resId,@userId,NOW(),"管理系统功能"),(UUID(),@roleId,@resId2,@userId,NOW(),"管理系统日志"),
                  
                  (UUID(),@roleId,@resUser,@userId,NOW(),"管理系统用户"),(UUID(),@roleId,@resrole,@userId,NOW(),"管理系统角色"),
                  (UUID(),@roleId,@resres,@userId,NOW(),"管理系统资源"),
                  (UUID(),@roleId,@resaction,@userId,NOW(),"管理操作日志"),(UUID(),@roleId,@reslog,@userId,NOW(),"管理登录日志");
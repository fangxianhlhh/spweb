package com.inf.sys.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.inf.sys.user.mapper.TbSysActionLogMapper;
import com.inf.sys.user.mapper.TbSysLoginLogMapper;
import com.inf.sys.user.mapper.TbSysRoleMapper;
import com.inf.sys.user.mapper.TbSysUserMapper;
import com.inf.sys.user.model.Menu;
import com.inf.sys.user.model.SessionSysUser;
import com.inf.sys.user.model.TbSysLoginLog;
import com.inf.sys.user.model.TbSysUser;
import com.inf.sys.user.model.TbUserRole;
import com.inf.sys.user.service.TbSysUserService;
import com.inf.system.utiles.Constant;
import com.inf.system.utiles.IpUtil;
import com.inf.system.utiles.JsonBean;
import com.inf.system.utiles.MD5Util;
import com.inf.system.utiles.page.PageInfo;
import com.inf.system.utiles.page.PageUtil;

@Service
public class TbSysUserServiceImpl implements TbSysUserService{

	//用户信息
	@Autowired
	private  TbSysUserMapper tbSysUserMapper;
	
	//角色信息
	@Autowired
	private TbSysRoleMapper tbSysRoleMapper;
	
	//登录日志
	@Autowired
	private  TbSysLoginLogMapper tbSysLoginLogMapper;
	
	//操作日志
	@Autowired
	private TbSysActionLogMapper tbSysActionLogMapper;
	
	
	//新增管理员用户
	@Override
	public int insertTbSysUser(HttpServletRequest request,TbSysUser tbSysUser,String[] roleId) throws Exception{
		//密码加密
		parseEncoding(tbSysUser);
		
		//获取当前登录用户信息
		SessionSysUser  sysusersession =  (SessionSysUser) request.getSession().getAttribute(Constant.USER_SESSION);
		 if(null == sysusersession){
			 return 0;
		 }	
		  //创建者
		  tbSysUser.setCreateUser((sysusersession.getUserId()));
		  int  resule=tbSysUserMapper.insertTbSysUser(tbSysUser);
		  
		  //获取创建管理员的信息
		  if(resule == Constant.DEFAULT_RESULT_1){
			  tbSysUser= tbSysUserMapper.checkValidLoginName(tbSysUser.getLoginName());
		  }
		  
		  if(null==tbSysUser){
			  throw new Exception();
		  }
		  
		  //建立用户角色关系
		  	List<TbUserRole> list = new ArrayList<TbUserRole>();
			for (String rid : roleId) {
				if(StringUtils.isNotBlank(rid)){
					TbUserRole tbUserRole2=new TbUserRole(tbSysUser.getUserId(),rid,sysusersession.getUserId());
					list.add(tbUserRole2);
				}
			}
			
			if(list.size()>=0){
				tbSysUserMapper.insertUserRoles(list);	
			}
		  
		  return  resule;
	}

	//用户登录
	@Override
	public Map<String,Object> getLoginUser(HttpServletRequest request, Map<String, Object> mapper) {

		Map<String, Object> resultMap= new HashMap<String, Object>();
		//获取用户基本信息
		TbSysUser tbSysUser=tbSysUserMapper.getLoginUser(mapper);

		if(null == tbSysUser || StringUtils.isBlank(tbSysUser.getUserId())){
			return null;
		}

		Map<String, Object> rolmap= new HashMap<String, Object>();
		rolmap.put(Constant.USER_ROLE_USERID, tbSysUser.getUserId());

		boolean isAdmin=false;

		//获取用户的角色，判断是否是超级管理员
		List<Map<String, Object>> listTbSysRoles = tbSysRoleMapper.selectByTbSysRole(rolmap);

		if (CollectionUtils.isEmpty(listTbSysRoles)) {
			return null;
		}	
		
		//登录管理员角色ids
		List<String> sysUserIdsList= new ArrayList<String>();
		
		for(Map<String, Object> tbsysrole: listTbSysRoles){
			if(Constant.SYSTEN_USER_ROLENAME.equals(String.valueOf(tbsysrole.get(Constant.ROLE_SYS_ROLENAME)))){
				isAdmin=true;
			}
			sysUserIdsList.add(String.valueOf(tbsysrole.get(Constant.ROLE_SYS_ROLID)));
		}
		
		//登录用户信息存入session
		SessionSysUser sessionUser= new SessionSysUser();
		sessionUser.setUserId(tbSysUser.getUserId());
		sessionUser.setUserName(tbSysUser.getUserName());
		sessionUser.setLoginName(tbSysUser.getLoginName());
		sessionUser.setPassword(tbSysUser.getPassword());
		sessionUser.setIsAdmin(isAdmin);
		sessionUser.setRoleIds(sysUserIdsList);
		
		request.getSession().setAttribute(Constant.USER_SESSION, sessionUser);

		//获取用户的资源权限
		List<Menu> menuList=this.getUserPrivices(tbSysUser.getUserId(), isAdmin);

		resultMap.put("tbSysUser", tbSysUser);
		resultMap.put("resList", menuList);
		
		//登录日志记录
		tbSysLoginLogMapper.insertTbSysLoginLog(new TbSysLoginLog(
				tbSysUser.getUserId(),
				IpUtil.getRemortIP(request),
				Constant.SYSUSER_LOGIN_MANAGER
				));
		
		return resultMap;
	}

	
	//获取管理员的权限资源
	@Override
	public List<Menu> getUserPrivices(String userId, boolean isAdmin) {
		//获取用户相应的权限
		List<Menu> menuList = new ArrayList<Menu>();

		List<Menu> allList = new ArrayList<Menu>();

		if(isAdmin){
			allList=tbSysUserMapper.getUserRoleResourceIsAdmin();
		}else{
			//获取系统分配的资源
			allList=tbSysUserMapper.getUserRoleResource(userId);
		
			//获取父资源，并添加到list 中
			List<String> parentList= new ArrayList<String>();
			
				for(Menu al1:allList){
					Boolean hasParent =false;
					for(Menu al2:allList){
						if(al1.getResParentId().equals(al2.getResId())){
							hasParent=true;
							break;
						}
					}
					
					if(!hasParent){
						if(StringUtils.isNotBlank(al1.getResParentId())){
							parentList.add(al1.getResParentId());
						}
				   }
			    }
				
				if(!CollectionUtils.isEmpty(parentList)){
					List<Menu> pllList	= tbSysUserMapper.getUserRoleResourceIsParent(parentList);
					
					if(!CollectionUtils.isEmpty(pllList)){
						allList.addAll(pllList);
					}
					
				}
		}

		//拼接用户权限树
		for (Menu menu1 : allList) {
			boolean mark = false;
			for (Menu menu2 : allList) {
				if (StringUtils.isNotBlank(menu1.getResParentId()) && menu1.getResParentId().equals(menu2.getResId())) {
					mark = true;
					if (menu2.getResChildren() == null){
						menu2.setResChildren(new ArrayList<Menu>());
					}
					menu2.getResChildren().add(menu1);
					break;
				}
			}
			if (!mark) {
				menuList.add(menu1);
			}
		}

		// 将只勾了权限后作为模块显示的去掉
		/*Iterator<Menu> iter = menuList.iterator();

		while (iter.hasNext()) {
			Menu m = iter.next();
			if (!("0".equals(m.getResParentId()))) {
				iter.remove();
			}
		}*/
		
		return menuList;
	}

	
	
	//分页获取系统所有管理员信息
	@Override
	public String getSysUserListPage(HttpServletRequest request,	Map<String, Object> mapper) throws Exception {
		
		if(mapper == null){
			mapper = new HashMap<String, Object>();
		}
		// 获取页面显示条数
		int pageSize = Integer.parseInt(request.getParameter(Constant.ROWS));
		pageSize = pageSize < Constant.PageSize ? Constant.PageSize : pageSize;
		// 获取当前页数
		int currentPage = request.getParameter(Constant.PAGE) == null ? Constant.BEGIN_PAGE : Integer.parseInt(request.getParameter(Constant.PAGE));
		// 分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(currentPage);
		pageInfo.setPageSize(pageSize);
		
		PageUtil.buildPageBeforeDao(pageInfo, null, currentPage);
		// 获取分页信息
		List<Map<String, Object>> list = tbSysUserMapper.getSysUserListPage(pageInfo, mapper);
		
		PageUtil.buildPageAfterDao(pageInfo, pageInfo.getCurrentPage());
		// 获取总条数
		String pageTotal = pageInfo.getTotalResult() + "";
		// 返回Json格式数据包
		return JsonBean.toJson(pageTotal, list);

	}
	
	//密码加密
	private void parseEncoding(TbSysUser tbSysUser)throws Exception {
		String pwd = tbSysUser.getPassword();
		if(pwd != null){
			tbSysUser.setPassword((MD5Util.convertMD5(pwd)));
		}
	}

	//验证用户名是否已经使用
	@Override
	public boolean checkValidLoginName(String loginName) throws Exception {
		
		TbSysUser tbSysUser=tbSysUserMapper.checkValidLoginName(loginName);
		
		boolean result=false;
		if(null != tbSysUser && StringUtils.isNotBlank(tbSysUser.getUserId())){
		 result=true;
		}
		return result;
	}

	//更新人员修改信息,修改密码
	@Override
	public void updateSysUser(HttpServletRequest request, TbSysUser tbSysUse,String[] roleId)
			throws Exception {
		
		SessionSysUser sessionSysUser = (SessionSysUser) request.getSession().getAttribute(Constant.USER_SESSION);
		
		tbSysUse.setUpdateUser(sessionSysUser.getUserId()); 
		
		tbSysUserMapper.updateTbSysUser(tbSysUse);
		
		//修改用户角色
		if(null !=roleId && roleId.length > Constant.DEFAULT_RESULT_0 ){
			//删除原来的用户角色信息
			tbSysUserMapper.updateSysUserRole(tbSysUse.getUserId());
			
			List<TbUserRole> list = new ArrayList<TbUserRole>();
			
			//新增用户角色信息
			for (int i = Constant.DEFAULT_RESULT_0 ; i < roleId.length; i++) {
				if (StringUtils.isNotBlank(roleId[i])) {
					TbUserRole tbUserRole2=new TbUserRole(tbSysUse.getUserId(),roleId[i],sessionSysUser.getUserId());
					list.add(tbUserRole2);
				}
			}
			
			if(list.size() >= Constant.DEFAULT_RESULT_0 ){
				tbSysUserMapper.insertUserRoles(list);	
			}
			
		}
	}

	//获取单个人员信息
	@Override
	public String getSysUserDetail(String userId) throws Exception {
		
		Map<String,Object> user = tbSysUserMapper.getSysUserDetail(userId);
		return JsonBean.toJson(user);
	}

	//删除用户信息，可批量删除
	@Override
	public void deleteSysUser(HttpServletRequest request, String userId)
			throws Exception {
		
		SessionSysUser sessionSysUser = (SessionSysUser) request.getSession().getAttribute(Constant.USER_SESSION);
		
		TbSysUser tbSysUse= new TbSysUser(userId,sessionSysUser.getUserId());
		
		tbSysUserMapper.deleteSysUser(tbSysUse);
	}

}

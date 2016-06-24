package com.inf.sys.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inf.sys.user.mapper.TbSysRoleMapper;
import com.inf.sys.user.model.SessionSysUser;
import com.inf.sys.user.model.TbSysPrivilege;
import com.inf.sys.user.model.TbSysRole;
import com.inf.sys.user.model.TreeNode;
import com.inf.sys.user.service.TbSysRoleService;
import com.inf.system.utiles.Constant;
import com.inf.system.utiles.JsonBean;
import com.inf.system.utiles.page.PageInfo;
import com.inf.system.utiles.page.PageUtil;

/**
 * 系统角色
 * @author Administrator
 *
 */
@Service
public class TbSysRoleServiceImpl implements  TbSysRoleService{

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(TbSysRoleServiceImpl.class);
	
	@Autowired
	private  TbSysRoleMapper tbSysRoleMapper;

	//获取系统角色列表
	@Override
	public String getSysRoleList(HttpServletRequest request) throws Exception {

		// 获取页面显示条数
		int pageSize = Integer.parseInt(request.getParameter(Constant.ROWS));
		pageSize = pageSize < Constant.PageSize ? Constant.PageSize : pageSize;
		// 获取当前页数
		int currentPage = request.getParameter(Constant.PAGE) == null ? Constant.BEGIN_PAGE: Integer
				.parseInt(request.getParameter(Constant.PAGE));
		
		String roleName = request.getParameter(Constant.ROLE_SYS_ROLENAME);
		// 分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(currentPage);
		pageInfo.setPageSize(pageSize);
		PageUtil.buildPageBeforeDao(pageInfo, null, currentPage);
		// 获取分页信息
		List<Map<String, Object>> list = tbSysRoleMapper.getSysRolesListPage(pageInfo,roleName);
		PageUtil.buildPageAfterDao(pageInfo, pageInfo.getCurrentPage());
		// 获取总条数
		String pageTotal = pageInfo.getTotalResult() + "";
		// 返回Json格式数据包
		return JsonBean.toJson(pageTotal, list);
	}

	// 检测角色是否存在
	@Override
	public int checkExist(String newValue, String oldValue) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newValue", newValue);
		map.put("oldValue", oldValue);
		int num = tbSysRoleMapper.checkExist(map);
		return num;
	}

	//获取所有权限列表
	@Override
	public List<TreeNode> getRoleTreeList(String roleIds) throws Exception {
		List<TreeNode> list = tbSysRoleMapper.getRoleTreeList(roleIds);
		return list;
	}

	// 添加角色
	@Override
	public void addSysRoleAdd(String roleName, String resourceIds, SessionSysUser loginUser) throws Exception {

		
		TbSysRole tbSysRole = new TbSysRole(roleName,roleName,loginUser.getUserId());
		
		//新增角色
		tbSysRoleMapper.addSysRoleAdd(tbSysRole);
		
		TbSysRole tbSysRole1=tbSysRoleMapper.getSysRoleData(tbSysRole);

		List<TbSysPrivilege> pList = new ArrayList<TbSysPrivilege>();
		String[] resourceIdsArr = new String[] {};
		
		if (StringUtils.isNotBlank(resourceIds)) {
			resourceIdsArr = resourceIds.split(",");
		}
		
		for (int i = 0; i < resourceIdsArr.length; i++) {
			if(StringUtils.isNotBlank(resourceIdsArr[i])){
				TbSysPrivilege tbSysPrivilege = new TbSysPrivilege(
						tbSysRole1.getRoleId(),
						resourceIdsArr[i],
						loginUser.getUserId(),null);
		
				pList.add(tbSysPrivilege);
			}
		}
		// ----------新增权限----------
		if (pList.size() != 0) {
			tbSysRoleMapper.addPrivilegeList(pList);
		}
	}

	
	//根据角色ID获取相关权限列表
	@Override
	public List<TbSysPrivilege> getPrivilegeListByRoleId(String roleId)
			throws Exception {
		List<TbSysPrivilege> list = tbSysRoleMapper.getPrivilegeListByRoleId(roleId);
		return list;
	}

	
	//更新角色权限
	@Override
	public void updateSysRole(String roleId, String roleName, String resourceIds,
			SessionSysUser loginUser) throws Exception {

		TbSysRole tbSysRole= new TbSysRole(roleId,roleName,roleName,loginUser.getUserId());

		tbSysRoleMapper.updateSysRole(tbSysRole);
		// 删除原有权限
		tbSysRoleMapper.deletePrivilegeListByRoleId(roleId);
		// 新增编辑后权限
		List<TbSysPrivilege> pList = new ArrayList<TbSysPrivilege>();
		String[] resourceIdsArr = new String[] {};
		if (!("".equals(resourceIds))) {
			resourceIdsArr = resourceIds.split(",");
		}
		for (int i = 0; i < resourceIdsArr.length; i++) {
			if(StringUtils.isNotBlank(resourceIdsArr[i])){
				TbSysPrivilege tbSysPrivilege = new TbSysPrivilege(
						roleId,
						resourceIdsArr[i],
						loginUser.getUserId(),null);
				pList.add(tbSysPrivilege);
			}
			
		}
		// 向权限表增加记录
		if (pList.size() != 0) {
			tbSysRoleMapper.addPrivilegeList(pList);
		}
	}

	//校验改角色下是否存在用户
	@Override
	public int sysRoleCheckUser(String roleId) throws Exception {

		int num = tbSysRoleMapper.sysRoleCheckUser(roleId);
		return num;
	}

	//删除角色信息，可批量删除
	@Override
	public void deleteSysRole(String[] roleIds) throws Exception {
		
		List<String> list = new ArrayList<String>();
		for (String roleId : roleIds) {
			list.add(roleId);
		}
		// ---------更新角色表-------------
		tbSysRoleMapper.deleteSysRole(list);
		
	}

	
	//查询角色权限信息列表
	@Override
	public List<TbSysRole> getSysRole() {

		return tbSysRoleMapper.getSysRole();
	}
	
	
	
}

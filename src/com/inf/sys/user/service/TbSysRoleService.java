package com.inf.sys.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.inf.sys.user.model.SessionSysUser;
import com.inf.sys.user.model.TbSysPrivilege;
import com.inf.sys.user.model.TbSysRole;
import com.inf.sys.user.model.TreeNode;



/**
 * 系统角色
 * @author Administrator
 *
 */
public interface TbSysRoleService {

	//获取系统用户角色列表
	String getSysRoleList(HttpServletRequest request) throws Exception;
	
	/**
	 * 检测角色是否存在
	 * @param newValue
	 * @param oldValue
	 * @return
	 * @throws Exception
	 */
	public int checkExist(String newValue, String oldValue) throws Exception;
	
	/**
	 * 获取所有权限列表
	 * @return
	 */
	public List<TreeNode> getRoleTreeList(String roleIds) throws Exception;
	
	/**
	 * 添加角色
	 * @param roleName
	 * @param resourceIds
	 * @param loginUser
	 * @throws Exception
	 */
	public void addSysRoleAdd(String roleName, String resourceIds,SessionSysUser loginUser)throws Exception;
	

	/**
	 * 根据角色ID获取相关权限列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<TbSysPrivilege> getPrivilegeListByRoleId(String roleId) throws Exception;
	
	/**
	 * 更新角色资源
	 * @param roleId
	 * @param roleName
	 * @param resourceIds
	 * @param loginUser
	 * @throws Exception
	 */
	void updateSysRole(String roleId, String roleName, String resourceIds,
			SessionSysUser loginUser) throws Exception;
	
	/**
	 * 校验改角色下是否存在用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int sysRoleCheckUser(String roleId) throws Exception;
	
	
	/**
	 * 删除角色信息，可批量删除
	 * @param request
	 * @throws Exception
	 */
	public void deleteSysRole(String[] roleIds)throws Exception;

	/**
	 * 查询角色权限信息列表
	 * @return
	 */
	public List<TbSysRole> getSysRole();

}

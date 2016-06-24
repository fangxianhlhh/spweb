package com.inf.sys.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.inf.sys.user.model.TbSysPrivilege;
import com.inf.sys.user.model.TbSysRole;
import com.inf.sys.user.model.TreeNode;
import com.inf.system.utiles.page.PageInfo;
/**
 * 系统角色
 * @author Administrator
 *
 */
public interface TbSysRoleMapper {

    //增加角色
    int addSysRoleAdd(TbSysRole tbSysRole);

    //根据用户获取用户的角色
    List<Map<String, Object>> selectByTbSysRole(@Param("mapper") Map<String, Object> mapper);
    
    //获取角色信息
    TbSysRole getSysRoleData(TbSysRole tbSysRole);
    
	/**
	 *	获取角色
	 */
	public List<Map<String, Object>> getSysRolesListPage(@Param(value="page") PageInfo page, @Param(value = "roleName") String roleName);
	
	/**
	 * 验证角色是否已存在
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int checkExist(Map<String,Object> map) throws Exception;
	
	/**
	 * 获取角色树节点
	 * @return
	 * @throws Exception
	 */
	public List<TreeNode> getRoleTreeList(String roleIds) throws Exception;
	
	
	/**
	 * 更新角色信息
	 */
	public void updateSysRole(TbSysRole tbSysRole);
	
	/**
	 * 角色添加资源
	 */
	public void addPrivilegeList(List<TbSysPrivilege> list) throws Exception;
	
	/**
	 * 根据角色id获取权限列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<TbSysPrivilege> getPrivilegeListByRoleId(String roleId) throws Exception;
	
	
	/**
	 * 根据角色id删除权限
	 * @param id
	 * @throws Exception
	 */
	public void deletePrivilegeListByRoleId(String roleId) throws Exception;
	
	
	/**
	 * 验证角色下是否存在用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int sysRoleCheckUser(String roleId) throws Exception;
	
	/**
	 * 删除角色
	 */
	public void deleteSysRole(List<String> list);
	
	/**
	 * 查询角色列表
	 */
	public List<TbSysRole> getSysRole();
}
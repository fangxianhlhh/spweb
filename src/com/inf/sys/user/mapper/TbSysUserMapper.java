package com.inf.sys.user.mapper;

import java.util.List;
import java.util.Map;











import org.apache.ibatis.annotations.Param;

import com.inf.sys.user.model.Menu;
import com.inf.sys.user.model.TbSysUser;
import com.inf.sys.user.model.TbUserRole;
import com.inf.system.utiles.page.PageInfo;
/**
 * 系统用户
 * @author Administrator
 *
 */
public interface TbSysUserMapper {

	//增加用户管理员
    int insertTbSysUser(TbSysUser tbSysUser);
    
    //分页查询所有的管理员信息
	public List<Map<String, Object>> getSysUserListPage(@Param(value = "page") PageInfo page, @Param(value = "mapper") Map<String, Object> mapper);


    //查询用户信息
    List<TbSysUser> selectByParam(@Param("mapper") Map<String, Object> mapper);
    
    //用户登录
    TbSysUser getLoginUser(@Param("mapper") Map<String, Object> mapper);

    //修改用户信息
    int updateTbSysUser(TbSysUser tbSysUser);

    
    //获取用户对应的权限资源,根据用户id
    List<Menu>  getUserRoleResource(String  userId);

    //超级管理员获取所有的资源
    List<Menu>  getUserRoleResourceIsAdmin();
    
    //根据父资源id 获取所有的父资源信息
    List<Menu>  getUserRoleResourceIsParent(List<String> list);
    
    //验证用户名是否已经使用
    TbSysUser  checkValidLoginName(String loginName);
    
    //用户添加角色
    void insertUserRoles(List<TbUserRole> list);
    
    //获取单个用户信息
    Map<String,Object> getSysUserDetail(String userId);
    
    //根据用户id删除用户的角色
    void updateSysUserRole(String userId);
    
    //根据用户id 删除用户
    void deleteSysUser(TbSysUser tbSysUser);
    
}
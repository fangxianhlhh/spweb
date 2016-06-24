package com.inf.sys.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.inf.sys.user.model.ResourceList;
import com.inf.sys.user.model.ResourceTree;
import com.inf.sys.user.model.TbSysResource;

//系统资源
public interface TbSysResourceService {

	//获取所有资源列表树
	List<ResourceTree> getResourceTreeList();
	
	//获取资源列表
	List<ResourceList> getResourceList(ResourceList resourceList);
	
	//验证系统资源名是否被使用
	boolean checkValidResName(String resName);
	
	//添加系统资源
	void addSysResourdeAdd(TbSysResource tbSysResource);
	
	//获取单个资源信息
	String getSysResourceDetail(String resId);
	
	//修改资源信息
	void updateSysResource(TbSysResource tbSysResource);
	
	//删除资源信息
	void  deleteSysResource(HttpServletRequest  request,String resId);
}

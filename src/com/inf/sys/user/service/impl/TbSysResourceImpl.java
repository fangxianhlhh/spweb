package com.inf.sys.user.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inf.sys.user.mapper.TbSysResourceMapper;
import com.inf.sys.user.model.ResourceList;
import com.inf.sys.user.model.ResourceTree;
import com.inf.sys.user.model.SessionSysUser;
import com.inf.sys.user.model.TbSysResource;
import com.inf.sys.user.service.TbSysResourceService;
import com.inf.system.utiles.Constant;
import com.inf.system.utiles.JsonBean;

// 系统资源
@Service
public class TbSysResourceImpl implements TbSysResourceService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(TbSysResourceImpl.class); 
	
	@Autowired
	private TbSysResourceMapper tbSysResourceMapper;

	
	//获取所有资源列表（一次全部加载）
	@Override
	public List<ResourceTree> getResourceTreeList() {
		 List<ResourceTree> resourceList=tbSysResourceMapper.getResourceTreeList();
		return resourceList;
	}


	//获取资源列表（异步加载）
	@Override
	public List<ResourceList> getResourceList(ResourceList resourceList) {
		
		return tbSysResourceMapper.getResourceList(resourceList);
	}


	//验证系统资源名是否被使用
	@Override
	public boolean checkValidResName(String resName) {
		
		TbSysResource tbSysResource=tbSysResourceMapper.checkValidResName(resName);
		
		boolean result=false;
		if(null != tbSysResource && StringUtils.isNotBlank(tbSysResource.getResId())){
		 result=true;
		}
		return result;
	}


	//添加系统资源
	@Override
	public void addSysResourdeAdd(TbSysResource tbSysResource) {
		
		if(StringUtils.isBlank(tbSysResource.getParendResId())){
			tbSysResource.setResLevel(Constant.DEFAULT_LONG_1);
		}else{
			TbSysResource resource=tbSysResourceMapper.findResourceByResId(tbSysResource.getParendResId());
			if(null == resource || StringUtils.isBlank(resource.getResId())){
				tbSysResource.setResLevel(Constant.DEFAULT_LONG_1);
			}else{
				tbSysResource.setResLevel(resource.getResLevel()+Constant.DEFAULT_LONG_1);
			}
		}
		tbSysResourceMapper.insertTbSysResource(tbSysResource);
	}


	//获取单个资源信息
	@Override
	public String getSysResourceDetail(String resId) {
		
		TbSysResource tbSysResource = tbSysResourceMapper.findResourceByResId(resId);
		return JsonBean.toJson(tbSysResource);
	}


	//修改资源信息
	@Override
	public void updateSysResource(TbSysResource tbSysResource) {
		
		tbSysResourceMapper.updateTbSysResource(tbSysResource);
	}


	//删除资源信息
	@Override
	public void deleteSysResource(HttpServletRequest request, String resId) {
		
		SessionSysUser loginUser = (SessionSysUser) request.getSession().getAttribute(Constant.USER_SESSION);
		
		TbSysResource tbSysResource = new TbSysResource();
		tbSysResource.setResId(resId);
		tbSysResource.setUpdateUser(loginUser.getUserId());
		
		tbSysResourceMapper.deleteByResId(tbSysResource);
	}
	
	
}

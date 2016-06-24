package com.inf.sys.user.mapper;

import java.util.List;
import com.inf.sys.user.model.ResourceList;
import com.inf.sys.user.model.ResourceTree;
import com.inf.sys.user.model.TbSysResource;

/**
 * 系统资源表
 * @author Administrator
 *
 */
public interface TbSysResourceMapper {

	//删除资源
    int deleteByResId(TbSysResource tbSysResource);

    //添加系统资源
    int insertTbSysResource(TbSysResource tbSysResource);

    // 修改资源信息
    int updateTbSysResource(TbSysResource tbSysResource);
    
    //获取所有资源列表树 （ 一次全部加载）
  	List<ResourceTree> getResourceTreeList();
  	
  	//获取资源列表(异步加载)
  	List<ResourceList> getResourceList(ResourceList resourceList);
  	
  	//验证资源名是否被使用
  	TbSysResource checkValidResName(String resName);
  	
  	// 通过资源id查询资源信息
  	TbSysResource findResourceByResId(String resId);
  	
  	

}
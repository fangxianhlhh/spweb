package com.inf.sys.user.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inf.base.BaseController;
import com.inf.sys.user.mapper.TbSysActionLogMapper;
import com.inf.sys.user.model.ResourceList;
import com.inf.sys.user.model.ResourceTree;
import com.inf.sys.user.model.SessionSysUser;
import com.inf.sys.user.model.TbSysActionLog;
import com.inf.sys.user.model.TbSysResource;
import com.inf.sys.user.service.TbSysResourceService;
import com.inf.system.utiles.Constant;
import com.inf.system.utiles.JsonBean;

//资源信息
@Controller
@RequestMapping("sysResource/")
public class SysResourceCollection extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleCollection.class);
	
	//资源
	@Autowired
	private TbSysResourceService tbSysResourceService;
	
	//系统操作日志
	@Autowired
	private TbSysActionLogMapper tbSysActionLogMapper;
		
	

	/**
	 * 获取所有资源列表树（一次全部加载）
	 * @param request
	 * @param response
	 */
	@RequestMapping("getResourceTreeList.do")
	@ResponseBody
	public void getResourceTreeList(HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	
		List<ResourceTree> resourceList=new ArrayList<ResourceTree>();
		
		try {
			
			List<ResourceTree> resultList=tbSysResourceService.getResourceTreeList();
			
			for(ResourceTree node1 : resultList){  
			    boolean mark = false;  
			    for(ResourceTree node2 : resultList){  
			        if(node1.getParendResId()!=null && node1.getParendResId().equals(node2.getResId())){  
			            mark = true;  
			            if(node2.getChildren() == null)  
			                node2.setChildren(new ArrayList<ResourceTree>());  
			            node2.getChildren().add(node1);   
			            break;  
			        }  
			    }  
			    if(!mark){  
			    	resourceList.add(node1);   
			    }  
			}
			
			String json = JSONArray.fromObject(resourceList).toString(); 
			response.getWriter().print(json);
		} catch (Exception e) {
			LOGGER.error("获取资源树列表失败！",e);
		}
	}
	
	
	//获取资源列表（异步加载）
	@RequestMapping("getResourceList.do")
	@ResponseBody
	public void getResourceList(@RequestParam(required=false,defaultValue="1") Long resLevel,
			@RequestParam(required=false) String parendResId,
			HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		ResourceList resourceList=new ResourceList();
		
		if(StringUtils.isBlank(parendResId)){
			resourceList.setResLevel(resLevel);
		}else{
			resourceList.setParendResId(parendResId);
		}
	
		
		List<ResourceList> resultList=new ArrayList<ResourceList>();
		
		try {
			resultList=tbSysResourceService.getResourceList(resourceList);
			String json = JSONArray.fromObject(resultList).toString(); 
			response.getWriter().print(json);
		} catch (Exception e) {
			LOGGER.error("获取资源树列表失败！",e);
		}
	}
	
	//验证资源名称是否被使用
	@RequestMapping("checkValidResName.do")
	@ResponseBody
	public void checkValidResName(@RequestParam(required=true) String resName,
		HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> reponseMessage = new HashMap<String, Object>();
		
		boolean inValidresName = tbSysResourceService.checkValidResName(resName);
		//用户名已存在
		if (inValidresName) {
			reponseMessage.put("success", true);
			response.getWriter().print(false);
		} else {
			reponseMessage.put("success", false);
			response.getWriter().print(true);
		}
}
	
	//添加系统资源
	@RequestMapping("sysResourceAdd.do")
	@ResponseBody
	public void sysResourceAdd(TbSysResource tbSysResource,HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		Map<String, Object> reponseMessage = new HashMap<String, Object>();
		
		SessionSysUser loginUser=null;
		try {
			loginUser = (SessionSysUser) request.getSession().getAttribute(Constant.USER_SESSION);
			tbSysResource.setCreateUser(loginUser.getUserId());
			
			tbSysResourceService.addSysResourdeAdd(tbSysResource);
			reponseMessage.put("success", "true");
			reponseMessage.put("msg", "添加成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("添加系统资源失败",ex);
			reponseMessage.put("success", "false");
			reponseMessage.put("msg", ex.getMessage());
		}
		response.getWriter().print(JsonBean.toJson(reponseMessage));
		
		//操作日志
		tbSysActionLogMapper.insertTbSysActionLog(new TbSysActionLog(
				loginUser.getUserId(),
				Constant.RES_SYS_ADDRES,
				Constant.RES_SYS_ADDRES+",系统资源名称："+tbSysResource.getResName()
				));
	}
	
	
	//修改系统资源  updateSysResource.do
	@RequestMapping("updateSysResource.do")
	@ResponseBody
	public void updateSysResource(TbSysResource tbSysResource,HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		Map<String, Object> reponseMessage = new HashMap<String, Object>();
		
		SessionSysUser loginUser=null;
		try {
			loginUser= (SessionSysUser) request.getSession().getAttribute(Constant.USER_SESSION);
			
			tbSysResource.setUpdateUser(loginUser.getUserId());
			
			tbSysResourceService.updateSysResource(tbSysResource);
			reponseMessage.put("success", "true");
			reponseMessage.put("msg", "修改成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("添加系统资源失败",ex);
			reponseMessage.put("success", "false");
			reponseMessage.put("msg", ex.getMessage());
		}
		response.getWriter().print(JsonBean.toJson(reponseMessage));	
		
		//操作日志
		tbSysActionLogMapper.insertTbSysActionLog(new TbSysActionLog(
				loginUser.getUserId(),
				Constant.RES_SYS_UPDATERES,
				Constant.RES_SYS_UPDATERES+",系统资源名称："+tbSysResource.getResName()
			));
	}
	
	
 //获取单个资源数据 sysResourceDetail
	@RequestMapping("sysResourceDetail.do")
	@ResponseBody
	public void sysResourceDetail(@RequestParam(required=false) String resId,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String json = tbSysResourceService.getSysResourceDetail(resId);
		PrintWriter out = response.getWriter();
		out.print(json);
	}
	
//删除系统资源
	@RequestMapping("sysResourceDelete.do")
	@ResponseBody
	public void sysResourceDelete(@RequestParam(required=false) String resId,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		Map<String, Object> reponseMessage = new HashMap<String, Object>();
		try {
			tbSysResourceService.deleteSysResource(request,resId);
			reponseMessage.put("success", true);
			reponseMessage.put("msg", "删除成功");
		} catch (Exception ex) {
			reponseMessage.put("success", false);
			reponseMessage.put("msg", ex.getMessage());
		}
		
		String json = JsonBean.toJson(reponseMessage);
		PrintWriter out = response.getWriter();
		out.print(json);
		
		//操作日志
		tbSysActionLogMapper.insertTbSysActionLog(new TbSysActionLog(
				resId,
				Constant.RES_SYS_DELETERES,
				Constant.RES_SYS_DELETERES+",系统资源id："+resId
			));
	}
}

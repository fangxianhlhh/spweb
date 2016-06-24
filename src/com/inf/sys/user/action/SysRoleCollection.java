package com.inf.sys.user.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inf.base.BaseController;
import com.inf.sys.user.mapper.TbSysActionLogMapper;
import com.inf.sys.user.model.SessionSysUser;
import com.inf.sys.user.model.TbSysActionLog;
import com.inf.sys.user.model.TbSysPrivilege;
import com.inf.sys.user.model.TbSysRole;
import com.inf.sys.user.model.TreeNode;
import com.inf.sys.user.service.TbSysRoleService;
import com.inf.system.utiles.CollecUtils;
import com.inf.system.utiles.Constant;
import com.inf.system.utiles.JsonBean;

@Controller
@RequestMapping("sysRole/")
public class SysRoleCollection extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleCollection.class);
	
	@Autowired
	private  TbSysRoleService tbSysRoleService;
	
	
	//系统操作日志
	@Autowired
	private TbSysActionLogMapper tbSysActionLogMapper;
	
	
	
	//获取角色集合
	@RequestMapping("getSysRoleList.do")
	public void getSysRoleList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//查询到的角色list并转换为Json字符串
		String jsonString = tbSysRoleService.getSysRoleList(request);
		// 返回一个Json字符串
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonString);
	}
	
	
	/**
	 * 验证角色名称重复
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("checkExist.do")
	@ResponseBody
	public void checkExist(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String message = "{result:false}";
		JSONObject json = new JSONObject();
		try {
			String newValue = request.getParameter("newValue");
			String oldValue = request.getParameter("oldValue")==null?"":request.getParameter("oldValue");
			int num = tbSysRoleService.checkExist(newValue,oldValue);
			if(num > 0){
				message = "{result:true}";
			}
		} catch (Exception e) {
			LOGGER.error("校验角色是否存在错误");
			e.printStackTrace();
			message = "{result:'校验出错'}";
		}
		json = JSONObject.fromObject(message);
		response.getWriter().print(json);
	}
	
	
	/**
	 * 获取所有权限列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("getRoleTreeList.do")
	@ResponseBody
	public void getRoleTreeList(HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		List<TreeNode> nodeList = new ArrayList<TreeNode>(); 
		
		SessionSysUser sessionUser=(SessionSysUser) request.getSession().getAttribute(Constant.USER_SESSION);
		if(null == sessionUser){
			return ;
		}
		String roleIds=null;
		
		if(!sessionUser.getIsAdmin()){
			roleIds=CollecUtils.listTranString(sessionUser.getRoleIds());
		}
		
		try {
			List<TreeNode> menuList = tbSysRoleService.getRoleTreeList(roleIds);   
			for(TreeNode node1 : menuList){  
			    boolean mark = false;  
			    for(TreeNode node2 : menuList){  
			        if(node1.getParentId()!=null && node1.getParentId().equals(node2.getId())){  
			            mark = true;  
			            if(node2.getChildren() == null)  
			                node2.setChildren(new ArrayList<TreeNode>());  
			            node2.getChildren().add(node1);   
			            break;  
			        }  
			    }  
			    if(!mark){  
			        nodeList.add(node1);   
			    }  
			}
			//去除组织机构
			Iterator<TreeNode> iter = nodeList.iterator();   
			while(iter.hasNext()){   
				TreeNode t = iter.next();   
				if(("3".equals(t.getId()))){   
					iter.remove();   
				}   
			}
			//转为json格式        
			String json = JSONArray.fromObject(nodeList).toString(); 
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 添加角色
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sysRoleAdd.do")
	public void sysRoleAdd(@RequestParam(required =true ) String roleName,
			@RequestParam(required = false ) String resourceIds,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String message = "{result:false}";
		JSONObject json = new JSONObject();
		SessionSysUser loginUser=null;
		try {
			 loginUser = (SessionSysUser) request.getSession().getAttribute(Constant.USER_SESSION);
			tbSysRoleService.addSysRoleAdd(roleName, resourceIds, loginUser);
			message = "{result:true}";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		json = JSONObject.fromObject(message);
		response.getWriter().print(json);
		
		//操作日志
		tbSysActionLogMapper.insertTbSysActionLog(new TbSysActionLog(
				loginUser.getUserId(), 
				Constant.ROLE_SYS_ADDROLE,
				Constant.ROLE_SYS_ADDROLE+",系统角色名称："+roleName));
	}
	
	//获取当前角色的资源
	@RequestMapping("getPrivilegeListByRoleId")
	@ResponseBody
	public void getPrivilegeListByRoleId(@RequestParam(required =true ) String roleId,
			HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			List<TbSysPrivilege> list = tbSysRoleService.getPrivilegeListByRoleId(roleId);
			JSONArray jsonArray = JSONArray.fromObject(list);
			response.getWriter().print(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新角色信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("sysRoleUpdate.do")
	public void sysRoleUpdate(@RequestParam(required =true ) String roleId,
			@RequestParam(required =true ) String roleName,@RequestParam(required =true ) String resourceIds,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String message = "{result:false}";
		JSONObject json = new JSONObject();
		
		SessionSysUser loginUser=null;
		try {
			loginUser = (SessionSysUser) request.getSession().getAttribute(Constant.USER_SESSION);
			//更新角色
			tbSysRoleService.updateSysRole(roleId, roleName, resourceIds, loginUser);
			message = "{result:true}";

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		json = JSONObject.fromObject(message);
		response.getWriter().print(json);
		
		//操作日志
		tbSysActionLogMapper.insertTbSysActionLog(new TbSysActionLog(
				loginUser.getLoginName(), 
				Constant.ROLE_SYS_UPDATEROLE,
				Constant.ROLE_SYS_UPDATEROLE+",系统角色名称："+roleName));
	}
	
	/**
	 * 校验该角色是否被使用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("sysRoleCheckUser.do")
	@ResponseBody
	public void sysRoleCheckUser(@RequestParam(required =true ) String roleId,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String message = "{result:false}";
		JSONObject json = new JSONObject();
		try {
			int num = tbSysRoleService.sysRoleCheckUser(roleId);
			if(num > 0){
				message = "{result:true}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "{result:'校验出错'}";
		}
		json = JSONObject.fromObject(message);
		response.getWriter().print(json);
	}
	
	/**
	 * 删除角色
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("sysRoleDelete.do")
	public void roleDelete(@RequestParam(required =true ) String[] roleIds,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		Map<String, Object> reponseMessage = new HashMap<String, Object>();
		try {
			tbSysRoleService.deleteSysRole(roleIds);
			reponseMessage.put("success", "true");
			reponseMessage.put("msg", "角色删除成功");
		} catch (Exception ex) {
			reponseMessage.put("success", "false");
			reponseMessage.put("msg", ex.getMessage());
		}
		
		String json = JsonBean.toJson(reponseMessage);
		PrintWriter out = response.getWriter();
		out.print(json);
		
	
		//操作日志
		tbSysActionLogMapper.insertTbSysActionLog(new TbSysActionLog(
						String.valueOf(roleIds), 
						Constant.ROLE_SYS_DELETEROLE,
						Constant.ROLE_SYS_DELETEROLE+",系统角色id："+String.valueOf(roleIds)));
	}
	
	/**
	 * 查询角色权限信息列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getSysRole.do")
	public void getSysRole(HttpServletRequest request,HttpServletResponse response)throws Exception{
		//获取JSON格式列表
		List<TbSysRole> tbSysRole = tbSysRoleService.getSysRole();
		//返回Json格式数据
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(JsonBean.toJson(tbSysRole));
	}
}

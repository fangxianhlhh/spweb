package com.inf.sys.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inf.sys.user.mapper.TbSysActionLogMapper;
import com.inf.sys.user.service.TbSysActionLogService;
import com.inf.system.utiles.GsonUtil;
import com.inf.system.utiles.JsonBean;
import com.inf.system.utiles.page.PageInfo;
import com.inf.system.utiles.page.PageUtil;

// 操作日志
@Service
public class TbSysActionLogServiceImpl implements TbSysActionLogService{

	@Autowired
	private TbSysActionLogMapper tbSysActionLogMapper;
	
	//操作日志分页查询
	@Override
	public String getSystemlogListPage(Map<String, Object> paramMap,
			HttpServletRequest request) throws Exception {

		if (paramMap == null){
			paramMap = new HashMap<String, Object>();
		}
		
		// 获取页面显示条数
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		pageSize = pageSize < 20 ? 20 : pageSize;
		// 获取当前页数
		int currentPage = request.getParameter("page") == null ? 1 : Integer
				.parseInt(request.getParameter("page"));
		// 分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(currentPage);
		pageInfo.setPageSize(pageSize);
		PageUtil.buildPageBeforeDao(pageInfo, null, currentPage);
		// 获取分页信息
		List<Map<String, Object>> list = tbSysActionLogMapper.getSystemlogListPage(pageInfo, paramMap);
		PageUtil.buildPageAfterDao(pageInfo, pageInfo.getCurrentPage());
		// 获取总条数
		String pageTotal = pageInfo.getTotalResult() +"";
		// 返回Json格式数据包
		return GsonUtil.toJson(pageTotal,list);
	
	}

	//操作日志详情
	@Override
	public String getSystemLog(String actionId) {
		Map<String,Object> systemLog = tbSysActionLogMapper.getSystemLogDetail(actionId);
		return JsonBean.toJson(systemLog);
	}

	
}

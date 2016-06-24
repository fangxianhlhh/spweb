package com.inf.sys.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inf.sys.user.mapper.TbSysLoginLogMapper;
import com.inf.sys.user.service.TbSysLoginLogService;
import com.inf.system.utiles.GsonUtil;
import com.inf.system.utiles.JsonBean;
import com.inf.system.utiles.page.PageInfo;
import com.inf.system.utiles.page.PageUtil;

@Service
public class TbSysLoginLogServiceImpl implements TbSysLoginLogService {

	@Autowired
	private TbSysLoginLogMapper tbSysLoginLogMapper;
	//登录日志分页查询
	@Override
	public String getLoginLogListPage(Map<String, Object> paramMap,
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
		List<Map<String,Object>> list = tbSysLoginLogMapper.getLoginLogListPage(pageInfo, paramMap);
		PageUtil.buildPageAfterDao(pageInfo, pageInfo.getCurrentPage());
		 
		String pageTotal = pageInfo.getTotalResult() +"";
		// 返回Json格式数据包
		return GsonUtil.toJson(pageTotal,list);
	
	}

	//日志详情
	@Override
	public String getLoginLog(HttpServletRequest request) {
		String lid = request.getParameter("lid");
		Map<String ,Object> loginLog = tbSysLoginLogMapper.getLoginLogDetail(lid);
		return JsonBean.toJson(loginLog);
	}

}

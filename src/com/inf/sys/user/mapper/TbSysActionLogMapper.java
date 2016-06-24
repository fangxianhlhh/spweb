package com.inf.sys.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.inf.sys.user.model.TbSysActionLog;
import com.inf.system.utiles.page.PageInfo;
/**
 * 系统操作日志
 * @author Administrator
 *
 */
public interface TbSysActionLogMapper {

	// 新增系统操作日志
    int insertTbSysActionLog(TbSysActionLog tbSysActionLog);

	/**
	 * 操作日志分页查询
	 * @param page
	 * @param paramMap
	 * @return
	 */
	List<Map<String,Object>>  getSystemlogListPage(@Param("page") PageInfo page,@Param("paramMap") Map<String,Object> paramMap);
	/**
	 * 操作日志详情
	 * @param sId
	 * @return
	 */
	Map<String,Object> getSystemLogDetail(String sId);
}
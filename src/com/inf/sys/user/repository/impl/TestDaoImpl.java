package com.inf.sys.user.repository.impl;


import org.springframework.stereotype.Repository;

import com.inf.base.BaseDao;
import com.inf.sys.user.repository.TestDao;

@Repository
public class TestDaoImpl extends BaseDao implements TestDao{

	public Long getTestIds(String generName){
		Object object= this.selectOne("com.inf.sys.user.mapper.TestMapper.getTestIds",generName);
		Long reault=Long.valueOf(String.valueOf(object));
		return reault;
	}
}

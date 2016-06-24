package com.inf.sys.user.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inf.sys.user.repository.TbSysIdGrneratorRepository;
import com.inf.sys.user.repository.TestDao;
import com.inf.system.utiles.JsonBean;

//test
@Controller
@RequestMapping("test/")
public class TestCollection {

	@Autowired
	private  TbSysIdGrneratorRepository tbSysIdGrneratorRepository;
	
	@Autowired
	private TestDao testDao;
	
	//测试生成唯一id
	@RequestMapping("testgetone.do")
	public void testgetone(@RequestParam(required=true) String generName,
			HttpServletRequest request, HttpServletResponse response){
		
		Long tr=tbSysIdGrneratorRepository.getTbSysIdGeneratorId();
		
		System.out.println("UNEQUEID IS  ====="+tr);
	}
			
	@RequestMapping("testGetgen.do")
	public void testGetgen(@RequestParam(required=true) String generName,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		Map<String, Object> result=new HashMap<String, Object>();
		Long resulte=testDao.getTestIds(generName);
		result.put("success",true);
		result.put("msg",resulte);
		System.out.println( "this is result======"+result);
		PrintWriter out = response.getWriter();
		out.print(JsonBean.toJson(result));
	}
			
			
}

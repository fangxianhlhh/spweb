package com.inf.system.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.inf.system.utiles.Constant;

/**
 * 登录过滤
 * 
 * @author zongxin 上午10:39:04
 */
public class LoginFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 不过滤的url
		String[] notFilter = new String[] { "offLogin.do", "SysUserLogin.do","sheetTabs.jsp"};
		
		// 过滤的url
		String[] isFilter = new String[] { "jsp", "do" };
		
		// 请求的url
		String url = request.getRequestURI();
		
		// 过滤 标记，false表示不需要过虑，true表示需要过虑
		boolean doFilter = false;
		
		// 获取session
		Object obj = request.getSession().getAttribute(Constant.USER_SESSION);
		Object oamObj = request.getSession().getAttribute("oamUserMap");
		
		// 要过滤 的url
		for (String s : isFilter) {
			// 如果是已“jsp”或“do”结尾的url
			if (url.indexOf(s) != -1) {
				// 如果请求地址包含“oam”
				if (url.indexOf("oam") != -1) {
					doFilter = (null == oamObj) ? true : false;
					break;
				} else {
					doFilter = (null == obj) ? true : false;
					break;
				}
			}
		}
		
		// 不过滤的url，关于离线包的
		for (String s : notFilter) {
			if (url.indexOf(s) != -1) {
				doFilter = (request.getParameter("userName") == null ? true : false);
				break;
			}
		}

		if (url.indexOf("app/") != -1 || url.indexOf("app1/") != -1) {
			doFilter = false;
		}
		
		if (url.indexOf("news.jsp") != -1) {
			doFilter = false;
		}

		// 执行过滤
		if (doFilter) {
			// 如果session中不存在登录者实体，则弹出框提示重新登录
			// 设置request和response的字符集，防止乱码
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();

			String loginPage = null;
			loginPage = "";
			
			StringBuilder builder = new StringBuilder();
			builder.append("<script type=\"text/javascript\">");
			builder.append("window.top.location.href='");
			builder.append(loginPage);
			builder.append("';");
			builder.append("</script>");
			out.print(builder.toString());
		} else {
			// 如果session中存在登录者实体，则继续
			filterChain.doFilter(request, response);
		}
	}

}
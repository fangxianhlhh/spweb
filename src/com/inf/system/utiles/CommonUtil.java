package com.inf.system.utiles;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class CommonUtil {
	/**
	 * 输出响应流
	 * 
	 * @param response
	 *            Http响应
	 * @param content
	 *            输出的内容
	 */
	public static void printResponse(HttpServletResponse response,
			Object content) {
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.print(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.flush();
			}
		}
	}
}

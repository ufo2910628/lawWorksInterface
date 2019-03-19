package com.dtxx.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class HttpServlerResponseUtil {
	public static void println(HttpServletResponse response,String str) {
		//设置编码格式
		 response.setCharacterEncoding("UTF-8");
	        response.setContentType("application/json;charset=UTF-8");
			try {
				response.getWriter().println(str);
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

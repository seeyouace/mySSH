package com.mySSH.base.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageRedirect extends HttpServlet{
	
		public void doGet(HttpServletRequest request,
	            HttpServletResponse response)
		    throws ServletException, IOException
		{
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.write("<html>");
		out.write("<title>");
		out.write("Servlet测试");
		out.write("</title>");
		out.write("<body>");
		out.write("HelloWorld!"+request.getRequestURI());
		out.write("</body>");
		out.write("</html>");
		
		out.close();
		
		}
}

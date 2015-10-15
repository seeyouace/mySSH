package com.mySSH.base.filter;

import java.io.IOException;

import javax.servlet.Filter;  
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DomainURLFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;  
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String requestURI = request.getRequestURI();  
		String serverName = request.getServerName().toLowerCase(); 
		
		System.out.println("doFilter-serverName:"+serverName);
		System.out.println("doFilter-requestURI:"+requestURI);
		
		getRealRequestURI(serverName, requestURI, request, response, filterChain);
	}

	private void getRealRequestURI(String serverName, String requestURI,
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) {
		System.out.println("getRealRequestURI-serverName:"+serverName);
		System.out.println("getRealRequestURI-requestURI:"+requestURI);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("init");
	}
	
}

package com.heima.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MyFilter implements Filter{


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		
		HttpServletRequest hsr = (HttpServletRequest)Proxy.newProxyInstance(
				request.getClass().getClassLoader(), 
				request.getClass().getInterfaces(), 
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						//判断是不是要增强的那个方法
						if("getParameter".equals(method.getName())) {
							//获得提交的方式
							String m = request.getMethod();
							if("get".equalsIgnoreCase(m)) {
								
								String s = (String) method.invoke(request, args);
								
								s = new String(s.getBytes("iso8859-1"), "utf-8");
								
								return s;
							}else if("post".equalsIgnoreCase(m)) {
								request.setCharacterEncoding("utf-8");
							}
						}
						
						
						return method.invoke(request, args);
					}
				});
		
		
		
		chain.doFilter(hsr, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

package com.heima.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Class clazz = this.getClass();
		try {
			Method method = clazz.getMethod(request.getParameter("method"), HttpServletRequest.class,HttpServletResponse.class);
			String invoke = (String)method.invoke(clazz.newInstance(), request,response);
			if(invoke!=null) {
				request.getRequestDispatcher(invoke).forward(request, response);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

package com.heima.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.service.CategoryService;
import com.heima.serviceImpl.CategoryServiceImpl;


public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public String findCategoryAll(HttpServletRequest request,HttpServletResponse response)  {
		response.setCharacterEncoding("utf-8");
		
		try {
			CategoryService categoryService = new CategoryServiceImpl();
			String json = categoryService.findCategoryAll();
//			String json = categoryService.fromByRedis();
			
//			System.out.println(json);
			response.getWriter().print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "²éÑ¯Êý¾ÝÊ§°Ü");
			return "/jsp/info.jsp";
		}
		
		return null;
	}
	

}

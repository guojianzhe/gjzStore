package com.heima.web;


import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.domain.PageBean;
import com.heima.domain.Product;
import com.heima.service.ProductService;
import com.heima.serviceImpl.ProductServiceImpl;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findProduct(HttpServletRequest request,HttpServletResponse response)  {
		try {
			String cid = request.getParameter("cid");
			
			int page = Integer.parseInt(request.getParameter("page"));
			
			int pageSize = 12;
			
			ProductService productService = new ProductServiceImpl();
			
			PageBean findProduct = productService.findProduct(cid,page,pageSize);
			request.setAttribute("cid", cid);
			request.setAttribute("pb", findProduct);
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "查询商品失败");
			return "/jsp/info.jsp";
			
		}
		return "/jsp/product_list.jsp";
	}
	
	public String findByPid(HttpServletRequest request,HttpServletResponse response)  {
		
		try {
			String pid = request.getParameter("pid");
			
			ProductService productService = new ProductServiceImpl();
			Product product = productService.findByPid(pid);
			request.setAttribute("product", product);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "查询失败");
			return "/jsp/info.jsp";
		}
		
		
		
		return "/jsp/product_info.jsp";
	}
	
	
	
}

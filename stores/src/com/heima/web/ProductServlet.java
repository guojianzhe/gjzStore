package com.heima.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.domain.PageBean;
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
			
			PageBean pb = productService.findProduct(cid,page,pageSize);
			
			request.setAttribute("cid", cid);
			request.setAttribute("pb", pb);
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "≤È—Ø…Ã∆∑ ß∞‹");
			return "/jsp/info.jsp";
			
		}
		return "/jsp/product_list.jsp";
	}
}

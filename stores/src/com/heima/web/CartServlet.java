package com.heima.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.heima.domain.Cart;
import com.heima.domain.CartItem;
import com.heima.domain.Product;
import com.heima.service.ProductService;
import com.heima.serviceImpl.ProductServiceImpl;

public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
     
	private Cart getCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart==null) {
			//创建购物车
			cart = new Cart();
			//放在session
			session.setAttribute("cart", cart);
		}
		return cart;
		
	}
	//显示购物车
	public String myCart(HttpServletRequest request,HttpServletResponse response) {
		
		
		return "/jsp/cart.jsp";
	}
	
	
	//添加购物项
	public String addCart(HttpServletRequest request,HttpServletResponse response) {
		
		try {
			int count = Integer.parseInt(request.getParameter("count"));
			String pid = request.getParameter("pid");
			
			ProductService productService = new ProductServiceImpl();
			Product product = productService.findByPid(pid);
			
			CartItem cartItem = new CartItem();
			cartItem.setCount(count);
			cartItem.setProduct(product);
			Cart cart = getCart(request);
			cart.add(cartItem);
			request.setAttribute("cart", cart);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			request.setAttribute("msg", "添加商品失败");
			return "/jsp/info.jsp";
		}
		
		return "/jsp/cart.jsp";
	}

	//删除购物项
	
	public String remove(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		//获取pid
		String key = request.getParameter("pid");
		
		//获取购物车
		Cart cart = getCart(request);
		cart.remove(key);
		
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		
		
		return null;
	}
	
	//清空购物车
	public String clear(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		Cart cart = getCart(request);
		
		cart.clear();
		
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		
		return null;
	}
	
   
	
}

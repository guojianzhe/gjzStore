package com.heima.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.heima.domain.Cart;
import com.heima.domain.CartItem;
import com.heima.domain.OrderItem;
import com.heima.domain.Orders;
import com.heima.domain.PageBean;
import com.heima.domain.User;
import com.heima.service.OrderService;
import com.heima.serviceImpl.OrderServiceImpl;
import com.heima.utils.UUIDUtils;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//将购物车添加到订单
	//将购物车添加到订单
	public String addOrder(HttpServletRequest request,HttpServletResponse response) {
		try {
			
		
		//判断用户有没有登陆
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null) {
			request.setAttribute("msg","没有登陆,请登录");
			return "/jsp/info.jsp";
		}
		
		Orders orders = new Orders();
		orders.setOid(UUIDUtils.getUUID());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		orders.setOrdertime(date);
		orders.setState(0);
		//在购物车中将订单的总金额拿到
		Cart cart = (Cart)session.getAttribute("cart");
		orders.setTotal(cart.getTotal());
		orders.setUser(user);
		
		List<OrderItem> list = orders.getList();
		
		Map<String, CartItem> map = cart.getMap();
		
		for(String key:map.keySet()) {
			//循环一次创建一个订单项的对象
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(UUIDUtils.getUUID());
			orderItem.setCount(map.get(key).getCount());
			orderItem.setSubtotal(map.get(key).getSubtotal());
			orderItem.setProduct(map.get(key).getProduct());
			orderItem.setOrders(orders);
			//添加订单项
			list.add(orderItem);
		}
		
			OrderService orderService = new OrderServiceImpl(); 
			//保存订单
			orderService.save(orders);
			
			//清空购物车
			cart.clear();
			//保存订单商品项
			request.setAttribute("orders", orders);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "添加订单失败");
			return "/jsp/info.jsp";
		}
		return "/jsp/order_info.jsp";
	}
	//
	//我的购物车
	
	public String myOrder(HttpServletRequest request,HttpServletResponse response) {
		try {
			//获取当前页
			int page = Integer.parseInt(request.getParameter("page"));
			//显示的条数
			int pageSize = 3;
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			OrderService orderService = new OrderServiceImpl();
			
			PageBean pb = orderService.findByOrderId(user.getUid(),page,pageSize);
			request.setAttribute("pb", pb);
			
			
		} catch (Exception e) {
			request.setAttribute("msg", "查询订单失败");
			e.printStackTrace();
			return "/jsp/info.jsp";
		}
		
		return "/jsp/order_list.jsp";
	}
	
	
	public String findByOid(HttpServletRequest request,HttpServletResponse response)  {
		
		try {
			String oid = request.getParameter("oid");
			
			
			OrderService orderService = new OrderServiceImpl();
			
			Orders findByOid = orderService.findByOid(oid);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "/jsp/order_info.jsp";
	}
}

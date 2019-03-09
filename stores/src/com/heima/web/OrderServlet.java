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
	//�����ﳵ��ӵ�����
	//�����ﳵ��ӵ�����
	public String addOrder(HttpServletRequest request,HttpServletResponse response) {
		try {
			
		
		//�ж��û���û�е�½
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null) {
			request.setAttribute("msg","û�е�½,���¼");
			return "/jsp/info.jsp";
		}
		
		Orders orders = new Orders();
		orders.setOid(UUIDUtils.getUUID());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		orders.setOrdertime(date);
		orders.setState(0);
		//�ڹ��ﳵ�н��������ܽ���õ�
		Cart cart = (Cart)session.getAttribute("cart");
		orders.setTotal(cart.getTotal());
		orders.setUser(user);
		
		List<OrderItem> list = orders.getList();
		
		Map<String, CartItem> map = cart.getMap();
		
		for(String key:map.keySet()) {
			//ѭ��һ�δ���һ��������Ķ���
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(UUIDUtils.getUUID());
			orderItem.setCount(map.get(key).getCount());
			orderItem.setSubtotal(map.get(key).getSubtotal());
			orderItem.setProduct(map.get(key).getProduct());
			orderItem.setOrders(orders);
			//��Ӷ�����
			list.add(orderItem);
		}
		
			OrderService orderService = new OrderServiceImpl(); 
			//���涩��
			orderService.save(orders);
			
			//��չ��ﳵ
			cart.clear();
			//���涩����Ʒ��
			request.setAttribute("orders", orders);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "��Ӷ���ʧ��");
			return "/jsp/info.jsp";
		}
		return "/jsp/order_info.jsp";
	}
	//
	//�ҵĹ��ﳵ
	
	public String myOrder(HttpServletRequest request,HttpServletResponse response) {
		try {
			//��ȡ��ǰҳ
			int page = Integer.parseInt(request.getParameter("page"));
			//��ʾ������
			int pageSize = 3;
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			OrderService orderService = new OrderServiceImpl();
			
			PageBean pb = orderService.findByOrderId(user.getUid(),page,pageSize);
			request.setAttribute("pb", pb);
			
			
		} catch (Exception e) {
			request.setAttribute("msg", "��ѯ����ʧ��");
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

package com.heima.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.heima.dao.OrderDao;
import com.heima.daoImpl.OrderDaoImpl;
import com.heima.domain.OrderItem;
import com.heima.domain.Orders;
import com.heima.domain.PageBean;
import com.heima.service.OrderService;
import com.heima.utils.JDBCUtils;

public class OrderServiceImpl implements OrderService {

	@Override
	public void save(Orders orders) throws SQLException {
		
		Connection  conn = null;
		try {
			
			
			OrderDao orderDao = new OrderDaoImpl();
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			orderDao.save(orders,conn);
			
			List<OrderItem> lists = orders.getList();
			
			for(OrderItem orderItem:lists) {
				orderDao.saveOrdersItem(orderItem,conn);
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			
			conn.rollback();
		}
	}


	@Override
	public PageBean findByOrderId(String uid, int page, int pageSize) throws Exception {
		OrderDao orderDao = new OrderDaoImpl();
		
		List<Orders> list = orderDao.findOrder(uid,(page-1)*pageSize,pageSize);
		
		//获取总条数
		int totalCount = orderDao.count(uid);
		
		
		//获取页数
		int totalPage = 0;
		if(totalCount%pageSize==0) {
			totalPage = totalCount/pageSize;
		}else {
			totalPage = totalCount/pageSize+1;
		}
		
		
		//封装pageBean
		PageBean pageBean = new PageBean();
		
		pageBean.setPageSize(pageSize);
		pageBean.setPage(page);
		pageBean.setList(list);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		
		return pageBean;
	}


	@Override
	public Orders findByOid(String oid) throws Exception {
		
		OrderDao orderDao = new OrderDaoImpl();
		Orders orders = orderDao.findByOid(oid);
		
		
		return orders;
	}

}

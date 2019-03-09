package com.heima.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.heima.domain.OrderItem;
import com.heima.domain.Orders;

public interface OrderDao {

	void save(Orders orders, Connection conn) throws SQLException;

	void saveOrdersItem(OrderItem orderItem,Connection conn) throws SQLException;

	List<Orders> findOrder(String uid, int i, int pageSize) throws  Exception;

	int count(String uid) throws SQLException;

	Orders findByOid(String oid) throws Exception;
	
	


}

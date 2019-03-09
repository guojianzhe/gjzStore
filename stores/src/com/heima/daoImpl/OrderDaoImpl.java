package com.heima.daoImpl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.heima.dao.OrderDao;
import com.heima.domain.OrderItem;
import com.heima.domain.Orders;
import com.heima.domain.Product;
import com.heima.utils.JDBCUtils;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void save(Orders orders,Connection conn) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		
		qr.update(conn,sql,orders.getOid(),orders.getOrdertime(),orders.getTotal(),
				orders.getState(),orders.getAddress(),orders.getName(),orders.getTelephone(),orders.getUser().getUid());
		
	}

	@Override
	public void saveOrdersItem(OrderItem orderItem,Connection conn) throws SQLException {
		QueryRunner qr = new QueryRunner();
		
		String sql = "insert into orderitem values(?,?,?,?,?)";
		
		qr.update(conn,sql,orderItem.getItemid(),orderItem.getCount(),orderItem.getSubtotal(),orderItem.getProduct().getPid(),orderItem.getOrders().getOid());
		
		
	}

	@Override
	public List<Orders> findOrder(String uid, int i, int pageSize) throws Exception {
		
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		
		String sql = "select * from orders where uid=? limit ?,?";
		
		List<Orders> list = qr.query(sql, new BeanListHandler<Orders>(Orders.class),uid,i,pageSize);
		
		for (Orders orders : list) {
			
			List<OrderItem> ordersList = orders.getList();
			String sql1 = "select * from orderitem o,product p where o.oid =? and o.pid=p.pid";
			
			List<Map<String, Object>> mList = qr.query(sql1, new MapListHandler(),orders.getOid());
			
			
			for (Map<String, Object> map: mList) {

				OrderItem orderItem = new OrderItem();
				
				BeanUtils.populate(orderItem, map);
				Product product = new Product();
				BeanUtils.populate(product, map);
				
				
				orderItem.setProduct(product);
				ordersList.add(orderItem);
				
			}
		}
		
		
		return list;
	}

	@Override
	public int count(String uid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		
		String sql = "select count(*) from orders where uid=?";
		
		Long id = (Long)qr.query(sql, new ScalarHandler(),uid);
		
		return id.intValue();
	}

	@Override
	public Orders findByOid(String oid) throws Exception {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		
		String sql = "select * from orders where oid=?";
		
		Orders orders = qr.query(sql, new BeanHandler<Orders>(Orders.class),oid);
		
		if(orders!=null) {
			List<OrderItem> orderItemList = orders.getList();
			String sql2 = "select * from orderItem o,product p where o.oid=? and o.pid=p.pid";
			
			List<Map<String, Object>> mList = qr.query(sql2, new MapListHandler(),oid);
			
			for (Map<String, Object> map : mList) {
				
				OrderItem orderItem = new OrderItem();
				BeanUtils.populate(orderItem, map);
				Product product = new Product();
				BeanUtils.populate(product, map);
				
				
				orderItem.setProduct(product);
				orderItemList.add(orderItem);
			}
		}
		
		
		return orders;
	}

}

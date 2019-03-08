package com.heima.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.heima.dao.ProductDao;
import com.heima.domain.Product;
import com.heima.utils.JDBCUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public int findCount(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(*) from product where cid=?";
		Long num = (Long) qr.query(sql, new ScalarHandler(),cid);
		return num.intValue();
	}

	@Override
	public List<Product> findList(String cid, int i, int pageSize) throws SQLException {
		
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		
		String sql = "select * from product where cid=? limit ?,?"; 
		
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class),cid,i,pageSize);
		
		return list;
	}

}

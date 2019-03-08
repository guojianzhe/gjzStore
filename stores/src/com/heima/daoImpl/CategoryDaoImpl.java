package com.heima.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.heima.dao.CategoryDao;
import com.heima.domain.CateGory;
import com.heima.utils.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<CateGory> findCategoryAll() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		
		String sql = "select * from category";
		List<CateGory> list = qr.query(sql, new BeanListHandler<CateGory>(CateGory.class));
		
		return list;
	}

}

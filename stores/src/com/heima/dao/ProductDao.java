package com.heima.dao;

import java.sql.SQLException;
import java.util.List;

import com.heima.domain.Product;

public interface ProductDao {

	int findCount(String cid) throws SQLException;

	List<Product> findList(String cid, int i, int pageSize) throws SQLException;

}

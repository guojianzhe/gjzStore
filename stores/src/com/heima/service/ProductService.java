package com.heima.service;

import java.sql.SQLException;

import com.heima.domain.PageBean;
import com.heima.domain.Product;

public interface ProductService {

	PageBean findProduct(String cid, int page, int pageSize) throws SQLException, Exception;

	Product findByPid(String pid) throws SQLException;

}

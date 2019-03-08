package com.heima.service;

import java.sql.SQLException;

import com.heima.domain.PageBean;

public interface ProductService {

	PageBean findProduct(String cid, int page, int pageSize) throws SQLException, Exception;

}

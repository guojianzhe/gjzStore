package com.heima.service;

import java.sql.SQLException;

import com.heima.domain.Orders;
import com.heima.domain.PageBean;

public interface OrderService {

	void save(Orders orders) throws SQLException;

	PageBean findByOrderId(String uid, int page, int pageSize) throws Exception;

	Orders findByOid(String oid) throws Exception;

}

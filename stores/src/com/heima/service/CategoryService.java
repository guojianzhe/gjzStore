package com.heima.service;

import java.sql.SQLException;


public interface CategoryService {

	String findCategoryAll() throws SQLException;

	String fromByRedis() throws SQLException;

}

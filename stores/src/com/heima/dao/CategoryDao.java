package com.heima.dao;

import java.sql.SQLException;
import java.util.List;

import com.heima.domain.CateGory;

public interface CategoryDao {

	List<CateGory> findCategoryAll() throws SQLException;

}

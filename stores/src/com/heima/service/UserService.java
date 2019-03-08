package com.heima.service;

import java.sql.SQLException;

import com.heima.domain.User;

public interface UserService {

	public void save(User user) throws SQLException;

	public User findByCode(String code) throws SQLException;

	public void update(User user) throws SQLException;

	public User login(String username, String password) throws SQLException;
	
}

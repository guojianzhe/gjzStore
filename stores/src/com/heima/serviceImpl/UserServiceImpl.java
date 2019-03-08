package com.heima.serviceImpl;

import java.sql.SQLException;

import com.heima.dao.UserDao;
import com.heima.daoImpl.UserDaoImpl;
import com.heima.domain.User;
import com.heima.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public void save(User user) throws SQLException {
		UserDao userDao = new UserDaoImpl();
		userDao.save(user);
		
	}

	@Override
	public User findByCode(String code) throws SQLException {
		UserDao userDao = new UserDaoImpl();
		User user = userDao.findByCode(code);
		return user;
	}

	@Override
	public void update(User user) throws SQLException {
		UserDao userDao = new UserDaoImpl();
		userDao.update(user);
		
	}

	@Override
	public User login(String username, String password) throws SQLException {
		UserDao userDao = new UserDaoImpl();
		User user = userDao.login(username,password);
		return user;
	}

}

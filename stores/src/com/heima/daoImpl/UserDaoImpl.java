package com.heima.daoImpl;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.heima.dao.UserDao;
import com.heima.domain.User;
import com.heima.utils.JDBCUtils;

public class UserDaoImpl implements UserDao{

	@Override
	public void save(User user) throws SQLException {
		
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?)";
		Object[] obj = {user.getUid(),user.getUsername(),user.getPassword(),user.getName(),
		user.getEmail(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()}; 
		
		qr.update(sql, obj);
		
	}

	@Override
	public User findByCode(String code) throws SQLException {
		
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where code=?";
		User user = qr.query(sql, new BeanHandler<User>(User.class),code);
		
		return user;
	}

	@Override
	public void update(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update user set state=? where uid=?";
		
		qr.update(sql,user.getState(),user.getUid());
		
	}

	@Override
	public User login(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where username=? and password=?";
		
		User user = qr.query(sql, new BeanHandler<User>(User.class),username,password);
		
		return user;
	}

}

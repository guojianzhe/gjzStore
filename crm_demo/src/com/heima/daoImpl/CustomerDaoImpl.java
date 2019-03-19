package com.heima.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.heima.dao.CustomerDao;
import com.heima.domain.Customer;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public List<Customer> findAll() {
		 Configuration configuration = new Configuration();
		 //加载src下的hibernate.cfg.xml 文件
		 configuration.configure();
		 SessionFactory factory = configuration.buildSessionFactory();
		 Session session = factory.openSession();
		 Transaction ts = session.beginTransaction();
		 
		 //查
		 Criteria iter = session.createCriteria(Customer.class);
		 List<Customer> list = iter.list();
		
		 ts.commit();
		 session.close();
		 
		return list;
	}

}

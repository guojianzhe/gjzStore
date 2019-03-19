package com.heima.serviceImpl;

import java.util.List;

import com.heima.dao.CustomerDao;
import com.heima.daoImpl.CustomerDaoImpl;
import com.heima.domain.Customer;
import com.heima.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public List<Customer> findAll() {
		
		CustomerDao customerDao =  new CustomerDaoImpl();
		List<Customer> list = customerDao.findAll();
		
		return list;
	}

	@Override
	public void add(Customer customer) {
		
		CustomerDao customerDao = new CustomerDaoImpl();
		
		customerDao.add(customer);
	}

}

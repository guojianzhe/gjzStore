package com.heima.dao;

import java.util.List;

import com.heima.domain.Customer;

public interface CustomerDao {

	List<Customer> findAll();

	void add(Customer customer);

}

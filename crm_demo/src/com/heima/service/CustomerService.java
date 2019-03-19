package com.heima.service;

import java.util.List;

import com.heima.domain.Customer;

public interface CustomerService {

	List<Customer> findAll();

	void add(Customer customer);

}

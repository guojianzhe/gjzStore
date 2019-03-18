package com.heima.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.struts2.ServletActionContext;

import com.heima.domain.Customer;
import com.heima.service.CustomerService;
import com.heima.serviceImpl.CustomerServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerAction extends ActionSupport{


	public String findAll() {
		
		CustomerService customerService=new CustomerServiceImpl();
		
		List<Customer> list = customerService.findAll();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list",list);
		return "success";
	}
	
	
}

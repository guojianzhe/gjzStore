package com.heima.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.struts2.ServletActionContext;

import com.heima.domain.Customer;
import com.heima.service.CustomerService;
import com.heima.serviceImpl.CustomerServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import sun.awt.ModalExclude;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	private Customer customer = new Customer();
	
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}
	//查询所有客户
	public String findAll() {
		
		CustomerService customerService=new CustomerServiceImpl();
		
		List<Customer> list = customerService.findAll();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list",list);
		return "findAll";
	}
	//跳转到添加页面
	public String addUI() {
		
		return "addUI";
	}
	
	//保存客户
	public String add() {
		
		CustomerService customerService = new CustomerServiceImpl();
		customerService.add(customer);
		
		return "add";
	}
	
	
	
}

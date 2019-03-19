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
	//��ѯ���пͻ�
	public String findAll() {
		
		CustomerService customerService=new CustomerServiceImpl();
		
		List<Customer> list = customerService.findAll();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list",list);
		return "findAll";
	}
	//��ת�����ҳ��
	public String addUI() {
		
		return "addUI";
	}
	
	//����ͻ�
	public String add() {
		
		CustomerService customerService = new CustomerServiceImpl();
		customerService.add(customer);
		
		return "add";
	}
	
	
	
}

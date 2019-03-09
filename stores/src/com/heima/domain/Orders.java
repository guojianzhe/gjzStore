package com.heima.domain;

import java.util.ArrayList;
import java.util.List;

// ��������
public class Orders 
{
	private String oid;   // ����
	private String ordertime; //�µ�ʱ��
	private double total; // �ܽ��
	private Integer state; // ״̬ 0:δ����  1 δ����  2 δ����  3 �����
	private String address; // �ջ��˵�ַ
	private String name;  // �ջ�������
	private String telephone; // �ջ��˵绰
	  // �����û�--���
	private User user;  // ���������ĸ��û�
	
	//��Ҫ�����������
	List<OrderItem> list = new ArrayList<OrderItem>();
	
	public List<OrderItem> getList() {
		return list;
	}
	public void setList(List<OrderItem> list) {
		this.list = list;
	}
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Orders [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state
				+ ", address=" + address + ", name=" + name + ", telephone=" + telephone + ", user=" + user + ", list="
				+ list + "]";
	}
	
	
	
	
}


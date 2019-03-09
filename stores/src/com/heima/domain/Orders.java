package com.heima.domain;

import java.util.ArrayList;
import java.util.List;

// 订单对象
public class Orders 
{
	private String oid;   // 主键
	private String ordertime; //下单时间
	private double total; // 总金额
	private Integer state; // 状态 0:未付款  1 未发货  2 未评价  3 已完成
	private String address; // 收货人地址
	private String name;  // 收货人姓名
	private String telephone; // 收货人电话
	  // 所属用户--外键
	private User user;  // 订单属于哪个用户
	
	//还要订单项的数据
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


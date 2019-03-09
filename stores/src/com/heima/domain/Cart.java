package com.heima.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	//购物项集合  删除快,并且有序
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();
	
	//总金额
	private double total;

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}

	public double getTotal() {  
		return total;
	}
	
	//清空购物车
	public void clear() {
		//将总金额设置为零
		total = 0;
		//将购物项清空
		map.clear();
	}
	
	//删除
	public void remove(String key) {
		//去购物项集合中删除根据key对应的购物项
		CartItem cartItem = map.remove(key);
		//将总金额减去移除的购物项中的金额
		total = total-cartItem.getSubtotal();
	}
	
	//添加
	public void add(CartItem cartItem) {
		//判断添加的购物项集合中有没有新添加的购物项
		
		//没有
		CartItem _cartItem = map.get(cartItem.getProduct().getPid());
		if(_cartItem==null) {
			map.put(cartItem.getProduct().getPid(), cartItem);
			//总金额=总金额+购物项小计
			total = total+cartItem.getSubtotal();
		}else {
			//集合中有新添加的购物项
			//将这一种购物项的数量相加
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
			//将总金额相加
			total = total +cartItem.getSubtotal();
			
		}
		
	}

	@Override
	public String toString() {
		return "Cart [map=" + map + ", total=" + total + "]";
	}
	
	
	
	
}

package com.heima.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	//�������  ɾ����,��������
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();
	
	//�ܽ��
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
	
	//��չ��ﳵ
	public void clear() {
		//���ܽ������Ϊ��
		total = 0;
		//�����������
		map.clear();
	}
	
	//ɾ��
	public void remove(String key) {
		//ȥ���������ɾ������key��Ӧ�Ĺ�����
		CartItem cartItem = map.remove(key);
		//���ܽ���ȥ�Ƴ��Ĺ������еĽ��
		total = total-cartItem.getSubtotal();
	}
	
	//���
	public void add(CartItem cartItem) {
		//�ж���ӵĹ����������û������ӵĹ�����
		
		//û��
		CartItem _cartItem = map.get(cartItem.getProduct().getPid());
		if(_cartItem==null) {
			map.put(cartItem.getProduct().getPid(), cartItem);
			//�ܽ��=�ܽ��+������С��
			total = total+cartItem.getSubtotal();
		}else {
			//������������ӵĹ�����
			//����һ�ֹ�������������
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
			//���ܽ�����
			total = total +cartItem.getSubtotal();
			
		}
		
	}

	@Override
	public String toString() {
		return "Cart [map=" + map + ", total=" + total + "]";
	}
	
	
	
	
}

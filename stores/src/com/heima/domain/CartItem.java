package com.heima.domain;

public class CartItem {
	//��Ʒ����
	private Product product;
	//��Ʒ����
	private Integer count;
	 // ��ƷС��
	private double  subtotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public double getSubtotal() {
		return product.getShop_price()*count;
	}
	@Override
	public String toString() {
		return "CartItem [product=" + product + ", count=" + count + ", subtotal=" + subtotal + "]";
	}
	
	
	
}

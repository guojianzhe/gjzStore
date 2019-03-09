package com.heima.domain;

public class CartItem {
	//商品对象
	private Product product;
	//商品数量
	private Integer count;
	 // 商品小计
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

package com.yaksha.assignment;

public class Order {

	private String orderId;
	private double totalAmount;

	private Product product;

	public Order() {
		this.orderId = "ORD12345";
		this.totalAmount = 1000.0;
	}

	// Getters and Setters
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}

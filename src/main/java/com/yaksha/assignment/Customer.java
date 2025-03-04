package com.yaksha.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Customer {

	private String name;
	private String customerId;

	@Autowired
	private Order order;

	public Customer() {
		this.name = "John Doe";
		this.customerId = "CUST001";
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void placeOrder() {
		System.out.println(name + " placed an order: " + order.getOrderId());
	}
}

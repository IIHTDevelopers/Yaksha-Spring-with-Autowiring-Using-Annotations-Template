package com.yaksha.assignment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Product {

	private String name;
	private double price;

	public Product(@Value("Laptop") String name, @Value("1000.0") double price) {
		this.name = name;
		this.price = price;
	}

	// Getters
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
}

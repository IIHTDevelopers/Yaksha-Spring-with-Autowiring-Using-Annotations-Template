package com.yaksha.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		// Run the Spring Boot application
		ApplicationContext context = SpringApplication.run(ECommerceApplication.class, args);

		// Retrieving beans from the Spring container
		Customer customer = context.getBean(Customer.class);
		Order order = context.getBean(Order.class);

		// Display customer and order details
		System.out.println("Customer: " + customer.getName() + " (" + customer.getCustomerId() + ")");
		System.out.println("Order ID: " + order.getOrderId() + ", Total Amount: " + order.getTotalAmount());

		// Place an order
		customer.placeOrder();
	}
}

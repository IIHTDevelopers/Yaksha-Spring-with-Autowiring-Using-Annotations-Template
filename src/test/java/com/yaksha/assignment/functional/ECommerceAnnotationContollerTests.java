package com.yaksha.assignment.functional;

import static com.yaksha.assignment.utils.TestUtils.businessTestFile;
import static com.yaksha.assignment.utils.TestUtils.currentTest;
import static com.yaksha.assignment.utils.TestUtils.testReport;
import static com.yaksha.assignment.utils.TestUtils.yakshaAssert;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yaksha.assignment.Customer;
import com.yaksha.assignment.Order;
import com.yaksha.assignment.utils.CustomParser;

@SpringBootTest
public class ECommerceAnnotationContollerTests {

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Autowired
	private Customer customer;

	@Autowired
	private Order order;

	// Test if the Spring Boot application context loads correctly
	@Test
	public void testApplicationContextLoads() throws IOException {
		try {
			// Assert that the application context loads and the beans are created
			boolean customerLoaded = customer != null;
			boolean orderLoaded = order != null;

			System.out.println("Customer bean loaded: " + customerLoaded);
			System.out.println("Order bean loaded: " + orderLoaded);

			// Auto-grading with yakshaAssert
			yakshaAssert(currentTest(), customerLoaded && orderLoaded, businessTestFile);
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	// Test if the customer and order details are correctly initialized and business
	// logic works
	@Test
	public void testECommerceApplicationFunctionality() throws IOException {
		try {
			// Verify if customer details are correct
			boolean customerNameValid = customer.getName() != null && customer.getName().equals("John Doe");
			boolean customerIdValid = customer.getCustomerId() != null && customer.getCustomerId().equals("CUST001");

			// Verify if order details are correct
			boolean orderIdValid = order.getOrderId() != null && order.getOrderId().equals("ORD12345");
			boolean orderAmountValid = order.getTotalAmount() == 1000.0;

			// Log the checks for customer and order
			System.out.println("Customer name valid: " + customerNameValid);
			System.out.println("Customer ID valid: " + customerIdValid);
			System.out.println("Order ID valid: " + orderIdValid);
			System.out.println("Order total amount valid: " + orderAmountValid);

			// Assert customer and order details
			yakshaAssert(currentTest(), customerNameValid && customerIdValid && orderIdValid && orderAmountValid,
					businessTestFile);
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	// Test if ECommerceApplication class has @SpringBootApplication annotation
	@Test
	public void testECommerceApplicationHasSpringBootApplication() throws Exception {
		String filePath = "src/main/java/com/yaksha/assignment/ECommerceApplication.java"; // Path to your
																							// ECommerceApplication.java

		try {
			// Check for @SpringBootApplication annotation in class-level
			boolean result = CustomParser.checkClassAnnotation(filePath, "SpringBootApplication");

			// Auto-grading with yakshaAssert
			yakshaAssert(currentTest(), result, businessTestFile);
		} catch (Exception e) {
			e.printStackTrace();
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	// Test if Customer class has @Component annotation
	@Test
	public void testCustomerHasComponent() throws Exception {
		String filePath = "src/main/java/com/yaksha/assignment/Customer.java"; // Path to your Customer.java

		try {
			// Check for @Component annotation in class-level
			boolean classLevelAnnotationResult = CustomParser.checkClassAnnotation(filePath, "Component");

			// Check for @Autowired annotation on the 'order' field in the Customer class
			boolean fieldLevelAnnotationResult = CustomParser.checkFieldAnnotation(filePath, "Order", "Autowired");

			// Auto-grading with yakshaAssert
			yakshaAssert(currentTest(), classLevelAnnotationResult && fieldLevelAnnotationResult, businessTestFile);
		} catch (Exception e) {
			e.printStackTrace();
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	// Test if Product class has @Component annotation
	@Test
	public void testProductHasComponent() throws Exception {
		String filePath = "src/main/java/com/yaksha/assignment/Product.java"; // Path to your Product.java

		try {
			// Check for @Component annotation in class-level
			boolean classLevelAnnotationResult = CustomParser.checkClassAnnotation(filePath, "Component");

			// Check if the constructor of Product class has @Value annotation on both
			// 'name' and 'price' parameters
			boolean result = CustomParser.checkConstructorParameterAnnotation(filePath, "Value", "name", "price");

			// Auto-grading with yakshaAssert
			yakshaAssert(currentTest(), classLevelAnnotationResult && result, businessTestFile);
		} catch (Exception e) {
			e.printStackTrace();
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	// Test if Order class has @Component annotation
	@Test
	public void testOrderHasComponent() throws Exception {
		String filePath = "src/main/java/com/yaksha/assignment/Order.java"; // Path to your Order.java

		try {
			// Check for @Component annotation in class-level
			boolean classLevelAnnotationResult = CustomParser.checkClassAnnotation(filePath, "Component");

			// Check for @Autowired annotation on the 'order' field in the Customer class
			boolean fieldLevelAnnotationResult = CustomParser.checkFieldAnnotation(filePath, "Product", "Autowired");

			// Auto-grading with yakshaAssert
			yakshaAssert(currentTest(), classLevelAnnotationResult && fieldLevelAnnotationResult, businessTestFile);
		} catch (Exception e) {
			e.printStackTrace();
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}
}

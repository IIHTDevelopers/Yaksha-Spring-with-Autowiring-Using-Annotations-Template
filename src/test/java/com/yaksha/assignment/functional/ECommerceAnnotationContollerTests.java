package com.yaksha.assignment.functional;

import static com.yaksha.assignment.utils.TestUtils.businessTestFile;
import static com.yaksha.assignment.utils.TestUtils.currentTest;
import static com.yaksha.assignment.utils.TestUtils.testReport;
import static com.yaksha.assignment.utils.TestUtils.yakshaAssert;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

import com.yaksha.assignment.Customer;
import com.yaksha.assignment.Order;
import com.yaksha.assignment.utils.CustomParser;

// Helper class to provide access to the Spring ApplicationContext
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }
}

// Ensure fresh Spring context for each test execution
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ECommerceAnnotationContollerTests {

    @Autowired
    private ApplicationContext context;

    private boolean customerBeanExists;
    private boolean orderBeanExists;

    @MockBean
    private Customer mockCustomer;

    @MockBean
    private Order mockOrder;

    private Customer customer;
    private Order order;

    @BeforeEach
    public void fetchBeans() {
        customerBeanExists = context.containsBean("customer");
        orderBeanExists = context.containsBean("order");

        if (customerBeanExists) {
            customer = context.getBean(Customer.class);
        } else {
            customer = mockCustomer; // Use mock if real bean is missing
        }

        if (orderBeanExists) {
            order = context.getBean(Order.class);
        } else {
            order = mockOrder; // Use mock if real bean is missing
        }
    }

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    // Test if Customer class has @Component annotation
    @Test
    public void testCustomerHasComponent() throws Exception {
        String filePath = "src/main/java/com/yaksha/assignment/Customer.java"; 

        try {
            boolean classLevelAnnotationResult = CustomParser.checkClassAnnotation(filePath, "Component");
            boolean fieldLevelAnnotationResult = CustomParser.checkFieldAnnotation(filePath, "Order", "Autowired");

            yakshaAssert(currentTest(), classLevelAnnotationResult && fieldLevelAnnotationResult, businessTestFile);
        } catch (Exception e) {
            e.printStackTrace();
            yakshaAssert(currentTest(), false, businessTestFile);
        }
    }

    // Test if Product class has @Component annotation
    @Test
    public void testProductHasComponent() throws Exception {
        String filePath = "src/main/java/com/yaksha/assignment/Product.java"; 

        try {
            boolean classLevelAnnotationResult = CustomParser.checkClassAnnotation(filePath, "Component");
            boolean result = CustomParser.checkConstructorParameterAnnotation(filePath, "Value", "name", "price");

            yakshaAssert(currentTest(), classLevelAnnotationResult && result, businessTestFile);
        } catch (Exception e) {
            e.printStackTrace();
            yakshaAssert(currentTest(), false, businessTestFile);
        }
    }

    // Test if Order class has @Component annotation
    @Test
    public void testOrderHasComponent() throws Exception {
        String filePath = "src/main/java/com/yaksha/assignment/Order.java"; 

        try {
            boolean classLevelAnnotationResult = CustomParser.checkClassAnnotation(filePath, "Component");
            boolean fieldLevelAnnotationResult = CustomParser.checkFieldAnnotation(filePath, "Product", "Autowired");

            yakshaAssert(currentTest(), classLevelAnnotationResult && fieldLevelAnnotationResult, businessTestFile);
        } catch (Exception e) {
            e.printStackTrace();
            yakshaAssert(currentTest(), false, businessTestFile);
        }
    }
}

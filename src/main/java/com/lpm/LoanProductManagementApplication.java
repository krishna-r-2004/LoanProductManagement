package com.lpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The main class for the Loan Product Management Application.
 * This class is responsible for bootstrapping the Spring Boot application.
 */
@SpringBootApplication
@ComponentScan({"com.lpm*"})
public class LoanProductManagementApplication {

	/**
	 * The main method which serves as the entry point for the Spring Boot application.
	 *
	 * @param args Command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(LoanProductManagementApplication.class, args);
	}
}
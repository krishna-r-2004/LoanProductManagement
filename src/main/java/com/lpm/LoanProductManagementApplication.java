package com.lpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.lpm*"})
public class LoanProductManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanProductManagementApplication.class, args);
	}
}

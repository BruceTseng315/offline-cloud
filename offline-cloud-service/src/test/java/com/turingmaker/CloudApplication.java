package com.turingmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CloudApplication {

	public static void main(String[] args) {

		SpringApplication.run(CloudApplication.class,args);
	
	}

}

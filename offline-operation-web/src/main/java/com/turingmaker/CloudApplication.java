package com.turingmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableConfigurationProperties
public class CloudApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(CloudApplication.class,args);
		
	}

}

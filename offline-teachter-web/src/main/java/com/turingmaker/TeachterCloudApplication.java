package com.turingmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableConfigurationProperties
public class TeachterCloudApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TeachterCloudApplication.class,args);
		
	}

}

package com.turingmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author zengdingyang
 * @Date 2018\7\5 0005.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
public class StudentApplication {
    public static void main(String[] args) {

        SpringApplication.run(StudentApplication.class,args);

    }
}

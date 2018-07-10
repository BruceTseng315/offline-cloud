package com.turingmaker.controll.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class BeandefineConfig {

	/**
	 * 添加自己的配置文件
	 * @return
	 */
	@Bean
	 public  PropertySourcesPlaceholderConfigurer properties() {  
	        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();  
	        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();  
	        yaml.setResources(new ClassPathResource("constant.yml"));//File引入    
	        configurer.setProperties(yaml.getObject());  
	        return configurer;  
	    }  
	
	
}

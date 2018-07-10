package com.turingmaker.controll.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class BeandefineConfig {

//	/**
//	 * 添加自己的配置文件
//	 * @return
//	 */
//	@Bean
//	 public static PropertySourcesPlaceholderConfigurer properties() {  
//	        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();  
//	        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();  
//	        yaml.setResources(new ClassPathResource("constant.yml"));//File引入    
//	        configurer.setProperties(yaml.getObject());  
//	        return configurer;  
//	    }  
}

package com.turingmaker.controll.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class BeandefineConfig {

	/**
	 * 添加自己的配置文件
	 * @return
	 */
	//@Bean
	 public  PropertySourcesPlaceholderConfigurer properties() {  
	        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();  
	        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
			//File引入
	        yaml.setResources(new ClassPathResource("constant.yml"));
	        configurer.setProperties(yaml.getObject());  
	        return configurer;  
	    }  
	
	
}

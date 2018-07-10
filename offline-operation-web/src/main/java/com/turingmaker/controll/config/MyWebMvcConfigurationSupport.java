package com.turingmaker.controll.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.turingmaker.controll.config.filters.AuthFilter;

/**
 * 不要使用spring boot自带的那个配置
 * @author tzj
 *
 */
@Configuration
public class MyWebMvcConfigurationSupport extends WebMvcConfigurationSupport{

	
	
	final String SESSION_USER_KEY="user";
	
	
	@Override
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		// TODO Auto-generated method stub
		RequestMappingHandlerMapping handlerMapping=super.requestMappingHandlerMapping();
		handlerMapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return  handlerMapping;
	}

	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		

		registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(60*60*24);
		
	
	}

	/**
	 * 授权过滤器，访问接口的时候查看是否有足够权限
	 * 
	 * @return
	 */
	//@Bean
	public FilterRegistrationBean filter() {

		List<String> strings=new ArrayList<>();
		strings.add("/turing/api/v2/admin_manage/operation_login");
		strings.add("/turing/api/v2/manage/fileupload/compressppt");
		
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new AuthFilter(strings,SESSION_USER_KEY,	getCorsConfigurations().get("/**")));// 添加过滤器
		registration.addUrlPatterns("/*");// 设置过滤路径，/*所有路径
		registration.setName("authfilter");// 设置优先级
		return registration;
	}
}

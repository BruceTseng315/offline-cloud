package com.turingmaker.controll.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.turingmaker.controll.config.filters.AuthFilter;
import com.turingmaker.controll.controllers.AuthController;

/**
 * 不要使用spring boot自带的那个配置
 * @author tzj
 *
 */
@Configuration
public class MyWebMvcConfigurationSupport extends WebMvcConfigurationSupport{

	
	
	
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
	@Bean
	public FilterRegistrationBean filter() {

		List<String> strings=new ArrayList<>();
		//设置登录页面，直接过去
		strings.add("/turing/api/v2/teacher/auth/login");
		
		FilterRegistrationBean registration = new FilterRegistrationBean();
		// 添加过滤器
		registration.setFilter(new AuthFilter(strings,AuthController.SESSION_TEACHTER_KEY,	getCorsConfigurations().get("/**")));
		// 设置过滤路径，/*所有路径
		registration.addUrlPatterns("/*");
		// 设置优先级
		registration.setName("authfilter");
		return registration;
	}
}

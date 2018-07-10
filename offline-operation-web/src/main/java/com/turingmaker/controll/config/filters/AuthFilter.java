package com.turingmaker.controll.config.filters;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.DefaultCorsProcessor;

/**
 * 
 * @author tzj
 *
 */
public class AuthFilter implements Filter {

	Logger logger = LoggerFactory.getLogger(getClass());

	private List<String> allowUrls;

	final private String noLoginString;

	final private String errorString;

	private String userLoginKey;
	
	/**
	 * 跨域配置
	 */
	private CorsConfiguration corsConfiguration;
	
	
	DefaultCorsProcessor corsProcessor=new DefaultCorsProcessor();

	public AuthFilter() {
		super();
		noLoginString = "{\"code\": 402,\"msg\": \"success\",data:\"Not logged\"}";
		errorString = "{\"code\": 500,\"msg\": \"error\",data:\"System Error\"}";
	}

	public AuthFilter(List<String> allowUrls, String userLoginKey,CorsConfiguration corsConfiguration) {
		this();
		this.allowUrls = allowUrls;
		this.userLoginKey = userLoginKey;
		this.corsConfiguration=corsConfiguration;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		if (logger.isDebugEnabled()) {
			logger.debug("初始化logger");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String requesturl = servletRequest.getRequestURI();
		logger.info("访问"+requesturl);
		if (allowUrls != null) {
			
			for(String allowurl:allowUrls) {
				if (requesturl.equals(allowurl)) {

					try {
						chain.doFilter(servletRequest, response);
					} catch (Exception e) {
						logger.warn("filter出错", e);
						try {
							printError(response.getWriter(), 0);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					return;
				}
			}
			
		}

		Object user = servletRequest.getSession().getAttribute(userLoginKey);
		if (user != null) {
			chain.doFilter(servletRequest, response);
			return;
		}
	
		corsProcessor.processRequest(corsConfiguration, servletRequest, servletResponse);
		printError(response.getWriter(), 1);
	}

	void printError(Writer writer, int type) throws IOException {

		switch (type) {
		case 0:
			writer.write(errorString);
			break;
		case 1:
			writer.write(noLoginString);
			break;

		default:
			break;
		}

		writer.flush();
	}

	@Override
	public void destroy() {
		if (logger.isDebugEnabled()) {
			logger.debug("销毁logger");
		}
	}

}

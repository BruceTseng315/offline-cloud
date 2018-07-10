package com.turingmaker.controll.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * 
 * @author tzj
 *
 */
public class AuthFilter implements Filter {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		if (logger.isDebugEnabled()) {
			logger.debug("初始化logger");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		if (logger.isDebugEnabled()) {
			logger.debug("销毁logger");
		}
	}

}

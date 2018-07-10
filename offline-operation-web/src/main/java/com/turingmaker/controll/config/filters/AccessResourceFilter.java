package com.turingmaker.controll.config.filters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author tzj
 *
 */
public class AccessResourceFilter implements Filter{

	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	String resourceLocation;
	
	String filterName;
	
	
	String onlyUrl;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		String rl=filterConfig.getInitParameter("resourceLocation");
		String onlyUrl=filterConfig.getInitParameter("onlyurl");
		if(rl!=null&&onlyUrl!=null) {
			this.resourceLocation=rl;
			this.onlyUrl=onlyUrl;
			
		}else {
			throw new ServletException("缺少必要参数resourceLocation");
		}
		
	
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		HttpServletResponse httpServletResponse=(HttpServletResponse)response;
		
		String requesturl=httpServletRequest.getRequestURI();
		String onlyUrl=this.onlyUrl;
		
		
		
		if(!("GET".equalsIgnoreCase(httpServletRequest.getMethod()))) {
			httpServletResponse.sendError(400, "不支持访问类型"+requesturl);
			return;
		}
		
		String contextpath=httpServletRequest.getContextPath();

		if((requesturl.contains(contextpath))) {
			requesturl=requesturl.substring(contextpath.length());
		}
		
		
		if(!requesturl.startsWith("/")) {
			requesturl="/"+requesturl;
		}
		
		
		
		if(requesturl.contains(onlyUrl)) {
			requesturl=requesturl.substring(onlyUrl.length());
		}
		
		File file=new File(resourceLocation,requesturl);
		
		if(!file.exists()) {
			httpServletResponse.sendError(400, "没有这个requesturl"+requesturl);
			return;
		}
	
		InputStream inputStream=new FileInputStream(file);
		OutputStream outputStream=response.getOutputStream();
		
		try {
			IOUtils.copy(inputStream,outputStream );
		} catch (IOException e) {
			logger.warn("访问文件出错");
		}finally {
			if(inputStream!=null) {
				inputStream.close();
			}
			if(outputStream!=null) {
				outputStream.close();
			}
		}
		
		
	}

	@Override
	public void destroy() {}

}

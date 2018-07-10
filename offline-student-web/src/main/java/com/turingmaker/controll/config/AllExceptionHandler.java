package com.turingmaker.controll.config;


import com.turingmaker.common.domain.Result;
import com.turingmaker.service.exception.HttpException;
import com.turingmaker.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 处理所有controller的异常
 * @author tzj
 *
 */
@ControllerAdvice
public class AllExceptionHandler {

    
	Logger logger=LoggerFactory.getLogger(getClass());
  
    @ExceptionHandler(value = {HttpException.class,ServiceException.class})
    @ResponseBody
    public Result<?> jsonExceptionHandler(HttpServletRequest request,HttpServletResponse httpResponse, ServiceException e) {

        logger.warn("rest系统异常",e);
    	if( e  instanceof HttpException) {
    	 
    		 HttpException exception=(HttpException)e;
    		 httpResponse.setStatus(exception.getHttpcode());
    	}
    	
    	
    	
    	
    	if( e instanceof  ServiceException) {
    		return Result.errorrResult(e.getCode(),e.getMessage());
    	}
    	return Result.errorresult;
        
    }
    
    
    
    
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<?> exceptionHandler(HttpServletRequest request, Exception e) {
        
    
    	logger.warn("系统异常",e);
    	return Result.errorresult;
        
    }
    
}
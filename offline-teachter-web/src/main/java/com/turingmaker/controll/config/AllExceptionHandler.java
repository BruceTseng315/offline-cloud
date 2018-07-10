package com.turingmaker.controll.config;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.turingmaker.common.domain.Result;
import com.turingmaker.service.exception.HttpException;
import com.turingmaker.service.exception.ServiceException;


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
    
    
    
    
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<?> methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
    	
    
    		logger.warn("参数校验失败",e);
    		StringBuilder builder=new StringBuilder();
    		e.getBindingResult().getAllErrors().forEach((l)->{
    			builder.append(l.getDefaultMessage()+",");
    		});
    	return Result.errorrResult(builder.toString());
        
    }
    
    
    
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<?> exceptionHandler(HttpServletRequest request, Exception e) {
    	
    
    	logger.warn("系统异常",e);
    	return Result.errorrResult("系统异常");
        
    }
    
}
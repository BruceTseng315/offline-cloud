package com.turingmaker.controll.config;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.turingmaker.service.exception.HttpException;

/**
 * 得处理404的情况
 * @author Administrator
 *
 */
@RestController
public class MyErrorController implements  ErrorController{


	    private static final String ERROR_PATH = "/error";

	    
	    @RequestMapping(value = ERROR_PATH)
	    @ResponseStatus(value = HttpStatus.NOT_FOUND)
	    public void handleError() {
	    	throw new HttpException(404,"没有找到这个接口");
	    }

	    @Override
	    public String getErrorPath() {
	        return ERROR_PATH;
	    }

}

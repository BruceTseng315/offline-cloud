package com.turingmaker.common.exception;

/**
 * 
 * @author tzj
 *
 */
public class CheckParamException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2196797052746105215L;

	String paramName;

	public CheckParamException(String paramName,String errorInfo) {
		super(errorInfo);
		this.paramName = paramName;
	}


}

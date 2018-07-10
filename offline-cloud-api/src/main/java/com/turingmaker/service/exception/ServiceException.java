package com.turingmaker.service.exception;

/**
 * 
 * @author tzj
 *
 */
public class ServiceException extends RuntimeException{

	/**
	 * service 异常状态码
	 */
	int code=-1;
	
	private static final long serialVersionUID = -8826299197977729171L;

	public ServiceException(int code) {
		super();
		this.code = code;
	}

	public ServiceException(int code,String errormsg) {
		super(errormsg);
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	

	
	
}

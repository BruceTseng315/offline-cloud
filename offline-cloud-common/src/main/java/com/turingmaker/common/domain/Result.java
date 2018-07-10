package com.turingmaker.common.domain;

import java.io.Serializable;


/**
 * 
 * 
 * @author tzj
 *
 * @param <T>
 */
public class Result<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 错误码. */
	private Integer code;

	/** 提示信息. */
	private String msg;

	/** 具体的内容. */
	private T data;
	
	final static int DEFAULTERRORCODE=-1;
	
	final static int DEFAULTSUCCESSCODE=0;
	
	public final  static Result<?> errorresult;
	
	
	public final  static Result<?> successresult;
	
	
	
	static {
		errorresult=new Result<Object>(DEFAULTERRORCODE,"error");
		successresult=new Result<Object>(DEFAULTSUCCESSCODE,"success");
	}
	
	
	/**
	 * 返回错误信息  默认状态码
	 * @param errorMsg
	 * @return
	 */
	public static Result<?> errorrResult(String errorMsg) {
		return new Result<Object>(DEFAULTERRORCODE, errorMsg);
	}
	
	public static Result<?> successResult(String msg) {
		return new Result<Object>(0, msg);
	}
	
	/**
	 * 返回error信息  包含状态码
	 * @param code
	 * @param errorMsg
	 * @return
	 */
	public static Result<?> errorrResult(Integer code,String errorMsg) {
		return new Result<Object>(code, errorMsg);
	}
	
	
	public Result(){
		this(0,"success" , null);
	}
	
	

	public Result(Integer code, String msg) {
		this(code,msg,null);
	}


	public Result(T data) {
		this(0,"success" , data);
	}

	public Result(Integer code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

	public static Result success(Object data){
		Result result = new Result(DEFAULTSUCCESSCODE,"success",data);
		
		return result;
	}
	
}

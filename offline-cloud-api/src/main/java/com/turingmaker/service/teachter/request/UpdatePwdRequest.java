package com.turingmaker.service.teachter.request;

import javax.validation.constraints.NotNull;

/**
 * 封装新旧密码
 * @author tzj
 *
 */
public class UpdatePwdRequest {

	private String passwordOld;		
			
	private String passwordNew;

	@NotNull(message="就密码不能不能空")
	public String getPasswordOld() {
		return passwordOld;
	}

	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}
	@NotNull(message="新密码不能空")
	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}

	public UpdatePwdRequest(String passwordOld, String passwordNew) {
		super();
		this.passwordOld = passwordOld;
		this.passwordNew = passwordNew;
	}
	
	
	public UpdatePwdRequest() {}
}

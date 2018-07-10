package com.turingmaker.service.request;

import javax.validation.constraints.NotNull;

/**
 * 这个只为登录服务
 * @author Administrator
 *
 */
public class UsernameAndPassword {

	private String username;
	
	private String password;

	@NotNull(message="用户名必填")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotNull(message="密码必填")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

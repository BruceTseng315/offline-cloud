package com.turingmaker.service.api;

import com.turingmaker.service.exception.ServiceException;

/**
 * 
 * @author tzj
 *
 */
public interface AuthService {

	
	/**
	 * 用户登录
	 * @param username  用户名
	 * @param password  密码
	 * @param rolename 权限 
	 */
	public void  userLoginByPassword(String username,String password,String rolename) throws ServiceException;
	
	
	
	/**
	 * 检查用户是否有这个权限
	 * @param username  用户名
	 * @param rolename 权限
	 * @return  true 如果检查通过 false 检查不通过
	 */
	public boolean  checkUserHasRole(String username,String rolename);
	
	
	/**
	 * 检查用户名和密码是否相符
	 * @param username 用户名
	 * @param password 密码
	 * @return  true 如果检查通过 false 检查不通过
	 */
	public boolean checkUsernamePasword(String username,String password);
	
	
	
	/**
	 * 生成账号
	 * @param type  可选值 0:运营端  ,1:学校管理的 ,2:老师端,3:学生端
	 * @return 分配之后的用户名
	 */
	public String  generateAccount(int type);
}

package com.turingmaker.service.imp;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turingmaker.dao.mapper.operation.ext.UserMapperExt;
import com.turingmaker.entity.operation.YUser;
import com.turingmaker.service.api.AuthService;
import com.turingmaker.service.exception.ServiceException;

/**
 * 权限相关的操作
 * @author tzj
 *
 */
@Service
public class AuthServiceImpl implements AuthService{

	
	@Autowired
	UserMapperExt userMapper;
	
	
	
	Logger logger=LoggerFactory.getLogger(getClass());
	
	@Override
	public void userLoginByPassword(String username, String password, String rolename) throws ServiceException{
		
		if(!checkUserHasRole(username, rolename)) {
			throw new ServiceException(401,"没有足够权限");
		}
		
		if(!checkUsernamePasword(username, password)) {
			throw new ServiceException(401,"用户名密码不正确");
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug(username+"登录成功");
		}
			
		
	}

	@Override
	public boolean checkUserHasRole(String username, String rolename) {
		return userMapper.checkUserHasRole(username, rolename);
	}

	@Override
	public boolean checkUsernamePasword(String username, String password) {
		
		YUser user=userMapper.findUserByUserName(username);
		
		if(username.equals(user.getAccount())&&password.equals(user.getPassword())) {
			return true;
		}
		
		return false;
	}

	@Override
	public String generateAccount(int type) {
	
		Map<String, Object> map=new HashMap<>();
		map.put("accounttype", type);
		userMapper.generateAccount(map);
		
		if(map.containsKey("accountname")) {
			return (String) map.get("accountname");
		}
		
		return null;
	}

}

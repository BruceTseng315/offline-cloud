package com.turingmaker.controll.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turingmaker.common.domain.Result;
import com.turingmaker.dao.mapper.operation.ext.UserMapperExt;
import com.turingmaker.dao.mapper.school.ext.TTeacherMapperExt;
import com.turingmaker.entity.operation.YUser;
import com.turingmaker.entity.school.TTeacher;
import com.turingmaker.service.api.AuthService;
import com.turingmaker.service.exception.HttpException;
import com.turingmaker.service.request.UsernameAndPassword;
import com.turingmaker.service.school.api.TeacherService;
import com.turingmaker.service.teachter.request.UpdatePwdRequest;

/**
 * 
 * @author tzj
 *
 */
@RestController
@RequestMapping("/turing/api/v2/teacher/auth")
public class AuthController {

	
	final String ROLE_NAME="ROLE_TEACHTER";
	@Autowired
	AuthService authservice;
	
	@Autowired
	UserMapperExt userMapperExt;
	
	@Autowired
	TTeacherMapperExt teacherMapperExt;
	
    public static final String SESSION_TEACHTER_KEY="teachterId";
    public static final String SESSION_TEACHTER_USER="teachterUser";
    public static final String SESSION_TEACHTER_TEACHTER="teachterInfo";
	
    @Autowired
    TeacherService teacherService;
    
	Logger logger=LoggerFactory.getLogger(getClass());
	
	/**
	 * 登录
	 * @param session
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("login")
	public Result<?> teachterLogin(HttpSession session,@RequestBody  @Valid UsernameAndPassword andPassword){
		
		
		String username=andPassword.getUsername();
		String password=andPassword.getPassword();
		
		try {
			authservice.userLoginByPassword(username, password, ROLE_NAME);
		} catch (Exception e) {
			logger.warn(username+"权限检验失败",e);
			throw new HttpException(e.getMessage());
		}
		YUser user=userMapperExt.findUserByUserName(username);
		
		TTeacher teacher=teacherMapperExt.selectTeachterByAccountName(username);
		
		session.setAttribute(SESSION_TEACHTER_KEY, String.valueOf(teacher.getId()));
		session.setAttribute(SESSION_TEACHTER_TEACHTER, teacher);
		session.setAttribute(SESSION_TEACHTER_USER, user);
		
		return new Result<String>("登录成功");
	}
	
	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@GetMapping("login_out")
	public Result<?> operationAdminLoginOut(HttpSession session){
		
		
		session.removeAttribute(SESSION_TEACHTER_KEY);
		session.invalidate();
		return new Result<String>("退出登录成功");
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@PostMapping("password/update")
	public Result<?> updatePwd(HttpSession session,@RequestBody @Valid UpdatePwdRequest pwdRequest){
		 
		Long teachterId= Long.valueOf((String)session.getAttribute(SESSION_TEACHTER_KEY));
		teacherService.updatePwd(teachterId, pwdRequest);
		return new Result<>("密码修改成功");
	}
	
	/**
	 * 获取个人信息
	 * @param session
	 * @return
	 */
	@GetMapping("info")
	public  Result<?> info(HttpSession session){
		
		TTeacher teacher=(TTeacher)session.getAttribute(SESSION_TEACHTER_TEACHTER);
		
		return new Result<Object>(teacher);
	}
	
}

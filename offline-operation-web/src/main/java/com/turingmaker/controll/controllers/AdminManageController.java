package com.turingmaker.controll.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turingmaker.common.domain.Result;
import com.turingmaker.dao.mapper.operation.ext.TAreaMapperExt;
import com.turingmaker.entity.operation.TArea;
import com.turingmaker.service.api.AuthService;
import com.turingmaker.service.api.DataexportService;
import com.turingmaker.service.exception.HttpException;
import com.turingmaker.service.operation.api.DataMode;
import com.turingmaker.service.operation.request.SaveSchoolRequest;
import com.turingmaker.service.operation.response.AreaResponse;
import com.turingmaker.service.operation.response.SchoolInfo;
import com.turingmaker.service.operation.response.SchoolListResponse;
import com.turingmaker.service.request.UsernameAndPassword;
import com.turingmaker.service.school.api.SchoolService;

/**
 *  运营端管理员的一些操作
 * @author tzj
 *
 */
@RestController
@RequestMapping("/turing/api/v2/admin_manage/")
public class AdminManageController {

	
	@Autowired
	SchoolService schoolService;
	
	@Autowired
	AuthService authservice;

	@Autowired
	TAreaMapperExt areaMapperExt;
	
	final String ROLE_NAME="ROLE_OPERATION";
	
	final String SESSION_USER_KEY="user";
	

	
	
	@Value("${page.defaultSize}")
	int defaultPageSize;

	@Value("${page.defaultNo}")
	int defaultPageNo;
	
	Logger logger=LoggerFactory.getLogger(getClass());
	/**
	 * 添加学校
	 * @param schoolRequest
	 * @return
	 */
	@PostMapping("add_school")
	public Result<?> saveSchool(@RequestBody SaveSchoolRequest schoolRequest){
		Set<Long> set = new HashSet<Long>();
		for(Long lo : schoolRequest.getCourses()){
			set.add(lo);
		}
		if(set.size() != schoolRequest.getCourses().size()){
			return Result.errorrResult("不可添加重复课程");//有重复
		}
		schoolService.saveSchool(schoolRequest);
		
		return Result.successresult;
	}

	/**
	 * @Description:  返回初始密码
	 * @Param: []
	 * @return: com.turingmaker.common.domain.Result
	 * @Date: 2018/6/21
	 */
	@GetMapping("initial_password")
	public Result getInitialPassword(){
		Map<String,String> map = new HashMap<>();
		map.put("password","123456");
		return Result.success(map);
	}
	
	
	@GetMapping("areas")
	public Result<?> getAreaList(Long areaId){
		List<AreaResponse> areaResponses = new ArrayList<>();
		List<TArea> areaList = new ArrayList<>();
		if(areaId==0){
			 areaList = areaMapperExt.findProvinceName();
		}else {
			 areaList = areaMapperExt.findAreaNameByPid(areaId);
		}
		for (TArea x : areaList){
			AreaResponse response = new AreaResponse();
			response.setAreaId(x.getId());
			response.setAreaName(x.getAreaName());
			areaResponses.add(response);
		}
		return Result.success(areaResponses);
	}

	/**
	 * 登录
	 * @param session
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("operation_login")
	public Result<?> operationAdminLogin(HttpSession session,@RequestBody  @Valid UsernameAndPassword andPassword){
		
		String username=andPassword.getUsername();
		String password=andPassword.getPassword();
		
		try {
			authservice.userLoginByPassword(username, password, ROLE_NAME);
		} catch (Exception e) {
			logger.warn(username+"权限检验失败",e);
			throw new HttpException(e.getMessage());
		}
		
		
		session.setAttribute(SESSION_USER_KEY, username);
		
		return new Result<String>("登录成功");
	}
	
	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@PostMapping("operation_loginout")
	public Result<?> operationAdminLoginOut(HttpSession session){
		
		
		
		session.removeAttribute(SESSION_USER_KEY);
		session.invalidate();
		return new Result<String>("退出登录成功");
	}
	
	
}

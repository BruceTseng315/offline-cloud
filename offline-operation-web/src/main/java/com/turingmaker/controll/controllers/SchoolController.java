package com.turingmaker.controll.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.turingmaker.dao.mapper.operation.ext.UserMapperExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turingmaker.common.domain.Result;
import com.turingmaker.dao.mapper.operation.ext.TAreaMapperExt;
import com.turingmaker.dao.mapper.school.ext.TSchoolAdminMapperExt;
import com.turingmaker.entity.operation.YUser;
import com.turingmaker.entity.school.TSchool;
import com.turingmaker.entity.school.TSchoolAdmin;
import com.turingmaker.service.api.AuthService;
import com.turingmaker.service.api.DataexportService;
import com.turingmaker.service.operation.api.DataMode;
import com.turingmaker.service.operation.request.SaveSchoolRequest;
import com.turingmaker.service.operation.response.SchoolCourseInfo;
import com.turingmaker.service.operation.response.SchoolDetailResponse;
import com.turingmaker.service.operation.response.SchoolInfo;
import com.turingmaker.service.operation.response.SchoolListResponse;
import com.turingmaker.service.school.api.SchoolService;

/**
 * 的操作
 * 
 * @author
 *
 */
@RestController
@RequestMapping("/turing/api/v2/admin_manage/school/")
public class SchoolController {

	@Autowired
	SchoolService schoolService;

	@Autowired
	AuthService authservice;

	@Autowired
	TSchoolAdminMapperExt schoolAdminMapperExt;

	@Autowired
	DataexportService dataexportService;

	@Autowired
	UserMapperExt userMapperExt;

	Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${page.defaultSize}")
	int defaultPageSize;

	@Value("${page.defaultNo}")
	int defaultPageNo;

	/**
	 * @Description: 根据条件获取学校信息
	 * @Param: ...
	 * @return:
	 * @Date: 2018/6/20
	 */
	@GetMapping("all")
	public Result<?> getSchoolInfo(String schoolCode, String schoolName, String adminName, Integer regionCode,
			Byte type, Integer pageNo, Integer pageSize) {

		if (pageNo == null) {
			pageNo = defaultPageNo;
		}
		if (pageSize == null) {
			pageSize = defaultPageSize;
		}

		SchoolListResponse schoolListResponse = schoolService.getSchoolListNew(schoolCode, schoolName, adminName,
				regionCode, type, pageNo, pageSize);
		return new Result<Object>(schoolListResponse);

	}

	/**
	 * 导出学校
	 * 
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 */
	@GetMapping("export")
	public void exportSchool(HttpServletResponse response, String schoolCode, String schoolName, String adminName,
			Integer regionCode, Byte type, Integer pageNo, Integer pageSize) {

		if (pageNo == null) {
			pageNo = defaultPageNo;
		}
		if (pageSize == null) {
			pageSize = defaultPageSize;
		}

		DataMode<SchoolInfo> dataMode = new DataMode<>();
		Map<String, String> map = new HashMap<>();
		map.put("学校编号", "schoolCode");
		map.put("学校名称", "schoolName");
		map.put("班级数量", "classCount");
		map.put("教师数量", "teacherCount");
		map.put("学生数量", "studentCount");
		map.put("已开课程", "courses");
		dataMode.setColumnandFieldNameMap(map);

		SchoolListResponse schoolListResponse = schoolService.getSchoolListNew(schoolCode, schoolName, adminName,
				regionCode, type, pageNo, pageSize);

		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String("学校信息表格.xls".getBytes("GBK"), "ISO8859_1"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		try {
			dataexportService.exportExcel(response.getOutputStream(), dataMode, schoolListResponse.getSchoolInfoList());
		} catch (IOException e) {
			logger.warn("下载失败", e);
		}

	}

	/**
	 * @Description: 学校添加课程获取可选课程列表
	 * @Param: schoolId
	 * @return:
	 * @Date: 2018/6/20
	 */
	@GetMapping("enable")
	public Result<?> getEnableCourse(Long schoolId) {
		TSchool school = schoolService.findBySchoolId(schoolId);
		if (school == null) {
			return Result.errorrResult("该学校不存在");
		}
		List<SchoolCourseInfo> courses = schoolService.getEnableCourse(schoolId);
		return new Result<Object>(courses);

	}

	/**
	 * @Description: 获取学校管理员账号信息
	 * @Param: schoolId
	 * @return:
	 * @Date: 2018/6/20
	 */
	@GetMapping("admin")
	public Result<?> getSchoolAdmin(Long schoolId) {
		if(schoolId==null) {
			return new Result<String>("schoolId不能空");
		}
		YUser user = schoolService.getSchoolAdmin(schoolId);
		if (user == null) {
			return Result.errorrResult("该学校不存在");
		}
		return new Result<Object>(user);

	}

	/**
	 * @Description: 获取学校详情
	 * @Param: schoolId
	 * @return:
	 * @Date: 2018/6/20
	 */
	@GetMapping("details")
	public Result<?> getSchoolDetail(Long schoolId) {
		TSchool school = schoolService.findBySchoolId(schoolId);
		if (school == null) {
			return Result.errorrResult("该学校不存在");
		}
		TSchoolAdmin schoolAdmin = schoolService.getSchoolAdminInfo(schoolId);
		SchoolDetailResponse response = new SchoolDetailResponse();
		BeanUtils.copyProperties(school, response);
		response.setAdminName(schoolAdmin.getName());
		response.setAdminPhone(schoolAdmin.getPhone());
		response.setAccount(userMapperExt.selectByPrimaryKey(schoolAdmin.getAccountId()).getAccount());
		response.setRegion(schoolService.getAreaDetailById(schoolService.findBySchoolId(schoolId).getAreaCode()));
		List<SchoolCourseInfo> courses = schoolService.findCoursesBySchoolId(schoolId);
		response.setCourses(courses);
		return new Result<Object>(response);

	}

	/**
	 * 编辑学校
	 * 
	 * @param schoolRequest
	 * @return
	 */
	@PostMapping("edit")
	public Result<?> saveSchool(@RequestBody SaveSchoolRequest schoolRequest) {
		Set<Long> set = new HashSet<>();
		for (SchoolCourseInfo map : schoolRequest.getCourseList()) {
			set.add(map.getId());
		}
		if (set.size() != schoolRequest.getCourseList().size()) {
			// 有重复
			return Result.errorrResult("不可添加重复课程");
		}

		schoolService.editSchool(schoolRequest);

		return Result.successresult;
	}

	/**
	 * 学校课程停课
	 * 
	 * @param schoolId,courseId
	 * @return
	 */
	@GetMapping("suspend")
	public Result<?> suspendCourse(Long schoolId, Long courseId) {

		schoolService.updateState(schoolId, courseId, (byte) 0);

		return Result.successresult;
	}

	/**
	 * 学校课程开课
	 * 
	 * @param schoolId,courseId
	 * @return
	 */
	@GetMapping("begin")
	public Result<?> beginCourse(Long schoolId, Long courseId) {

		schoolService.updateState(schoolId, courseId, (byte) 1);

		return Result.successresult;
	}

}

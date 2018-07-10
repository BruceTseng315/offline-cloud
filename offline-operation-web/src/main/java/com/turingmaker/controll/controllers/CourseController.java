package com.turingmaker.controll.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turingmaker.common.domain.Result;
import com.turingmaker.service.operation.api.CourseService;
import com.turingmaker.service.operation.request.SaveCourseRequest;
import com.turingmaker.service.operation.response.Pageinfo;

/**
 * 
 * @author tzj
 *
 */
@RestController
@RequestMapping("/turing/api/v2/manage/course")
public class CourseController {

	@Autowired
	CourseService courseService;
	

	
	@Value("${page.defaultSize}")
	int defaultPageSize;

	@Value("${page.defaultNo}")
	int defaultPageNo;
	
	
	
	/**
	 * 列出课程
	 * @param courseCode
	 * @param courseName
	 * @param courseStatus
	 * @return
	 */
	@GetMapping("all")
	public Result<?> listAllCourse(String courseCode,String courseName,Byte courseState, Integer pageNo, Integer pageSize){
		
		if(pageNo==null) {
			pageNo = defaultPageNo;
		}
		if(pageSize==null) {
			pageSize = defaultPageSize;
		}
		
		return  new Result<Object>(courseService.listCoursesPage(
				new Pageinfo(pageNo, pageSize),
				courseCode, courseName, courseState));
	}
	
	
	/**
	 * 课程发布
	 * @param courseId
	 * @return
	 */
	@GetMapping("publish")
	public Result<?> publish(Long courseId){
		
	
		courseService.updateCourseState(courseId, CourseService.statePublish);
		return  Result.successResult("课程发布成功");
	}
	
	/**
	 * 课程上架
	 * @param courseId
	 * @return
	 */
	@GetMapping("on")
	public Result<?> courseOn(Long courseId){
		
		
		courseService.updateCourseState(courseId, CourseService.stateUnpub);
		return  Result.successResult("课程上架成功");
	}
	
	
	/**
	 * 课程下架
	 * @param courseId
	 * @return
	 */
	@GetMapping("off")
	public Result<?> courseOff(Long courseId){
		
	
		courseService.updateCourseState(courseId,CourseService.stateOff);
		return  Result.successResult("课程下架成功");
	}
	
	/**
	 * 添加课程
	 * @param courseRequest
	 * @return
	 */
	@PostMapping("add")
	public Result<?> courseAdd(@RequestBody @Valid SaveCourseRequest courseRequest){
		
		if(courseRequest.getLessonNum()==null) {
			return Result.errorrResult("课程数量必填");
		}

		courseService.saveCourse(courseRequest);
		
		return  Result.successresult;
	}
	
	/**
	 * 编辑保存课程
	 * @param courseRequest
	 * @return
	 */
	@PostMapping("edit")
	public Result<?> courseEdit(@RequestBody @Valid SaveCourseRequest courseRequest){
		
		if(courseRequest.getId()==null) {
			return Result.errorrResult("课程id必填");
		}
		courseService.saveCourse(courseRequest);
		
		return  Result.successresult;
	}
	
	/**
	 * 获取课程详情
	 * @param courseId
	 * @return
	 */
	@GetMapping("details")
	public Result<?> courseDetails(Long courseId){

		return  new Result<Object>(courseService.courseDetails(courseId));
	}
	
	
	
	
}

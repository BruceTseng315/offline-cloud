package com.turingmaker.service.operation.imp;

import java.util.*;

import com.turingmaker.dao.mapper.teachter.ext.ClassLessonMapperExt;
import com.turingmaker.entity.operation.TLesson;
import com.turingmaker.service.operation.response.*;
import com.turingmaker.service.teachter.entity.ClassInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turingmaker.dao.mapper.operation.ext.CourseMapperExt;
import com.turingmaker.dao.mapper.operation.ext.LessonMapperExt;
import com.turingmaker.entity.operation.TCourse;
import com.turingmaker.service.exception.ServiceException;
import com.turingmaker.service.operation.api.CourseService;
import com.turingmaker.service.operation.request.SaveCourseRequest;

/**
 * 
 * @author tzj
 *
 */
@Service
public class CourseServiceImpl implements  CourseService{

	
	@Autowired
	CourseMapperExt courseMapper;
	
	@Autowired
	LessonMapperExt lessonMapperExt;

	@Autowired
	ClassLessonMapperExt classLessonMapperExt;
	
	Logger logger=LoggerFactory.getLogger(getClass());
	
	
	
	@Override
	public void saveCourse(SaveCourseRequest courseRequest) throws ServiceException {
		
		
		if(courseRequest.getId()==null) {
			courseMapper.insertSelective(courseRequest);
			courseRequest.setCourseCode(String.valueOf(courseRequest.getId()));
			courseMapper.updateByPrimaryKeySelective(courseRequest);
		}
		else {
			courseRequest.setUpdateTime(new Date());
			courseMapper.updateByPrimaryKeySelective(courseRequest);
		}
	}

	@Override
	public List<TCourse> listCourses(String courseCode, String courseName, Byte courseStatus) {
	
		TCourse course=new TCourse();
		course.setCourseCode(courseCode);
		course.setCourseName(courseName);
		course.setCourseState(courseStatus);
		
	
		return courseMapper.selectForListCourse(course);
	}

	@Override
	public void updateCourseState(Long courseId, Byte state) {
		
		
		if(state<0||state>2) {
			throw new ServiceException(500,"state不能小于0. 0;1;2为可选值") ;
		}
		
		TCourse course=new TCourse();
		course.setId(courseId);
		course.setCourseState(state);

		
		int count=courseMapper.updateByPrimaryKeySelective(course);
		if(count<=0) {
			throw new ServiceException(500,"课程更改失败,没有找到课程");
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("更改状态成功");
		}
	}

	@Override
	public CourseDetailResponse courseDetails(Long courseId) {
	
		TCourse tCourse=courseMapper.selectByPrimaryKey(courseId);
		
		if(tCourse==null)
			throw new ServiceException(500,"没有这个课程");
		
		CourseDetailResponse courseDetailResponse=new CourseDetailResponse(lessonMapperExt.findByCourse(courseId));
		
		BeanUtils.copyProperties(tCourse,
				courseDetailResponse);
		return courseDetailResponse;
	}

	@Override
	public CoursePage listCoursesPage(Pageinfo pageinfo, String courseCode, String courseName,
			Byte courseState) {
		
	
		TCourse course=new TCourse();
		course.setCourseCode(courseCode);
		course.setCourseName(courseName);
		course.setCourseState(courseState);
		
	
		return new CoursePage(pageinfo, courseMapper.selectForListCoursePage(pageinfo, course));
	}

	@Override
	public List<CourseInfo> findAllCourseByTeacherId(Long teacherId){
		List<CourseInfo> courses = courseMapper.findAllCourseByTercherId(teacherId);
		return courses;
	}

	@Override
	public List<TLesson> findLessonByCourseId(Long courseId){
		List<TLesson> lessons = lessonMapperExt.findByCourse(courseId);
		return lessons;
	}

	@Override
	public Map<String,Object> findByTeacherIdAndClassId(Long teacherId , Long classId , Integer pagNo , Integer pageSize){
		Pageinfo pageinfo = new Pageinfo(pagNo,pageSize);
		Map<String,Object> map = new HashMap<>();
		List<CourseInfo> courses = courseMapper.findAllCourseByTercherIdAndClassIdPage(pageinfo,teacherId,classId);
		map.put("pageInfo",pageinfo);
		map.put("courses",courses);
		return map;

	}

	@Override
	public CourseInfo getCourseInfo(Long courseId){
		return courseMapper.findCourseInfo(courseId);
	}
}

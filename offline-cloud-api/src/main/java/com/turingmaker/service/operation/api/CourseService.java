package com.turingmaker.service.operation.api;

import java.util.List;
import java.util.Map;

import com.turingmaker.entity.operation.TLesson;
import com.turingmaker.service.operation.response.CourseInfo;
import org.springframework.beans.factory.annotation.Value;

import com.turingmaker.entity.operation.TCourse;
import com.turingmaker.service.exception.ServiceException;
import com.turingmaker.service.operation.request.SaveCourseRequest;
import com.turingmaker.service.operation.response.CourseDetailResponse;
import com.turingmaker.service.operation.response.CoursePage;
import com.turingmaker.service.operation.response.Pageinfo;

/**
 * 
 * @author tzj
 *
 */
public interface CourseService {

	
	

	byte stateUnpub=0;
	

	byte statePublish=1;
	
	
	byte stateOff=2;
	/**
	 * 保存新增的课程
	 * @param courseRequest
	 */
	public void saveCourse(SaveCourseRequest courseRequest) throws ServiceException;
	
	
	/**
	 * 根据条件列出课程
	 * @param courseCode
	 * @param courseName
	 * @param courseStatus
	 * @return
	 */
	public List<TCourse> listCourses(String courseCode,String courseName,Byte courseStatus);
	
	
	/**
	 * 分页查询 根据条件列出课程
	 * @param courseCode
	 * @param courseName
	 * @param courseStatus
	 * @return
	 */
	public CoursePage listCoursesPage(Pageinfo pageinfo,String courseCode,String courseName,Byte courseState);
	
	
	
	/**
	 * 更改课程状态
	 * @param courseId
	 * @param state  可选值 0,1,2
	 */
	public  void updateCourseState(Long courseId,Byte state);
	
	
	
	/**
	 * 获取课程详情
	 * @param courseId
	 * @return
	 */
	public CourseDetailResponse courseDetails(Long courseId);

	/**
	 * 获取老师教课程列表
	 * @param teacherId
	 * @return
	 */
	public List<CourseInfo> findAllCourseByTeacherId(Long teacherId);

	/**
	 * 获取课时列表
	 * @param courseId
	 * @return
	 */
	public List<TLesson> findLessonByCourseId(Long courseId);

	/**
	 * 获取老师指定班级的所有授课课程列表
	 * @param teacherId,classId
	 * @return
	 */
	Map<String,Object> findByTeacherIdAndClassId(Long teacherId , Long classId , Integer pagNo , Integer pageSize);

	/**
	 * 获取课程基本信息
	 * @param courseId
	 * @return
	 */
	CourseInfo getCourseInfo(Long courseId);
}

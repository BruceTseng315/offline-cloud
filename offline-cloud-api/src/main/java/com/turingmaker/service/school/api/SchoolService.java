package com.turingmaker.service.school.api;

import java.util.List;
import java.util.Map;

import com.turingmaker.entity.operation.TCourse;
import com.turingmaker.entity.operation.YUser;
import com.turingmaker.entity.school.TSchool;
import com.turingmaker.entity.school.TSchoolAdmin;
import com.turingmaker.service.exception.ServiceException;
import com.turingmaker.service.operation.request.SaveSchoolRequest;
import com.turingmaker.service.operation.response.AreaResponse;
import com.turingmaker.service.operation.response.SchoolCourseInfo;
import com.turingmaker.service.operation.response.SchoolListResponse;

/**
 * 学校接口
 * @author tzj
 *
 */
public interface  SchoolService {
	
	
	
	/**
	 *添加学校
	 * 
	 */
	void  saveSchool(SaveSchoolRequest schoolRequest)  throws ServiceException;

	/**
	 *查找学校管理员账号
	 *
	 */
	YUser getSchoolAdmin(Long schoolId)  throws ServiceException;

	/**
	 *查找学校管理员信息
	 *
	 */
	TSchoolAdmin getSchoolAdminInfo(Long schoolId) throws ServiceException;

	/**
	 *查找学校详情
	 *
	 */
	TSchool findBySchoolId(Long schoolId) throws ServiceException;

	/**
	 *根据学校查找开课课程
	 *
	 */
	List<SchoolCourseInfo> findCoursesBySchoolId(Long schoolId) throws ServiceException;

	/**
	 *编辑学校
	 *
	 */
	void  editSchool(SaveSchoolRequest schoolRequest)  throws ServiceException;

	/**
	 *更新学校课程信息
	 *
	 */
	void updateState(Long schoolId , Long courseId , Byte state) throws ServiceException;

	/**
	 *查询学校可添加新课程信息
	 *
	 */
	List<SchoolCourseInfo> getEnableCourse(Long schoolId) throws ServiceException;
	
	
	/**
	 *按条件查询学校信息
	 *
	 */
	@Deprecated
	SchoolListResponse getSchoolList(String schoolCode, String schoolName, String adminName, Integer regionCode, Byte type, Integer pageNo, Integer pageSize) throws ServiceException;
	
	
	/**
	 *按条件查询学校信息
	 *
	 */
	SchoolListResponse getSchoolListNew(String schoolCode, String schoolName, String adminName, Integer regionCode, Byte type, Integer pageNo, Integer pageSize) throws ServiceException;

	/**
	 *根据Id得到省，市，区
	 *
	 */
	Map<String,AreaResponse> getAreaDetailById(Long areaId);
}

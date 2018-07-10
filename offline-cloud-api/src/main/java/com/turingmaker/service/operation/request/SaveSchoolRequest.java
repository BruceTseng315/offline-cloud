package com.turingmaker.service.operation.request;

import java.util.List;
import java.util.Map;

import com.turingmaker.entity.school.TSchool;
import com.turingmaker.service.operation.response.SchoolCourseInfo;

/**
 * 添加学校的请求实体
 * @author tzj
 *
 */
public class SaveSchoolRequest extends  TSchool{

	private String adminName;
	private String adminPhone;
	private Long regionCode;
	private List<SchoolCourseInfo> courseList;
	private int refresh;

	public int getRefresh() {
		return refresh;
	}

	public void setRefresh(int refresh) {
		this.refresh = refresh;
	}

	public List<SchoolCourseInfo> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<SchoolCourseInfo> courseList) {
		this.courseList = courseList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1463659232005673154L;
	/**
	 * 开设课程id
	 */
	List<Long>  courses;

	public List<Long> getCourses() {
		return courses;
	}

	public void setCourses(List<Long> courses) {
		this.courses = courses;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}


	public Long getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(Long regionCode) {
		this.regionCode = regionCode;
	}

	@Override
	public String toString() {
		return "SaveSchoolRequest{" +
				"adminName='" + adminName + '\'' +
				", adminPhone='" + adminPhone + '\'' +
				", regionCode=" + regionCode +
				", courseList=" + courseList +
				", refresh=" + refresh +
				", courses=" + courses +
				'}';
	}
}

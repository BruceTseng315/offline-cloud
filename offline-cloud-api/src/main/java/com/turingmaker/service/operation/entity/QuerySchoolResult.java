package com.turingmaker.service.operation.entity;

import com.turingmaker.entity.school.TSchool;
import com.turingmaker.service.operation.response.SchoolInfo;

/**
 * 
 * @author tzj
 *
 */
public class QuerySchoolResult extends TSchool {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8776120641731359910L;

	private String adminname;
	private String adminphone;
	private Long classCount;
	private Long teacherCount;
	private Long studentCount;
	private String courseName;
	private String areaName;
	

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getAdminphone() {
		return adminphone;
	}

	public void setAdminphone(String adminphone) {
		this.adminphone = adminphone;
	}

	public Long getClassCount() {
		return classCount;
	}

	public void setClassCount(Long classCount) {
		this.classCount = classCount;
	}

	public Long getTeacherCount() {
		return teacherCount;
	}

	public void setTeacherCount(Long teacherCount) {
		this.teacherCount = teacherCount;
	}

	public Long getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(Long studentCount) {
		this.studentCount = studentCount;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public String toString() {
		return "QuerySchoolResult [adminname=" + adminname + ", adminphone=" + adminphone + ", classCount=" + classCount
				+ ", teacherCount=" + teacherCount + ", studentCount=" + studentCount + ", courseName=" + courseName
				+ ", areaName=" + areaName + "]"+super.toString();
	}

	
	
}

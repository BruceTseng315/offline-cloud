package com.turingmaker.service.operation.entity;

import com.turingmaker.entity.school.TTeacher;

/**
 * 
 * @author tzj
 *
 */
public class QueryTeachterResult extends TTeacher{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2304158531584439915L;

	
	private Long classId;
	
	private Long courseId;
	
	private String className;
	
	private String courseName;
	
	private String account;

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "QueryTeachterResult [classId=" + classId + ", courseId=" + courseId + ", className=" + className
				+ ", courseName=" + courseName + ", account=" + account + "]"+super.toString();
	}
	
	
	
	
}

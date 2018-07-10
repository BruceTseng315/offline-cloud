package com.turingmaker.service.operation.request;

import javax.validation.constraints.NotNull;

import com.turingmaker.entity.operation.TCourse;

public class SaveCourseRequest extends TCourse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -232122183213295463L;

	@NotNull(message="课程名称必填")
	@Override
	public String getCourseName() {
		// TODO Auto-generated method stub
		return super.getCourseName();
	}

	@NotNull(message="课程描述必填")
	@Override
	public String getCourseDescription() {
		// TODO Auto-generated method stub
		return super.getCourseDescription();
	}

	@NotNull(message="课程封面必填")
	@Override
	public String getCourseAvatar() {
		// TODO Auto-generated method stub
		return super.getCourseAvatar();
	}


	@Override
	public Integer getLessonNum() {
		// TODO Auto-generated method stub
		return super.getLessonNum();
	}

	
}

package com.turingmaker.service.operation.entity;

import com.turingmaker.entity.operation.TCourse;

public class TCourseLessons extends TCourse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8672116135099846192L;
	private int  lessonUpload;

	public int getLessonUpload() {
		return lessonUpload;
	}

	public void setLessonUpload(int lessonUpload) {
		this.lessonUpload = lessonUpload;
	}
	
	
}

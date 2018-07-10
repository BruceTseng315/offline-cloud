package com.turingmaker.service.operation.response;

import java.util.List;

import com.turingmaker.entity.operation.TCourse;
/**
 * 
 * @author tzj
 *
 */
import com.turingmaker.entity.operation.TLesson;
public class CourseDetailResponse extends TCourse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 157328312986518031L;

	
	List<TLesson> lessons;

	public CourseDetailResponse() {
		
	}

	public CourseDetailResponse(List<TLesson> lessons) {
		super();
		this.lessons = lessons;
	}


	public List<TLesson> getLessons() {
		return lessons;
	}


	public void setLessons(List<TLesson> lessons) {
		this.lessons = lessons;
	}
	
	
	
	
}

package com.turingmaker.service.operation.request;

import com.turingmaker.service.operation.entity.Resourceinfo;

/**
 * 
 * @author tzj
 *
 */
public class SaveLessonTask extends Resourceinfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6216065053150831708L;

	/**
	 * 上传的资源类型
	 */
	private Long lessonId;
	
	private  Resourceinfo program;

	
	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

	public Resourceinfo getProgram() {
		return program;
	}

	public void setProgram(Resourceinfo program) {
		this.program = program;
	}

	@Override
	public String toString() {
		return "SaveLessonTask [lessonId=" + lessonId + ", program=" + program + "]"+super.toString();
	}

	
	
	
	
}

package com.turingmaker.service.operation.request;

import com.turingmaker.service.operation.entity.Resourceinfo;

/**
 * 上传课时资源的实体类
 * @author tzj
 *
 */
public class SaveLessonResource extends Resourceinfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5920880560366868718L;

	/**
	 * 上传的资源类型
	 */
	private Integer type;
	
	private Long lessonId;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

	@Override
	public String toString() {
		return "SaveLessonResource [type=" + type + ", lessonId=" + lessonId + "]"+super.toString();
	}
	
	
	
}

package com.turingmaker.service.operation.response;

import java.io.Serializable;
import java.util.List;

/**
 * 课时的分页对象
 * @author Administrator
 *
 */
public class LessonPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3627992660478600621L;

	Pageinfo page;
	
	List<? super LessonResponse> datas;
	
	
	

	
	public LessonPage(Pageinfo page, List<? super LessonResponse> datas) {
		super();
		this.page = page;
		this.datas = datas;
	}


	public Pageinfo getPage() {
		return page;
	}


	public void setPage(Pageinfo page) {
		this.page = page;
	}

	

	public List<? super LessonResponse> getDatas() {
		return datas;
	}


	public void setDatas(List<? super LessonResponse> datas) {
		this.datas = datas;
	}


	@Override
	public String toString() {
		return "LessonPage [page=" + page + ", datas=" + datas + "]";
	}
	
	
}

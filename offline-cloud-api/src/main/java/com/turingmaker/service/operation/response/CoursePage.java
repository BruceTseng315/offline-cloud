package com.turingmaker.service.operation.response;

import java.io.Serializable;
import java.util.List;

import com.turingmaker.entity.operation.TCourse;

public class CoursePage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5046121515133376428L;

	Pageinfo page;

	List<? extends TCourse> datas;

	public Pageinfo getPage() {
		return page;
	}

	public void setPage(Pageinfo page) {
		this.page = page;
	}

	public List<? extends TCourse> getDatas() {
		return datas;
	}

	public void setDatas(List<? extends TCourse> datas) {
		this.datas = datas;
	}

	public CoursePage(Pageinfo page, List<? extends TCourse> datas) {
		super();
		this.page = page;
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "CoursePage [page=" + page + ", datas=" + datas + "]";
	}
	
	
	
}

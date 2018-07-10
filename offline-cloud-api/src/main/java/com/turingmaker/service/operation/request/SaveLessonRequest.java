package com.turingmaker.service.operation.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.turingmaker.entity.operation.TLesson;
import com.turingmaker.service.operation.entity.Resourceinfo;
import com.turingmaker.service.operation.entity.Taskinfo;

/**
 * 
 * @author tzj
 *
 */
public class SaveLessonRequest extends TLesson{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5258882409764267380L;

	
	private List<Resourceinfo> ppts;
	
	private List<Resourceinfo> plans;
	
	private List<Resourceinfo> videos;
	
	private List<Resourceinfo> others;
	
	private List<Resourceinfo> programs;
	
	private List<Taskinfo> tasks;
	
	
	
	@NotNull
	public List<Resourceinfo> getPpts() {
		return ppts;
	}

	public void setPpts(List<Resourceinfo> ppts) {
		this.ppts = ppts;
	}

	public List<Resourceinfo> getPlans() {
		return plans;
	}

	public void setPlans(List<Resourceinfo> plans) {
		this.plans = plans;
	}

	public List<Resourceinfo> getVideos() {
		return videos;
	}

	public void setVideos(List<Resourceinfo> videos) {
		this.videos = videos;
	}

	public List<Resourceinfo> getOthers() {
		return others;
	}

	public void setOthers(List<Resourceinfo> others) {
		this.others = others;
	}


	
	public List<Taskinfo> getTasks() {
		return tasks;
	}

	public void setTasks(List<Taskinfo> tasks) {
		this.tasks = tasks;
	}

	public List<Resourceinfo> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Resourceinfo> programs) {
		this.programs = programs;
	}


	@Override
	public String toString() {
		return "SaveLessonRequest [ppts=" + ppts + ", plans=" + plans + ", videos=" + videos + ", others=" + others
				+ ", programs=" + programs + ", task=" + tasks + "]";
	}


	
	
	
}

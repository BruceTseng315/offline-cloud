package com.turingmaker.controll.controllers;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turingmaker.common.domain.Result;
import com.turingmaker.entity.school.TTask;
import com.turingmaker.service.exception.ServiceException;
import com.turingmaker.service.operation.api.LessonService;
import com.turingmaker.service.operation.entity.Taskinfo;
import com.turingmaker.service.operation.request.SaveLessonRequest;
import com.turingmaker.service.operation.request.SaveLessonResource;
import com.turingmaker.service.operation.request.SaveLessonTask;

/**
 * 
 * @author tzj
 *
 */
@RestController
@RequestMapping("/turing/api/v2/manage/course/lesson")
public class LessonControll {

	
	
	
	Logger logger=LoggerFactory.getLogger(getClass());

	@Autowired
	LessonService lessonService;

	/**
	 * 课时编辑
	 * 
	 * @param lessonRequest
	 * @return
	 */
	@PostMapping("edit")
	public Result<?> editLesson(@RequestBody  SaveLessonRequest lessonRequest) {

		
		if(lessonRequest.getId()==null) {
			return  Result.errorrResult("课时id必填");
		}
		
		
		lessonRequest.setUpdateTime(new Date());
		try {
			lessonService.addLesson(lessonRequest);
		} catch (ServiceException e) {
			return  Result.errorrResult(e.getMessage());
		}
		return Result.successresult;
	}

	/**
	 * 课时添加
	 * 
	 * @param lessonRequest
	 * @return
	 */
	@PostMapping("save")
	public Result<?> addLesson(@RequestBody  @Valid SaveLessonRequest lessonRequest) {

		try {
			lessonService.addLesson(lessonRequest);
		} catch (ServiceException e) {
			return  Result.errorrResult(e.getMessage());
		}
		return Result.successresult;
	}

	/**
	 * 课时详情
	 * 
	 * @param lessonId
	 * @return
	 */
	@GetMapping("detailinfo")
	public Result<?> detailinfoLesson( Long lessonId) {

		return new Result<Object>(lessonService.detailsLesson(lessonId));
	}

	/**
	 * 课时编辑
	 * 
	 * @param lessonId
	 * @return
	 */
	@GetMapping("publish")
	public Result<?> publishLesson(Long lessonId) {

		lessonService.updateLessonState(lessonId, LessonService.STATE_PUBLISH);
		return Result.successresult;
	}

	/**
	 * 课时删除
	 * 
	 * @param lessonId
	 * @return
	 */
	@GetMapping("delete")
	public Result<?> deleteLesson(@NotNull(message="课时id不能为空") Long lessonId) {

		try {
			lessonService.deleteLesson(lessonId);
		} catch (Exception e) {
			return Result.errorrResult("删除失败");
		}
		return Result.successresult;
	}

	
	/**
	 * 添加资源(ppt,教案，程序,视频)
	 * @param lessonResource
	 * @return
	 */
	@PostMapping("addlesson_resources")
	public  Result<?> addlessonResources(@RequestBody SaveLessonResource lessonResource){
		 Long lessonId=lessonResource.getLessonId();
		 
		 Integer type=lessonResource.getType();
		 
		if(lessonId==null) {
			return Result.errorrResult("课时id不能为空");
		}
		
		if(type==null) {
			return Result.errorrResult("资源类型不能为空");
		}
		
		if(type==LessonService.RESOURSE_TYPE_PROGRAM) {
		
			return  new Result<Long>(	lessonService.addProgram(lessonId, lessonResource,lessonResource.getType()).getId());
		}else {
			return new Result<Long>(lessonService.addLessonResourse(lessonId, lessonResource,lessonResource.getType() ).getId());
		}
	
		
	}
	
	/**
	 * 添加课时作业
	 * @param lessonTask
	 * @return
	 */
	@PostMapping("addlesson_task")
	public  Result<?> addLessonTasl(@RequestBody  SaveLessonTask lessonTask){
		 Long lessonId=lessonTask.getLessonId();
		 String name=lessonTask.getName();
		 String url=lessonTask.getUrl();
		if(lessonId==null) {
			return Result.errorrResult("课时id不能为空");
		}
		 
		if(name==null) {
			return Result.errorrResult("作业不能为空");
		}
		
		if(url==null) {
			return Result.errorrResult("作业描述不能为空");
		}
		
	TTask task=
			lessonService.addTask(lessonId, new Taskinfo(name, url, lessonTask.getProgram()), null);
		
		return  new Result<Long>(task.getId());
	}
	
	@GetMapping("deleteTask")
	public  Result<?> deleteTask( Long lessonId, Long taskId){
		
		if(lessonId==null||taskId==null) {
			return Result.errorrResult("缺少必要参数");
		}
		
		
		try {
			lessonService.deleteLessonTask(lessonId, taskId);
		} catch (Exception e) {
			logger.warn("删除作业失败",e);
			return Result.errorrResult("删除失败");
		}
		return Result.successResult("删除成功"); 
	}
	
	
	@GetMapping("deleteResource")
	public  Result<?> deleteResource(Long lessonId, Long resourceId, Integer type){
		
		if(lessonId==null||resourceId==null||type==null) {
			return Result.errorrResult("缺少必要参数");
		}
		
		try {
			lessonService.deleteLessonResourceByType(lessonId, resourceId, type);
		} catch (Exception e) {
			logger.warn("删除资源失败",e);
			return Result.errorrResult("删除失败");
		}
		
		return Result.successResult("删除成功");
	}
	
}

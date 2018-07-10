package com.turingmaker.service.operation.imp;

import java.sql.SQLException;
import java.util.List;

import com.turingmaker.service.operation.response.LessonInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.turingmaker.dao.mapper.operation.ext.LessonMapperExt;
import com.turingmaker.dao.mapper.school.TLessonProgramMapper;
import com.turingmaker.dao.mapper.school.TResourceLessonMapper;
import com.turingmaker.dao.mapper.school.ext.ProgramMapperExt;
import com.turingmaker.dao.mapper.school.ext.ResourceMapperExt;
import com.turingmaker.dao.mapper.school.ext.TaskMapperExt;
import com.turingmaker.entity.operation.TLesson;
import com.turingmaker.entity.school.TLessonProgram;
import com.turingmaker.entity.school.TProgram;
import com.turingmaker.entity.school.TResource;
import com.turingmaker.entity.school.TResourceLesson;
import com.turingmaker.entity.school.TTask;
import com.turingmaker.service.exception.ServiceException;
import com.turingmaker.service.operation.api.LessonService;
import com.turingmaker.service.operation.entity.Resourceinfo;
import com.turingmaker.service.operation.entity.Taskinfo;
import com.turingmaker.service.operation.request.SaveLessonRequest;
import com.turingmaker.service.operation.response.LessonPage;
import com.turingmaker.service.operation.response.LessonResponse;
import com.turingmaker.service.operation.response.Pageinfo;

/**
 * 
 * @author tzj
 *
 */
@Service
public class LessonServiceImpl implements LessonService {

	@Autowired
	LessonMapperExt lessMapper;

	@Autowired
	TResourceLessonMapper resourseLessonMapper;

	@Autowired
	TLessonProgramMapper lessonProgramMapper;

	@Autowired
	TaskMapperExt taskMapper;

	@Autowired
	ResourceMapperExt resourceMapper;

	@Autowired
	ProgramMapperExt programMapperExt;

	Logger logger = LoggerFactory.getLogger(getClass());

	

	final String LESSONSELECTKEY = "COURSE_SELECT_UNIQ";

	@Transactional
	@Override
	public void addLesson(SaveLessonRequest lessonRequest) throws ServiceException {

		try {
			saveLessonAndResourse(lessonRequest);
		} catch (DuplicateKeyException e) {
			logger.warn("值与数据库存在冲突失败", e);

			if (e.getMessage().contains(LESSONSELECTKEY)) {
				throw new ServiceException(500, "入库失败" + "违反章节课时唯一约束");
			}
			throw new ServiceException(500, "值与数据库存在冲突失败");

		} catch (Exception e) {
			logger.warn("入库失败", e);
			throw new ServiceException(500, "入库失败" + e.getMessage());
		}

	}

	void saveLessonAndResourse(SaveLessonRequest lessonRequest) throws SQLException {

		TLesson lesson = new TLesson();
		BeanUtils.copyProperties(lessonRequest, lesson);

		if (lessonRequest.getId() == null) {
			lessMapper.insertSelective(lesson);
		} else {
			lessMapper.updateByPrimaryKeySelective(lesson);
		}

		/**
		 * 添加ppt ,教案 ，视频,程序，其他
		 */
		addLessonResourses(lesson.getId(), lessonRequest.getPpts(), RESOURSE_TYPE_PPTS);

		addLessonResourses(lesson.getId(), lessonRequest.getVideos(), RESOURSE_TYPE_VIDEO);

		addLessonResourses(lesson.getId(), lessonRequest.getPlans(), RESOURSE_TYPE_PLANS);

		addLessonResourses(lesson.getId(), lessonRequest.getOthers(), RESOURSE_TYPE_OTHER);

		addPrograms(lesson.getId(), lessonRequest.getPrograms(), RESOURSE_TYPE_PROGRAM);

		addTasks(lesson.getId(), lessonRequest.getTasks(), RESOURSE_TYPE_PROGRAM);

	}



	/**
	 * 添加一组资源
	 * 
	 * @param lessonId
	 * @param resourses
	 * @param resourseType
	 */
	public void addLessonResourses(Long lessonId, List<Resourceinfo> resourses, Integer resourseType) {

		if (resourses == null)
			return;

		resourses.forEach((e) -> {
			addLessonResourse(lessonId, e, resourseType);
		});

	}

	/**
	 * 添加一个资源
	 */
	@Transactional
	@Override
	public TResource addLessonResourse(Long lessonId, Resourceinfo resourceInfo, Integer resourseType) {

		if (resourceInfo == null)
			return null;
		
		if(resourseType>RESOURSE_TYPE_OTHER||resourseType<RESOURSE_TYPE_PPTS) {
			throw new ServiceException(500,"resourcetype不正确，可选值0,1,2,3,4");
		}

		TResource resource = new TResource();

		TResourceLesson resourceLesson = new TResourceLesson();

		resource.setResourceType(resourseType);
		resource.setResourceUrl(resourceInfo.getUrl());
		resource.setResourceName(resourceInfo.getName());
		resource.setResourceFileType(findFileSuffix(resourceInfo.getName()));
		resourceMapper.insertSelective(resource);

		resourceLesson.setLessonId(lessonId);
		resourceLesson.setResourceId(resource.getId());
		resourseLessonMapper.insertSelective(resourceLesson);
		
		
		return resource;

	}
	
	/**
	 * 添加程序 一组程序
	 * 
	 * @param lessonId
	 * @param resourses
	 * @param resourseType
	 *            预留着吧
	 */
	@Transactional
	public void addPrograms(Long lessonId, List<Resourceinfo> programs, Integer resourseType) {

		if (programs == null)
			return;
		programs.forEach((e) -> {
			addProgram(lessonId, e, resourseType);
		});

	}

	/**
	 * 添加一个程序
	 * 
	 * @param lessonId
	 * @param programinfo
	 * @param resourseType
	 * @return
	 */
	@Transactional
	@Override
	public TProgram addProgram(Long lessonId, Resourceinfo programinfo, Integer resourseType) {

		TProgram program = new TProgram();
		TLessonProgram lessonProgram = new TLessonProgram();

		program.setProgramContentUrl(programinfo.getUrl());
		program.setProgramName(programinfo.getName());

		if(lessonId!=null) {
			lessonProgram.setLessonId(lessonId);
		}

		try {
			programMapperExt.insertSelective(program);
			lessonProgram.setProgramId(program.getId());
			if(lessonId!=null) {
				lessonProgramMapper.insert(lessonProgram);
			}
		} catch (Exception e) {
			throw new ServiceException(500, "添加资源失败");
		}

		return program;
	}

	/**
	 * 添加作业
	 * 
	 * @param lessonId
	 * @param resourses
	 * @param resourseType
	 *            保留着
	 */
	public void addTasks(Long lessonId, List<Taskinfo> taskinfos, Integer resourseType) {

		TTask task = new TTask();

		if (taskinfos == null)
			return;

		taskinfos.forEach((e) -> {

			if (e instanceof Taskinfo) {

				Taskinfo t = (Taskinfo) e;
				TProgram program = addProgram(null, t.getProgram(), resourseType);

				task.setLessonId(lessonId);
				task.setProgramId(program.getId());
				task.setTaskDescurl(t.getUrl());
				task.setTaskName(t.getName());

				taskMapper.insertSelective(task);
			}

		});

	}
	
	/**
	 * 添加一个作业
	 */
	@Transactional
	@Override
	public TTask addTask(Long lessonId, Taskinfo taskinfo, Integer resourseType) {
		
		
		TTask task = new TTask();
		TProgram program = addProgram(null, taskinfo.getProgram(), resourseType);

		task.setLessonId(lessonId);
		task.setProgramId(program.getId());
		task.setTaskDescurl(taskinfo.getUrl());
		task.setTaskName(taskinfo.getName());

		try {
			taskMapper.insertSelective(task);
		} catch (Exception e) {
			throw new ServiceException(500, "添加作业失败");
		}
		
		return task;
		
	}
	
	

	@Override
	public LessonResponse detailsLesson(Long lessonId) {

		TLesson lesson = lessMapper.selectByPrimaryKey(lessonId);
		if (lesson == null) {
			throw new ServiceException(404, "没有找到这个课时");
		}

		LessonResponse lessonResponse = new LessonResponse();
		BeanUtils.copyProperties(lesson, lessonResponse);

		lessonResponse.setPpts(findResourseUrlForLesson(lessonId, RESOURSE_TYPE_PPTS));

		lessonResponse.setVideos(findResourseUrlForLesson(lessonId, RESOURSE_TYPE_VIDEO));

		lessonResponse.setPlans(findResourseUrlForLesson(lessonId, RESOURSE_TYPE_PLANS));

		lessonResponse.setOthers(findResourseUrlForLesson(lessonId, RESOURSE_TYPE_OTHER));

		lessonResponse.setTasks(findTaskForLesson(lessonId));

		lessonResponse.setPrograms(findProgramForLesson(lessonId));

		return lessonResponse;

	}

	private String findFileSuffix(String filename) {

		int index = -1;
		if ((index = filename.lastIndexOf(".")) > 0) {
			return filename.substring(index);
		}
		
		return filename;
	}

	@Override
	public List<Resourceinfo> findResourseUrlForLesson(Long lessonId, Integer resourseType) {

		return lessMapper.findResourseUrlForLesson(lessonId, resourseType);
	}

	
	
	@Override
	public LessonPage findByPage(Pageinfo pageinfo) {

		return new LessonPage(pageinfo, lessMapper.findByPage(pageinfo));
	}

	
	/**
	 * 更新课时状态
	 */
	@Override
	public void updateLessonState(Long lessonId, Integer state) {

		if (state < 0 || state > 2) {
			throw new ServiceException(500, "state不能小于0. 0;1;2为可选值");
		}

		TLesson lesson = new TLesson();
		lesson.setId(lessonId);
		lesson.setLessonState(state);

		int count = lessMapper.updateByPrimaryKeySelective(lesson);
		if (count <= 0) {
			throw new ServiceException(500, "课程更改失败,没有找到课程");
		}

	}

	
	
	
	
	
	/**
	 * 删除课时，一定先删除关联
	 */
	@Transactional
	@Override
	public void deleteLesson(Long lessonId) {

		/**
		 * 先删除关联的表，然后再删除课时本身
		 */
		resourceMapper.deleteByLessonId(lessonId);
		taskMapper.deleteByLessonId(lessonId);
		programMapperExt.deleteByLessonId(lessonId);

		int count = lessMapper.deleteByPrimaryKey(lessonId);

		if (count <= 0) {
			throw new ServiceException(404, "删除失败，没有对应课时");
		}
	}

	@Override
	public List<Taskinfo> findTaskForLesson(Long lessonId) {

		return lessMapper.findTasksForLesson(lessonId);
	}

	@Override
	public List<Resourceinfo> findProgramForLesson(Long lessonId) {

		return lessMapper.findProgramsForLesson(lessonId);
	}

	@Override
	@Transactional
	public void deleteLessonResourceByType(Long lessonId, long resourceId, Integer resourseType) {
	
		if(resourseType>RESOURSE_TYPE_OTHER||resourseType<RESOURSE_TYPE_PPTS) {
			throw new ServiceException(500,"resourcetype不正确，可选值0,1,2,3,4");
		}

		 if(resourseType==RESOURSE_TYPE_PROGRAM) {
			 programMapperExt.deleteByLessonAndId(lessonId,resourceId);
			 programMapperExt.deleteByPrimaryKey(resourceId);
		 }else {
			 resourceMapper.deleteByLessonAndId(lessonId,resourceId);
			 resourceMapper.deleteByPrimaryKey(resourceId);
		 }
	}

	@Override
	@Transactional
	public void deleteLessonTask(Long lessonId, long taskId) {
				
		taskMapper.deleteByPrimaryKey(taskId);
		
	}

	/**
	 * 获取课程中课时的基本信息
	 *
	 */
	@Override
	public List<LessonInfo> getInfoListByCourseId(Long courseId){
		List<LessonInfo> lessonInfos = lessMapper.getInfoListByCourseId(courseId);
		return lessonInfos;
	}
}

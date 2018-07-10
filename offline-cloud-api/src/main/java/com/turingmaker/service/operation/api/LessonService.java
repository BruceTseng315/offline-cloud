package com.turingmaker.service.operation.api;

import java.util.List;

import com.turingmaker.entity.school.TProgram;
import com.turingmaker.entity.school.TResource;
import com.turingmaker.entity.school.TTask;
import com.turingmaker.service.exception.ServiceException;
import com.turingmaker.service.operation.entity.Resourceinfo;
import com.turingmaker.service.operation.entity.Taskinfo;
import com.turingmaker.service.operation.request.SaveLessonRequest;
import com.turingmaker.service.operation.response.LessonInfo;
import com.turingmaker.service.operation.response.LessonPage;
import com.turingmaker.service.operation.response.LessonResponse;
import com.turingmaker.service.operation.response.Pageinfo;

/**
 * 
 * @author tzj
 *
 */
public interface LessonService {

	
	final int STATE_UNPUB = 0;
	final int STATE_PUBLISH = 1;
	final int STATE_OFF = 2;
	
	
	
	final int RESOURSE_TYPE_PPTS = 0;
	final int RESOURSE_TYPE_VIDEO = 1;
	final int RESOURSE_TYPE_PLANS = 2;
	final int RESOURSE_TYPE_PROGRAM = 3;
	final int RESOURSE_TYPE_OTHER = 4;

	/**
	 * 添加课时
	 * 
	 * @param lessonRequest
	 * @throws ServiceException
	 */
	public void addLesson(SaveLessonRequest lessonRequest) throws ServiceException;

	/**
	 * 获取课时的详情
	 * 
	 * @param lessonId
	 *            课时id
	 */
	public LessonResponse detailsLesson(Long lessonId) throws ServiceException;

	/**
	 * 查询课时的素材
	 * 
	 * @param lessonId
	 * @return
	 */
	public List<Resourceinfo> findResourseUrlForLesson(Long lessonId, Integer resourseType);

	/**
	 * 查询课时的作业
	 * 
	 * @param lessonId
	 * @return
	 */
	public List<Taskinfo> findTaskForLesson(Long lessonId);

	/**
	 * 查询课时的程序
	 * 
	 * @param lessonId
	 * @return
	 */
	public List<Resourceinfo> findProgramForLesson(Long lessonId);

	/**
	 * 分页查询
	 * 
	 * @param pageinfo
	 * @return
	 */
	public LessonPage findByPage(Pageinfo pageinfo);

	/**
	 * 更改课程状态
	 * 
	 * @param lessonId
	 * @param state
	 *            为发布 ，1:已发布，2：已下架
	 */
	public void updateLessonState(Long lessonId, Integer state);



	/**
	 * 添加课时程序
	 * 
	 * @param lessonId
	 * @param programinfo
	 * @param resourseType
	 * @return
	 */
	public TProgram addProgram(Long lessonId, Resourceinfo programinfo, Integer resourseType);

	/**
	 * 添加课时一个作业
	 * 
	 * @param lessonId
	 * @param taskinfos
	 * @param resourseType
	 */
	public TTask addTask(Long lessonId, Taskinfo taskinfo, Integer resourseType);

	/**
	 * 添加课时资源单个
	 * 
	 * @param lessonId
	 *            课时id
	 * @param resourses
	 *            资源列表
	 * @param resourseType
	 *            资源类型
	 */

	public TResource addLessonResourse(Long lessonId, Resourceinfo resourceInfo, Integer resourseType);

	
	/**
	 * 删除课时
	 * 
	 * @param lessonId
	 *
	 */
	public void deleteLesson(Long lessonId);
	
	
	
	
	/**
	 * 删除资源
	 * @param lessonId
	 * @param type
	 */
	public  void deleteLessonResourceByType(Long lessonId,long resourceId,Integer type);
	
	
	
	/**
	 * 删除作业
	 * @param lessonId
	 * @param type
	 */
	public  void deleteLessonTask(Long lessonId,long taskId);

	/**
	 * 获取课程中课时的基本信息
	 *
	 */
	public List<LessonInfo> getInfoListByCourseId(Long courseId);
}

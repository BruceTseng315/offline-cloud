package com.turingmaker.dao.mapper.school.ext;

import org.apache.ibatis.annotations.Delete;

import com.turingmaker.dao.mapper.school.TTaskMapper;

/**
 * task 扩展
 * 
 * @author Administrator
 *
 */
public interface TaskMapperExt extends TTaskMapper {

	/**
	 * 删除课程资源
	 */
	@Delete({ "delete from  `T_TASK`  WHERE LESSON_ID=#{arg0}" })
	int deleteByLessonId(Long lessonId);


}

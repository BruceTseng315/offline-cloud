package com.turingmaker.dao.mapper.school.ext;

import org.apache.ibatis.annotations.Delete;

import com.turingmaker.dao.mapper.school.TResourceMapper;

/**
 * resource 扩展
 * 
 * @author Administrator
 *
 */
public interface ResourceMapperExt extends TResourceMapper {

	/**
	 * 删除课程所有资源
	 */
	@Delete({ "delete from  `T_RESOURCE_LESSON`  WHERE LESSON_ID=#{arg0}" })
	int deleteByLessonId(Long id);

	/**
	 * 删除课程的资源
	 */
	@Delete({ "delete from  `T_RESOURCE_LESSON`  WHERE LESSON_ID=#{arg0} and RESOURCE_ID=#{arg1}" })
	int deleteByLessonAndId(Long lessonId, Long resourceId);

}

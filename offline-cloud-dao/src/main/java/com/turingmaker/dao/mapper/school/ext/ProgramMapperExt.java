package com.turingmaker.dao.mapper.school.ext;

import org.apache.ibatis.annotations.Delete;

import com.turingmaker.dao.mapper.school.TProgramMapper;

/**
 * TProgramMapper 扩展
 * 
 * @author Administrator
 *
 */
public interface ProgramMapperExt extends TProgramMapper {

	/**
	 * 删除课程所有程序
	 */
	@Delete({ "delete from  `T_LESSON_PROGRAM`  WHERE LESSON_ID=#{arg0}" })
	int deleteByLessonId(Long lessonId);

	/**
	 * 删除课程的程序
	 */
	@Delete({ "delete from  `T_LESSON_PROGRAM`  WHERE LESSON_ID=#{arg0} and PROGRAM_ID=#{arg1}" })
	int deleteByLessonAndId(Long lessonId, Long programId);

}

package com.turingmaker.dao.mapper.operation.ext;

import java.util.List;

import com.turingmaker.service.operation.response.CourseInfo;
import com.turingmaker.service.operation.response.LessonInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import com.turingmaker.dao.mapper.operation.TLessonMapper;
import com.turingmaker.entity.operation.TLesson;
import com.turingmaker.service.operation.entity.Resourceinfo;
import com.turingmaker.service.operation.entity.Taskinfo;
import com.turingmaker.service.operation.response.Pageinfo;

/**
 * 
 * @author tzj
 *
 */
public interface LessonMapperExt extends TLessonMapper{

	
	/**
	 * 查找课时的资源URL列表
	 * @param lessonId
	 * @param resourseType
	 * @return
	 */
	@Select("select ID as TID, RESOURCE_URL,RESOURCE_NAME from `T_RESOURCE` tr where `ID`  in  ( select trl.`RESOURCE_ID`  from `T_RESOURCE_LESSON` trl  where trl.`LESSON_ID` =#{arg0}) and `RESOURCE_TYPE` =#{arg1}")
	
	@Results({
		@Result(column="TID",property="id",jdbcType=JdbcType.VARCHAR),
		@Result(column="RESOURCE_NAME",property="name",jdbcType=JdbcType.VARCHAR),
		@Result(column="RESOURCE_URL",property="url",jdbcType=JdbcType.VARCHAR)
		})
	public List<Resourceinfo> findResourseUrlForLesson(Long lessonId,Integer resourseType);
	
	
	
	
	/**
	 * 查找课时的资源URL列表
	 * @param lessonId
	 * @param resourseType
	 * @return
	 */
	@Select("select tp.`ID` as TID,tp.`PROGRAM_NAME` ,tp.`PROGRAM_CONTENT_URL`   from `T_PROGRAM`  tp  left join  T_LESSON_PROGRAM   tlp on tlp.`PROGRAM_ID` =tp.id where tlp.`LESSON_ID` =#{arg0}")
	
	@Results({
		@Result(column="TID",property="id",jdbcType=JdbcType.VARCHAR),
		@Result(column="PROGRAM_NAME",property="name",jdbcType=JdbcType.VARCHAR),
		@Result(column="PROGRAM_CONTENT_URL",property="url",jdbcType=JdbcType.VARCHAR)
		})
	public List<Resourceinfo> findProgramsForLesson(Long lessonId);
	
	
	
	
	
	/**
	 * 查找课时的资源URL列表
	 * @param lessonId
	 * @param resourseType
	 * @return
	 */
	@Select("select  ttask.ID as TID,TASK_NAME,TASK_DESCURL,`PROGRAM_ID`,PROGRAM_CONTENT_URL,PROGRAM_NAME   from  `T_TASK` ttask  LEFT JOIN `T_PROGRAM` tp on ttask.`PROGRAM_ID` =tp.`ID` WHERE `LESSON_ID`=#{arg0} ")
	
	@Results({
		@Result(column="TID",property="id",jdbcType=JdbcType.VARCHAR),
		@Result(column="TASK_NAME",property="name",jdbcType=JdbcType.VARCHAR),
		@Result(column="TASK_DESCURL",property="url",jdbcType=JdbcType.VARCHAR),
		@Result(column="PROGRAM_CONTENT_URL",property="program.url",jdbcType=JdbcType.VARCHAR),
		@Result(column="PROGRAM_NAME",property="program.name",jdbcType=JdbcType.VARCHAR)
		})
	public List<Taskinfo> findTasksForLesson(Long lessonId);
	
	
	/**
	 * 分页查询
	 * @param pageinfo
	 * @return
	 */
	@Select("SELECT *  FROM  T_LESSON")
	 @Results(id="resultMap",value={
	        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
	        @Result(column="LESSON_NAME", property="lessonName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="LESSON_DESCRIPTION", property="lessonDescription", jdbcType=JdbcType.VARCHAR),
	        @Result(column="LESSON_AVATAR", property="lessonAvatar", jdbcType=JdbcType.VARCHAR),
	        @Result(column="LESSON_SECTION", property="lessonSection", jdbcType=JdbcType.INTEGER),
	        @Result(column="LESSON_STATE", property="lessonState", jdbcType=JdbcType.INTEGER),
	        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="COURSE_ID", property="courseId", jdbcType=JdbcType.BIGINT)
	    })
	public List<TLesson> findByPage(Pageinfo pageinfo);
	
	
	/**
	 * 分页查询
	 * @param courseId
	 * @return
	 */
	@Select("SELECT *  FROM  T_LESSON where  `COURSE_ID` =#{arg0} order by LESSON_SECTION asc")
	@ResultMap("resultMap")
	public List<TLesson> findByCourse(Long courseId);

	/**
	 * 查询
	 * @param courseId
	 * @return
	 */
	@Select("select `ID`  ,`LESSON_NAME` ,`LESSON_DESCRIPTION` ,`LESSON_AVATAR` ,`LESSON_AVATAR` ,LESSON_SECTION from `T_LESSON` WHERE `COURSE_ID` = #{arg0} AND `LESSON_STATE` NOT LIKE 0 ORDER BY LESSON_SECTION  ASC")
	@Results({
			@Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
			@Result(column="LESSON_NAME", property="lessonName", jdbcType=JdbcType.VARCHAR),
			@Result(column="LESSON_DESCRIPTION", property="lessonDescription", jdbcType=JdbcType.VARCHAR),
			@Result(column="LESSON_AVATAR", property="lessonAvatar", jdbcType=JdbcType.VARCHAR),
			@Result(column="LESSON_SECTION", property="lessonSection", jdbcType=JdbcType.INTEGER),
	})
	@ResultType(LessonInfo.class)
	public List<LessonInfo> getInfoListByCourseId(Long courseId);

	
	
}

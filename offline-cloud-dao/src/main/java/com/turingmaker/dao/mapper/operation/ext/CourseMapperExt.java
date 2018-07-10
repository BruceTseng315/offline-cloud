package com.turingmaker.dao.mapper.operation.ext;

import java.util.List;

import com.turingmaker.service.operation.response.CourseInfo;
import org.apache.ibatis.annotations.*;

import org.apache.ibatis.type.JdbcType;

import com.turingmaker.dao.mapper.operation.TCourseMapper;
import com.turingmaker.entity.operation.TCourse;
import com.turingmaker.service.operation.entity.TCourseLessons;
import com.turingmaker.service.operation.response.Pageinfo;

public interface CourseMapperExt extends  TCourseMapper{
	
	@SelectProvider(type=CourseSqlProvider.class,method="selectForListCourse")
    @Results(id="resultMap",value={
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="COURSE_CODE", property="courseCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="COURSE_NAME", property="courseName", jdbcType=JdbcType.VARCHAR),
        @Result(column="COURSE_DESCRIPTION", property="courseDescription", jdbcType=JdbcType.VARCHAR),
        @Result(column="COURSE_AVATAR", property="courseAvatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="COURSE_STATE", property="courseState", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LESSON_NUM", property="lessonNum", jdbcType=JdbcType.INTEGER)
    })

    List<TCourse> selectForListCourse(@Param("course")  TCourse record);

    @Select("select * from T_COURSE where ID not in (select COURSE_ID from T_SCHOOL_COURSE where SCHOOL_ID =#{arg0})" )
    @ResultMap("resultMap")
    List<TCourse> selectForListCourseBySchoolId(@Param("course") TCourse record);
	
	
	
	@SelectProvider(type=CourseSqlProvider.class,method="selectForListCoursePage")
	@Results(id="resultMapLessons",value={
	        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
	        @Result(column="COURSE_CODE", property="courseCode", jdbcType=JdbcType.VARCHAR),
	        @Result(column="COURSE_NAME", property="courseName", jdbcType=JdbcType.VARCHAR),
	        @Result(column="COURSE_DESCRIPTION", property="courseDescription", jdbcType=JdbcType.VARCHAR),
	        @Result(column="COURSE_AVATAR", property="courseAvatar", jdbcType=JdbcType.VARCHAR),
	        @Result(column="COURSE_STATE", property="courseState", jdbcType=JdbcType.INTEGER),
	        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="upload_lessons", property="lessonUpload", jdbcType=JdbcType.INTEGER),
	        @Result(column="LESSON_NUM", property="lessonNum", jdbcType=JdbcType.INTEGER)
	    })
	@ResultType(TCourseLessons.class)
    List<TCourseLessons> selectForListCoursePage(Pageinfo pageinfo,@Param("course")TCourse record);
	
	
	
	

    @Select("select * from T_COURSE where ID not in (select COURSE_ID from T_SCHOOL_COURSE where SCHOOL_ID =#{arg0}) AND COURSE_STATE =1" )
    @ResultMap("resultMap")
    public List<TCourse> getEnableCourse(Long schoolId);

    @Select("select tc.ID,tc.COURSE_NAME,tc.COURSE_DESCRIPTION,tc.COURSE_AVATAR ,tc.LESSON_NUM  from T_COURSE tc where ID in (select COURSE_ID from T_CLASS_COURSE_TEACHER where TEACHER_ID= #{arg0}  and STATE =1)")
	@Results({
			@Result(column = "ID", property = "courseId", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "COURSE_CODE", property = "courseCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "COURSE_NAME", property = "courseName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "COURSE_DESCRIPTION", property = "courseDesc", jdbcType = JdbcType.VARCHAR),
			@Result(column="LESSON_NUM", property="lessonNum", jdbcType=JdbcType.INTEGER),
			@Result(column = "COURSE_AVATAR", property = "courseAvatar", jdbcType = JdbcType.VARCHAR),
	})
	@ResultType(CourseInfo.class)
	List<CourseInfo> findAllCourseByTercherId(Long teacherId);

	@Select("select tc.ID,tc.COURSE_NAME,tc.COURSE_DESCRIPTION,tc.COURSE_AVATAR ,tc.LESSON_NUM ,(select count(0) from T_CLASS_LESSON tcl where tcl.COURSE_ID = tc.ID and tcl.CLASS_ID = #{arg2}) as FINISH_NUM from T_COURSE tc where ID in (select COURSE_ID from T_CLASS_COURSE_TEACHER where TEACHER_ID = #{arg1} AND CLASS_ID = #{arg2} and STATE =1)")
	@Results({
			@Result(column = "ID", property = "courseId", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "COURSE_NAME", property = "courseName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "COURSE_DESCRIPTION", property = "courseDesc", jdbcType = JdbcType.VARCHAR),
			@Result(column = "COURSE_AVATAR", property = "courseAvatar", jdbcType = JdbcType.VARCHAR),
			@Result(column="LESSON_NUM", property="lessonNum", jdbcType=JdbcType.INTEGER),
			@Result(column="FINISH_NUM", property="finishNum", jdbcType=JdbcType.INTEGER),

	})
	@ResultType(CourseInfo.class)
	List<CourseInfo> findAllCourseByTercherIdAndClassIdPage(Pageinfo pageinfo ,Long teacherId ,Long classId);

	@Select("select tc.ID,tc.COURSE_NAME,tc.COURSE_DESCRIPTION,tc.COURSE_AVATAR ,tc.LESSON_NUM  from T_COURSE tc where ID = #{arg0}")
	@Results({
			@Result(column = "ID", property = "courseId", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "COURSE_NAME", property = "courseName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "COURSE_DESCRIPTION", property = "courseDesc", jdbcType = JdbcType.VARCHAR),
			@Result(column = "COURSE_AVATAR", property = "courseAvatar", jdbcType = JdbcType.VARCHAR),
			@Result(column="LESSON_NUM", property="lessonNum", jdbcType=JdbcType.INTEGER),
	})
	@ResultType(CourseInfo.class)
	CourseInfo findCourseInfo(Long courseId);

}

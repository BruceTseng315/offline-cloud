package com.turingmaker.dao.mapper.teachter.ext;

import com.turingmaker.dao.mapper.teachter.TClassLessonMapper;
import com.turingmaker.entity.teachter.TClassLesson;
import com.turingmaker.service.student.entity.LessonState;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @Author zengdingyang
 * @Date 2018\7\6 0006
 */
public interface TClassLessonMapperExt extends TClassLessonMapper {

    @Select({
            "select tcl.LESSON_SECTION,tcl.LESSON_ID from T_CLASS_LESSON tcl\n" +
                    "LEFT JOIN T_CLASS_COURSE_TEACHER tct ON tcl.CLASS_ID=tct.CLASS_ID AND tcl.COURSE_ID=tct.COURSE_ID " +
                    "where tcl.CLASS_ID=#{arg0} and tcl.COURSE_ID=#{arg1} order by LESSON_SECTION asc"
    })
    @Results({
            @Result(column = "LESSON_SECTION",property = "lessonSection",jdbcType = JdbcType.INTEGER),
            @Result(column = "LESSON_ID",property = "lessonId",jdbcType = JdbcType.INTEGER)
    })
    List<LessonState> getCourseLessonStateList(Long classId,Long courseId);

    @Select("select * from T_CLASS_LESSON where CLASS_ID =#{arg0} and LESSON_ID = #{arg1}")
    @Results({
            @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="CLASS_ID", property="classId", jdbcType=JdbcType.BIGINT),
            @Result(column="LESSON_ID", property="lessonId", jdbcType=JdbcType.BIGINT),
            @Result(column="COURSE_ID", property="courseId", jdbcType=JdbcType.BIGINT),
            @Result(column="LESSON_SECTION", property="lessonSection", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TClassLesson getByClassIdAndLessonId(Long classId ,Long lessonId);
}

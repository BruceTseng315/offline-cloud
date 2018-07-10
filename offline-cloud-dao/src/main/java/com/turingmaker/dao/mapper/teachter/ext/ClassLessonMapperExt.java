package com.turingmaker.dao.mapper.teachter.ext;

import com.turingmaker.dao.mapper.teachter.TClassLessonMapper;
import com.turingmaker.service.teachter.entity.CourseSchdule;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * 班级课时的相关mapper扩展
 * @author  tzj
 */
public interface ClassLessonMapperExt  extends TClassLessonMapper {


    /**
     *  获取指定班级的课时进度
     * @param teachterId 教师id
     * @param classId  班级id
     */
    @Results({
            @Result(column="CLASS_ID", property="classId", jdbcType=JdbcType.BIGINT),
            @Result(column="COURSE_ID", property="courseId", jdbcType=JdbcType.BIGINT),
            @Result(column="COURSE_NAME", property="courseName", jdbcType=JdbcType.VARCHAR),
            @Result(column="CLASS_NAME", property="className", jdbcType=JdbcType.VARCHAR),
            @Result(column="LESSON_ID", property="lessonId", jdbcType=JdbcType.BIGINT),
            @Result(column="LESSON_STATE", property="state", jdbcType=JdbcType.INTEGER),
            @Result(column="LESSON_NAME", property="lessonName", jdbcType=JdbcType.VARCHAR),
            @Result(column="LESSON_NUM", property="totalLessonNum", jdbcType=JdbcType.INTEGER)

    })
    @Select("select tcct.*,tc.`COURSE_NAME`,tl.`LESSON_NAME`,tl.`ID` AS LESSON_ID,tc2.`CLASS_NAME`,tc.`LESSON_NUM`,(select count(0)  from `T_CLASS_LESSON` tcl where  tcl.`LESSON_ID`=tl.`ID` and tc2.id=tcct.`CLASS_ID`  ) as LESSON_STATE " +
            "FROM `T_CLASS_COURSE_TEACHER`  tcct  " +
            "LEFT JOIN  `T_COURSE` tc  on tcct.`COURSE_ID`  =tc.`ID` " +
            "LEFT JOIN  `T_LESSON`  tl  on tc.`ID` =tl.`COURSE_ID` " +
            "LEFT JOIN  `T_CLASS` tc2 on tcct.`CLASS_ID` =tc2.`ID` " +
            "where tcct.`TEACHER_ID` =#{arg0}  and tcct.`CLASS_ID` =#{arg1} ")
    List<CourseSchdule> findForClassCourseSchdule(Long teachterId,Long classId);


    /**
     * 获取老师下面所有的班级的课程进度
     * @param teachterId  教师id
     * @return
     */
    @Results({
            @Result(column="CLASS_ID", property="classId", jdbcType=JdbcType.BIGINT),
            @Result(column="COURSE_ID", property="courseId", jdbcType=JdbcType.BIGINT),
            @Result(column="LESSON_ID", property="lessonId", jdbcType=JdbcType.BIGINT),
            @Result(column="COURSE_NAME", property="courseName", jdbcType=JdbcType.VARCHAR),
            @Result(column="CLASS_NAME", property="className", jdbcType=JdbcType.VARCHAR),
            @Result(column="LESSON_STATE", property="state", jdbcType=JdbcType.INTEGER),
    })
    @Select("select tcct.*,tc.`COURSE_NAME`,tl.`LESSON_NAME`,tl.`ID` AS LESSON_ID,tc2.`CLASS_NAME`,tc.`LESSON_NUM` ,(select count(0)  from `T_CLASS_LESSON` tcl where  tcl.`LESSON_ID`=tl.`ID` and tc2.id=tcct.`CLASS_ID`  ) as LESSON_STATE " +
            "FROM `T_CLASS_COURSE_TEACHER`  tcct  " +
            "LEFT JOIN  `T_COURSE` tc  on tcct.`COURSE_ID`  =tc.`ID` " +
            "LEFT JOIN  `T_LESSON`  tl  on tc.`ID` =tl.`COURSE_ID` " +
            "LEFT JOIN  `T_CLASS` tc2 on tcct.`CLASS_ID` =tc2.`ID` " +
            "where tcct.`TEACHER_ID` =#{arg0} ")
    List<CourseSchdule> findForMyCourseSchdule(Long teachterId);

}

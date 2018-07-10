package com.turingmaker.dao.mapper.school.ext;

import com.turingmaker.dao.mapper.school.TClassMapper;
import com.turingmaker.entity.operation.TCourse;
import com.turingmaker.entity.school.TClass;
import com.turingmaker.service.operation.request.ClassListRequest;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.school.bo.ClassBo;
import com.turingmaker.service.teachter.entity.ClassCourseInfo;
import com.turingmaker.service.teachter.entity.ClassInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TClassMapperExt extends TClassMapper{
    /**
     * 查找学生所在班级
     * @param studentId
     * @return
     */
    @Select({
            "select",
            "ID, CLASS_CODE, CLASS_NAME, CLASS_TYPE, CREATE_TIME, UPDATE_TIME, SCHOOL_ID, ",
            "GRADE",
            "from T_CLASS",
            "where ID in (select CLASS_ID from T_CLASS_STUDENT where STUDENT_ID = #{arg0,jdbcType=BIGINT})"
    })
    @Results(id="resultMap",value={
            @Result(column="ID", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="CLASS_CODE", property="classCode", jdbcType=JdbcType.INTEGER),
            @Result(column="CLASS_NAME", property="className", jdbcType=JdbcType.VARCHAR),
            @Result(column="CLASS_TYPE", property="classType", jdbcType=JdbcType.TINYINT),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="SCHOOL_ID", property="schoolId", jdbcType=JdbcType.BIGINT),
            @Result(column="GRADE", property="grade", jdbcType=JdbcType.INTEGER)
    })
    List<TClass> selectByStudentId(Long studentId);


    /**
     * c查找指定学校班级详情列表
     * @param pageinfo
     * @param schoolId
     * @param classListRequest
     * @return
     */
    @SelectProvider(type=ClassSqlProvider.class,method="selectClassListPage")
    @Results({
            @Result(column="ID", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="CLASS_CODE", property="classCode", jdbcType=JdbcType.INTEGER),
            @Result(column="CLASS_NAME", property="className", jdbcType=JdbcType.VARCHAR),
            @Result(column="CLASS_TYPE", property="classType", jdbcType=JdbcType.TINYINT),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="SCHOOL_ID", property="schoolId", jdbcType=JdbcType.BIGINT),
            @Result(column="GRADE", property="grade", jdbcType=JdbcType.INTEGER),
            @Result(property = "studentCount",column = "ID",javaType = Integer.class,one=@One(select = "com.turingmaker.dao.mapper.school.ext.TClassStudentMapperExt.selectStudentCntByClassId")),
            @Result(property = "courses",column = "ID",javaType = List.class,many=@Many(select = "com.turingmaker.dao.mapper.school.ext.TClassCourseTeacherMapperExt.getClassCourseTeacherByClassId"))

    })
    List<ClassBo> selectClassListPage(Pageinfo pageinfo, @Param("schoolId") Long schoolId,@Param("classListRequest") ClassListRequest classListRequest);

    /**
     * 查找指定学校所有班级
     * @param schoolId
     * @return
     */
    @Select({"select",
            "ID, CLASS_CODE, CLASS_NAME, CLASS_TYPE, CREATE_TIME, UPDATE_TIME, SCHOOL_ID, ",
            "GRADE",
            "from T_CLASS",
            "where  SCHOOL_ID = #{arg0,jdbcType=BIGINT}"
    })
    @ResultMap("resultMap")
    List<TClass> selectClassesBySchoolId(Long schoolId);

    @Select({
            "select",
            "ID, COURSE_CODE, COURSE_NAME, COURSE_DESCRIPTION, COURSE_AVATAR, COURSE_STATE, ",
            "CREATE_TIME, UPDATE_TIME, LESSON_NUM",
            "from T_COURSE",
            "where ID in (select COURSE_ID from T_SCHOOL_COURSE where SCHOOL_ID=#{arg0} and STATE=1)",
            " and ID not in (select COURSE_ID from T_CLASS_COURSE_TEACHER where CLASS_ID=#{arg1})"
    })
    @Results({
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
    List<TCourse> getAddableCourses(Long schoolId,Long classId);


    @Select("select tc.* from T_CLASS tc where tc.ID in (select CLASS_ID from T_CLASS_COURSE_TEACHER where TEACHER_ID = #{arg0} and COURSE_ID = #{arg1} and STATE =1)")
    @ResultMap("resultMap")
    List<TClass> getClassByTeacherIdAndCourseId(Long teacherId ,Long courseId);

    /**
     *
     * 获取班级上次上课课程
     */

    @Results({
            @Result(column="COURSE_ID", property="courseId", jdbcType=JdbcType.BIGINT),
            @Result(column="LESSON_ID", property="lessonId", jdbcType=JdbcType.BIGINT),
            @Result(column="LESSON_NAME", property="lessonName", jdbcType=JdbcType.VARCHAR),
            @Result(column="COURSE_NAME", property="courseName", jdbcType=JdbcType.VARCHAR)
    })
    @Select("select  tcl.`LESSON_ID`  ,tcl.`COURSE_ID` ,tc.`COURSE_NAME`,tl.`LESSON_NAME`   FROM `T_CLASS_LESSON` tcl   LEFT JOIN `T_COURSE` tc on tc.`ID` =tcl.`COURSE_ID`  LEFT JOIN `T_LESSON` tl on tl.`ID` =tcl.`LESSON_ID`    where `CLASS_ID` =#{arg0}  ORDER BY  tcl.`UPDATE_TIME`  DESC  limit 1")
    ClassCourseInfo selectClassLastCourse(Long classId);


    /**
     * 根据教师查询班级列表
     * @param teachterId 教师id
     */
    @Select("select tcct.`CLASS_ID`,tcct.`TEACHER_ID` ,tc.SCHOOL_ID,tc.CLASS_CODE,tc.CLASS_TYPE  ,tc.`CLASS_NAME`,(select count(0)  from  `T_CLASS_STUDENT`  tcs where  tcs.`CLASS_ID` =tc.`ID` ) as STUDENT_COUNT  from `T_CLASS_COURSE_TEACHER`  tcct " +
            "LEFT JOIN   `T_CLASS`  tc  on  tcct.`CLASS_ID` =tc.`ID` " +
            "where tcct.`TEACHER_ID` =#{arg1}")
    @Results(id="classInfoMap",value={

            @Result(column="CLASS_ID", property="classId", jdbcType=JdbcType.BIGINT),
            @Result(column="CLASS_NAME", property="className", jdbcType=JdbcType.BIGINT),
            @Result(column="STUDENT_COUNT", property="studentCount", jdbcType=JdbcType.VARCHAR),
            @Result(column="COURSE_CODE", property="courseCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="SCHOOL_ID", property="schoolId", jdbcType=JdbcType.BIGINT),
            @Result(column="CLASS_TYPE", property="classType", jdbcType=JdbcType.TINYINT)
    })
    List<ClassInfo> selectTeachterClassInfoPage(Pageinfo pageinfo,Long teachterId);

    
    /**
     * 根据教师查询班级列表
     * @param teachterId 教师id
     */
    @Select("select tc.ID, tc.CLASS_CODE, tc.CLASS_NAME, tc.CLASS_TYPE, tc.SCHOOL_ID,(select count(0) "
    		+ "from  `T_CLASS_STUDENT` tcs WHERE tcs.`CLASS_ID` =tc.`ID`   )STUDENT_COUNT FROM `T_CLASS` tc where tc.`ID` =#{arg0}")
   @ResultMap("classInfoMap")
    ClassInfo selectClassInfoByClassId(Long classId);

}

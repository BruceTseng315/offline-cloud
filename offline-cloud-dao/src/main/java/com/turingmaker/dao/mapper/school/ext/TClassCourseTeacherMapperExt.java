package com.turingmaker.dao.mapper.school.ext;

import com.turingmaker.dao.mapper.school.TClassCourseTeacherMapper;
import com.turingmaker.entity.operation.TCourse;
import com.turingmaker.entity.school.TClass;
import com.turingmaker.entity.school.TClassCourseTeacher;
import com.turingmaker.service.school.bo.ClassCourseBo;
import com.turingmaker.service.student.entity.ClassCourseOutline;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;

import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TClassCourseTeacherMapperExt extends TClassCourseTeacherMapper {

    @Select({
            "select",
            "ID, CLASS_ID, COURSE_ID, TEACHER_ID, CREATE_TIME, UPDATE_TIME, STATE",
            "from T_CLASS_COURSE_TEACHER",
            "where CLASS_ID = #{arg0,jdbcType=BIGINT}"
    })
    @Results(id="resultMap",value={
            @Result(column="ID", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="CLASS_ID", property="classId", jdbcType=JdbcType.BIGINT),
            @Result(column="COURSE_ID", property="courseId", jdbcType=JdbcType.BIGINT),
            @Result(column="TEACHER_ID", property="teacherId", jdbcType=JdbcType.BIGINT),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER)
    })
    List<TClassCourseTeacher> selectByClassId(Long classId);

    @Select({
            "select",
            "ID, CLASS_ID, COURSE_ID, TEACHER_ID, CREATE_TIME, UPDATE_TIME, STATE",
            "from T_CLASS_COURSE_TEACHER",
            "where CLASS_ID = #{arg0,jdbcType=BIGINT} and COURSE_ID = #{arg1,jdbcType=BIGINT}"
    })
    @ResultMap("resultMap")
    TClassCourseTeacher selectByClassIdAndCourseId(Long classId,Long courseId);

    @Select("select * from T_CLASS_COURSE_TEACHER where TEACHER_ID = #{arg0}")
    @ResultMap("resultMap")
    List<TClassCourseTeacher> findByTeacherId(Long teacherId);

    /**
     *查找老师授课班级
     * @param teacherId
     * @return
     */
    @Select({"select",
            " CLASS_NAME ",
            " from T_CLASS where ID in (select CLASS_ID from T_CLASS_COURSE_TEACHER where TEACHER_ID = #{arg0})"
    })
    @Results({
            @Result(column="CLASS_NAME", property="className", jdbcType=JdbcType.VARCHAR)
    })
    List<String> findClassesByTeacherId(Long teacherId);

    @Select({
            "select",
            " COURSE_NAME ",
            "from T_COURSE",
            "where ID in (select COURSE_ID from T_CLASS_COURSE_TEACHER where TEACHER_ID=#{arg0})"
    })
    @Results({
            @Result(column="COURSE_NAME", property="courseName", jdbcType=JdbcType.VARCHAR)
    })
    List<String> findCoursesByTeacherId(Long teacherId);

    @Select({
            "select",
            "tcct.CLASS_ID as CLASS_ID,tcct.COURSE_ID as COURSE_ID,tcct.TEACHER_ID as TEACHER_ID,tc.COURSE_NAME as COURSE_NAME,tc.COURSE_STATE as STATE,tt.TEACHER_NAME as TEACHER_NAME",
            "from T_CLASS_COURSE_TEACHER tcct,T_COURSE tc,T_TEACHER tt",
            "where tcct.CLASS_ID=#{arg0} and tcct.TEACHER_ID=tt.ID and tcct.COURSE_ID=tc.ID"
    })
    @Results({
            @Result(column = "CLASS_ID",property = "classId",jdbcType = JdbcType.BIGINT),
            @Result(column = "COURSE_ID",property = "courseId",jdbcType = JdbcType.BIGINT),
            @Result(column = "COURSE_NAME",property = "courseName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "TEACHER_NAME",property = "teacherName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "STATE",property = "state",jdbcType = JdbcType.TINYINT),
            @Result(column = "TEACHER_ID",property = "teacherId",jdbcType = JdbcType.BIGINT)
    })
    List<ClassCourseBo> getClassCourseTeacherByClassId(Long classId);

    @Select({
            "SELECT tcct.*,tc.COURSE_AVATAR,tc.COURSE_NAME,tcl.CLASS_NAME,tt.TEACHER_NAME FROM T_CLASS_COURSE_TEACHER tcct\n" +
                    "LEFT JOIN T_COURSE tc ON tcct.COURSE_ID=tc.ID\n" +
                    "LEFT JOIN T_CLASS tcl ON tcct.CLASS_ID=tcl.ID\n" +
                    "LEFT JOIN T_TEACHER tt ON tcct.TEACHER_ID=tt.ID\n" +
                    "WHERE tcct.CLASS_ID in (select CLASS_ID from T_CLASS_STUDENT where STUDENT_ID = #{arg0} ) "
    })
    @Results({
            @Result(column="ID", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="CLASS_ID", property="classId", jdbcType=JdbcType.BIGINT),
            @Result(column="COURSE_ID", property="courseId", jdbcType=JdbcType.BIGINT),
            @Result(column="TEACHER_ID", property="teacherId", jdbcType=JdbcType.BIGINT),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
            @Result(column = "COURSE_NAME",property = "courseName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "TEACHER_NAME",property = "teacherName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "CLASS_NAME",property = "className",jdbcType = JdbcType.VARCHAR),
            @Result(column = "COURSE_AVATAR",property = "courseAvatar",jdbcType = JdbcType.VARCHAR)
    })
    List<ClassCourseOutline> getStudentCourseList(Long studentId);

}

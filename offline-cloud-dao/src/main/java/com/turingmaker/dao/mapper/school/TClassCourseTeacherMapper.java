package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TClassCourseTeacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

/**
@author tzj
2018-07-03 18:40
*/
public interface TClassCourseTeacherMapper {
    @Delete({
        "delete from T_CLASS_COURSE_TEACHER",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_CLASS_COURSE_TEACHER (CLASS_ID, COURSE_ID, ",
        "TEACHER_ID, CREATE_TIME, ",
        "UPDATE_TIME, STATE)",
        "values (#{classId,jdbcType=BIGINT}, #{courseId,jdbcType=BIGINT}, ",
        "#{teacherId,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{state,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TClassCourseTeacher record);

    @InsertProvider(type=TClassCourseTeacherSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TClassCourseTeacher record);

    @Select({
        "select",
        "ID, CLASS_ID, COURSE_ID, TEACHER_ID, CREATE_TIME, UPDATE_TIME, STATE",
        "from T_CLASS_COURSE_TEACHER",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="CLASS_ID", property="classId", jdbcType=JdbcType.BIGINT),
        @Result(column="COURSE_ID", property="courseId", jdbcType=JdbcType.BIGINT),
        @Result(column="TEACHER_ID", property="teacherId", jdbcType=JdbcType.BIGINT),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="STATE", property="state", jdbcType=JdbcType.TINYINT)
    })
    TClassCourseTeacher selectByPrimaryKey(Long id);

    @UpdateProvider(type=TClassCourseTeacherSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TClassCourseTeacher record);

    @Update({
        "update T_CLASS_COURSE_TEACHER",
        "set CLASS_ID = #{classId,jdbcType=BIGINT},",
          "COURSE_ID = #{courseId,jdbcType=BIGINT},",
          "TEACHER_ID = #{teacherId,jdbcType=BIGINT},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
          "STATE = #{state,jdbcType=TINYINT}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TClassCourseTeacher record);

    @SelectProvider(type=com.turingmaker.dao.mapper.school.TClassCourseTeacherSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TClassCourseTeacher param);
}
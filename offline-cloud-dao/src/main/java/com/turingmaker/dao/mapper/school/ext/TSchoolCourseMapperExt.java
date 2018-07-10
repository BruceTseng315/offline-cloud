package com.turingmaker.dao.mapper.school.ext;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.turingmaker.dao.mapper.school.TSchoolCourseMapper;
import com.turingmaker.entity.school.TSchoolCourse;

public interface TSchoolCourseMapperExt extends TSchoolCourseMapper {
//    @Select("select * from T_SCHOOL_COURSE where SCHOOL_ID = #{arg0} and STATE = 1")
//    @Results(id="resultMap",value={
//            @Result(column="ID", property="id", jdbcType= JdbcType.BIGINT, id=true),
//            @Result(column="SCHOOL_ID", property="schoolId", jdbcType=JdbcType.BIGINT),
//            @Result(column="COURSE_ID", property="courseId", jdbcType=JdbcType.BIGINT),
//            @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER)
//    })
//    List<TSchoolCourse> findAllOnBySchoolId(Long schoolId);

    @Select("select * from T_SCHOOL_COURSE where SCHOOL_ID = #{arg0}")
    @Results(id="resultMap",value={
            @Result(column="ID", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="SCHOOL_ID", property="schoolId", jdbcType=JdbcType.BIGINT),
            @Result(column="COURSE_ID", property="courseId", jdbcType=JdbcType.BIGINT),
            @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER)
    })
    List<TSchoolCourse> findAllBySchoolId(Long schoolId);

    @Select("select * from T_SCHOOL_COURSE where SCHOOL_ID = #{arg0} and COURSE_ID = #{arg1} LIMIT 1")
   @ResultMap("resultMap")
    TSchoolCourse findBySchoolIdAndCourseId(Long schoolId ,Long courseId);

    @Select("delete from T_SCHOOL_COURSE where COURSE_ID = #{arg0}")
    @ResultMap("resultMap")
    TSchoolCourse deleteByCourseId(Long courseId);
}

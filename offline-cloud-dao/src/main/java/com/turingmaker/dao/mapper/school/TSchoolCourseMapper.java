package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TSchoolCourse;
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
public interface TSchoolCourseMapper {
    @Delete({
        "delete from T_SCHOOL_COURSE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_SCHOOL_COURSE (SCHOOL_ID, COURSE_ID, ",
        "STATE)",
        "values (#{schoolId,jdbcType=BIGINT}, #{courseId,jdbcType=BIGINT}, ",
        "#{state,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TSchoolCourse record);

    @InsertProvider(type=TSchoolCourseSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TSchoolCourse record);

    @Select({
        "select",
        "ID, SCHOOL_ID, COURSE_ID, STATE",
        "from T_SCHOOL_COURSE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="SCHOOL_ID", property="schoolId", jdbcType=JdbcType.BIGINT),
        @Result(column="COURSE_ID", property="courseId", jdbcType=JdbcType.BIGINT),
        @Result(column="STATE", property="state", jdbcType=JdbcType.TINYINT)
    })
    TSchoolCourse selectByPrimaryKey(Long id);

    @UpdateProvider(type=TSchoolCourseSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TSchoolCourse record);

    @Update({
        "update T_SCHOOL_COURSE",
        "set SCHOOL_ID = #{schoolId,jdbcType=BIGINT},",
          "COURSE_ID = #{courseId,jdbcType=BIGINT},",
          "STATE = #{state,jdbcType=TINYINT}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TSchoolCourse record);

    @SelectProvider(type=com.turingmaker.dao.mapper.school.TSchoolCourseSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TSchoolCourse param);
}
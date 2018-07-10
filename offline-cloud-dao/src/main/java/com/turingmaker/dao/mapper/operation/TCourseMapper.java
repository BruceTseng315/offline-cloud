package com.turingmaker.dao.mapper.operation;

import com.turingmaker.entity.operation.TCourse;
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
2018-07-03 18:38
*/
public interface TCourseMapper {
    @Delete({
        "delete from T_COURSE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_COURSE (COURSE_CODE, COURSE_NAME, ",
        "COURSE_DESCRIPTION, COURSE_AVATAR, ",
        "COURSE_STATE, CREATE_TIME, ",
        "UPDATE_TIME, LESSON_NUM)",
        "values (#{courseCode,jdbcType=VARCHAR}, #{courseName,jdbcType=VARCHAR}, ",
        "#{courseDescription,jdbcType=VARCHAR}, #{courseAvatar,jdbcType=VARCHAR}, ",
        "#{courseState,jdbcType=TINYINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{lessonNum,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TCourse record);

    @InsertProvider(type=TCourseSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TCourse record);

    @Select({
        "select",
        "ID, COURSE_CODE, COURSE_NAME, COURSE_DESCRIPTION, COURSE_AVATAR, COURSE_STATE, ",
        "CREATE_TIME, UPDATE_TIME, LESSON_NUM",
        "from T_COURSE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="COURSE_CODE", property="courseCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="COURSE_NAME", property="courseName", jdbcType=JdbcType.VARCHAR),
        @Result(column="COURSE_DESCRIPTION", property="courseDescription", jdbcType=JdbcType.VARCHAR),
        @Result(column="COURSE_AVATAR", property="courseAvatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="COURSE_STATE", property="courseState", jdbcType=JdbcType.TINYINT),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LESSON_NUM", property="lessonNum", jdbcType=JdbcType.INTEGER)
    })
    TCourse selectByPrimaryKey(Long id);

    @UpdateProvider(type=TCourseSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCourse record);

    @Update({
        "update T_COURSE",
        "set COURSE_CODE = #{courseCode,jdbcType=VARCHAR},",
          "COURSE_NAME = #{courseName,jdbcType=VARCHAR},",
          "COURSE_DESCRIPTION = #{courseDescription,jdbcType=VARCHAR},",
          "COURSE_AVATAR = #{courseAvatar,jdbcType=VARCHAR},",
          "COURSE_STATE = #{courseState,jdbcType=TINYINT},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
          "LESSON_NUM = #{lessonNum,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TCourse record);

    @SelectProvider(type=com.turingmaker.dao.mapper.operation.TCourseSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TCourse param);
}
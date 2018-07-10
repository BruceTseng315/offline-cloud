package com.turingmaker.dao.mapper.operation;

import com.turingmaker.entity.operation.TLesson;
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
public interface TLessonMapper {
    @Delete({
        "delete from T_LESSON",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_LESSON (LESSON_NAME, LESSON_DESCRIPTION, ",
        "LESSON_AVATAR, LESSON_SECTION, ",
        "LESSON_STATE, CREATE_TIME, ",
        "UPDATE_TIME, COURSE_ID)",
        "values (#{lessonName,jdbcType=VARCHAR}, #{lessonDescription,jdbcType=VARCHAR}, ",
        "#{lessonAvatar,jdbcType=VARCHAR}, #{lessonSection,jdbcType=INTEGER}, ",
        "#{lessonState,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{courseId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TLesson record);

    @InsertProvider(type=TLessonSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TLesson record);

    @Select({
        "select",
        "ID, LESSON_NAME, LESSON_DESCRIPTION, LESSON_AVATAR, LESSON_SECTION, LESSON_STATE, ",
        "CREATE_TIME, UPDATE_TIME, COURSE_ID",
        "from T_LESSON",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
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
    TLesson selectByPrimaryKey(Long id);

    @UpdateProvider(type=TLessonSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TLesson record);

    @Update({
        "update T_LESSON",
        "set LESSON_NAME = #{lessonName,jdbcType=VARCHAR},",
          "LESSON_DESCRIPTION = #{lessonDescription,jdbcType=VARCHAR},",
          "LESSON_AVATAR = #{lessonAvatar,jdbcType=VARCHAR},",
          "LESSON_SECTION = #{lessonSection,jdbcType=INTEGER},",
          "LESSON_STATE = #{lessonState,jdbcType=INTEGER},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
          "COURSE_ID = #{courseId,jdbcType=BIGINT}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TLesson record);

    @SelectProvider(type=com.turingmaker.dao.mapper.operation.TLessonSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TLesson param);
}
package com.turingmaker.dao.mapper.teachter;

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

import com.turingmaker.entity.teachter.TClassLesson;

/**
@author tzj
2018-07-03 18:34
*/
public interface TClassLessonMapper {
    @Delete({
        "delete from T_CLASS_LESSON",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_CLASS_LESSON (CLASS_ID, LESSON_ID, ",
        "COURSE_ID, LESSON_SECTION, ",
        "CREATE_TIME, UPDATE_TIME)",
        "values (#{classId,jdbcType=BIGINT}, #{lessonId,jdbcType=BIGINT}, ",
        "#{courseId,jdbcType=BIGINT}, #{lessonSection,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TClassLesson record);

    @InsertProvider(type=TClassLessonSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TClassLesson record);

    @Select({
        "select",
        "ID, CLASS_ID, LESSON_ID, COURSE_ID, LESSON_SECTION, CREATE_TIME, UPDATE_TIME",
        "from T_CLASS_LESSON",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="CLASS_ID", property="classId", jdbcType=JdbcType.BIGINT),
        @Result(column="LESSON_ID", property="lessonId", jdbcType=JdbcType.BIGINT),
        @Result(column="COURSE_ID", property="courseId", jdbcType=JdbcType.BIGINT),
        @Result(column="LESSON_SECTION", property="lessonSection", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TClassLesson selectByPrimaryKey(Long id);

    @UpdateProvider(type=TClassLessonSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TClassLesson record);

    @Update({
        "update T_CLASS_LESSON",
        "set CLASS_ID = #{classId,jdbcType=BIGINT},",
          "LESSON_ID = #{lessonId,jdbcType=BIGINT},",
          "COURSE_ID = #{courseId,jdbcType=BIGINT},",
          "LESSON_SECTION = #{lessonSection,jdbcType=INTEGER},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TClassLesson record);

    @SelectProvider(type=com.turingmaker.dao.mapper.teachter.TClassLessonSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TClassLesson param);
}
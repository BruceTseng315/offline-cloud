package com.turingmaker.dao.mapper.school;

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

import com.turingmaker.entity.school.TResourceLesson;

/**
@author tzj
2018-06-19 19:18
*/
public interface TResourceLessonMapper {
    @Delete({
        "delete from T_RESOURCE_LESSON",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_RESOURCE_LESSON (RESOURCE_ID, LESSON_ID)",
        "values (#{resourceId,jdbcType=BIGINT}, #{lessonId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TResourceLesson record);

    @InsertProvider(type=TResourceLessonSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TResourceLesson record);

    @Select({
        "select",
        "ID, RESOURCE_ID, LESSON_ID",
        "from T_RESOURCE_LESSON",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="RESOURCE_ID", property="resourceId", jdbcType=JdbcType.BIGINT),
        @Result(column="LESSON_ID", property="lessonId", jdbcType=JdbcType.BIGINT)
    })
    TResourceLesson selectByPrimaryKey(Long id);

    @UpdateProvider(type=TResourceLessonSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TResourceLesson record);

    @Update({
        "update T_RESOURCE_LESSON",
        "set RESOURCE_ID = #{resourceId,jdbcType=BIGINT},",
          "LESSON_ID = #{lessonId,jdbcType=BIGINT}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TResourceLesson record);

    @SelectProvider(type=com.turingmaker.dao.mapper.school.TResourceLessonSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TResourceLesson param);
}
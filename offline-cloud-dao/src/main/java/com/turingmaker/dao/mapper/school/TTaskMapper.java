package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TTask;
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
public interface TTaskMapper {
    @Delete({
        "delete from T_TASK",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_TASK (TASK_NAME, TASK_DESCURL, ",
        "LESSON_ID, PROGRAM_ID, ",
        "CREATE_TIME, UPDATE_TIME)",
        "values (#{taskName,jdbcType=VARCHAR}, #{taskDescurl,jdbcType=VARCHAR}, ",
        "#{lessonId,jdbcType=BIGINT}, #{programId,jdbcType=BIGINT}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TTask record);

    @InsertProvider(type=TTaskSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TTask record);

    @Select({
        "select",
        "ID, TASK_NAME, TASK_DESCURL, LESSON_ID, PROGRAM_ID, CREATE_TIME, UPDATE_TIME",
        "from T_TASK",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="TASK_NAME", property="taskName", jdbcType=JdbcType.VARCHAR),
        @Result(column="TASK_DESCURL", property="taskDescurl", jdbcType=JdbcType.VARCHAR),
        @Result(column="LESSON_ID", property="lessonId", jdbcType=JdbcType.BIGINT),
        @Result(column="PROGRAM_ID", property="programId", jdbcType=JdbcType.BIGINT),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TTask selectByPrimaryKey(Long id);

    @UpdateProvider(type=TTaskSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TTask record);

    @Update({
        "update T_TASK",
        "set TASK_NAME = #{taskName,jdbcType=VARCHAR},",
          "TASK_DESCURL = #{taskDescurl,jdbcType=VARCHAR},",
          "LESSON_ID = #{lessonId,jdbcType=BIGINT},",
          "PROGRAM_ID = #{programId,jdbcType=BIGINT},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TTask record);

    @SelectProvider(type=com.turingmaker.dao.mapper.school.TTaskSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TTask param);
}
package com.turingmaker.dao.mapper.teachter;

import com.turingmaker.entity.teachter.THomework;
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
2018-07-04 10:33
*/
public interface THomeworkMapper {
    @Delete({
        "delete from T_HOMEWORK",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_HOMEWORK (STUDENT_ID, PROGRAM_ID, ",
        "IS_DELETE, TASK_ID, ",
        "HOMEWORK_DESC, HOMEWORK_NAME, ",
        "HOMEWORK_AVATAR, MARK, ",
        "CREATE_TIME, UPDATE_TIME)",
        "values (#{studentId,jdbcType=BIGINT}, #{programId,jdbcType=BIGINT}, ",
        "#{isDelete,jdbcType=INTEGER}, #{taskId,jdbcType=BIGINT}, ",
        "#{homeworkDesc,jdbcType=VARCHAR}, #{homeworkName,jdbcType=VARCHAR}, ",
        "#{homeworkAvatar,jdbcType=VARCHAR}, #{mark,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(THomework record);

    @InsertProvider(type=THomeworkSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(THomework record);

    @Select({
        "select",
        "ID, STUDENT_ID, PROGRAM_ID, IS_DELETE, TASK_ID, HOMEWORK_DESC, HOMEWORK_NAME, ",
        "HOMEWORK_AVATAR, MARK, CREATE_TIME, UPDATE_TIME",
        "from T_HOMEWORK",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="STUDENT_ID", property="studentId", jdbcType=JdbcType.BIGINT),
        @Result(column="PROGRAM_ID", property="programId", jdbcType=JdbcType.BIGINT),
        @Result(column="IS_DELETE", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="TASK_ID", property="taskId", jdbcType=JdbcType.BIGINT),
        @Result(column="HOMEWORK_DESC", property="homeworkDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="HOMEWORK_NAME", property="homeworkName", jdbcType=JdbcType.VARCHAR),
        @Result(column="HOMEWORK_AVATAR", property="homeworkAvatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="MARK", property="mark", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    THomework selectByPrimaryKey(Long id);

    @UpdateProvider(type=THomeworkSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(THomework record);

    @Update({
        "update T_HOMEWORK",
        "set STUDENT_ID = #{studentId,jdbcType=BIGINT},",
          "PROGRAM_ID = #{programId,jdbcType=BIGINT},",
          "IS_DELETE = #{isDelete,jdbcType=INTEGER},",
          "TASK_ID = #{taskId,jdbcType=BIGINT},",
          "HOMEWORK_DESC = #{homeworkDesc,jdbcType=VARCHAR},",
          "HOMEWORK_NAME = #{homeworkName,jdbcType=VARCHAR},",
          "HOMEWORK_AVATAR = #{homeworkAvatar,jdbcType=VARCHAR},",
          "MARK = #{mark,jdbcType=INTEGER},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(THomework record);

    @SelectProvider(type=com.turingmaker.dao.mapper.teachter.THomeworkSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") THomework param);
}
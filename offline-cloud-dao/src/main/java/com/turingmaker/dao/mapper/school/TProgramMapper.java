package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TProgram;
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
public interface TProgramMapper {
    @Delete({
        "delete from T_PROGRAM",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_PROGRAM (PROGRAM_CONTENT_URL, PROGRAM_NAME, ",
        "PROGRAM_CONTENT, CREATE_TIME, ",
        "UPDATE_TIME)",
        "values (#{programContentUrl,jdbcType=VARCHAR}, #{programName,jdbcType=VARCHAR}, ",
        "#{programContent,jdbcType=CHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TProgram record);

    @InsertProvider(type=TProgramSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TProgram record);

    @Select({
        "select",
        "ID, PROGRAM_CONTENT_URL, PROGRAM_NAME, PROGRAM_CONTENT, CREATE_TIME, UPDATE_TIME",
        "from T_PROGRAM",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="PROGRAM_CONTENT_URL", property="programContentUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="PROGRAM_NAME", property="programName", jdbcType=JdbcType.VARCHAR),
        @Result(column="PROGRAM_CONTENT", property="programContent", jdbcType=JdbcType.CHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TProgram selectByPrimaryKey(Long id);

    @UpdateProvider(type=TProgramSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TProgram record);

    @Update({
        "update T_PROGRAM",
        "set PROGRAM_CONTENT_URL = #{programContentUrl,jdbcType=VARCHAR},",
          "PROGRAM_NAME = #{programName,jdbcType=VARCHAR},",
          "PROGRAM_CONTENT = #{programContent,jdbcType=CHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TProgram record);

    @SelectProvider(type=com.turingmaker.dao.mapper.school.TProgramSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TProgram param);
}
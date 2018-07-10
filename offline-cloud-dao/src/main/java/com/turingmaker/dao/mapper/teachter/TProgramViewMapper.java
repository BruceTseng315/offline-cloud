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

import com.turingmaker.entity.teachter.TProgramView;

/**
@author tzj
2018-07-03 18:34
*/
public interface TProgramViewMapper {
    @Delete({
        "delete from T_PROGRAM_VIEW",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_PROGRAM_VIEW (PROGRAM_ID, VIEW_COUNT, ",
        "LIKE_USERS, CREATE_TIME, ",
        "UPDATE_TIME)",
        "values (#{programId,jdbcType=BIGINT}, #{viewCount,jdbcType=INTEGER}, ",
        "#{likeUsers,jdbcType=CHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TProgramView record);

    @InsertProvider(type=TProgramViewSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TProgramView record);

    @Select({
        "select",
        "ID, PROGRAM_ID, VIEW_COUNT, LIKE_USERS, CREATE_TIME, UPDATE_TIME",
        "from T_PROGRAM_VIEW",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="PROGRAM_ID", property="programId", jdbcType=JdbcType.BIGINT),
        @Result(column="VIEW_COUNT", property="viewCount", jdbcType=JdbcType.INTEGER),
        @Result(column="LIKE_USERS", property="likeUsers", jdbcType=JdbcType.CHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TProgramView selectByPrimaryKey(Long id);

    @UpdateProvider(type=TProgramViewSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TProgramView record);

    @Update({
        "update T_PROGRAM_VIEW",
        "set PROGRAM_ID = #{programId,jdbcType=BIGINT},",
          "VIEW_COUNT = #{viewCount,jdbcType=INTEGER},",
          "LIKE_USERS = #{likeUsers,jdbcType=CHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TProgramView record);

    @SelectProvider(type=com.turingmaker.dao.mapper.teachter.TProgramViewSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TProgramView param);
}
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

import com.turingmaker.entity.teachter.TProgramLike;

/**
@author tzj
2018-07-03 18:34
*/
public interface TProgramLikeMapper {
    @Delete({
        "delete from T_PROGRAM_LIKE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_PROGRAM_LIKE (PROGRAM_ID, LIKE_COUNT, ",
        "LIKE_USERS, UPDATE_TIME, ",
        "CREATE_TIME)",
        "values (#{programId,jdbcType=BIGINT}, #{likeCount,jdbcType=INTEGER}, ",
        "#{likeUsers,jdbcType=CHAR}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TProgramLike record);

    @InsertProvider(type=TProgramLikeSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TProgramLike record);

    @Select({
        "select",
        "ID, PROGRAM_ID, LIKE_COUNT, LIKE_USERS, UPDATE_TIME, CREATE_TIME",
        "from T_PROGRAM_LIKE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="PROGRAM_ID", property="programId", jdbcType=JdbcType.BIGINT),
        @Result(column="LIKE_COUNT", property="likeCount", jdbcType=JdbcType.INTEGER),
        @Result(column="LIKE_USERS", property="likeUsers", jdbcType=JdbcType.CHAR),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TProgramLike selectByPrimaryKey(Long id);

    @UpdateProvider(type=TProgramLikeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TProgramLike record);

    @Update({
        "update T_PROGRAM_LIKE",
        "set PROGRAM_ID = #{programId,jdbcType=BIGINT},",
          "LIKE_COUNT = #{likeCount,jdbcType=INTEGER},",
          "LIKE_USERS = #{likeUsers,jdbcType=CHAR},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TProgramLike record);

    @SelectProvider(type=com.turingmaker.dao.mapper.teachter.TProgramLikeSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TProgramLike param);
}
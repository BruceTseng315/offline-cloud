package com.turingmaker.dao.mapper.operation;

import com.turingmaker.entity.operation.YUser;
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
public interface YUserMapper {
    @Delete({
        "delete from Y_USER",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into Y_USER (ACCOUNT, PASSWORD, ",
        "CREATE_TIME, UPDATE_TIME)",
        "values (#{account,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(YUser record);

    @InsertProvider(type=YUserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(YUser record);

    @Select({
        "select",
        "ID, ACCOUNT, PASSWORD, CREATE_TIME, UPDATE_TIME",
        "from Y_USER",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="ACCOUNT", property="account", jdbcType=JdbcType.VARCHAR),
        @Result(column="PASSWORD", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    YUser selectByPrimaryKey(Long id);

    @UpdateProvider(type=YUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(YUser record);

    @Update({
        "update Y_USER",
        "set ACCOUNT = #{account,jdbcType=VARCHAR},",
          "PASSWORD = #{password,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(YUser record);

    @SelectProvider(type=com.turingmaker.dao.mapper.operation.YUserSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") YUser param);
}
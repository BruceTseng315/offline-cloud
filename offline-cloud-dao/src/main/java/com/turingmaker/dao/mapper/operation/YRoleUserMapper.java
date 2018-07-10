package com.turingmaker.dao.mapper.operation;

import com.turingmaker.entity.operation.YRoleUser;
import com.turingmaker.entity.operation.YRoleUserKey;
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
public interface YRoleUserMapper {
    @Delete({
        "delete from Y_ROLE_USER",
        "where ID = #{id,jdbcType=BIGINT}",
          "and USER_ID = #{userId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(YRoleUserKey key);

    @Insert({
        "insert into Y_ROLE_USER (USER_ID, ROLE_ID)",
        "values (#{userId,jdbcType=BIGINT}, #{roleId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(YRoleUser record);

    @InsertProvider(type=YRoleUserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(YRoleUser record);

    @Select({
        "select",
        "ID, USER_ID, ROLE_ID",
        "from Y_ROLE_USER",
        "where ID = #{id,jdbcType=BIGINT}",
          "and USER_ID = #{userId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.BIGINT)
    })
    YRoleUser selectByPrimaryKey(YRoleUserKey key);

    @UpdateProvider(type=YRoleUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(YRoleUser record);

    @Update({
        "update Y_ROLE_USER",
        "set ROLE_ID = #{roleId,jdbcType=BIGINT}",
        "where ID = #{id,jdbcType=BIGINT}",
          "and USER_ID = #{userId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(YRoleUser record);

    @SelectProvider(type=com.turingmaker.dao.mapper.operation.YRoleUserSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") YRoleUser param);
}
package com.turingmaker.dao.mapper.operation;

import com.turingmaker.entity.operation.YRole;
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
public interface YRoleMapper {
    @Delete({
        "delete from Y_ROLE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into Y_ROLE (ROLE_NAME, ROLE_CODE, ",
        "CREATE_TIME, UPDATE_TIME)",
        "values (#{roleName,jdbcType=VARCHAR}, #{roleCode,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(YRole record);

    @InsertProvider(type=YRoleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(YRole record);

    @Select({
        "select",
        "ID, ROLE_NAME, ROLE_CODE, CREATE_TIME, UPDATE_TIME",
        "from Y_ROLE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="ROLE_NAME", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ROLE_CODE", property="roleCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    YRole selectByPrimaryKey(Long id);

    @UpdateProvider(type=YRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(YRole record);

    @Update({
        "update Y_ROLE",
        "set ROLE_NAME = #{roleName,jdbcType=VARCHAR},",
          "ROLE_CODE = #{roleCode,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(YRole record);

    @SelectProvider(type=com.turingmaker.dao.mapper.operation.YRoleSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") YRole param);
}
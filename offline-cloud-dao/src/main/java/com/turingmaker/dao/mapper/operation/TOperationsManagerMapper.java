package com.turingmaker.dao.mapper.operation;

import com.turingmaker.entity.operation.TOperationsManager;
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
public interface TOperationsManagerMapper {
    @Delete({
        "delete from T_OPERATIONS_MANAGER",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_OPERATIONS_MANAGER (NAME, PHONE, ",
        "SCHOOL_ID, ACCOUNT_ID, ",
        "CREATE_TIME, UPDATE_TIME)",
        "values (#{name,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{schoolId,jdbcType=BIGINT}, #{accountId,jdbcType=BIGINT}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TOperationsManager record);

    @InsertProvider(type=TOperationsManagerSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TOperationsManager record);

    @Select({
        "select",
        "ID, NAME, PHONE, SCHOOL_ID, ACCOUNT_ID, CREATE_TIME, UPDATE_TIME",
        "from T_OPERATIONS_MANAGER",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHONE", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="SCHOOL_ID", property="schoolId", jdbcType=JdbcType.BIGINT),
        @Result(column="ACCOUNT_ID", property="accountId", jdbcType=JdbcType.BIGINT),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TOperationsManager selectByPrimaryKey(Long id);

    @UpdateProvider(type=TOperationsManagerSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TOperationsManager record);

    @Update({
        "update T_OPERATIONS_MANAGER",
        "set NAME = #{name,jdbcType=VARCHAR},",
          "PHONE = #{phone,jdbcType=VARCHAR},",
          "SCHOOL_ID = #{schoolId,jdbcType=BIGINT},",
          "ACCOUNT_ID = #{accountId,jdbcType=BIGINT},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TOperationsManager record);

    @SelectProvider(type=com.turingmaker.dao.mapper.operation.TOperationsManagerSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TOperationsManager param);
}
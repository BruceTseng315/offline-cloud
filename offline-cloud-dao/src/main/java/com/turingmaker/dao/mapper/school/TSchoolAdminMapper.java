package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TSchoolAdmin;
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
public interface TSchoolAdminMapper {
    @Delete({
        "delete from T_SCHOOL_ADMIN",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_SCHOOL_ADMIN (NAME, PHONE, ",
        "SCHOOL_ID, ACCOUNT_ID, ",
        "CREATE_TIME, UPDATE_TIME)",
        "values (#{name,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{schoolId,jdbcType=BIGINT}, #{accountId,jdbcType=BIGINT}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TSchoolAdmin record);

    @InsertProvider(type=TSchoolAdminSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TSchoolAdmin record);

    @Select({
        "select",
        "ID, NAME, PHONE, SCHOOL_ID, ACCOUNT_ID, CREATE_TIME, UPDATE_TIME",
        "from T_SCHOOL_ADMIN",
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
    TSchoolAdmin selectByPrimaryKey(Long id);

    @UpdateProvider(type=TSchoolAdminSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TSchoolAdmin record);

    @Update({
        "update T_SCHOOL_ADMIN",
        "set NAME = #{name,jdbcType=VARCHAR},",
          "PHONE = #{phone,jdbcType=VARCHAR},",
          "SCHOOL_ID = #{schoolId,jdbcType=BIGINT},",
          "ACCOUNT_ID = #{accountId,jdbcType=BIGINT},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TSchoolAdmin record);

    @SelectProvider(type=com.turingmaker.dao.mapper.school.TSchoolAdminSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TSchoolAdmin param);
}
package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TStudent;
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
public interface TStudentMapper {
    @Delete({
        "delete from T_STUDENT",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_STUDENT (STUDENT_CODE, STUDENT_NAME, ",
        "STUDENT_NUMBER, IDENTIFIER, ",
        "GUARDER_NAME, GUARDER_PHONE, ",
        "CREATE_TIME, UPDATE_TIME, ",
        "ACCOUNT_ID, SCHOOL_ID)",
        "values (#{studentCode,jdbcType=VARCHAR}, #{studentName,jdbcType=VARCHAR}, ",
        "#{studentNumber,jdbcType=VARCHAR}, #{identifier,jdbcType=VARCHAR}, ",
        "#{guarderName,jdbcType=VARCHAR}, #{guarderPhone,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{accountId,jdbcType=BIGINT}, #{schoolId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TStudent record);

    @InsertProvider(type=TStudentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TStudent record);

    @Select({
        "select",
        "ID, STUDENT_CODE, STUDENT_NAME, STUDENT_NUMBER, IDENTIFIER, GUARDER_NAME, GUARDER_PHONE, ",
        "CREATE_TIME, UPDATE_TIME, ACCOUNT_ID, SCHOOL_ID",
        "from T_STUDENT",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="STUDENT_CODE", property="studentCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="STUDENT_NAME", property="studentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="STUDENT_NUMBER", property="studentNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="IDENTIFIER", property="identifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="GUARDER_NAME", property="guarderName", jdbcType=JdbcType.VARCHAR),
        @Result(column="GUARDER_PHONE", property="guarderPhone", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ACCOUNT_ID", property="accountId", jdbcType=JdbcType.BIGINT),
        @Result(column="SCHOOL_ID", property="schoolId", jdbcType=JdbcType.BIGINT)
    })
    TStudent selectByPrimaryKey(Long id);

    @UpdateProvider(type=TStudentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TStudent record);

    @Update({
        "update T_STUDENT",
        "set STUDENT_CODE = #{studentCode,jdbcType=VARCHAR},",
          "STUDENT_NAME = #{studentName,jdbcType=VARCHAR},",
          "STUDENT_NUMBER = #{studentNumber,jdbcType=VARCHAR},",
          "IDENTIFIER = #{identifier,jdbcType=VARCHAR},",
          "GUARDER_NAME = #{guarderName,jdbcType=VARCHAR},",
          "GUARDER_PHONE = #{guarderPhone,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
          "ACCOUNT_ID = #{accountId,jdbcType=BIGINT},",
          "SCHOOL_ID = #{schoolId,jdbcType=BIGINT}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TStudent record);

    @SelectProvider(type=com.turingmaker.dao.mapper.school.TStudentSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TStudent param);
}
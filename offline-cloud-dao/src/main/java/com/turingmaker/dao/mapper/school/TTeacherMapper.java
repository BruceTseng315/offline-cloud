package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TTeacher;
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
public interface TTeacherMapper {
    @Delete({
        "delete from T_TEACHER",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_TEACHER (TEACHER_CODE, TEACHER_NAME, ",
        "CREATE_TIME, UPDATE_TIME, ",
        "ACCOUNT_ID, PHONE, ",
        "SCHOOL_ID)",
        "values (#{teacherCode,jdbcType=VARCHAR}, #{teacherName,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{accountId,jdbcType=BIGINT}, #{phone,jdbcType=VARCHAR}, ",
        "#{schoolId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TTeacher record);

    @InsertProvider(type=TTeacherSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TTeacher record);

    @Select({
        "select",
        "ID, TEACHER_CODE, TEACHER_NAME, CREATE_TIME, UPDATE_TIME, ACCOUNT_ID, PHONE, ",
        "SCHOOL_ID",
        "from T_TEACHER",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="TEACHER_CODE", property="teacherCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="TEACHER_NAME", property="teacherName", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ACCOUNT_ID", property="accountId", jdbcType=JdbcType.BIGINT),
        @Result(column="PHONE", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="SCHOOL_ID", property="schoolId", jdbcType=JdbcType.BIGINT)
    })
    TTeacher selectByPrimaryKey(Long id);

    @UpdateProvider(type=TTeacherSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TTeacher record);

    @Update({
        "update T_TEACHER",
        "set TEACHER_CODE = #{teacherCode,jdbcType=VARCHAR},",
          "TEACHER_NAME = #{teacherName,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
          "ACCOUNT_ID = #{accountId,jdbcType=BIGINT},",
          "PHONE = #{phone,jdbcType=VARCHAR},",
          "SCHOOL_ID = #{schoolId,jdbcType=BIGINT}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TTeacher record);

    @SelectProvider(type=com.turingmaker.dao.mapper.school.TTeacherSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TTeacher param);
}
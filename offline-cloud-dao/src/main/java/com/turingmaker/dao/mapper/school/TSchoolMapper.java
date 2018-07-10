package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TSchool;
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
public interface TSchoolMapper {
    @Delete({
        "delete from T_SCHOOL",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_SCHOOL (SCHOOL_CODE, SCHOOL_TYPE, ",
        "SCHOOL_NAME, AREA_CODE, ",
        "ADDRESS, CONTACT_PHONE, ",
        "CONTACT_NAME, CHANNEL_NAME, ",
        "CHANNEL_PHONE, CREATE_TIME, ",
        "UPDATE_TIME)",
        "values (#{schoolCode,jdbcType=VARCHAR}, #{schoolType,jdbcType=INTEGER}, ",
        "#{schoolName,jdbcType=VARCHAR}, #{areaCode,jdbcType=BIGINT}, ",
        "#{address,jdbcType=VARCHAR}, #{contactPhone,jdbcType=VARCHAR}, ",
        "#{contactName,jdbcType=VARCHAR}, #{channelName,jdbcType=VARCHAR}, ",
        "#{channelPhone,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TSchool record);

    @InsertProvider(type=TSchoolSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TSchool record);

    @Select({
        "select",
        "ID, SCHOOL_CODE, SCHOOL_TYPE, SCHOOL_NAME, AREA_CODE, ADDRESS, CONTACT_PHONE, ",
        "CONTACT_NAME, CHANNEL_NAME, CHANNEL_PHONE, CREATE_TIME, UPDATE_TIME",
        "from T_SCHOOL",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="SCHOOL_CODE", property="schoolCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="SCHOOL_TYPE", property="schoolType", jdbcType=JdbcType.INTEGER),
        @Result(column="SCHOOL_NAME", property="schoolName", jdbcType=JdbcType.VARCHAR),
        @Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.BIGINT),
        @Result(column="ADDRESS", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONTACT_PHONE", property="contactPhone", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONTACT_NAME", property="contactName", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHANNEL_NAME", property="channelName", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHANNEL_PHONE", property="channelPhone", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TSchool selectByPrimaryKey(Long id);

    @UpdateProvider(type=TSchoolSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TSchool record);

    @Update({
        "update T_SCHOOL",
        "set SCHOOL_CODE = #{schoolCode,jdbcType=VARCHAR},",
          "SCHOOL_TYPE = #{schoolType,jdbcType=INTEGER},",
          "SCHOOL_NAME = #{schoolName,jdbcType=VARCHAR},",
          "AREA_CODE = #{areaCode,jdbcType=BIGINT},",
          "ADDRESS = #{address,jdbcType=VARCHAR},",
          "CONTACT_PHONE = #{contactPhone,jdbcType=VARCHAR},",
          "CONTACT_NAME = #{contactName,jdbcType=VARCHAR},",
          "CHANNEL_NAME = #{channelName,jdbcType=VARCHAR},",
          "CHANNEL_PHONE = #{channelPhone,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TSchool record);

    @SelectProvider(type=com.turingmaker.dao.mapper.school.TSchoolSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TSchool param);
}
package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TResource;
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
public interface TResourceMapper {
    @Delete({
        "delete from T_RESOURCE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_RESOURCE (RESOURCE_NAME, RESOURCE_URL, ",
        "RESOURCE_TYPE, RESOURCE_FILE_TYPE, ",
        "CREATE_TIME, UPDATE_TIME)",
        "values (#{resourceName,jdbcType=VARCHAR}, #{resourceUrl,jdbcType=VARCHAR}, ",
        "#{resourceType,jdbcType=INTEGER}, #{resourceFileType,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TResource record);

    @InsertProvider(type=TResourceSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TResource record);

    @Select({
        "select",
        "ID, RESOURCE_NAME, RESOURCE_URL, RESOURCE_TYPE, RESOURCE_FILE_TYPE, CREATE_TIME, ",
        "UPDATE_TIME",
        "from T_RESOURCE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="RESOURCE_NAME", property="resourceName", jdbcType=JdbcType.VARCHAR),
        @Result(column="RESOURCE_URL", property="resourceUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="RESOURCE_TYPE", property="resourceType", jdbcType=JdbcType.INTEGER),
        @Result(column="RESOURCE_FILE_TYPE", property="resourceFileType", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TResource selectByPrimaryKey(Long id);

    @UpdateProvider(type=TResourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TResource record);

    @Update({
        "update T_RESOURCE",
        "set RESOURCE_NAME = #{resourceName,jdbcType=VARCHAR},",
          "RESOURCE_URL = #{resourceUrl,jdbcType=VARCHAR},",
          "RESOURCE_TYPE = #{resourceType,jdbcType=INTEGER},",
          "RESOURCE_FILE_TYPE = #{resourceFileType,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TResource record);

    @SelectProvider(type=com.turingmaker.dao.mapper.school.TResourceSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TResource param);
}
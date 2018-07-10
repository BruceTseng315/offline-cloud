package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TClass;
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
public interface TClassMapper {
    @Delete({
        "delete from T_CLASS",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_CLASS (CLASS_CODE, CLASS_NAME, ",
        "CLASS_TYPE, CREATE_TIME, ",
        "UPDATE_TIME, SCHOOL_ID, ",
        "GRADE)",
        "values (#{classCode,jdbcType=VARCHAR}, #{className,jdbcType=VARCHAR}, ",
        "#{classType,jdbcType=TINYINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{schoolId,jdbcType=BIGINT}, ",
        "#{grade,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TClass record);

    @InsertProvider(type=TClassSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TClass record);

    @Select({
        "select",
        "ID, CLASS_CODE, CLASS_NAME, CLASS_TYPE, CREATE_TIME, UPDATE_TIME, SCHOOL_ID, ",
        "GRADE",
        "from T_CLASS",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="CLASS_CODE", property="classCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLASS_NAME", property="className", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLASS_TYPE", property="classType", jdbcType=JdbcType.TINYINT),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="SCHOOL_ID", property="schoolId", jdbcType=JdbcType.BIGINT),
        @Result(column="GRADE", property="grade", jdbcType=JdbcType.INTEGER)
    })
    TClass selectByPrimaryKey(Long id);

    @UpdateProvider(type=TClassSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TClass record);

    @Update({
        "update T_CLASS",
        "set CLASS_CODE = #{classCode,jdbcType=VARCHAR},",
          "CLASS_NAME = #{className,jdbcType=VARCHAR},",
          "CLASS_TYPE = #{classType,jdbcType=TINYINT},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
          "SCHOOL_ID = #{schoolId,jdbcType=BIGINT},",
          "GRADE = #{grade,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TClass record);

    @SelectProvider(type=com.turingmaker.dao.mapper.school.TClassSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TClass param);
}
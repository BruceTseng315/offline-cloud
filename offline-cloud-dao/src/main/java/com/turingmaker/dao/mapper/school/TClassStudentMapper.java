package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TClassStudent;
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
public interface TClassStudentMapper {
    @Delete({
        "delete from T_CLASS_STUDENT",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_CLASS_STUDENT (CLASS_ID, STUDENT_ID, ",
        "CREATE_TIME, UPDATE_TIME)",
        "values (#{classId,jdbcType=BIGINT}, #{studentId,jdbcType=BIGINT}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TClassStudent record);

    @InsertProvider(type=TClassStudentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TClassStudent record);

    @Select({
        "select",
        "ID, CLASS_ID, STUDENT_ID, CREATE_TIME, UPDATE_TIME",
        "from T_CLASS_STUDENT",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="CLASS_ID", property="classId", jdbcType=JdbcType.BIGINT),
        @Result(column="STUDENT_ID", property="studentId", jdbcType=JdbcType.BIGINT),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TClassStudent selectByPrimaryKey(Long id);

    @UpdateProvider(type=TClassStudentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TClassStudent record);

    @Update({
        "update T_CLASS_STUDENT",
        "set CLASS_ID = #{classId,jdbcType=BIGINT},",
          "STUDENT_ID = #{studentId,jdbcType=BIGINT},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TClassStudent record);

    @SelectProvider(type=com.turingmaker.dao.mapper.school.TClassStudentSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TClassStudent param);
}
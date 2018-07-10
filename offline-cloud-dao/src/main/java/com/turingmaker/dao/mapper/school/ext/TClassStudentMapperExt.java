package com.turingmaker.dao.mapper.school.ext;

import com.turingmaker.dao.mapper.school.TClassStudentMapper;
import com.turingmaker.entity.school.TClassCourseTeacher;
import com.turingmaker.entity.school.TClassStudent;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TClassStudentMapperExt extends TClassStudentMapper {
    @Select({
            "select",
            "ID, CLASS_ID, STUDENT_ID, CREATE_TIME, UPDATE_TIME",
            "from T_CLASS_STUDENT",
            "where STUDENT_ID = #{arg0,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="CLASS_ID", property="classId", jdbcType=JdbcType.BIGINT),
            @Result(column="STUDENT_ID", property="studentId", jdbcType=JdbcType.BIGINT),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TClassStudent> selectByStudentId(Long studentId);

    @Delete({
            "delete from T_CLASS_STUDENT",
            "where STUDENT_ID = #{arg0,jdbcType=BIGINT}"
    })
    int deleteByStudentId(Long studentId);

    @Delete({
            "delete from T_CLASS_STUDENT",
            "where STUDENT_ID = #{arg0} and CLASS_ID = #{arg1}"
    })
    int deleteByStudentIdAndClassId(Long studentId,Long classId);

    @Select({
            "select count(STUDENT_ID) from T_CLASS_STUDENT where CLASS_ID = #{arg0}"
    })
    int selectStudentCntByClassId(Long classId);
}

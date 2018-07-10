package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TSchoolCourse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:40
*/
public class TSchoolCourseSqlProvider {

    public String insertSelective(TSchoolCourse record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_SCHOOL_COURSE");
        
        if (record.getSchoolId() != null) {
            sql.VALUES("SCHOOL_ID", "#{schoolId,jdbcType=BIGINT}");
        }
        
        if (record.getCourseId() != null) {
            sql.VALUES("COURSE_ID", "#{courseId,jdbcType=BIGINT}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("STATE", "#{state,jdbcType=TINYINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TSchoolCourse record) {
        SQL sql = new SQL();
        sql.UPDATE("T_SCHOOL_COURSE");
        
        if (record.getSchoolId() != null) {
            sql.SET("SCHOOL_ID = #{schoolId,jdbcType=BIGINT}");
        }
        
        if (record.getCourseId() != null) {
            sql.SET("COURSE_ID = #{courseId,jdbcType=BIGINT}");
        }
        
        if (record.getState() != null) {
            sql.SET("STATE = #{state,jdbcType=TINYINT}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    public String selectForCount(@Param("param") TSchoolCourse param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_SCHOOL_COURSE");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TSchoolCourse param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getSchoolId() != null) {
                 sql.WHERE("`SCHOOL_ID`=#{param.schoolId}");
            }
            if (param.getCourseId() != null) {
                 sql.WHERE("`COURSE_ID`=#{param.courseId}");
            }
            if (param.getState() != null) {
                 sql.WHERE("`STATE`=#{param.state}");
            }
        }
         return  sql;
    }
}
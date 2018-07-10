package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TClassCourseTeacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:40
*/
public class TClassCourseTeacherSqlProvider {

    public String insertSelective(TClassCourseTeacher record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_CLASS_COURSE_TEACHER");
        
        if (record.getClassId() != null) {
            sql.VALUES("CLASS_ID", "#{classId,jdbcType=BIGINT}");
        }
        
        if (record.getCourseId() != null) {
            sql.VALUES("COURSE_ID", "#{courseId,jdbcType=BIGINT}");
        }
        
        if (record.getTeacherId() != null) {
            sql.VALUES("TEACHER_ID", "#{teacherId,jdbcType=BIGINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("STATE", "#{state,jdbcType=TINYINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TClassCourseTeacher record) {
        SQL sql = new SQL();
        sql.UPDATE("T_CLASS_COURSE_TEACHER");
        
        if (record.getClassId() != null) {
            sql.SET("CLASS_ID = #{classId,jdbcType=BIGINT}");
        }
        
        if (record.getCourseId() != null) {
            sql.SET("COURSE_ID = #{courseId,jdbcType=BIGINT}");
        }
        
        if (record.getTeacherId() != null) {
            sql.SET("TEACHER_ID = #{teacherId,jdbcType=BIGINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getState() != null) {
            sql.SET("STATE = #{state,jdbcType=TINYINT}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    public String selectForCount(@Param("param") TClassCourseTeacher param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_CLASS_COURSE_TEACHER");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TClassCourseTeacher param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getClassId() != null) {
                 sql.WHERE("`CLASS_ID`=#{param.classId}");
            }
            if (param.getCourseId() != null) {
                 sql.WHERE("`COURSE_ID`=#{param.courseId}");
            }
            if (param.getTeacherId() != null) {
                 sql.WHERE("`TEACHER_ID`=#{param.teacherId}");
            }
            if (param.getCreateTime() != null) {
                 sql.WHERE("`CREATE_TIME`=#{param.createTime}");
            }
            if (param.getUpdateTime() != null) {
                 sql.WHERE("`UPDATE_TIME`=#{param.updateTime}");
            }
            if (param.getState() != null) {
                 sql.WHERE("`STATE`=#{param.state}");
            }
        }
         return  sql;
    }
}
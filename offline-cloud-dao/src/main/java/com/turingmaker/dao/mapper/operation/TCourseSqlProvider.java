package com.turingmaker.dao.mapper.operation;

import com.turingmaker.entity.operation.TCourse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:38
*/
public class TCourseSqlProvider {

    public String insertSelective(TCourse record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_COURSE");
        
        if (record.getCourseCode() != null) {
            sql.VALUES("COURSE_CODE", "#{courseCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCourseName() != null) {
            sql.VALUES("COURSE_NAME", "#{courseName,jdbcType=VARCHAR}");
        }
        
        if (record.getCourseDescription() != null) {
            sql.VALUES("COURSE_DESCRIPTION", "#{courseDescription,jdbcType=VARCHAR}");
        }
        
        if (record.getCourseAvatar() != null) {
            sql.VALUES("COURSE_AVATAR", "#{courseAvatar,jdbcType=VARCHAR}");
        }
        
        if (record.getCourseState() != null) {
            sql.VALUES("COURSE_STATE", "#{courseState,jdbcType=TINYINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLessonNum() != null) {
            sql.VALUES("LESSON_NUM", "#{lessonNum,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TCourse record) {
        SQL sql = new SQL();
        sql.UPDATE("T_COURSE");
        
        if (record.getCourseCode() != null) {
            sql.SET("COURSE_CODE = #{courseCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCourseName() != null) {
            sql.SET("COURSE_NAME = #{courseName,jdbcType=VARCHAR}");
        }
        
        if (record.getCourseDescription() != null) {
            sql.SET("COURSE_DESCRIPTION = #{courseDescription,jdbcType=VARCHAR}");
        }
        
        if (record.getCourseAvatar() != null) {
            sql.SET("COURSE_AVATAR = #{courseAvatar,jdbcType=VARCHAR}");
        }
        
        if (record.getCourseState() != null) {
            sql.SET("COURSE_STATE = #{courseState,jdbcType=TINYINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLessonNum() != null) {
            sql.SET("LESSON_NUM = #{lessonNum,jdbcType=INTEGER}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    public String selectForCount(@Param("param") TCourse param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_COURSE");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TCourse param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getCourseCode() != null) {
                 sql.WHERE("`COURSE_CODE`=#{param.courseCode}");
            }
            if (param.getCourseName() != null) {
                 sql.WHERE("`COURSE_NAME`=#{param.courseName}");
            }
            if (param.getCourseDescription() != null) {
                 sql.WHERE("`COURSE_DESCRIPTION`=#{param.courseDescription}");
            }
            if (param.getCourseAvatar() != null) {
                 sql.WHERE("`COURSE_AVATAR`=#{param.courseAvatar}");
            }
            if (param.getCourseState() != null) {
                 sql.WHERE("`COURSE_STATE`=#{param.courseState}");
            }
            if (param.getCreateTime() != null) {
                 sql.WHERE("`CREATE_TIME`=#{param.createTime}");
            }
            if (param.getUpdateTime() != null) {
                 sql.WHERE("`UPDATE_TIME`=#{param.updateTime}");
            }
            if (param.getLessonNum() != null) {
                 sql.WHERE("`LESSON_NUM`=#{param.lessonNum}");
            }
        }
         return  sql;
    }
}
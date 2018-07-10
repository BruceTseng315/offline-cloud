package com.turingmaker.dao.mapper.teachter;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.turingmaker.entity.teachter.TClassLesson;

/**
@author tzj
2018-07-03 18:34
*/
public class TClassLessonSqlProvider {

    public String insertSelective(TClassLesson record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_CLASS_LESSON");
        
        if (record.getClassId() != null) {
            sql.VALUES("CLASS_ID", "#{classId,jdbcType=BIGINT}");
        }
        
        if (record.getLessonId() != null) {
            sql.VALUES("LESSON_ID", "#{lessonId,jdbcType=BIGINT}");
        }
        
        if (record.getCourseId() != null) {
            sql.VALUES("COURSE_ID", "#{courseId,jdbcType=BIGINT}");
        }
        
        if (record.getLessonSection() != null) {
            sql.VALUES("LESSON_SECTION", "#{lessonSection,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TClassLesson record) {
        SQL sql = new SQL();
        sql.UPDATE("T_CLASS_LESSON");
        
        if (record.getClassId() != null) {
            sql.SET("CLASS_ID = #{classId,jdbcType=BIGINT}");
        }
        
        if (record.getLessonId() != null) {
            sql.SET("LESSON_ID = #{lessonId,jdbcType=BIGINT}");
        }
        
        if (record.getCourseId() != null) {
            sql.SET("COURSE_ID = #{courseId,jdbcType=BIGINT}");
        }
        
        if (record.getLessonSection() != null) {
            sql.SET("LESSON_SECTION = #{lessonSection,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    public String selectForCount(@Param("param") TClassLesson param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_CLASS_LESSON");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TClassLesson param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getClassId() != null) {
                 sql.WHERE("`CLASS_ID`=#{param.classId}");
            }
            if (param.getLessonId() != null) {
                 sql.WHERE("`LESSON_ID`=#{param.lessonId}");
            }
            if (param.getCourseId() != null) {
                 sql.WHERE("`COURSE_ID`=#{param.courseId}");
            }
            if (param.getLessonSection() != null) {
                 sql.WHERE("`LESSON_SECTION`=#{param.lessonSection}");
            }
            if (param.getCreateTime() != null) {
                 sql.WHERE("`CREATE_TIME`=#{param.createTime}");
            }
            if (param.getUpdateTime() != null) {
                 sql.WHERE("`UPDATE_TIME`=#{param.updateTime}");
            }
        }
         return  sql;
    }
}
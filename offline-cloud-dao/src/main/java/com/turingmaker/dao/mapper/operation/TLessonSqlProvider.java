package com.turingmaker.dao.mapper.operation;

import com.turingmaker.entity.operation.TLesson;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:38
*/
public class TLessonSqlProvider {

    public String insertSelective(TLesson record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_LESSON");
        
        if (record.getLessonName() != null) {
            sql.VALUES("LESSON_NAME", "#{lessonName,jdbcType=VARCHAR}");
        }
        
        if (record.getLessonDescription() != null) {
            sql.VALUES("LESSON_DESCRIPTION", "#{lessonDescription,jdbcType=VARCHAR}");
        }
        
        if (record.getLessonAvatar() != null) {
            sql.VALUES("LESSON_AVATAR", "#{lessonAvatar,jdbcType=VARCHAR}");
        }
        
        if (record.getLessonSection() != null) {
            sql.VALUES("LESSON_SECTION", "#{lessonSection,jdbcType=INTEGER}");
        }
        
        if (record.getLessonState() != null) {
            sql.VALUES("LESSON_STATE", "#{lessonState,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCourseId() != null) {
            sql.VALUES("COURSE_ID", "#{courseId,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TLesson record) {
        SQL sql = new SQL();
        sql.UPDATE("T_LESSON");
        
        if (record.getLessonName() != null) {
            sql.SET("LESSON_NAME = #{lessonName,jdbcType=VARCHAR}");
        }
        
        if (record.getLessonDescription() != null) {
            sql.SET("LESSON_DESCRIPTION = #{lessonDescription,jdbcType=VARCHAR}");
        }
        
        if (record.getLessonAvatar() != null) {
            sql.SET("LESSON_AVATAR = #{lessonAvatar,jdbcType=VARCHAR}");
        }
        
        if (record.getLessonSection() != null) {
            sql.SET("LESSON_SECTION = #{lessonSection,jdbcType=INTEGER}");
        }
        
        if (record.getLessonState() != null) {
            sql.SET("LESSON_STATE = #{lessonState,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCourseId() != null) {
            sql.SET("COURSE_ID = #{courseId,jdbcType=BIGINT}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    public String selectForCount(@Param("param") TLesson param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_LESSON");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TLesson param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getLessonName() != null) {
                 sql.WHERE("`LESSON_NAME`=#{param.lessonName}");
            }
            if (param.getLessonDescription() != null) {
                 sql.WHERE("`LESSON_DESCRIPTION`=#{param.lessonDescription}");
            }
            if (param.getLessonAvatar() != null) {
                 sql.WHERE("`LESSON_AVATAR`=#{param.lessonAvatar}");
            }
            if (param.getLessonSection() != null) {
                 sql.WHERE("`LESSON_SECTION`=#{param.lessonSection}");
            }
            if (param.getLessonState() != null) {
                 sql.WHERE("`LESSON_STATE`=#{param.lessonState}");
            }
            if (param.getCreateTime() != null) {
                 sql.WHERE("`CREATE_TIME`=#{param.createTime}");
            }
            if (param.getUpdateTime() != null) {
                 sql.WHERE("`UPDATE_TIME`=#{param.updateTime}");
            }
            if (param.getCourseId() != null) {
                 sql.WHERE("`COURSE_ID`=#{param.courseId}");
            }
        }
         return  sql;
    }
}
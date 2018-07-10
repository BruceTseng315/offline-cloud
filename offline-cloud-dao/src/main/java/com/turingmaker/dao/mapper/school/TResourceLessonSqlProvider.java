package com.turingmaker.dao.mapper.school;

import org.apache.ibatis.jdbc.SQL;

import com.turingmaker.entity.school.TResourceLesson;

/**
@author tzj
2018-06-19 19:18
*/
public class TResourceLessonSqlProvider {

    public String insertSelective(TResourceLesson record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_RESOURCE_LESSON");
        
        if (record.getResourceId() != null) {
            sql.VALUES("RESOURCE_ID", "#{resourceId,jdbcType=BIGINT}");
        }
        
        if (record.getLessonId() != null) {
            sql.VALUES("LESSON_ID", "#{lessonId,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TResourceLesson record) {
        SQL sql = new SQL();
        sql.UPDATE("T_RESOURCE_LESSON");
        
        if (record.getResourceId() != null) {
            sql.SET("RESOURCE_ID = #{resourceId,jdbcType=BIGINT}");
        }
        
        if (record.getLessonId() != null) {
            sql.SET("LESSON_ID = #{lessonId,jdbcType=BIGINT}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    public String selectForCount(TResourceLesson param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_RESOURCE_LESSON");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TResourceLesson param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getResourceId() != null) {
                 sql.WHERE("`RESOURCE_ID`=#{param.resourceId}");
            }
            if (param.getLessonId() != null) {
                 sql.WHERE("`LESSON_ID`=#{param.lessonId}");
            }
        }
         return  sql;
    }
}
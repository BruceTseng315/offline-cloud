package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TTask;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:40
*/
public class TTaskSqlProvider {

    public String insertSelective(TTask record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_TASK");
        
        if (record.getTaskName() != null) {
            sql.VALUES("TASK_NAME", "#{taskName,jdbcType=VARCHAR}");
        }
        
        if (record.getTaskDescurl() != null) {
            sql.VALUES("TASK_DESCURL", "#{taskDescurl,jdbcType=VARCHAR}");
        }
        
        if (record.getLessonId() != null) {
            sql.VALUES("LESSON_ID", "#{lessonId,jdbcType=BIGINT}");
        }
        
        if (record.getProgramId() != null) {
            sql.VALUES("PROGRAM_ID", "#{programId,jdbcType=BIGINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TTask record) {
        SQL sql = new SQL();
        sql.UPDATE("T_TASK");
        
        if (record.getTaskName() != null) {
            sql.SET("TASK_NAME = #{taskName,jdbcType=VARCHAR}");
        }
        
        if (record.getTaskDescurl() != null) {
            sql.SET("TASK_DESCURL = #{taskDescurl,jdbcType=VARCHAR}");
        }
        
        if (record.getLessonId() != null) {
            sql.SET("LESSON_ID = #{lessonId,jdbcType=BIGINT}");
        }
        
        if (record.getProgramId() != null) {
            sql.SET("PROGRAM_ID = #{programId,jdbcType=BIGINT}");
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

    public String selectForCount(@Param("param") TTask param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_TASK");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TTask param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getTaskName() != null) {
                 sql.WHERE("`TASK_NAME`=#{param.taskName}");
            }
            if (param.getTaskDescurl() != null) {
                 sql.WHERE("`TASK_DESCURL`=#{param.taskDescurl}");
            }
            if (param.getLessonId() != null) {
                 sql.WHERE("`LESSON_ID`=#{param.lessonId}");
            }
            if (param.getProgramId() != null) {
                 sql.WHERE("`PROGRAM_ID`=#{param.programId}");
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
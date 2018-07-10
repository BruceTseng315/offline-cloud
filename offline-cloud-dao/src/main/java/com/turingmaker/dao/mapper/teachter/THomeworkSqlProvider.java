package com.turingmaker.dao.mapper.teachter;

import com.turingmaker.entity.teachter.THomework;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-04 10:33
*/
public class THomeworkSqlProvider {

    public String insertSelective(THomework record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_HOMEWORK");
        
        if (record.getStudentId() != null) {
            sql.VALUES("STUDENT_ID", "#{studentId,jdbcType=BIGINT}");
        }
        
        if (record.getProgramId() != null) {
            sql.VALUES("PROGRAM_ID", "#{programId,jdbcType=BIGINT}");
        }
        
        if (record.getIsDelete() != null) {
            sql.VALUES("IS_DELETE", "#{isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getTaskId() != null) {
            sql.VALUES("TASK_ID", "#{taskId,jdbcType=BIGINT}");
        }
        
        if (record.getHomeworkDesc() != null) {
            sql.VALUES("HOMEWORK_DESC", "#{homeworkDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getHomeworkName() != null) {
            sql.VALUES("HOMEWORK_NAME", "#{homeworkName,jdbcType=VARCHAR}");
        }
        
        if (record.getHomeworkAvatar() != null) {
            sql.VALUES("HOMEWORK_AVATAR", "#{homeworkAvatar,jdbcType=VARCHAR}");
        }
        
        if (record.getMark() != null) {
            sql.VALUES("MARK", "#{mark,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(THomework record) {
        SQL sql = new SQL();
        sql.UPDATE("T_HOMEWORK");
        
        if (record.getStudentId() != null) {
            sql.SET("STUDENT_ID = #{studentId,jdbcType=BIGINT}");
        }
        
        if (record.getProgramId() != null) {
            sql.SET("PROGRAM_ID = #{programId,jdbcType=BIGINT}");
        }
        
        if (record.getIsDelete() != null) {
            sql.SET("IS_DELETE = #{isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getTaskId() != null) {
            sql.SET("TASK_ID = #{taskId,jdbcType=BIGINT}");
        }
        
        if (record.getHomeworkDesc() != null) {
            sql.SET("HOMEWORK_DESC = #{homeworkDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getHomeworkName() != null) {
            sql.SET("HOMEWORK_NAME = #{homeworkName,jdbcType=VARCHAR}");
        }
        
        if (record.getHomeworkAvatar() != null) {
            sql.SET("HOMEWORK_AVATAR = #{homeworkAvatar,jdbcType=VARCHAR}");
        }
        
        if (record.getMark() != null) {
            sql.SET("MARK = #{mark,jdbcType=INTEGER}");
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

    public String selectForCount(@Param("param") THomework param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_HOMEWORK");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(THomework param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getStudentId() != null) {
                 sql.WHERE("`STUDENT_ID`=#{param.studentId}");
            }
            if (param.getProgramId() != null) {
                 sql.WHERE("`PROGRAM_ID`=#{param.programId}");
            }
            if (param.getIsDelete() != null) {
                 sql.WHERE("`IS_DELETE`=#{param.isDelete}");
            }
            if (param.getTaskId() != null) {
                 sql.WHERE("`TASK_ID`=#{param.taskId}");
            }
            if (param.getHomeworkDesc() != null) {
                 sql.WHERE("`HOMEWORK_DESC`=#{param.homeworkDesc}");
            }
            if (param.getHomeworkName() != null) {
                 sql.WHERE("`HOMEWORK_NAME`=#{param.homeworkName}");
            }
            if (param.getHomeworkAvatar() != null) {
                 sql.WHERE("`HOMEWORK_AVATAR`=#{param.homeworkAvatar}");
            }
            if (param.getMark() != null) {
                 sql.WHERE("`MARK`=#{param.mark}");
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
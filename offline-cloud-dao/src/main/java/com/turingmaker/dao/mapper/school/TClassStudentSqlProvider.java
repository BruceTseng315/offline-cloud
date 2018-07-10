package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TClassStudent;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:40
*/
public class TClassStudentSqlProvider {

    public String insertSelective(TClassStudent record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_CLASS_STUDENT");
        
        if (record.getClassId() != null) {
            sql.VALUES("CLASS_ID", "#{classId,jdbcType=BIGINT}");
        }
        
        if (record.getStudentId() != null) {
            sql.VALUES("STUDENT_ID", "#{studentId,jdbcType=BIGINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TClassStudent record) {
        SQL sql = new SQL();
        sql.UPDATE("T_CLASS_STUDENT");
        
        if (record.getClassId() != null) {
            sql.SET("CLASS_ID = #{classId,jdbcType=BIGINT}");
        }
        
        if (record.getStudentId() != null) {
            sql.SET("STUDENT_ID = #{studentId,jdbcType=BIGINT}");
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

    public String selectForCount(@Param("param") TClassStudent param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_CLASS_STUDENT");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TClassStudent param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getClassId() != null) {
                 sql.WHERE("`CLASS_ID`=#{param.classId}");
            }
            if (param.getStudentId() != null) {
                 sql.WHERE("`STUDENT_ID`=#{param.studentId}");
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
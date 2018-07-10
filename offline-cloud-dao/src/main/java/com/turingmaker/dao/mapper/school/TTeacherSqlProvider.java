package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TTeacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:40
*/
public class TTeacherSqlProvider {

    public String insertSelective(TTeacher record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_TEACHER");
        
        if (record.getTeacherCode() != null) {
            sql.VALUES("TEACHER_CODE", "#{teacherCode,jdbcType=VARCHAR}");
        }
        
        if (record.getTeacherName() != null) {
            sql.VALUES("TEACHER_NAME", "#{teacherName,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAccountId() != null) {
            sql.VALUES("ACCOUNT_ID", "#{accountId,jdbcType=BIGINT}");
        }
        
        if (record.getPhone() != null) {
            sql.VALUES("PHONE", "#{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getSchoolId() != null) {
            sql.VALUES("SCHOOL_ID", "#{schoolId,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TTeacher record) {
        SQL sql = new SQL();
        sql.UPDATE("T_TEACHER");
        
        if (record.getTeacherCode() != null) {
            sql.SET("TEACHER_CODE = #{teacherCode,jdbcType=VARCHAR}");
        }
        
        if (record.getTeacherName() != null) {
            sql.SET("TEACHER_NAME = #{teacherName,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAccountId() != null) {
            sql.SET("ACCOUNT_ID = #{accountId,jdbcType=BIGINT}");
        }
        
        if (record.getPhone() != null) {
            sql.SET("PHONE = #{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getSchoolId() != null) {
            sql.SET("SCHOOL_ID = #{schoolId,jdbcType=BIGINT}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    public String selectForCount(@Param("param") TTeacher param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_TEACHER");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TTeacher param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getTeacherCode() != null) {
                 sql.WHERE("`TEACHER_CODE`=#{param.teacherCode}");
            }
            if (param.getTeacherName() != null) {
                 sql.WHERE("`TEACHER_NAME`=#{param.teacherName}");
            }
            if (param.getCreateTime() != null) {
                 sql.WHERE("`CREATE_TIME`=#{param.createTime}");
            }
            if (param.getUpdateTime() != null) {
                 sql.WHERE("`UPDATE_TIME`=#{param.updateTime}");
            }
            if (param.getAccountId() != null) {
                 sql.WHERE("`ACCOUNT_ID`=#{param.accountId}");
            }
            if (param.getPhone() != null) {
                 sql.WHERE("`PHONE`=#{param.phone}");
            }
            if (param.getSchoolId() != null) {
                 sql.WHERE("`SCHOOL_ID`=#{param.schoolId}");
            }
        }
         return  sql;
    }
}
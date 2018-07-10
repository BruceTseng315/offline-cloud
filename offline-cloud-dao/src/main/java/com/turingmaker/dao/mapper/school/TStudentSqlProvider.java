package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TStudent;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:40
*/
public class TStudentSqlProvider {

    public String insertSelective(TStudent record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_STUDENT");
        
        if (record.getStudentCode() != null) {
            sql.VALUES("STUDENT_CODE", "#{studentCode,jdbcType=VARCHAR}");
        }
        
        if (record.getStudentName() != null) {
            sql.VALUES("STUDENT_NAME", "#{studentName,jdbcType=VARCHAR}");
        }
        
        if (record.getStudentNumber() != null) {
            sql.VALUES("STUDENT_NUMBER", "#{studentNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getIdentifier() != null) {
            sql.VALUES("IDENTIFIER", "#{identifier,jdbcType=VARCHAR}");
        }
        
        if (record.getGuarderName() != null) {
            sql.VALUES("GUARDER_NAME", "#{guarderName,jdbcType=VARCHAR}");
        }
        
        if (record.getGuarderPhone() != null) {
            sql.VALUES("GUARDER_PHONE", "#{guarderPhone,jdbcType=VARCHAR}");
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
        
        if (record.getSchoolId() != null) {
            sql.VALUES("SCHOOL_ID", "#{schoolId,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TStudent record) {
        SQL sql = new SQL();
        sql.UPDATE("T_STUDENT");
        
        if (record.getStudentCode() != null) {
            sql.SET("STUDENT_CODE = #{studentCode,jdbcType=VARCHAR}");
        }
        
        if (record.getStudentName() != null) {
            sql.SET("STUDENT_NAME = #{studentName,jdbcType=VARCHAR}");
        }
        
        if (record.getStudentNumber() != null) {
            sql.SET("STUDENT_NUMBER = #{studentNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getIdentifier() != null) {
            sql.SET("IDENTIFIER = #{identifier,jdbcType=VARCHAR}");
        }
        
        if (record.getGuarderName() != null) {
            sql.SET("GUARDER_NAME = #{guarderName,jdbcType=VARCHAR}");
        }
        
        if (record.getGuarderPhone() != null) {
            sql.SET("GUARDER_PHONE = #{guarderPhone,jdbcType=VARCHAR}");
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
        
        if (record.getSchoolId() != null) {
            sql.SET("SCHOOL_ID = #{schoolId,jdbcType=BIGINT}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    public String selectForCount(@Param("param") TStudent param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_STUDENT");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TStudent param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getStudentCode() != null) {
                 sql.WHERE("`STUDENT_CODE`=#{param.studentCode}");
            }
            if (param.getStudentName() != null) {
                 sql.WHERE("`STUDENT_NAME`=#{param.studentName}");
            }
            if (param.getStudentNumber() != null) {
                 sql.WHERE("`STUDENT_NUMBER`=#{param.studentNumber}");
            }
            if (param.getIdentifier() != null) {
                 sql.WHERE("`IDENTIFIER`=#{param.identifier}");
            }
            if (param.getGuarderName() != null) {
                 sql.WHERE("`GUARDER_NAME`=#{param.guarderName}");
            }
            if (param.getGuarderPhone() != null) {
                 sql.WHERE("`GUARDER_PHONE`=#{param.guarderPhone}");
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
            if (param.getSchoolId() != null) {
                 sql.WHERE("`SCHOOL_ID`=#{param.schoolId}");
            }
        }
         return  sql;
    }
}
package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TClass;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:40
*/
public class TClassSqlProvider {

    public String insertSelective(TClass record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_CLASS");
        
        if (record.getClassCode() != null) {
            sql.VALUES("CLASS_CODE", "#{classCode,jdbcType=VARCHAR}");
        }
        
        if (record.getClassName() != null) {
            sql.VALUES("CLASS_NAME", "#{className,jdbcType=VARCHAR}");
        }
        
        if (record.getClassType() != null) {
            sql.VALUES("CLASS_TYPE", "#{classType,jdbcType=TINYINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSchoolId() != null) {
            sql.VALUES("SCHOOL_ID", "#{schoolId,jdbcType=BIGINT}");
        }
        
        if (record.getGrade() != null) {
            sql.VALUES("GRADE", "#{grade,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TClass record) {
        SQL sql = new SQL();
        sql.UPDATE("T_CLASS");
        
        if (record.getClassCode() != null) {
            sql.SET("CLASS_CODE = #{classCode,jdbcType=VARCHAR}");
        }
        
        if (record.getClassName() != null) {
            sql.SET("CLASS_NAME = #{className,jdbcType=VARCHAR}");
        }
        
        if (record.getClassType() != null) {
            sql.SET("CLASS_TYPE = #{classType,jdbcType=TINYINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSchoolId() != null) {
            sql.SET("SCHOOL_ID = #{schoolId,jdbcType=BIGINT}");
        }
        
        if (record.getGrade() != null) {
            sql.SET("GRADE = #{grade,jdbcType=INTEGER}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    public String selectForCount(@Param("param") TClass param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_CLASS");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TClass param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getClassCode() != null) {
                 sql.WHERE("`CLASS_CODE`=#{param.classCode}");
            }
            if (param.getClassName() != null) {
                 sql.WHERE("`CLASS_NAME`=#{param.className}");
            }
            if (param.getClassType() != null) {
                 sql.WHERE("`CLASS_TYPE`=#{param.classType}");
            }
            if (param.getCreateTime() != null) {
                 sql.WHERE("`CREATE_TIME`=#{param.createTime}");
            }
            if (param.getUpdateTime() != null) {
                 sql.WHERE("`UPDATE_TIME`=#{param.updateTime}");
            }
            if (param.getSchoolId() != null) {
                 sql.WHERE("`SCHOOL_ID`=#{param.schoolId}");
            }
            if (param.getGrade() != null) {
                 sql.WHERE("`GRADE`=#{param.grade}");
            }
        }
         return  sql;
    }
}
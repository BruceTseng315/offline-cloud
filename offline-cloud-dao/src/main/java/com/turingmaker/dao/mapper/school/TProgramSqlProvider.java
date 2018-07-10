package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TProgram;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:40
*/
public class TProgramSqlProvider {

    public String insertSelective(TProgram record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_PROGRAM");
        
        if (record.getProgramContentUrl() != null) {
            sql.VALUES("PROGRAM_CONTENT_URL", "#{programContentUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getProgramName() != null) {
            sql.VALUES("PROGRAM_NAME", "#{programName,jdbcType=VARCHAR}");
        }
        
        if (record.getProgramContent() != null) {
            sql.VALUES("PROGRAM_CONTENT", "#{programContent,jdbcType=CHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TProgram record) {
        SQL sql = new SQL();
        sql.UPDATE("T_PROGRAM");
        
        if (record.getProgramContentUrl() != null) {
            sql.SET("PROGRAM_CONTENT_URL = #{programContentUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getProgramName() != null) {
            sql.SET("PROGRAM_NAME = #{programName,jdbcType=VARCHAR}");
        }
        
        if (record.getProgramContent() != null) {
            sql.SET("PROGRAM_CONTENT = #{programContent,jdbcType=CHAR}");
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

    public String selectForCount(@Param("param") TProgram param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_PROGRAM");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TProgram param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getProgramContentUrl() != null) {
                 sql.WHERE("`PROGRAM_CONTENT_URL`=#{param.programContentUrl}");
            }
            if (param.getProgramName() != null) {
                 sql.WHERE("`PROGRAM_NAME`=#{param.programName}");
            }
            if (param.getProgramContent() != null) {
                 sql.WHERE("`PROGRAM_CONTENT`=#{param.programContent}");
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
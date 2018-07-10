package com.turingmaker.dao.mapper.operation;

import com.turingmaker.entity.operation.TOperationsManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:38
*/
public class TOperationsManagerSqlProvider {

    public String insertSelective(TOperationsManager record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_OPERATIONS_MANAGER");
        
        if (record.getName() != null) {
            sql.VALUES("NAME", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            sql.VALUES("PHONE", "#{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getSchoolId() != null) {
            sql.VALUES("SCHOOL_ID", "#{schoolId,jdbcType=BIGINT}");
        }
        
        if (record.getAccountId() != null) {
            sql.VALUES("ACCOUNT_ID", "#{accountId,jdbcType=BIGINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TOperationsManager record) {
        SQL sql = new SQL();
        sql.UPDATE("T_OPERATIONS_MANAGER");
        
        if (record.getName() != null) {
            sql.SET("NAME = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            sql.SET("PHONE = #{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getSchoolId() != null) {
            sql.SET("SCHOOL_ID = #{schoolId,jdbcType=BIGINT}");
        }
        
        if (record.getAccountId() != null) {
            sql.SET("ACCOUNT_ID = #{accountId,jdbcType=BIGINT}");
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

    public String selectForCount(@Param("param") TOperationsManager param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_OPERATIONS_MANAGER");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TOperationsManager param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getName() != null) {
                 sql.WHERE("`NAME`=#{param.name}");
            }
            if (param.getPhone() != null) {
                 sql.WHERE("`PHONE`=#{param.phone}");
            }
            if (param.getSchoolId() != null) {
                 sql.WHERE("`SCHOOL_ID`=#{param.schoolId}");
            }
            if (param.getAccountId() != null) {
                 sql.WHERE("`ACCOUNT_ID`=#{param.accountId}");
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
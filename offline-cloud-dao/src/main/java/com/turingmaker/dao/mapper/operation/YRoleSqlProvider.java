package com.turingmaker.dao.mapper.operation;

import com.turingmaker.entity.operation.YRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:38
*/
public class YRoleSqlProvider {

    public String insertSelective(YRole record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("Y_ROLE");
        
        if (record.getRoleName() != null) {
            sql.VALUES("ROLE_NAME", "#{roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleCode() != null) {
            sql.VALUES("ROLE_CODE", "#{roleCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(YRole record) {
        SQL sql = new SQL();
        sql.UPDATE("Y_ROLE");
        
        if (record.getRoleName() != null) {
            sql.SET("ROLE_NAME = #{roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleCode() != null) {
            sql.SET("ROLE_CODE = #{roleCode,jdbcType=VARCHAR}");
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

    public String selectForCount(@Param("param") YRole param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("Y_ROLE");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(YRole param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getRoleName() != null) {
                 sql.WHERE("`ROLE_NAME`=#{param.roleName}");
            }
            if (param.getRoleCode() != null) {
                 sql.WHERE("`ROLE_CODE`=#{param.roleCode}");
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
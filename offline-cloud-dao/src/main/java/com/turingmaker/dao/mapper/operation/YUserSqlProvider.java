package com.turingmaker.dao.mapper.operation;

import com.turingmaker.entity.operation.YUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:38
*/
public class YUserSqlProvider {

    public String insertSelective(YUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("Y_USER");
        
        if (record.getAccount() != null) {
            sql.VALUES("ACCOUNT", "#{account,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.VALUES("PASSWORD", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(YUser record) {
        SQL sql = new SQL();
        sql.UPDATE("Y_USER");
        
        if (record.getAccount() != null) {
            sql.SET("ACCOUNT = #{account,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("PASSWORD = #{password,jdbcType=VARCHAR}");
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

    public String selectForCount(@Param("param") YUser param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("Y_USER");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(YUser param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getAccount() != null) {
                 sql.WHERE("`ACCOUNT`=#{param.account}");
            }
            if (param.getPassword() != null) {
                 sql.WHERE("`PASSWORD`=#{param.password}");
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
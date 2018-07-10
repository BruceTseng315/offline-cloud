package com.turingmaker.dao.mapper.operation;

import com.turingmaker.entity.operation.YRoleUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:38
*/
public class YRoleUserSqlProvider {

    public String insertSelective(YRoleUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("Y_ROLE_USER");
        
        if (record.getUserId() != null) {
            sql.VALUES("USER_ID", "#{userId,jdbcType=BIGINT}");
        }
        
        if (record.getRoleId() != null) {
            sql.VALUES("ROLE_ID", "#{roleId,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(YRoleUser record) {
        SQL sql = new SQL();
        sql.UPDATE("Y_ROLE_USER");
        
        if (record.getRoleId() != null) {
            sql.SET("ROLE_ID = #{roleId,jdbcType=BIGINT}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=BIGINT}");
        sql.WHERE("USER_ID = #{userId,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    public String selectForCount(@Param("param") YRoleUser param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("Y_ROLE_USER");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(YRoleUser param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getUserId() != null) {
                 sql.WHERE("`USER_ID`=#{param.userId}");
            }
            if (param.getRoleId() != null) {
                 sql.WHERE("`ROLE_ID`=#{param.roleId}");
            }
        }
         return  sql;
    }
}
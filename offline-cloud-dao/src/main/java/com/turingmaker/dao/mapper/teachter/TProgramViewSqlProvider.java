package com.turingmaker.dao.mapper.teachter;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.turingmaker.entity.teachter.TProgramView;

/**
@author tzj
2018-07-03 18:34
*/
public class TProgramViewSqlProvider {

    public String insertSelective(TProgramView record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_PROGRAM_VIEW");
        
        if (record.getProgramId() != null) {
            sql.VALUES("PROGRAM_ID", "#{programId,jdbcType=BIGINT}");
        }
        
        if (record.getViewCount() != null) {
            sql.VALUES("VIEW_COUNT", "#{viewCount,jdbcType=INTEGER}");
        }
        
        if (record.getLikeUsers() != null) {
            sql.VALUES("LIKE_USERS", "#{likeUsers,jdbcType=CHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TProgramView record) {
        SQL sql = new SQL();
        sql.UPDATE("T_PROGRAM_VIEW");
        
        if (record.getProgramId() != null) {
            sql.SET("PROGRAM_ID = #{programId,jdbcType=BIGINT}");
        }
        
        if (record.getViewCount() != null) {
            sql.SET("VIEW_COUNT = #{viewCount,jdbcType=INTEGER}");
        }
        
        if (record.getLikeUsers() != null) {
            sql.SET("LIKE_USERS = #{likeUsers,jdbcType=CHAR}");
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

    public String selectForCount(@Param("param") TProgramView param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_PROGRAM_VIEW");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TProgramView param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getProgramId() != null) {
                 sql.WHERE("`PROGRAM_ID`=#{param.programId}");
            }
            if (param.getViewCount() != null) {
                 sql.WHERE("`VIEW_COUNT`=#{param.viewCount}");
            }
            if (param.getLikeUsers() != null) {
                 sql.WHERE("`LIKE_USERS`=#{param.likeUsers}");
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
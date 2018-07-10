package com.turingmaker.dao.mapper.teachter;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.turingmaker.entity.teachter.TProgramLike;

/**
@author tzj
2018-07-03 18:34
*/
public class TProgramLikeSqlProvider {

    public String insertSelective(TProgramLike record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_PROGRAM_LIKE");
        
        if (record.getProgramId() != null) {
            sql.VALUES("PROGRAM_ID", "#{programId,jdbcType=BIGINT}");
        }
        
        if (record.getLikeCount() != null) {
            sql.VALUES("LIKE_COUNT", "#{likeCount,jdbcType=INTEGER}");
        }
        
        if (record.getLikeUsers() != null) {
            sql.VALUES("LIKE_USERS", "#{likeUsers,jdbcType=CHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TProgramLike record) {
        SQL sql = new SQL();
        sql.UPDATE("T_PROGRAM_LIKE");
        
        if (record.getProgramId() != null) {
            sql.SET("PROGRAM_ID = #{programId,jdbcType=BIGINT}");
        }
        
        if (record.getLikeCount() != null) {
            sql.SET("LIKE_COUNT = #{likeCount,jdbcType=INTEGER}");
        }
        
        if (record.getLikeUsers() != null) {
            sql.SET("LIKE_USERS = #{likeUsers,jdbcType=CHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    public String selectForCount(@Param("param") TProgramLike param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_PROGRAM_LIKE");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TProgramLike param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getProgramId() != null) {
                 sql.WHERE("`PROGRAM_ID`=#{param.programId}");
            }
            if (param.getLikeCount() != null) {
                 sql.WHERE("`LIKE_COUNT`=#{param.likeCount}");
            }
            if (param.getLikeUsers() != null) {
                 sql.WHERE("`LIKE_USERS`=#{param.likeUsers}");
            }
            if (param.getUpdateTime() != null) {
                 sql.WHERE("`UPDATE_TIME`=#{param.updateTime}");
            }
            if (param.getCreateTime() != null) {
                 sql.WHERE("`CREATE_TIME`=#{param.createTime}");
            }
        }
         return  sql;
    }
}
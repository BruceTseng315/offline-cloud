package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TResource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:40
*/
public class TResourceSqlProvider {

    public String insertSelective(TResource record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_RESOURCE");
        
        if (record.getResourceName() != null) {
            sql.VALUES("RESOURCE_NAME", "#{resourceName,jdbcType=VARCHAR}");
        }
        
        if (record.getResourceUrl() != null) {
            sql.VALUES("RESOURCE_URL", "#{resourceUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getResourceType() != null) {
            sql.VALUES("RESOURCE_TYPE", "#{resourceType,jdbcType=INTEGER}");
        }
        
        if (record.getResourceFileType() != null) {
            sql.VALUES("RESOURCE_FILE_TYPE", "#{resourceFileType,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TResource record) {
        SQL sql = new SQL();
        sql.UPDATE("T_RESOURCE");
        
        if (record.getResourceName() != null) {
            sql.SET("RESOURCE_NAME = #{resourceName,jdbcType=VARCHAR}");
        }
        
        if (record.getResourceUrl() != null) {
            sql.SET("RESOURCE_URL = #{resourceUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getResourceType() != null) {
            sql.SET("RESOURCE_TYPE = #{resourceType,jdbcType=INTEGER}");
        }
        
        if (record.getResourceFileType() != null) {
            sql.SET("RESOURCE_FILE_TYPE = #{resourceFileType,jdbcType=VARCHAR}");
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

    public String selectForCount(@Param("param") TResource param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_RESOURCE");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TResource param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getResourceName() != null) {
                 sql.WHERE("`RESOURCE_NAME`=#{param.resourceName}");
            }
            if (param.getResourceUrl() != null) {
                 sql.WHERE("`RESOURCE_URL`=#{param.resourceUrl}");
            }
            if (param.getResourceType() != null) {
                 sql.WHERE("`RESOURCE_TYPE`=#{param.resourceType}");
            }
            if (param.getResourceFileType() != null) {
                 sql.WHERE("`RESOURCE_FILE_TYPE`=#{param.resourceFileType}");
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
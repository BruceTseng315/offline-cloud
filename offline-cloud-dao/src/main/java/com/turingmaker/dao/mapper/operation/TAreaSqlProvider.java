package com.turingmaker.dao.mapper.operation;

import com.turingmaker.entity.operation.TArea;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:38
*/
public class TAreaSqlProvider {

    public String insertSelective(TArea record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_AREA");
        
        if (record.getAreaName() != null) {
            sql.VALUES("AREA_NAME", "#{areaName,jdbcType=VARCHAR}");
        }
        
        if (record.getAreaCode() != null) {
            sql.VALUES("AREA_CODE", "#{areaCode,jdbcType=VARCHAR}");
        }
        
        if (record.getAreaPid() != null) {
            sql.VALUES("AREA_PID", "#{areaPid,jdbcType=BIGINT}");
        }
        
        if (record.getDisorder() != null) {
            sql.VALUES("DISORDER", "#{disorder,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TArea record) {
        SQL sql = new SQL();
        sql.UPDATE("T_AREA");
        
        if (record.getAreaName() != null) {
            sql.SET("AREA_NAME = #{areaName,jdbcType=VARCHAR}");
        }
        
        if (record.getAreaCode() != null) {
            sql.SET("AREA_CODE = #{areaCode,jdbcType=VARCHAR}");
        }
        
        if (record.getAreaPid() != null) {
            sql.SET("AREA_PID = #{areaPid,jdbcType=BIGINT}");
        }
        
        if (record.getDisorder() != null) {
            sql.SET("DISORDER = #{disorder,jdbcType=INTEGER}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    public String selectForCount(@Param("param") TArea param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_AREA");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TArea param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getAreaName() != null) {
                 sql.WHERE("`AREA_NAME`=#{param.areaName}");
            }
            if (param.getAreaCode() != null) {
                 sql.WHERE("`AREA_CODE`=#{param.areaCode}");
            }
            if (param.getAreaPid() != null) {
                 sql.WHERE("`AREA_PID`=#{param.areaPid}");
            }
            if (param.getDisorder() != null) {
                 sql.WHERE("`DISORDER`=#{param.disorder}");
            }
        }
         return  sql;
    }
}
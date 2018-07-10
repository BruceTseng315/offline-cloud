package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TSchool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-03 18:40
*/
public class TSchoolSqlProvider {

    public String insertSelective(TSchool record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_SCHOOL");
        
        if (record.getSchoolCode() != null) {
            sql.VALUES("SCHOOL_CODE", "#{schoolCode,jdbcType=VARCHAR}");
        }
        
        if (record.getSchoolType() != null) {
            sql.VALUES("SCHOOL_TYPE", "#{schoolType,jdbcType=INTEGER}");
        }
        
        if (record.getSchoolName() != null) {
            sql.VALUES("SCHOOL_NAME", "#{schoolName,jdbcType=VARCHAR}");
        }
        
        if (record.getAreaCode() != null) {
            sql.VALUES("AREA_CODE", "#{areaCode,jdbcType=BIGINT}");
        }
        
        if (record.getAddress() != null) {
            sql.VALUES("ADDRESS", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getContactPhone() != null) {
            sql.VALUES("CONTACT_PHONE", "#{contactPhone,jdbcType=VARCHAR}");
        }
        
        if (record.getContactName() != null) {
            sql.VALUES("CONTACT_NAME", "#{contactName,jdbcType=VARCHAR}");
        }
        
        if (record.getChannelName() != null) {
            sql.VALUES("CHANNEL_NAME", "#{channelName,jdbcType=VARCHAR}");
        }
        
        if (record.getChannelPhone() != null) {
            sql.VALUES("CHANNEL_PHONE", "#{channelPhone,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TSchool record) {
        SQL sql = new SQL();
        sql.UPDATE("T_SCHOOL");
        
        if (record.getSchoolCode() != null) {
            sql.SET("SCHOOL_CODE = #{schoolCode,jdbcType=VARCHAR}");
        }
        
        if (record.getSchoolType() != null) {
            sql.SET("SCHOOL_TYPE = #{schoolType,jdbcType=INTEGER}");
        }
        
        if (record.getSchoolName() != null) {
            sql.SET("SCHOOL_NAME = #{schoolName,jdbcType=VARCHAR}");
        }
        
        if (record.getAreaCode() != null) {
            sql.SET("AREA_CODE = #{areaCode,jdbcType=BIGINT}");
        }
        
        if (record.getAddress() != null) {
            sql.SET("ADDRESS = #{address,jdbcType=VARCHAR}");
        }
        
        if (record.getContactPhone() != null) {
            sql.SET("CONTACT_PHONE = #{contactPhone,jdbcType=VARCHAR}");
        }
        
        if (record.getContactName() != null) {
            sql.SET("CONTACT_NAME = #{contactName,jdbcType=VARCHAR}");
        }
        
        if (record.getChannelName() != null) {
            sql.SET("CHANNEL_NAME = #{channelName,jdbcType=VARCHAR}");
        }
        
        if (record.getChannelPhone() != null) {
            sql.SET("CHANNEL_PHONE = #{channelPhone,jdbcType=VARCHAR}");
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

    public String selectForCount(@Param("param") TSchool param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_SCHOOL");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TSchool param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getSchoolCode() != null) {
                 sql.WHERE("`SCHOOL_CODE`=#{param.schoolCode}");
            }
            if (param.getSchoolType() != null) {
                 sql.WHERE("`SCHOOL_TYPE`=#{param.schoolType}");
            }
            if (param.getSchoolName() != null) {
                 sql.WHERE("`SCHOOL_NAME`=#{param.schoolName}");
            }
            if (param.getAreaCode() != null) {
                 sql.WHERE("`AREA_CODE`=#{param.areaCode}");
            }
            if (param.getAddress() != null) {
                 sql.WHERE("`ADDRESS`=#{param.address}");
            }
            if (param.getContactPhone() != null) {
                 sql.WHERE("`CONTACT_PHONE`=#{param.contactPhone}");
            }
            if (param.getContactName() != null) {
                 sql.WHERE("`CONTACT_NAME`=#{param.contactName}");
            }
            if (param.getChannelName() != null) {
                 sql.WHERE("`CHANNEL_NAME`=#{param.channelName}");
            }
            if (param.getChannelPhone() != null) {
                 sql.WHERE("`CHANNEL_PHONE`=#{param.channelPhone}");
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
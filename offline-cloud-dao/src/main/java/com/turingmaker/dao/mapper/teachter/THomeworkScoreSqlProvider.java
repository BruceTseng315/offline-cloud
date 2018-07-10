package com.turingmaker.dao.mapper.teachter;

import com.turingmaker.entity.teachter.THomeworkScore;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-06 10:11
*/
public class THomeworkScoreSqlProvider {

    public String insertSelective(THomeworkScore record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_HOMEWORK_SCORE");
        
        if (record.getHomeworkId() != null) {
            sql.VALUES("HOMEWORK_ID", "#{homeworkId,jdbcType=BIGINT}");
        }
        
        if (record.getSelfLearn() != null) {
            sql.VALUES("SELF_LEARN", "#{selfLearn,jdbcType=INTEGER}");
        }
        
        if (record.getInquiryLearn() != null) {
            sql.VALUES("INQUIRY_LEARN", "#{inquiryLearn,jdbcType=INTEGER}");
        }
        
        if (record.getCooperativeLearn() != null) {
            sql.VALUES("COOPERATIVE_LEARN", "#{cooperativeLearn,jdbcType=INTEGER}");
        }
        
        if (record.getUtilization() != null) {
            sql.VALUES("UTILIZATION", "#{utilization,jdbcType=INTEGER}");
        }
        
        if (record.getComment() != null) {
            sql.VALUES("COMMENT", "#{comment,jdbcType=VARCHAR}");
        }
        
        if (record.getTeachterId() != null) {
            sql.VALUES("TEACHTER_ID", "#{teachterId,jdbcType=BIGINT}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(THomeworkScore record) {
        SQL sql = new SQL();
        sql.UPDATE("T_HOMEWORK_SCORE");
        
        if (record.getHomeworkId() != null) {
            sql.SET("HOMEWORK_ID = #{homeworkId,jdbcType=BIGINT}");
        }
        
        if (record.getSelfLearn() != null) {
            sql.SET("SELF_LEARN = #{selfLearn,jdbcType=INTEGER}");
        }
        
        if (record.getInquiryLearn() != null) {
            sql.SET("INQUIRY_LEARN = #{inquiryLearn,jdbcType=INTEGER}");
        }
        
        if (record.getCooperativeLearn() != null) {
            sql.SET("COOPERATIVE_LEARN = #{cooperativeLearn,jdbcType=INTEGER}");
        }
        
        if (record.getUtilization() != null) {
            sql.SET("UTILIZATION = #{utilization,jdbcType=INTEGER}");
        }
        
        if (record.getComment() != null) {
            sql.SET("COMMENT = #{comment,jdbcType=VARCHAR}");
        }
        
        if (record.getTeachterId() != null) {
            sql.SET("TEACHTER_ID = #{teachterId,jdbcType=BIGINT}");
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

    public String selectForCount(@Param("param") THomeworkScore param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_HOMEWORK_SCORE");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(THomeworkScore param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getHomeworkId() != null) {
                 sql.WHERE("`HOMEWORK_ID`=#{param.homeworkId}");
            }
            if (param.getSelfLearn() != null) {
                 sql.WHERE("`SELF_LEARN`=#{param.selfLearn}");
            }
            if (param.getInquiryLearn() != null) {
                 sql.WHERE("`INQUIRY_LEARN`=#{param.inquiryLearn}");
            }
            if (param.getCooperativeLearn() != null) {
                 sql.WHERE("`COOPERATIVE_LEARN`=#{param.cooperativeLearn}");
            }
            if (param.getUtilization() != null) {
                 sql.WHERE("`UTILIZATION`=#{param.utilization}");
            }
            if (param.getComment() != null) {
                 sql.WHERE("`COMMENT`=#{param.comment}");
            }
            if (param.getTeachterId() != null) {
                 sql.WHERE("`TEACHTER_ID`=#{param.teachterId}");
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
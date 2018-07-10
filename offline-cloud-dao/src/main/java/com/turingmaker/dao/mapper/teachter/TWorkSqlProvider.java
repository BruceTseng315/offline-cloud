package com.turingmaker.dao.mapper.teachter;

import com.turingmaker.entity.teachter.TWork;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-07-05 14:58
*/
public class TWorkSqlProvider {

    public String insertSelective(TWork record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_WORK");
        
        if (record.getUserId() != null) {
            sql.VALUES("USER_ID", "#{userId,jdbcType=BIGINT}");
        }
        
        if (record.getWorkDesc() != null) {
            sql.VALUES("WORK_DESC", "#{workDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getWorkAuthor() != null) {
            sql.VALUES("WORK_AUTHOR", "#{workAuthor,jdbcType=VARCHAR}");
        }
        
        if (record.getWorkName() != null) {
            sql.VALUES("WORK_NAME", "#{workName,jdbcType=VARCHAR}");
        }
        
        if (record.getWorkAvatar() != null) {
            sql.VALUES("WORK_AVATAR", "#{workAvatar,jdbcType=VARCHAR}");
        }
        
        if (record.getIsDelete() != null) {
            sql.VALUES("IS_DELETE", "#{isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getProgramId() != null) {
            sql.VALUES("PROGRAM_ID", "#{programId,jdbcType=BIGINT}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("STATE", "#{state,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TWork record) {
        SQL sql = new SQL();
        sql.UPDATE("T_WORK");
        
        if (record.getUserId() != null) {
            sql.SET("USER_ID = #{userId,jdbcType=BIGINT}");
        }
        
        if (record.getWorkDesc() != null) {
            sql.SET("WORK_DESC = #{workDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getWorkAuthor() != null) {
            sql.SET("WORK_AUTHOR = #{workAuthor,jdbcType=VARCHAR}");
        }
        
        if (record.getWorkName() != null) {
            sql.SET("WORK_NAME = #{workName,jdbcType=VARCHAR}");
        }
        
        if (record.getWorkAvatar() != null) {
            sql.SET("WORK_AVATAR = #{workAvatar,jdbcType=VARCHAR}");
        }
        
        if (record.getIsDelete() != null) {
            sql.SET("IS_DELETE = #{isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getProgramId() != null) {
            sql.SET("PROGRAM_ID = #{programId,jdbcType=BIGINT}");
        }
        
        if (record.getState() != null) {
            sql.SET("STATE = #{state,jdbcType=INTEGER}");
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

    public String selectForCount(@Param("param") TWork param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_WORK");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TWork param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`ID`=#{param.id}");
            }
            if (param.getUserId() != null) {
                 sql.WHERE("`USER_ID`=#{param.userId}");
            }
            if (param.getWorkDesc() != null) {
                 sql.WHERE("`WORK_DESC`=#{param.workDesc}");
            }
            if (param.getWorkAuthor() != null) {
                 sql.WHERE("`WORK_AUTHOR`=#{param.workAuthor}");
            }
            if (param.getWorkName() != null) {
                 sql.WHERE("`WORK_NAME`=#{param.workName}");
            }
            if (param.getWorkAvatar() != null) {
                 sql.WHERE("`WORK_AVATAR`=#{param.workAvatar}");
            }
            if (param.getIsDelete() != null) {
                 sql.WHERE("`IS_DELETE`=#{param.isDelete}");
            }
            if (param.getProgramId() != null) {
                 sql.WHERE("`PROGRAM_ID`=#{param.programId}");
            }
            if (param.getState() != null) {
                 sql.WHERE("`STATE`=#{param.state}");
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
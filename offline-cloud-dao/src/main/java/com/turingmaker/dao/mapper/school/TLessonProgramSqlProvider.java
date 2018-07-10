package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TLessonProgram;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
@author tzj
2018-06-27 14:12
*/
public class TLessonProgramSqlProvider {

    public String insertSelective(TLessonProgram record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_LESSON_PROGRAM");
        
        if (record.getProgramId() != null) {
            sql.VALUES("PROGRAM_ID", "#{programId,jdbcType=BIGINT}");
        }
        
        if (record.getLessonId() != null) {
            sql.VALUES("LESSON_ID", "#{lessonId,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String selectForCount(@Param("param") TLessonProgram param) {
        SQL sql = new SQL();
        sql.SELECT("count(0)");
        sql.FROM("T_LESSON_PROGRAM");
        appendSql(param,sql);
        StringBuilder builder=new StringBuilder();
        builder.append(sql.toString());
         return  builder.toString(); 
    }

    public SQL appendSql(TLessonProgram param, SQL sql) {
        if(param!=null){
            if (param.getId() != null) {
                 sql.WHERE("`id`=#{param.id}");
            }
            if (param.getProgramId() != null) {
                 sql.WHERE("`PROGRAM_ID`=#{param.programId}");
            }
            if (param.getLessonId() != null) {
                 sql.WHERE("`LESSON_ID`=#{param.lessonId}");
            }
        }
         return  sql;
    }
}
package com.turingmaker.dao.mapper.school;

import com.turingmaker.entity.school.TLessonProgram;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;

/**
@author tzj
2018-06-27 14:12
*/
public interface TLessonProgramMapper {
    @Insert({
        "insert into T_LESSON_PROGRAM (PROGRAM_ID, LESSON_ID)",
        "values (#{programId,jdbcType=BIGINT}, #{lessonId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TLessonProgram record);

    @InsertProvider(type=TLessonProgramSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TLessonProgram record);

    @SelectProvider(type=com.turingmaker.dao.mapper.school.TLessonProgramSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TLessonProgram param);
}
package com.turingmaker.dao.mapper.teachter;

import com.turingmaker.entity.teachter.THomeworkScore;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

/**
@author tzj
2018-07-06 10:11
*/
public interface THomeworkScoreMapper {
    @Delete({
        "delete from T_HOMEWORK_SCORE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_HOMEWORK_SCORE (HOMEWORK_ID, SELF_LEARN, ",
        "INQUIRY_LEARN, COOPERATIVE_LEARN, ",
        "UTILIZATION, COMMENT, ",
        "TEACHTER_ID, UPDATE_TIME, ",
        "CREATE_TIME)",
        "values (#{homeworkId,jdbcType=BIGINT}, #{selfLearn,jdbcType=INTEGER}, ",
        "#{inquiryLearn,jdbcType=INTEGER}, #{cooperativeLearn,jdbcType=INTEGER}, ",
        "#{utilization,jdbcType=INTEGER}, #{comment,jdbcType=VARCHAR}, ",
        "#{teachterId,jdbcType=BIGINT}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(THomeworkScore record);

    @InsertProvider(type=THomeworkScoreSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(THomeworkScore record);

    @Select({
        "select",
        "ID, HOMEWORK_ID, SELF_LEARN, INQUIRY_LEARN, COOPERATIVE_LEARN, UTILIZATION, ",
        "COMMENT, TEACHTER_ID, UPDATE_TIME, CREATE_TIME",
        "from T_HOMEWORK_SCORE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="HOMEWORK_ID", property="homeworkId", jdbcType=JdbcType.BIGINT),
        @Result(column="SELF_LEARN", property="selfLearn", jdbcType=JdbcType.INTEGER),
        @Result(column="INQUIRY_LEARN", property="inquiryLearn", jdbcType=JdbcType.INTEGER),
        @Result(column="COOPERATIVE_LEARN", property="cooperativeLearn", jdbcType=JdbcType.INTEGER),
        @Result(column="UTILIZATION", property="utilization", jdbcType=JdbcType.INTEGER),
        @Result(column="COMMENT", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="TEACHTER_ID", property="teachterId", jdbcType=JdbcType.BIGINT),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    THomeworkScore selectByPrimaryKey(Long id);

    @UpdateProvider(type=THomeworkScoreSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(THomeworkScore record);

    @Update({
        "update T_HOMEWORK_SCORE",
        "set HOMEWORK_ID = #{homeworkId,jdbcType=BIGINT},",
          "SELF_LEARN = #{selfLearn,jdbcType=INTEGER},",
          "INQUIRY_LEARN = #{inquiryLearn,jdbcType=INTEGER},",
          "COOPERATIVE_LEARN = #{cooperativeLearn,jdbcType=INTEGER},",
          "UTILIZATION = #{utilization,jdbcType=INTEGER},",
          "COMMENT = #{comment,jdbcType=VARCHAR},",
          "TEACHTER_ID = #{teachterId,jdbcType=BIGINT},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(THomeworkScore record);

    @SelectProvider(type=com.turingmaker.dao.mapper.teachter.THomeworkScoreSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") THomeworkScore param);
}
package com.turingmaker.dao.mapper.teachter;

import com.turingmaker.entity.teachter.TWork;
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
2018-07-05 14:58
*/
public interface TWorkMapper {
    @Delete({
        "delete from T_WORK",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_WORK (USER_ID, WORK_DESC, ",
        "WORK_AUTHOR, WORK_NAME, ",
        "WORK_AVATAR, IS_DELETE, ",
        "PROGRAM_ID, STATE, ",
        "CREATE_TIME, UPDATE_TIME)",
        "values (#{userId,jdbcType=BIGINT}, #{workDesc,jdbcType=VARCHAR}, ",
        "#{workAuthor,jdbcType=VARCHAR}, #{workName,jdbcType=VARCHAR}, ",
        "#{workAvatar,jdbcType=VARCHAR}, #{isDelete,jdbcType=INTEGER}, ",
        "#{programId,jdbcType=BIGINT}, #{state,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TWork record);

    @InsertProvider(type=TWorkSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TWork record);

    @Select({
        "select",
        "ID, USER_ID, WORK_DESC, WORK_AUTHOR, WORK_NAME, WORK_AVATAR, IS_DELETE, PROGRAM_ID, ",
        "STATE, CREATE_TIME, UPDATE_TIME",
        "from T_WORK",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="WORK_DESC", property="workDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_AUTHOR", property="workAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_NAME", property="workName", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_AVATAR", property="workAvatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_DELETE", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="PROGRAM_ID", property="programId", jdbcType=JdbcType.BIGINT),
        @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TWork selectByPrimaryKey(Long id);

    @UpdateProvider(type=TWorkSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TWork record);

    @Update({
        "update T_WORK",
        "set USER_ID = #{userId,jdbcType=BIGINT},",
          "WORK_DESC = #{workDesc,jdbcType=VARCHAR},",
          "WORK_AUTHOR = #{workAuthor,jdbcType=VARCHAR},",
          "WORK_NAME = #{workName,jdbcType=VARCHAR},",
          "WORK_AVATAR = #{workAvatar,jdbcType=VARCHAR},",
          "IS_DELETE = #{isDelete,jdbcType=INTEGER},",
          "PROGRAM_ID = #{programId,jdbcType=BIGINT},",
          "STATE = #{state,jdbcType=INTEGER},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TWork record);

    @SelectProvider(type=com.turingmaker.dao.mapper.teachter.TWorkSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TWork param);
}
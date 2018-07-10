package com.turingmaker.dao.mapper.operation;

import com.turingmaker.entity.operation.TArea;
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
2018-07-03 18:38
*/
public interface TAreaMapper {
    @Delete({
        "delete from T_AREA",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into T_AREA (AREA_NAME, AREA_CODE, ",
        "AREA_PID, DISORDER)",
        "values (#{areaName,jdbcType=VARCHAR}, #{areaCode,jdbcType=VARCHAR}, ",
        "#{areaPid,jdbcType=BIGINT}, #{disorder,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(TArea record);

    @InsertProvider(type=TAreaSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(TArea record);

    @Select({
        "select",
        "ID, AREA_NAME, AREA_CODE, AREA_PID, DISORDER",
        "from T_AREA",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="AREA_NAME", property="areaName", jdbcType=JdbcType.VARCHAR),
        @Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="AREA_PID", property="areaPid", jdbcType=JdbcType.BIGINT),
        @Result(column="DISORDER", property="disorder", jdbcType=JdbcType.INTEGER)
    })
    TArea selectByPrimaryKey(Long id);

    @UpdateProvider(type=TAreaSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TArea record);

    @Update({
        "update T_AREA",
        "set AREA_NAME = #{areaName,jdbcType=VARCHAR},",
          "AREA_CODE = #{areaCode,jdbcType=VARCHAR},",
          "AREA_PID = #{areaPid,jdbcType=BIGINT},",
          "DISORDER = #{disorder,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TArea record);

    @SelectProvider(type=com.turingmaker.dao.mapper.operation.TAreaSqlProvider.class,method="selectForCount")
    int selectForCount(@Param("param") TArea param);
}
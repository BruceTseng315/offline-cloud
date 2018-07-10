package com.turingmaker.dao.mapper.operation.ext;

import com.turingmaker.dao.mapper.operation.TAreaMapper;
import com.turingmaker.entity.operation.TArea;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TAreaMapperExt extends TAreaMapper {

    @Select("SELECT * FROM T_AREA WHERE AREA_PID=100000")
    @Results({
            @Result(column="ID", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="AREA_NAME", property="areaName", jdbcType=JdbcType.VARCHAR),
            @Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="AREA_PID", property="areaPid", jdbcType=JdbcType.BIGINT),
            @Result(column="DISORDER", property="disorder", jdbcType=JdbcType.INTEGER)
    })
    List<TArea> findProvinceName();

    @Select("select * from T_AREA where AREA_PID = #{arg0}")
    @Results({
            @Result(column="ID", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="AREA_NAME", property="areaName", jdbcType=JdbcType.VARCHAR),
            @Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="AREA_PID", property="areaPid", jdbcType=JdbcType.BIGINT),
            @Result(column="DISORDER", property="disorder", jdbcType=JdbcType.INTEGER)
    })
    List<TArea> findAreaNameByPid(Long pid);

}

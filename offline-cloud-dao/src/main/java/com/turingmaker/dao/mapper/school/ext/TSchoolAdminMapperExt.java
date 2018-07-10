package com.turingmaker.dao.mapper.school.ext;

import com.turingmaker.dao.mapper.school.TSchoolAdminMapper;
import com.turingmaker.entity.school.TSchoolAdmin;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface TSchoolAdminMapperExt extends TSchoolAdminMapper {

    @Select("select * from T_SCHOOL_ADMIN where SCHOOL_ID = #{arg0,jdbcType=BIGINT}")
    @Results(id="resultMap",value={
            @Result(column="ID", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="PHONE", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="SCHOOL_ID", property="schoolId", jdbcType=JdbcType.BIGINT),
            @Result(column="ACCOUNT_ID", property="accountId", jdbcType=JdbcType.BIGINT)
    })
    TSchoolAdmin findBySchoolId(Long schoolId);

    @Select("select * from T_SCHOOL_ADMIN where ACCOUNT_ID = #{arg0,jdbcType=BIGINT}")
    @ResultMap("resultMap")
    TSchoolAdmin findByAccountId(Long accountId);
}

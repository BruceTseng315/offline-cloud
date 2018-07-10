package com.turingmaker.dao.mapper.school.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;

import com.turingmaker.dao.mapper.school.TSchoolMapper;
import com.turingmaker.entity.school.TSchool;
import com.turingmaker.service.exception.ServiceException;
import com.turingmaker.service.operation.entity.QuerySchoolResult;
import com.turingmaker.service.operation.response.Pageinfo;

public interface TSchoolMapperExt extends TSchoolMapper {
    @SelectProvider(type=SchoolSqlProvider.class,method="selectForListSchool")
    @Results(id="resultMap", value={
            @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="SCHOOL_CODE", property="schoolCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="SCHOOL_TYPE", property="schoolType", jdbcType=JdbcType.INTEGER),
            @Result(column="SCHOOL_NAME", property="schoolName", jdbcType=JdbcType.VARCHAR),
            @Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.BIGINT),
            @Result(column="ADDRESS", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="CONTACT_PHONE", property="contactPhone", jdbcType=JdbcType.VARCHAR),
            @Result(column="CONTACT_NAME", property="contactName", jdbcType=JdbcType.VARCHAR),
            @Result(column="CHANNEL_NAME", property="channelName", jdbcType=JdbcType.VARCHAR),
            @Result(column="CHANNEL_PHONE", property="channelPhone", jdbcType=JdbcType.VARCHAR)
    })
    @Deprecated
    List<TSchool> selectForListSchoolPage( Pageinfo pageinfo , @Param("param1")String schoolCode, @Param("param2")String schoolName, @Param("param3")String adminName, @Param("param4")Integer regionCode, @Param("param5")Byte type);

    
    
    /**
     * 查询一页数据
     * @param pageinfo
     * @param schoolCode
     * @param schoolName
     * @param adminName
     * @param regionCode
     * @param type
     * @return
     */
    @SelectProvider(type=SchoolSqlProvider.class,method="selectForListSchoolPageNew")
    @Results(id="resultMapQuerySchool", value={
            @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="SCHOOL_CODE", property="schoolCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="SCHOOL_TYPE", property="schoolType", jdbcType=JdbcType.INTEGER),
            @Result(column="SCHOOL_NAME", property="schoolName", jdbcType=JdbcType.VARCHAR),
            @Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.BIGINT),
            @Result(column="ADDRESS", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="CONTACT_PHONE", property="contactPhone", jdbcType=JdbcType.VARCHAR),
            @Result(column="CONTACT_NAME", property="contactName", jdbcType=JdbcType.VARCHAR),
            @Result(column="CHANNEL_NAME", property="channelName", jdbcType=JdbcType.VARCHAR),
            @Result(column="CHANNEL_PHONE", property="channelPhone", jdbcType=JdbcType.VARCHAR),
        
            @Result(column="CLASSCOUNT", property="classCount", jdbcType=JdbcType.BIGINT),
            @Result(column="TEACHTERCOUNT", property="teacherCount", jdbcType=JdbcType.BIGINT),
            @Result(column="STUDENTCOUNT", property="studentCount", jdbcType=JdbcType.BIGINT),
            
            @Result(column="NAME", property="adminname", jdbcType=JdbcType.VARCHAR),
            @Result(column="PHONE", property="adminphone", jdbcType=JdbcType.VARCHAR),
            @Result(column="COURSE_NAME", property="courseName", jdbcType=JdbcType.VARCHAR),
            
            @Result(column="AREA_NAME", property="areaName", jdbcType=JdbcType.VARCHAR),
            
    })
    List<QuerySchoolResult> selectForListSchoolPageNew(List<Long> ids, @Param("param1")String schoolCode, @Param("param2")String schoolName, @Param("param3")String adminName, @Param("param4")Integer regionCode, @Param("param5")Byte type);
    
    
    /**
     * 自己查询查询总也数
     * @param pageinfo
     * @param schoolCode
     * @param schoolName
     * @param adminName
     * @param regionCode
     * @param type
     * @return
     */
    @SelectProvider(type=SchoolSqlProvider.class,method="selectListSchoolNewCalPageTotal")
    Long selectListSchoolNewCalPageTotal( Pageinfo pageinfo , @Param("param1")String schoolCode, @Param("param2")String schoolName, @Param("param3")String adminName, @Param("param4")Integer regionCode, @Param("param5")Byte type);
    
    
    
  /**
   * 根据条件查询出ids
   * @param pageinfo
   * @param schoolCode
   * @param schoolName
   * @param adminName
   * @param regionCode
   * @param type
   * @return
   */
    @SelectProvider(type=SchoolSqlProvider.class,method="selectIdsForListSchool")
    List<Long> selectIdsForListSchool( Pageinfo pageinfo , @Param("param1")String schoolCode, @Param("param2")String schoolName, @Param("param3")String adminName, @Param("param4")Integer regionCode, @Param("param5")Byte type);
    
    /**
     *根据学校Id查询老师数量
     *
     */
//    @Select("select count(*) from T_CLASS_COURSE_TEACHER where CLASS_ID in (select ID from T_CLASS where SCHOOL_ID = #{arg0})")
    @Select("select count(*) from T_CLASS where SCHOOL_ID = #{arg0}")
    Long fingTeacherCountBySchoolId(Long schoolId) throws ServiceException;

    /**
     *根据学校Id查询学生数量
     *
     */
    @Select("select count(*) from T_CLASS_STUDENT where CLASS_ID in (select ID from T_CLASS where SCHOOL_ID = #{arg0})")
    Long fingStudentCountBySchoolId(Long schoolId) throws ServiceException;

    /**
     *根据学校Id查询班级数量
     *
     */
    @Select("select count(*) from T_CLASS where SCHOOL_ID = #{arg0}")
    Long fingClassCountBySchoolId(Long schoolId) throws ServiceException;
}

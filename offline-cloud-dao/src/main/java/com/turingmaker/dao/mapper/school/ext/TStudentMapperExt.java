package com.turingmaker.dao.mapper.school.ext;



import java.util.List;

import com.turingmaker.entity.operation.YUser;
import com.turingmaker.service.school.bo.StudentBo;
import com.turingmaker.service.teachter.entity.StudentStatistics;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import com.turingmaker.dao.mapper.school.TStudentMapper;
import com.turingmaker.entity.school.TStudent;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.school.entity.QueryStudentExportAccount;
import com.turingmaker.service.school.request.StudentListRequest;

public interface TStudentMapperExt extends TStudentMapper {

    @SelectProvider(type=StudentSqlProvider.class,method="getStudentDetailsListPage")
    @Results(id="resultMap",value={
            @Result(column="ID", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="STUDENT_CODE", property="studentCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="STUDENT_NAME", property="studentName", jdbcType=JdbcType.VARCHAR),
            @Result(column="STUDENT_NUMBER", property="studentNumber", jdbcType=JdbcType.VARCHAR),
            @Result(column="IDENTIFIER", property="identifier", jdbcType=JdbcType.VARCHAR),
            @Result(column="GUARDER_NAME", property="guarderName", jdbcType=JdbcType.VARCHAR),
            @Result(column="GUARDER_PHONE", property="guarderPhone", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="ACCOUNT_ID", property="accountId", jdbcType=JdbcType.BIGINT)
    })
    List<TStudent> findStudentsListBySchoolIdPage(Pageinfo pageInfo, long schoolId,@Param("request") StudentListRequest request);

    @SelectProvider(type=StudentSqlProvider.class,method="getStudentDetailsListNewPage")
    @Results({
            @Result(column="ID", property="studentId", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="STUDENT_CODE", property="studentCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="STUDENT_NAME", property="studentName", jdbcType=JdbcType.VARCHAR),
            @Result(column="STUDENT_NUMBER", property="studentNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="IDENTIFIER", property="identifier", jdbcType=JdbcType.VARCHAR),
            @Result(column="GUARDER_NAME", property="guarderName", jdbcType=JdbcType.VARCHAR),
            @Result(column="GUARDER_PHONE", property="guarderPhone", jdbcType=JdbcType.VARCHAR),
            //@Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
           // @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="ACCOUNT_ID", property="accountId", jdbcType=JdbcType.BIGINT),
            @Result(column = "ACCOUNT_NAME",property = "accountName",jdbcType=JdbcType.VARCHAR),
            @Result(column = "PASSWORD",property = "password",jdbcType=JdbcType.VARCHAR),
            @Result(property = "classes",column = "ID",javaType = List.class,many=@Many(select = "com.turingmaker.dao.mapper.school.ext.TClassMapperExt.selectByStudentId"))
    })
    List<StudentBo> findStudentListBySchoolNewPage(Pageinfo pageinfo,long schoolId,@Param("request")StudentListRequest request);
    /**
     * 查找学校里指定姓名的学生
     */
    @Select("select * from T_STUDENT where STUDENT_NAME=#{arg1} and ID in" +  // 指定名字的学生
            " (select STUDENT_ID from T_CLASS_STUDENT where CLASS_ID in" +  //学校里所有学生
            " (select ID from T_CLASS where SCHOOL_ID=#{arg0}) )")  //学校里所有班级
    @ResultMap("resultMap")
    List<TStudent> findStudentsBySchoolIdAndStudentName(long schoolId,String studentName);

    @Select("select * from T_STUDENT where id in (select STUDENT_ID from T_CLASS_STUDENT where CLASS_ID =#{arg1})")
    @ResultMap("resultMap")
    List<TStudent> findStudentListByClassIdPage(Pageinfo pageinfo,long classId);
    
    
    
    @Select("select ts.`STUDENT_NAME`,tu.`ACCOUNT` ,tu.`PASSWORD`,tcs.`ID`   from T_STUDENT  ts" + 
    		" left join T_CLASS_STUDENT tcs" + 
    		" on  ts.`ID` =tcs.`STUDENT_ID`" + 
    		" left  JOIN `Y_USER` tu" + 
    		" on  tu.`ID` =ts.`ACCOUNT_ID` " + 
    		" where  CLASS_ID =#{arg0}")
    @Results({
    		   @Result(column="STUDENT_NAME", property="studentName", jdbcType=JdbcType.VARCHAR),
               @Result(column="ACCOUNT", property="studentAccount", jdbcType=JdbcType.VARCHAR),
               @Result(column="PASSWORD", property="studentPassword", jdbcType=JdbcType.VARCHAR)
    })
    List<QueryStudentExportAccount>  findStudentListByClassIdForExport(Long classId);

    @Select({"select",
            "ID, STUDENT_CODE, STUDENT_NAME, STUDENT_NUMBER, IDENTIFIER, GUARDER_NAME, GUARDER_PHONE, ",
            "CREATE_TIME, UPDATE_TIME, ACCOUNT_ID, SCHOOL_ID",
            "from T_STUDENT",
            "where ACCOUNT_ID = #{arg0}"
    })
    @ResultMap("resultMap")
    TStudent findStudentByAccountId(Long accountId);


    /**
     * 查看学生账号密码
     */
    @Results({
            @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="ACCOUNT", property="account", jdbcType=JdbcType.VARCHAR),
            @Result(column="PASSWORD", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    @Select("SELECT tu.*  FROM  `T_STUDENT`  ts  LEFT JOIN  `Y_USER`  tu on ts.`ACCOUNT_ID` =tu.`ID`   where ts.id=#{arg0}")
    YUser  findStudentAccountAndPassword(Long studentid);


    /**
     *
     * 查询班级的学生统计信息
     */
    @Results({
            @Result(column = "ID", property = "studentId", jdbcType = JdbcType.BIGINT),
            @Result(column = "STUDENT_NAME", property = "studentName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WORK_COUNT", property = "workCount", jdbcType = JdbcType.BIGINT),
            @Result(column = "HOMEWORK_COUNT", property = "homeWorkCount", jdbcType = JdbcType.BIGINT),
            @Result(column = "HOMEWORK_MARK_COUNT", property = "noCorrectCount", jdbcType = JdbcType.BIGINT),
    })
    @Select("select  ts.id,ts.`STUDENT_NAME` , " +
            "(select count(0)  from  `T_HOMEWORK` th  WHERE th.`STUDENT_ID` =ts.`ID` ) as HOMEWORK_COUNT , " +
            "(select count(0)  from  `T_HOMEWORK` th  WHERE th.`STUDENT_ID` =ts.`ID` and th.`MARK` =0 ) as HOMEWORK_MARK_COUNT , " +
            "(select count(0)  from  `T_WORK`    where user_id=ts.`ACCOUNT_ID` ) as WORK_COUNT " +
            "from   `T_CLASS_STUDENT`   tcs  LEFT JOIN   `T_STUDENT`  ts on  tcs.`STUDENT_ID` =ts.`ID` " +
            "WHERE tcs.`CLASS_ID` =#{arg1} ")
    List<StudentStatistics>  findStudentStatisticsInfoPage(Pageinfo pageinfo,Long classId);


}

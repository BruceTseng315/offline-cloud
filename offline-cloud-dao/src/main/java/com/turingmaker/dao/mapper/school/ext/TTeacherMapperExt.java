package com.turingmaker.dao.mapper.school.ext;

import java.util.List;

import com.turingmaker.service.school.bo.TeacherSelectAss;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import com.turingmaker.dao.mapper.school.TTeacherMapper;
import com.turingmaker.entity.school.TTeacher;
import com.turingmaker.service.operation.entity.QueryTeachterResult;
import com.turingmaker.service.operation.response.Pageinfo;

public interface TTeacherMapperExt extends TTeacherMapper {

    @Select("select * from T_TEACHER where SCHOOL_ID = #{arg1}")
    @Results(id="resultMap",value={
            @Result(column="ID", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="TEACHER_CODE", property="teacherCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="TEACHER_NAME", property="teacherName", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="ACCOUNT_ID", property="accountId", jdbcType=JdbcType.BIGINT),
            @Result(column="PHONE", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="SCHOOL_ID", property="schoolId", jdbcType=JdbcType.BIGINT)
    })
    List<TTeacher> findAllBySchoolId(Pageinfo pageinfo ,Long schoolId);

    @SelectProvider(type=TeacherSqlProvider.class,method="selectForListTeacher")
    @ResultMap("resultMap")
    List<TTeacher> selectForListTeacherPage(Pageinfo pageinfo ,@Param("param0") Long schoolId, @Param("param1")String teacherCode, @Param("param2")String teacherName, @Param("param3")String className, @Param("param4")Long courseId);

    


    @SelectProvider(type=TeacherSqlProvider.class,method="selectForListTeacherNew")
    @Results({
            @Result(column="ID", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="TEACHER_CODE", property="teacherCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="TEACHER_NAME", property="teacherName", jdbcType=JdbcType.VARCHAR),
            @Result(column="CASS_ID", property="classId", jdbcType= JdbcType.BIGINT),
            @Result(column="COURSE_ID", property="courseId", jdbcType= JdbcType.BIGINT),
            
            @Result(column="CLASS_NAME", property="className", jdbcType= JdbcType.VARCHAR),
            @Result(column="COURSE_NAME", property="courseName", jdbcType= JdbcType.BIGINT),
            
            @Result(column="ACCOUNT", property="account", jdbcType= JdbcType.BIGINT),
    })
    List<QueryTeachterResult> selectForListTeacherNewPage(Pageinfo pageinfo , @Param("param1")String teacherCode, @Param("param2")String teacherName, @Param("param3")Long classId, @Param("param4")String courseCode);


    @SelectProvider(type=TeacherSqlProvider.class,method="selectTeacherForExport")
    @Results({
            @Result(column = "ID",property = "teacherId",jdbcType = JdbcType.BIGINT,id=true),
            @Result(column = "TEACHER_CODE",property = "teacherNo",jdbcType = JdbcType.VARCHAR),
            @Result(column = "TEACHER_NAME",property = "teacherName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "ACCOUNT",property = "account",jdbcType = JdbcType.VARCHAR),
            @Result(column = "PASSWORD",property = "password",jdbcType = JdbcType.VARCHAR),
            @Result(property = "classes",column = "ID",javaType = List.class,many=@Many(select = "com.turingmaker.dao.mapper.school.ext.TClassCourseTeacherMapperExt.findClassesByTeacherId")),
            @Result(property = "courses",column = "ID",javaType = List.class,many=@Many(select = "com.turingmaker.dao.mapper.school.ext.TClassCourseTeacherMapperExt.findCoursesByTeacherId"))
    })
    List<TeacherSelectAss> selectTeacherForExport(@Param("param0") Long schoolId, @Param("param1")String teacherCode, @Param("param2")String teacherName, @Param("param3")String className, @Param("param4")Long courseId);


    @SelectProvider(type=TeacherSqlProvider.class,method="selectTeacherListAssPage")
    @Results({
            @Result(column = "ID",property = "teacherId",jdbcType = JdbcType.BIGINT,id=true),
            @Result(column = "TEACHER_CODE",property = "teacherCode",jdbcType = JdbcType.VARCHAR),
            @Result(column = "TEACHER_NAME",property = "teacherName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "ACCOUNT",property = "account",jdbcType = JdbcType.VARCHAR),
            @Result(column = "PASSWORD",property = "password",jdbcType = JdbcType.VARCHAR),
            @Result(property = "classes",column = "ID",javaType = List.class,many=@Many(select = "com.turingmaker.dao.mapper.school.ext.TClassCourseTeacherMapperExt.findClassesByTeacherId")),
            @Result(property = "courses",column = "ID",javaType = List.class,many=@Many(select = "com.turingmaker.dao.mapper.school.ext.TClassCourseTeacherMapperExt.findCoursesByTeacherId"))
    })
    List<TeacherSelectAss> selectTeacherListAssPage(Pageinfo pageinfo , @Param("param0")Long schoolId,@Param("param1")String teacherCode, @Param("param2")String teacherName, @Param("param3")String className, @Param("param4")Long courseId);
    
    
    
    @Select("select tt.*,tu.`ACCOUNT`   from `T_TEACHER`  tt  LEFT JOIN  `Y_USER`  tu on  tt.`ACCOUNT_ID` =tu.`ID` " + 
    		" where  tu.`ACCOUNT` =#{arg0}")
    @ResultMap("resultMap")
    TTeacher selectTeachterByAccountName(String username); 
    
}

package com.turingmaker.dao.mapper.school.ext;

import com.turingmaker.service.operation.response.Pageinfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class TeacherSqlProvider {
    public String selectForListTeacher(Pageinfo pageinfo ,@Param("param0")Long schoolId, @Param("param1")String teacherCode, @Param("param2")String teacherName, @Param("param3")String className, @Param("param4")Long courseId) {
        SQL sql = new SQL();
        sql.SELECT("t1.*");
        sql.FROM("T_TEACHER t1");

        sql.WHERE("SCHOOL_ID = #{param0}");

        if(!StringUtils.isEmpty(teacherCode)) {
            sql.WHERE("t1.TEACHER_CODE like concat('%',#{param1},'%')");
        }


        if(!StringUtils.isEmpty(teacherName)) {
            sql.WHERE("t1.TEACHER_NAME like CONCAT('%',#{param2},'%')");

        }

        if(!StringUtils.isEmpty(className)) {
            sql.WHERE("t1.ID in (select TEACHER_ID from T_CLASS_COURSE_TEACHER where CLASS_ID in (select ID from T_CLASS where CLASS_NAME like CONCAT('%',#{param3},'%')))");

        }

        if(!StringUtils.isEmpty(courseId)) {
            sql.WHERE("t1.ID in (select TEACHER_ID from T_CLASS_COURSE_TEACHER where COURSE_ID=#{param4})");


        }


        return sql.toString();
    }


    public String selectTeacherForExport(@Param("param0")Long schoolId,@Param("param1")String teacherCode, @Param("param2")String teacherName, @Param("param3")String className, @Param("param4")Long courseId) {
        StringBuilder builder = new StringBuilder("select tt.ID as ID,TEACHER_CODE,TEACHER_NAME,ACCOUNT,PASSWORD");
        builder.append(" from T_TEACHER tt,Y_USER yu where tt.ACCOUNT_ID=yu.ID");
        builder.append(" and tt.SCHOOL_ID=#{param0}");

        if (!StringUtils.isEmpty(teacherCode)) {
            builder.append(" AND tt.TEACHER_CODE like CONCAT('%',#{param1},'%')");
        }

        if (!StringUtils.isEmpty(teacherName)) {
            builder.append(" AND tt.TEACHER_NAME like CONCAT('%',#{param2},'%')");

        }

        if (!StringUtils.isEmpty(className)) {
            builder.append(" AND tt.ID in (select TEACHER_ID from T_CLASS_COURSE_TEACHER where CLASS_ID in (select ID from T_CLASS where CLASS_NAME like CONCAT('%',#{param3},'%')))");

        }

        if (!StringUtils.isEmpty(courseId)) {
            builder.append(" AND tt.ID in (select TEACHER_ID from T_CLASS_COURSE_TEACHER where COURSE_ID=#{param4})");

        }

        return builder.toString();
    }
    

    

    public String selectForListTeacherNew(Pageinfo pageinfo, @Param("param1") String teacherCode,

			@Param("param2") String teacherName, @Param("param3") Long classId, @Param("param4") String courseCode) {

		/**
		 * select tt.`TEACHER_CODE` ,tt.`TEACHER_NAME` ,tcct.`CLASS_ID`
		 * ,tcct.`COURSE_ID`,tc.`COURSE_NAME` ,tclass.`CLASS_NAME`,tuser.`ACCOUNT` from
		 * T_TEACHER tt LEFT JOIN `T_CLASS_COURSE_TEACHER` tcct ON tt.`ID`
		 * =tcct.`TEACHER_ID`
		 * 
		 * LEFT JOIN `T_COURSE` tc on tcct.`COURSE_ID`=tc.id
		 * 
		 * LEFT JOIN `T_CLASS` tclass on tclass.`ID` =tcct.`CLASS_ID`
		 * 
		 * LEFT JOIN `Y_USER` tuser on tuser.`ID` =tt.`ACCOUNT_ID`
		 */

		StringBuilder builder = new StringBuilder(
				"select tt.ID, tt.`TEACHER_CODE` ,tt.`TEACHER_NAME` ,tcct.`CLASS_ID` ,tcct.`COURSE_ID`,tc.`COURSE_NAME` ,tclass.`CLASS_NAME`,tuser.`ACCOUNT`      from T_TEACHER  tt LEFT JOIN `T_CLASS_COURSE_TEACHER` tcct  ON  tt.`ID` =tcct.`TEACHER_ID` ");
		builder.append(" LEFT JOIN  `T_COURSE`  tc on tcct.`COURSE_ID`=tc.id");
		builder.append(" LEFT JOIN `T_CLASS` tclass on tclass.`ID` =tcct.`CLASS_ID` ");
		builder.append(" LEFT JOIN  `Y_USER` tuser on tuser.`ID` =tt.`ACCOUNT_ID` ");

		builder.append("WHERE  1=1 ");
		if (!StringUtils.isEmpty(teacherCode)) {
			builder.append(" AND t1.TEACHER_CODE like CONCAT('%',#{param1},'%')");
		}

		if (!StringUtils.isEmpty(teacherName)) {
			builder.append(" AND t1.TEACHER_NAME like CONCAT('%',#{param2},'%')");

		}

		if (!StringUtils.isEmpty(classId)) {
			builder.append(" AND t2.ID = CONCAT('%',#{param3},'%')");

		}

		if (!StringUtils.isEmpty(courseCode)) {
			builder.append(" AND t3.COURSE_CODE like CONCAT('%',#{param4},'%')");

		}

		return builder.toString();
	}


    public String selectTeacherListAssPage(Pageinfo pageinfo , @Param("param0")Long schoolId,@Param("param1")String teacherCode, @Param("param2")String teacherName, @Param("param3")String className, @Param("param4")Long courseId){
        StringBuilder builder = new StringBuilder("select tt.ID as ID,TEACHER_CODE,TEACHER_NAME,ACCOUNT,PASSWORD,tt.UPDATE_TIME");
        builder.append(" from T_TEACHER tt,Y_USER yu where tt.ACCOUNT_ID=yu.ID");
        builder.append(" and tt.SCHOOL_ID=#{param0}");

        if (!StringUtils.isEmpty(teacherCode)) {
            builder.append(" AND tt.TEACHER_CODE like CONCAT('%',#{param1},'%')");
        }

        if (!StringUtils.isEmpty(teacherName)) {
            builder.append(" AND tt.TEACHER_NAME like CONCAT('%',#{param2},'%')");

        }

        if (!StringUtils.isEmpty(className)) {
            builder.append(" AND tt.ID in (select TEACHER_ID from T_CLASS_COURSE_TEACHER where CLASS_ID in (select ID from T_CLASS where CLASS_NAME like CONCAT('%',#{param3},'%')))");

        }

        if (!StringUtils.isEmpty(courseId)) {
            builder.append(" AND tt.ID in (select TEACHER_ID from T_CLASS_COURSE_TEACHER where COURSE_ID=#{param4})");

        }
        builder.append(" order by tt.UPDATE_TIME desc");

        return builder.toString();
    }
}

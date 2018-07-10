package com.turingmaker.dao.mapper.school.ext;

import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.school.request.StudentListRequest;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class StudentSqlProvider {
    public String getStudentDetailsListPage(Pageinfo pageinfo,long schoolId,@Param("request") StudentListRequest request){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("T_STUDENT");


        String sqlClass = null;
        //schoolId, class_name, student_code, student_name
        if(!StringUtils.isEmpty(request.getClassName())) {
            sqlClass = "ID in (select STUDENT_ID from T_CLASS_STUDENT where CLASS_ID in  (select ID from T_CLASS where SCHOOL_ID=#{arg1} and CLASS_NAME like #{request.className}) )";
        }else{
            sqlClass =  "ID in (select STUDENT_ID from T_CLASS_STUDENT where CLASS_ID in  (select ID from T_CLASS where SCHOOL_ID=#{arg1}) )";
        }
        sql.WHERE(sqlClass);

        if(!StringUtils.isEmpty(request.getStudentCode())) {
            sql.WHERE("STUDENT_CODE like #{request.studentCode}");
        }

        if(!StringUtils.isEmpty(request.getStudentName())) {
            sql.WHERE("STUDENT_NAME like CONCAT('%',#{request.studentName},'%')");
        }


        return sql.toString();
    }

    public String getStudentDetailsListNewPage(Pageinfo pageinfo,long schoolId,@Param("request") StudentListRequest request){
        StringBuilder builder = new StringBuilder();
        builder.append("select ts.*,yu.ACCOUNT as ACCOUNT_NAME,yu.PASSWORD,yu.ID as XXID from T_STUDENT ts,Y_USER yu where ");

        builder.append(" ts.ACCOUNT_ID=yu.ID and ts.SCHOOL_ID=#{arg1}");
        String sqlClass = null;
        //schoolId, class_name, student_code, student_name
        if(!StringUtils.isEmpty(request.getClassName())) {
            sqlClass = " and ts.ID in (select STUDENT_ID from T_CLASS_STUDENT where CLASS_ID in  (select ID from T_CLASS where CLASS_NAME like CONCAT('%',#{request.className},'%')) )";
            builder.append(sqlClass);
        }

        if(!StringUtils.isEmpty(request.getStudentCode())) {
            builder.append(" and STUDENT_CODE like CONCAT('%',#{request.studentCode},'%')");
        }

        if(!StringUtils.isEmpty(request.getStudentName())) {
            builder.append(" and STUDENT_NAME like CONCAT('%',#{request.studentName},'%')");
        }

        builder.append(" order by UPDATE_TIME desc");

        return builder.toString();
    }
}

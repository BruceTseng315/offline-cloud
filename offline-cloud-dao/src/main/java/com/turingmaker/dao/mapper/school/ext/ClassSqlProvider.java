package com.turingmaker.dao.mapper.school.ext;

import com.turingmaker.service.operation.request.ClassListRequest;
import com.turingmaker.service.operation.response.Pageinfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class ClassSqlProvider {
    public String selectClassListPage(Pageinfo pageinfo, @Param("schoolId") Long schoolId,@Param("classListRequest") ClassListRequest classListRequest){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("T_CLASS");
        sql.WHERE("SCHOOL_ID=#{schoolId}");

        //classCode,className,grade,courseName
        if(!StringUtils.isEmpty(classListRequest.getClassCode())){
            sql.WHERE("CLASS_CODE like CONCAT('%',#{classListRequest.classCode},'%')");
        }

        if(!StringUtils.isEmpty(classListRequest.getClassName())){
            sql.WHERE("CLASS_NAME like CONCAT('%',#{classListRequest.className},'%' )");
        }

        if(!StringUtils.isEmpty((classListRequest.getCourseId()))) {
            sql.WHERE("ID in (select CLASS_ID from T_CLASS_COURSE_TEACHER where COURSE_ID=#{classListRequest.courseId})");
        }
        if(classListRequest.getGrade() != null){
            sql.WHERE("GRADE = #{classListRequest.grade}");
        }
        if(!StringUtils.isEmpty(classListRequest.getClassType())){
            sql.WHERE("CLASS_TYPE=#{classListRequest.classType}");
        }

        sql.ORDER_BY("UPDATE_TIME desc");
        return sql.toString();
    }
}

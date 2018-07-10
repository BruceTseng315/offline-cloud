package com.turingmaker.dao.mapper.operation.ext;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.turingmaker.entity.operation.TCourse;
import com.turingmaker.service.operation.response.Pageinfo;

/**
 * 
 * @author tzj
 *
 */
public class CourseSqlProvider {

	
	 public String selectForListCourse(@Param("course") TCourse record) {
	        SQL sql = new SQL();
	        sql.SELECT("tc.*,(SELECT COUNT(0) FROM T_LESSON tl where tl.`COURSE_ID` =tc.`ID`  ) as upload_lessons");
	        sql.FROM("T_COURSE tc");
	      
	        //COURSE_CODE, COURSE_NAME, COURSE_DESCRIPTION, COURSE_AVATAR, COURSE_STATE
	        if(!StringUtils.isEmpty(record.getCourseCode())) {
	        	record.setCourseCode("%"+record.getCourseCode()+"%");
	        	sql.WHERE("COURSE_CODE like #{course.courseCode}");
	        }
	        
	        if(!StringUtils.isEmpty(record.getCourseName())) {
	        	record.setCourseName("%"+record.getCourseName()+"%");
	        	sql.WHERE("COURSE_NAME like #{course.courseName}");
	        }
	        
	        if(record.getCourseState()!=null&&record.getCourseState()>=0) {
	        	sql.WHERE("COURSE_STATE=#{course.courseState}");
	        }
	        
	        sql.ORDER_BY("UPDATE_TIME desc");
	        return sql.toString();
	    }
	 
	 
	 public String selectForListCoursePage(Pageinfo pageinfo, @Param("course") TCourse record) {
	        
	        return selectForListCourse(record);
	    }


}

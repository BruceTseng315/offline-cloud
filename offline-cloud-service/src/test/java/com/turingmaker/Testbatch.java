package com.turingmaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.turingmaker.dao.mapper.operation.ext.CourseMapperExt;
import com.turingmaker.dao.mapper.school.ext.TStudentMapperExt;
import com.turingmaker.entity.operation.TCourse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CloudApplication.class)
public class Testbatch {


//	@Autowired
//	CourseMapperExt courseMapperExt;
//	
//	@Test
//	@Transactional
//	public void  testBatchInsert() {
//		
//		long l1=System.currentTimeMillis();
//		System.out.println("开始执行"+l1);
//		
//		for (int i = 0; i < 100; i++) {
//			
//			TCourse course=new TCourse();
//			course.setCourseAvatar("::void();");
//			course.setCourseCode(""+System.currentTimeMillis());
//			course.setCourseDescription("描述");
//			course.setCourseState((byte)1);
//			course.setLessonNum(10);
//			courseMapperExt.insert(course);
//		}
//		
//		
//		long l2=System.currentTimeMillis();
//		System.out.println("结束执行"+l2);
//
//		System.out.println("-----------执行时间"+(+l2-l1));
//	}
//	
//	@Autowired
//	DataSource dataSource;
//	
//	
//	@Test
//	public void testBatchCon() throws SQLException {
//		
//		
//		long l1=System.currentTimeMillis();
//		System.out.println("开始执行"+l1);
//		
//		Connection connection=dataSource.getConnection();
//		connection.setAutoCommit(false);
//		PreparedStatement preparedStatement=
//				connection.prepareStatement("insert into T_COURSE (COURSE_CODE, COURSE_DESCRIPTION,COURSE_AVATAR,COURSE_NAME,COURSE_STATE,LESSON_NUM ) VALUES(?,?,?,?,?,?)");
//		
//		for (int i = 0; i < 1000; i++) {
//			
//			
//			preparedStatement.setString(1, ""+System.currentTimeMillis());
//			preparedStatement.setString(2, "描述");
//			preparedStatement.setString(3, "::void();");
//			preparedStatement.setString(4, "python入门");
//			preparedStatement.setInt(5, 1);
//			preparedStatement.setInt(6, 10);
//		
//			preparedStatement.addBatch();
//		}
//		preparedStatement.executeBatch();
//		connection.commit();
//		
//		long l2=System.currentTimeMillis();
//		System.out.println("结束执行"+l2);		
//		System.out.println("-----------执行时间"+(+l2-l1));
//	}
	
	  @Autowired
	    TStudentMapperExt studentMapperExt;
	    
	  
	  @Test
	  public void test() {
		  
		  System.out.println(studentMapperExt.findStudentListByClassIdForExport(10l));
	  }
}

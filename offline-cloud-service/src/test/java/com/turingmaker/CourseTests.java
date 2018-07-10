package com.turingmaker;

import com.turingmaker.service.operation.api.LessonService;
import com.turingmaker.service.operation.request.SaveCourseRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.turingmaker.service.operation.api.CourseService;
import com.turingmaker.service.operation.response.Pageinfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CloudApplication.class)
public class CourseTests {

	
	@Autowired
	CourseService courseService;

	@Autowired
	LessonService lessonService;
	
//	@Test
//	public void testadd() {
//
//		SaveCourseRequest courseRequest=new SaveCourseRequest();
//		courseRequest.setCourseAvatar("1aaa2ssss3321");
////		courseRequest.setCourseCode("junit");
//		courseRequest.setCourseDescription("课程简介");
//		courseRequest.setCourseName("scratch入门");
////		courseRequest.setCourseState(1);
//		courseRequest.setLessonNum(10);
//		courseService.saveCourse(courseRequest);
//		System.out.println();
//	}
//	
//	
//	@Test
//	public void testedit() {
//		
//		SaveCourseRequest courseRequest=new SaveCourseRequest();
//		courseRequest.setCourseAvatar("1111");
//		courseRequest.setCourseCode("code11101");
//		courseRequest.setCourseDescription("课程简介");
//		courseRequest.setCourseName("scratch入门2");
//		courseRequest.setCourseState(1);
//		courseRequest.setLessonNum(10);
//		courseRequest.setId(100011l);
//		courseService.saveCourse(courseRequest);
//		System.out.println();
//	}
	
	@Test
	public void testall() {
		
		System.out.println(courseService.listCoursesPage(new Pageinfo(1,10), "code11","scratch",(byte) -1));
		
		//System.out.println(courseService.listCourses("","%python%",(byte) -1));
	}
//
//	
//
//	
//
//	@Test
//	public void testUpdate() {
//		
//		courseService.updateCourseState(100004l, 0);
//	}
//	
	//@Test
	public void testDetail() {
		long l1=System.currentTimeMillis();
		while(true) {
			long l2=System.currentTimeMillis();
			
			if((l2-l1)/(1000*60*60*24)>1)  break;
		}
	}
	@Test
	public void testfindAllCourseByTeacherId(){
//		System.out.println(courseService.findAllCourseByTeacherId(33L));
		System.out.println(lessonService.getInfoListByCourseId(105625L));
	}
}

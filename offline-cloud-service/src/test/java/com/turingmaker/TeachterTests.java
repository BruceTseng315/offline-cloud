package com.turingmaker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.turingmaker.service.school.api.TeacherService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CloudApplication.class)
public class TeachterTests {

	
	@Autowired
	TeacherService teacherService;
	
	@Test
	public void test1() {
		

	}
}

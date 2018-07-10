package com.turingmaker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.turingmaker.dao.mapper.school.ext.TSchoolMapperExt;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CloudApplication.class)
public class SchoolMapperTests {

	@Autowired
	TSchoolMapperExt schoolMapperExt;
	
	
	
	@Test
	public void testfingTeacherCountBySchoolId() {
		
		schoolMapperExt.fingStudentCountBySchoolId(1l);
		
		schoolMapperExt.fingTeacherCountBySchoolId(111111l);
	}
}

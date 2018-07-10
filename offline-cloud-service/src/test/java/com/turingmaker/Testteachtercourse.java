package com.turingmaker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.turingmaker.service.school.api.ClassService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CloudApplication.class)
public class Testteachtercourse {

	

    @Autowired
    ClassService classService;
    
    
    
    @Test
    public  void  test1() {
    	
    	//108,115,
    	classService.pushByLessonIdAndClassId(108L, 79l);
    	classService.pushByLessonIdAndClassId(109L, 81l);
    	
    	classService.pushByLessonIdAndClassId(109L, 83l);
    	classService.pushByLessonIdAndClassId(108L, 82l);
    	classService.pushByLessonIdAndClassId(111L, 79l);
    	classService.pushByLessonIdAndClassId(111L, 81l);
    	classService.pushByLessonIdAndClassId(111L, 83l);
    }
}

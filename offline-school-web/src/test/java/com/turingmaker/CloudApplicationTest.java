package com.turingmaker;


import com.turingmaker.dao.mapper.school.ext.TClassMapperExt;
import com.turingmaker.dao.mapper.school.ext.TClassStudentMapperExt;
import com.turingmaker.entity.school.TClassStudent;
import com.turingmaker.service.school.api.ClassService;
import com.turingmaker.service.school.api.TeacherService;
import com.turingmaker.service.school.bo.TeacherSelectAss;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by zengdingyang on 2018\6\29 0029.
 */
@MapperScan("com.turingmaker.dao.mapper")
@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudApplicationTest {

    @Autowired
    TeacherService teacherService;
    @Autowired
    ClassService classService;
   // @Autowired
   // TClassStudentMapperExt classStudentMapperExt;

    @Test
    public void teacherTest() {
        long schoolId = 11L;
      int pageNo = 1;
      int pageSize = 4;
        List<TeacherSelectAss> teacherSelectAsses = teacherService.getTeacherListAssPage(schoolId,null,null,null,null,pageNo,pageSize).getTeachers();
        for(int i=0;i<teacherSelectAsses.size();i++){
            System.out.println(teacherSelectAsses.get(i));
        }
    }
    @Test
    public void classTest( ){
           // List<TClassStudent> classStudents = classStudentMapperExt.
    }
}
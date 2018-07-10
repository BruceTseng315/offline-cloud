package com.turingmaker;

import com.turingmaker.dao.mapper.school.ext.TClassMapperExt;
import com.turingmaker.dao.mapper.school.ext.TStudentMapperExt;
import com.turingmaker.dao.mapper.teachter.ext.HomeworkMapperExt;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.teachter.response.ClassinfoPage;
import com.turingmaker.service.teachter.response.StudentStatisticsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CloudApplication.class)
public class TestclassMapper {

    @Autowired
    TClassMapperExt classMapperExt;

    @Autowired
    TStudentMapperExt studentMapperExt;


    @Autowired
    HomeworkMapperExt homeworkMapperExt;

    @Test
    public void test1(){
        Pageinfo pageinfo= new Pageinfo(1,10);
        System.out.println(
                new ClassinfoPage(pageinfo, classMapperExt.selectTeachterClassInfoPage(pageinfo,33l)));
    }


    @Test
    public void test2(){
        Pageinfo pageinfo= new Pageinfo(1,10);
        System.out.println(
                new StudentStatisticsPage(pageinfo,studentMapperExt.findStudentStatisticsInfoPage(pageinfo,111l)));
    }

    @Test
    public void test3(){
        Pageinfo pageinfo= new Pageinfo(1,10);
        System.out.println(homeworkMapperExt.findClassHomeWorkPage(pageinfo,33l,null,null,null,true));
    }
}

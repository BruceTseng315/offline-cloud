package com.turingmaker;

import com.turingmaker.dao.mapper.teachter.TProgramLikeMapper;
import com.turingmaker.dao.mapper.teachter.ext.ProgramLikeMapperExt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 性能测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=CloudApplication.class)
public class Testperformance {



    @Autowired
    ProgramLikeMapperExt programLikeMapperExt;


    @Test
    public void testUpdate(){

        Long l1=System.currentTimeMillis();
        for(int i=0;i<1000;i++){

            programLikeMapperExt.likeProgram((long) i,73l);
            programLikeMapperExt.unlikeProgram((long) i,73l);
        }

        Long l2=System.currentTimeMillis();

        System.out.println((l2-l1));
    }



}

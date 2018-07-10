package com.turingmaker;


import com.turingmaker.service.teachter.request.SaveWorkRequest;
import com.turingmaker.service.work.api.WorkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CloudApplication.class)
public class TestWork {


    @Autowired
     WorkService workService;


    @Test
    public void testSave(){
        SaveWorkRequest saveWorkRequest=new SaveWorkRequest();
        //saveWorkRequest.setId(5l);
        saveWorkRequest.setAuthor("赵子龙");
        saveWorkRequest.setDescription("作品描述");
        saveWorkRequest.setWorkName("测试作品10");
        saveWorkRequest.setProgramCode("{\"aa\":\"bbc\"}");
        saveWorkRequest.setUserId(191l);
        workService.saveWork(saveWorkRequest);
    }



    @Test
    public void testLike(){

        workService.likeWork(26L,162l,true);

//        workService.likeWork(5l,162l,false);
//
//        workService.likeWork(5l,162l,true);
//
//
        workService.viewWork(7l,162l);

    }


    @Test
    public void testdetail(){
            System.out.println(   workService.detailWork(8l));
    }

    @Test
    public void testListWork(){

        System.out.println(workService.listWorkByUserId(191l));
    }


    @Test
    public void testListWorkClass(){

        System.out.println(workService.listWorkByClassId(114l));
    }


    @Test
    public void testISlike(){
        System.out.println(workService.isLikeed(7l,6l));
    }
}

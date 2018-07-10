package com.turingmaker.controll.controllers;

import com.turingmaker.common.domain.Result;
import com.turingmaker.dao.mapper.school.ext.TClassMapperExt;
import com.turingmaker.dao.mapper.school.ext.TStudentMapperExt;
import com.turingmaker.dao.mapper.teachter.ext.ClassLessonMapperExt;
import com.turingmaker.dao.mapper.teachter.ext.HomeworkMapperExt;
import com.turingmaker.service.exception.HttpException;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.teachter.response.ClassinfoPage;
import com.turingmaker.service.teachter.response.HomeWorkPage;
import com.turingmaker.service.teachter.response.StudentStatisticsPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 *教师端 班级的接口
 * @author  tzh
 */
@RestController
@RequestMapping("turing/api/v2/teacher/class/")
public class TeachterClassController {


    @Autowired
    HomeworkMapperExt homeworkMapperExt;
    @Autowired
    TClassMapperExt classMapperExt;

    @Autowired
    TStudentMapperExt studentMapperExt;

    @Autowired
    ClassLessonMapperExt classLessonMapperExt;



    Pageinfo defaultPageInfo(Integer pageNo,Integer pageSize){

        if(pageNo==null){
            pageNo=1;
        }
        if(pageSize==null){
            pageSize=10;
        }

        return new Pageinfo(pageNo,pageSize);
    }
    /**
     * turing/api/v2/teacher/class/all/detail
     * GET
     * 我的班级详情列表
     * @return
     */
    @GetMapping("all")
    public Result<?>  listTeachterClass(HttpSession session,Integer pageNo,Integer pageSize){



        Pageinfo pageinfo=defaultPageInfo(pageNo,pageSize);

        Long teachterId= Long.valueOf((String)session.getAttribute(AuthController.SESSION_TEACHTER_KEY));


        return new Result<Object>(  new ClassinfoPage(pageinfo, classMapperExt.selectTeachterClassInfoPage(pageinfo,teachterId)));
    }


    /**
     * 获取班级上次课程
     * @param classId
     * @return
     */
    @GetMapping("lastcourse")
    public Result<?>  lastCourse(Long classId){

        if(classId==null){
            throw new HttpException("班级id不能为空");
        }
        return new Result<Object>( classMapperExt.selectClassLastCourse(classId));
    }


    /**
     * 查看学生账号密码
     * @param student
     * @return
     */
    @GetMapping("stuaccount_info")
    public Result<?>  studentAccountInfo(Long studentId){

        if(studentId==null){
            throw new HttpException("学生id不能为空");
        }

        return new Result<Object>(   studentMapperExt.findStudentAccountAndPassword(studentId));
    }


    /**
     * 获取班级学生统计信息
     * @param classId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("statistics_info")
    public Result<?>  studentStatisticstInfo(Long classId,Integer pageNo,Integer pageSize){

        Pageinfo pageinfo=defaultPageInfo(pageNo,pageSize);


        if(classId==null){
            throw new HttpException("班级id不能为空");
        }



        return new Result<Object>(
                new StudentStatisticsPage(pageinfo,studentMapperExt.findStudentStatisticsInfoPage(pageinfo,classId))
        );
    }


    /**
     * 查询班级作业
     * @param classId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("homeworklist")
    public Result<?>  studentHomeworkList(Long classId,Long courseId,Long lessonId,Long studentId,Boolean notCorrect,Integer pageNo,Integer pageSize){

        Pageinfo pageinfo=defaultPageInfo(pageNo,pageSize);


        return new Result<Object>(
                new HomeWorkPage(pageinfo,homeworkMapperExt.findClassHomeWorkPage(pageinfo,classId,courseId,lessonId,studentId,notCorrect))
        );
    }

    
    @GetMapping("baseinfo")
    public Result<?> basicClassInfo(Long classId){
    	  
    	 
    	return new Result<>(classMapperExt.selectClassInfoByClassId(classId));
    }
    

    /**
     * /turing/api/v2/teacher/class/detail
     * GET
     * 获取班级的课程进度
     */
    @GetMapping("course_schedule")
    public  Result<?> courseSchdule(HttpSession session,Long classId){

    	  Long teachterId= Long.valueOf((String)session.getAttribute(AuthController.SESSION_TEACHTER_KEY));
        if(classId==null){
            throw new HttpException("班级id不能为空");
        }

        return   new Result<>(classLessonMapperExt.findForClassCourseSchdule(teachterId,classId));
    }


}

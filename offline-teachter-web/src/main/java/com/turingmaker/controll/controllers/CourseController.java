package com.turingmaker.controll.controllers;

import com.turingmaker.common.domain.Result;
import com.turingmaker.dao.mapper.operation.ext.CourseMapperExt;
import com.turingmaker.dao.mapper.operation.ext.LessonMapperExt;
import com.turingmaker.dao.mapper.school.ext.TClassMapperExt;
import com.turingmaker.entity.operation.TCourse;
import com.turingmaker.entity.school.TClass;
import com.turingmaker.service.operation.api.CourseService;
import com.turingmaker.service.operation.api.LessonService;
import com.turingmaker.service.operation.entity.Resourceinfo;
import com.turingmaker.service.operation.response.CourseInfo;
import com.turingmaker.service.operation.response.LessonInfo;
import com.turingmaker.service.operation.response.LessonResponse;
import com.turingmaker.service.school.api.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.turingmaker.service.operation.api.LessonService.*;

/**
 * 课程的controller
* @authfilter sy
*/
@RestController
@RequestMapping("/turing/api/v2/teacher/course/")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    ClassService classService;

    @Autowired
    LessonService lessonService;

    @Autowired
    LessonMapperExt lessonMapperExt;

    @Autowired
    CourseMapperExt courseMapperExt;


    /**
    * @Description:获取老师所上课程
    * @Param:
    * @Date: 2018/7/5
    */
    @GetMapping("course/all")
    public Result getAllCourse(Long teacherId){
        List<CourseInfo> courses = courseService.findAllCourseByTeacherId(teacherId);
        return new Result(courses);
    }

    /**
     * @Description:获取课时的详情
     * @Param:
     * @Date: 2018/7/5
     */
    @GetMapping("lesson/detail")
    public Result getLessonDetail(Long lessonId){
        LessonResponse response = lessonService.detailsLesson(lessonId);
        return new Result(response);
    }

    /**
     * @Description:获取课程的详情
     * @Param:
     * @Date: 2018/7/5
     */
    @GetMapping("details")
    public Result getCourseDetails(Long courseId){
        Map<String,Object> map = new HashMap<>();
        CourseInfo courseInfo = courseService.getCourseInfo(courseId);
        map.put("course",courseInfo);
        List<LessonInfo> response = lessonService.getInfoListByCourseId(courseId);
        map.put("lessons",response);
        return new Result(map);
    }

    /**
     * @Description:老师-课程的授课班级列表
     * @Param:
     * @Date: 2018/7/5
     */
    @GetMapping("class/all")
    public Result getCourseByTeacherIdAndCourseId(Long courseId ,Long teacherId){
        List<TClass> classes = classService.getClassByCourseIdAndTeacherId(courseId,teacherId);
        return new Result(classes);
    }

    /**
     * @Description:获取老师指定班级的所有授课课程列表
     * @Param:
     * @Date: 2018/7/5
     */
    @GetMapping("clsss_all")
    public Result getCourseByClassIdAndTeacherId(Long classId ,Integer pageNo ,Integer pageSize ,Long teacherId){
        Map<String,Object> map = courseService.findByTeacherIdAndClassId(teacherId,classId,pageNo,pageSize);
        return new Result(map);
    }

    /**
     * @Description:根据课时的资源类型获取资源
     * @Param:
     * @Date: 2018/7/5
     */
    @GetMapping("lesson/resource")
    public Result getResourceByType(Long lessonId ,String type){
        Integer resourceType = 0;
        switch (type){
            case "ppts":resourceType=RESOURSE_TYPE_PPTS;break;
            case "plans":resourceType=RESOURSE_TYPE_PLANS;break;
            case "videos":resourceType=RESOURSE_TYPE_VIDEO;break;
            case "others":resourceType=RESOURSE_TYPE_OTHER;break;
            case "programs":resourceType=RESOURSE_TYPE_PROGRAM;break;
            case "tasks":resourceType=null;break;
            default:break;
        }

        if(resourceType!=null) {
            List<Resourceinfo> list = lessonService.findResourseUrlForLesson(lessonId, resourceType);
            return new Result(list);
        }else {
            return new Result(lessonService.findTaskForLesson(lessonId));
        }
    }

    /**
     * @Description:班级下指定课时推送
     * @Param:
     * @Date: 2018/7/5
     */
    @GetMapping("lesson/push")
    public Result pushLesson(Long classId ,Long lessonId){
        classService.pushByLessonIdAndClassId(classId,lessonId);
        return Result.successresult;
    }

}

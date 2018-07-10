package com.turingmaker.controll.controllers;

import com.turingmaker.common.config.Constant;
import com.turingmaker.common.domain.Result;
import com.turingmaker.controll.config.StudentSession;
import com.turingmaker.service.student.api.CourseService;
import com.turingmaker.service.student.entity.ClassCourseOutline;
import com.turingmaker.service.student.entity.LessonState;
import com.turingmaker.service.student.entity.StudentLessonTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author zengdingyang
 * @Date 2018\7\6 0006
 */
@RestController
@RequestMapping("/turing/api/v2/student/course")
public class CourseController {
    //@Qualifier("studentCourseService")
    @Autowired
    CourseService courseService;

    /**
     * 获取课程列表
     * @param session
     * @return
     */
    @GetMapping("/list")
    public Result getStudentCourseList(HttpSession session){
        StudentSession studentSession = (StudentSession)session.getAttribute(Constant.SESSION_USER_KEY);
        Long studentId = studentSession.getStudentId();

        List<ClassCourseOutline> classCourseOutlines = courseService.getStudentCourseList(studentId);

        return Result.success(classCourseOutlines);
    }

    @GetMapping("/lesson_schedule")
    public Result getCourseLessonStateList(@RequestParam("classId")Long classId,@RequestParam("courseId")Long courseId){
        List<LessonState> lessonStates = courseService.getClassCourseLessonState(classId,courseId);

        return Result.success(lessonStates);
    }

    /**
     * 获取课时任务列表
     * @param session
     * @param lessonId
     * @return
     */
    @GetMapping("/lesson/task/list")
    public Result getStudentLessonTaskList(HttpSession session,@RequestParam("lessonId")Long lessonId){
        StudentSession studentSession = (StudentSession)session.getAttribute(Constant.SESSION_USER_KEY);
        Long studentId = studentSession.getStudentId();

        List<StudentLessonTask> studentLessonTasks = courseService.getStudentLessonTaskList(studentId,lessonId);

        return Result.success(studentLessonTasks);
    }
}

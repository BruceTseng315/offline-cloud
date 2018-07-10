package com.turingmaker.service.student.api;

import com.turingmaker.entity.teachter.TWork;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.student.entity.ClassCourseOutline;
import com.turingmaker.service.student.entity.LessonState;
import com.turingmaker.service.student.entity.StudentLessonTask;

import java.util.List;

/**
 * @Author zengdingyang
 * @Date 2018\7\5 0005
 */
public interface CourseService {
    /**
     * 获取课程列表
     * @param studentId
     * @return
     */
    List<ClassCourseOutline> getStudentCourseList(Long studentId);

    /**
     * 获取课程进度
     * @param classId
     * @param courseId
     * @return
     */
    List<LessonState> getClassCourseLessonState(Long classId,Long courseId);

    List<StudentLessonTask> getStudentLessonTaskList(Long studentId,Long lessonId);

}

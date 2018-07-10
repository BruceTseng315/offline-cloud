package com.turingmaker.service.student.impl;

import com.turingmaker.dao.mapper.operation.ext.CourseMapperExt;
import com.turingmaker.dao.mapper.school.ext.TClassCourseTeacherMapperExt;
import com.turingmaker.dao.mapper.school.ext.TTaskMapperExt;
import com.turingmaker.dao.mapper.teachter.ext.TClassLessonMapperExt;
import com.turingmaker.entity.operation.TCourse;
import com.turingmaker.service.student.api.CourseService;
import com.turingmaker.service.student.entity.ClassCourseOutline;
import com.turingmaker.service.student.entity.LessonState;
import com.turingmaker.service.student.entity.StudentLessonTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zengdingyang
 * @Date 2018\7\6 0006
 */

@Service
public class StudentCourseServiceImpl implements CourseService {
    @Autowired
    TClassCourseTeacherMapperExt classCourseTeacherMapperExt;
    @Autowired
    CourseMapperExt courseMapperExt;
    @Autowired
    TClassLessonMapperExt classLessonMapperExt;
    @Autowired
    TTaskMapperExt tTaskMapperExt;

    @Override
    public List<ClassCourseOutline> getStudentCourseList(Long studentId) {
        return classCourseTeacherMapperExt.getStudentCourseList(studentId);
    }

    @Override
    public List<LessonState> getClassCourseLessonState(Long classId, Long courseId) {
        List<LessonState> lessonStates = classLessonMapperExt.getCourseLessonStateList(classId,courseId);
        TCourse course = courseMapperExt.selectByPrimaryKey(courseId);
        int lessonNum = course.getLessonNum();

        List<LessonState> result = new ArrayList<>(lessonNum);
        if(lessonNum < 1){
            return result;
        }
        //先添加默认值
        for(int i=0;i<lessonNum;i++){
            result.add(new LessonState(i+1,null,0));
        }
        if(lessonStates != null && lessonStates.size() > 0){
            for(LessonState lessonState:lessonStates){
                lessonState.setState(1);
                result.set(lessonState.getLessonSection()-1,lessonState);
            }
        }


        return result;
    }

    @Override
    public List<StudentLessonTask> getStudentLessonTaskList(Long studentId, Long lessonId) {
        List<StudentLessonTask> studentLessonTasks = tTaskMapperExt.getStudentTaskLessonList(studentId,lessonId);

        return studentLessonTasks;
    }
}

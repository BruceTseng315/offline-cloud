package com.turingmaker.service.student.response;


import com.turingmaker.service.student.entity.StudentLessonAndState;

import java.io.Serializable;
import java.util.List;

/**
 * @author  tzj
 * 学生课时进度返回
 */
public class StudentLessonSchduleResponse   implements Serializable {



    List<StudentLessonAndState>  lessonAndStates;


    public List<StudentLessonAndState> getLessonAndStates() {
        return lessonAndStates;
    }

    public void setLessonAndStates(List<StudentLessonAndState> lessonAndStates) {
        this.lessonAndStates = lessonAndStates;
    }
}

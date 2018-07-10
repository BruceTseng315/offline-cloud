package com.turingmaker.service.student.entity;

import com.turingmaker.entity.operation.TLesson;

/**
 * @author  tzj
 * 包装学生课时进度
 */
public class StudentLessonAndState {

    /**
     * 课时id
     */
    private Long id;

    /**
     * 课时名称
     */
    private  String lessonName;

    /**
     * 对应于学生的状态。
     * 0:正在上的,1:已经上过;2：未上过的
     */
    private int state;

    public String getLessonName() {
        return lessonName;
    }

    public int getState() {
        return state;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

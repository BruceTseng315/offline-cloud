package com.turingmaker.service.student.entity;

import com.turingmaker.entity.school.TTask;

/**
 * @Author zengdingyang
 * @Date 2018\7\9 0009
 */
public class StudentLessonTask extends TTask{
    //课时章节
    private Integer lessonSection;
    //课时名称
    private String lessonName;
    private Long homeworkId;

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Integer getLessonSection() {
        return lessonSection;
    }

    public void setLessonSection(Integer lessonSection) {
        this.lessonSection = lessonSection;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    @Override
    public String toString() {
        return "StudentLessonTask{" +
                "homeworkId=" + homeworkId +
                '}'+"\n" +
                super.toString();
    }
}

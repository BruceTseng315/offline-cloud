package com.turingmaker.service.student.entity;

/**
 * @Author zengdingyang
 * @Date 2018\7\6 0006
 */
public class LessonState {
    private Integer lessonSection;
    private Long lessonId;
    private Integer state = 0;

    public LessonState(){}
    public LessonState(Integer lessonSection,Long lessonId,Integer state){
        this.lessonId = lessonId;
        this.lessonSection = lessonSection;
        this.state = state;
    }
    public Integer getLessonSection() {
        return lessonSection;
    }

    public void setLessonSection(Integer lessonSection) {
        this.lessonSection = lessonSection;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

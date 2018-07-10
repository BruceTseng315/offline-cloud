package com.turingmaker.service.operation.response;

public class LessonInfo {

    private Long id;

    private String lessonName;

    private String lessonDescription;

    private String lessonAvatar;

    private Integer lessonSection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    public String getLessonAvatar() {
        return lessonAvatar;
    }

    public void setLessonAvatar(String lessonAvatar) {
        this.lessonAvatar = lessonAvatar;
    }

    public Integer getLessonSection() {
        return lessonSection;
    }

    public void setLessonSection(Integer lessonSection) {
        this.lessonSection = lessonSection;
    }

    @Override
    public String toString() {
        return "LessonInfo{" +
                "id=" + id +
                ", lessonName='" + lessonName + '\'' +
                ", lessonDescription='" + lessonDescription + '\'' +
                ", lessonAvatar='" + lessonAvatar + '\'' +
                ", lessonSection=" + lessonSection +
                '}';
    }
}

package com.turingmaker.service.teachter.entity;


/**
 * @author  tzj
 * 班级课程的实体
 */
public class ClassCourseInfo {

    /**
     *
     *    "courseId":1,
     *         "lessonId":2,
     *         "lessonName":"",
     *         "courseName":""
     */


    private   Long courseId;

    private Long lessonId;

    private   String lessonName;

    private  String courseName;


    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    @Override
    public String toString() {
        return "ClassCourseInfo{" +
                "courseId=" + courseId +
                ", lessonId=" + lessonId +
                ", lessonName='" + lessonName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}

package com.turingmaker.service.teachter.entity;

/**
 * 课程进度
 * @author  tzj
 */
public class CourseSchdule {


    /**
     * "courseId":1,
     *                 "courseName":"",
     *                 "lessons":[
     *                     {
     *                         "lessonId":1,
     *                         "lessonState":0,
     *                         "lessonName":""
     *                     }
     *                 ]
     */

    private  Long courseId;
    private  Long lessonId;
    private  Long classId;
    private  String className;
    private  String courseName;
    private  String lessonName;
    private  Integer state;
    /**
     * 总课时数量
     */
    private  Integer totalLessonNum;


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

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getTotalLessonNum() {
        return totalLessonNum;
    }

    public void setTotalLessonNum(Integer totalLessonNum) {
        this.totalLessonNum = totalLessonNum;
    }

    @Override
    public String toString() {
        return "CourseSchdule{" +
                "courseId=" + courseId +
                ", lessonId=" + lessonId +
                ", classId=" + classId +
                ", className='" + className + '\'' +
                ", courseName='" + courseName + '\'' +
                ", lessonName='" + lessonName + '\'' +
                ", state=" + state +
                ", totalLessonNum=" + totalLessonNum +
                '}';
    }
}

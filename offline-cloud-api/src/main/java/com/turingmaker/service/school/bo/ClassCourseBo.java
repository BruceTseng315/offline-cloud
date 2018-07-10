package com.turingmaker.service.school.bo;

public class ClassCourseBo {

    private Long classId;
    private Long courseId;
    private String courseName;
    private String teacherName;
    private Long teacherId;
    /**
     * 课程状态 0：停课 1：开课
     */
    private Byte state;


    public Byte getState() {
        return state;
    }

    public void setStatus(Byte state) {
        this.state = state;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "ClassCourseBo{" +
                "classId=" + classId +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherId=" + teacherId +
                ", status=" + state +
                '}';
    }
}

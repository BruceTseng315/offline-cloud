package com.turingmaker.service.student.entity;

import com.turingmaker.entity.school.TClassCourseTeacher;

/**
 * @Author zengdingyang
 * @Date 2018\7\6 0006
 */
public class ClassCourseOutline extends TClassCourseTeacher{
    private String courseName;
    private String teacherName;
    private String courseAvatar;
    private String className;

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

    public String getCourseAvatar() {
        return courseAvatar;
    }

    public void setCourseAvatar(String courseAvatar) {
        this.courseAvatar = courseAvatar;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

package com.turingmaker.service.operation.request;


import java.util.List;

import com.turingmaker.service.school.bo.ClassCourseBo;

public class AddClassRequest {
    private String className;
    private Byte classType;
    private Integer grade;
    private List<ClassCourseBo> courses;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Byte getClassType() {
        return classType;
    }

    public void setClassType(Byte classType) {
        this.classType = classType;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public List<ClassCourseBo> getCourses() {
        return courses;
    }

    public void setCourses(List<ClassCourseBo> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "AddClassRequest{" +
                "className='" + className + '\'' +
                ", classType=" + classType +
                ", grade=" + grade +
                ", courses=" + courses +
                '}';
    }
}

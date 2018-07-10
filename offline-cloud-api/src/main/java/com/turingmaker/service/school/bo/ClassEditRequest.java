package com.turingmaker.service.school.bo;

import java.util.List;

public class ClassEditRequest {
    private Long classId;
    private Integer grade;
    private String className;
    private List<ClassCourseBo> courses;
    private Byte classType;

    public Byte getClassType() {
        return classType;
    }

    public void setClassType(Byte classType) {
        this.classType = classType;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ClassCourseBo> getCourses() {
        return courses;
    }

    public void setCourses(List<ClassCourseBo> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "ClassEditRequest{" +
                "classId=" + classId +
                ", grade=" + grade +
                ", className='" + className + '\'' +
                ", courses=" + courses +
                '}';
    }
}

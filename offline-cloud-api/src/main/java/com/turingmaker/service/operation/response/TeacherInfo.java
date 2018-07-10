package com.turingmaker.service.operation.response;

import java.util.List;

public class TeacherInfo{

    private Long id;

    private String teacherCode;

    private String teacherName;

    private String account;

    private String password;

    private List<?> courses;

    private List<?> classes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<?> getCourses() {
        return courses;
    }

    public void setCourses(List<?> courses) {
        this.courses = courses;
    }

    public List<?> getClasses() {
        return classes;
    }

    public void setClasses(List<?> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "TeacherInfo{" +
                "id=" + id +
                ", teacherCode='" + teacherCode + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", courses=" + courses +
                ", classes=" + classes +
                '}';
    }
}

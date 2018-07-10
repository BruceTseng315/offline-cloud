package com.turingmaker.service.school.bo;


import java.util.List;

/**
 * Created by zengdingyang on 2018\6\29 0029.
 */
public class TeacherSelectAss {
    private Long teacherId;
    private String teacherCode;
    private String teacherName;
    private String account;
    private String password;

    private List<String> classes;
    private List<String> courses;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "TeacherSelectAss{" +
                "teacherId=" + teacherId +
                ", teacherCode='" + teacherCode + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", classes=" + classes +
                ", courses=" + courses +
                '}';
    }
}

package com.turingmaker.service.school.bo;

import java.io.Serializable;

/**
 * Created by zengdingyang on 2018\6\28 0028.
 */
public class ExportTeacherResponse {

    /**
     * 教师姓名
     */
    private String teacherName;
    /**
     * 授课班级
     */
    private String classIn;
    /**
     * 所授课程
     */
    private String course;

    /**
     * 账户名
     */
    private String account;
    /**
     *密码
     */
    private String password;



    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassIn() {
        return classIn;
    }

    public void setClassIn(String classIn) {
        this.classIn = classIn;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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

    @Override
    public String toString() {
        return "ExportTeacherResponse{" +
                "teacherName='" + teacherName + '\'' +
                ", classIn='" + classIn + '\'' +
                ", course='" + course + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

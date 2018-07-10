package com.turingmaker.service.operation.response;

import com.turingmaker.entity.school.TSchool;

import java.util.List;
import java.util.Map;

public class SchoolDetailResponse extends TSchool {
    private String adminName;
    private String adminPhone;
    private Map<String,AreaResponse> region;
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1463659232005673154L;
    /**
     * 开设课程
     */
    List<SchoolCourseInfo> courses;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public Map<String, AreaResponse> getRegion() {
        return region;
    }

    public void setRegion(Map<String, AreaResponse> region) {
        this.region = region;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<SchoolCourseInfo> getCourses() {
        return courses;
    }

    public void setCourses(List<SchoolCourseInfo> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "SchoolDetailResponse{" +
                "adminName='" + adminName + '\'' +
                ", adminPhone='" + adminPhone + '\'' +
                ", region=" + region +
                ", account='" + account + '\'' +
                ", courses=" + courses +
                '}';
    }
}

package com.turingmaker.entity.school;

import java.io.Serializable;
import java.util.Date;

public class TTeacher implements Serializable {
    private Long id;

    private String teacherCode;

    private String teacherName;

    private Date createTime;

    private Date updateTime;

    private Long accountId;

    private String phone;

    private Long schoolId;

    private static final long serialVersionUID = 1L;

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
        this.teacherCode = teacherCode == null ? null : teacherCode.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
         return  "TTeacher{"+
				"id="+this.getId()+
				"teacherCode="+this.getTeacherCode()+
				"teacherName="+this.getTeacherName()+
				"createTime="+this.getCreateTime()+
				"updateTime="+this.getUpdateTime()+
				"accountId="+this.getAccountId()+
				"phone="+this.getPhone()+
				"schoolId="+this.getSchoolId()+"}";
    }
}
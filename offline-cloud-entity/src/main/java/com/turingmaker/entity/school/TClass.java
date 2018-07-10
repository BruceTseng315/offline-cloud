package com.turingmaker.entity.school;

import java.io.Serializable;
import java.util.Date;

public class TClass implements Serializable {
    private Long id;

    private String classCode;

    private String className;

    private Byte classType;

    private Date createTime;

    private Date updateTime;

    private Long schoolId;

    private Integer grade;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode == null ? null : classCode.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Byte getClassType() {
        return classType;
    }

    public void setClassType(Byte classType) {
        this.classType = classType;
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

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
         return  "TClass{"+
				"id="+this.getId()+
				"classCode="+this.getClassCode()+
				"className="+this.getClassName()+
				"classType="+this.getClassType()+
				"createTime="+this.getCreateTime()+
				"updateTime="+this.getUpdateTime()+
				"schoolId="+this.getSchoolId()+
				"grade="+this.getGrade()+"}";
    }
}
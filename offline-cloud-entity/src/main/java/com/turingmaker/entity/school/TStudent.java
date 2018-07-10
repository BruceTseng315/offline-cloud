package com.turingmaker.entity.school;

import java.io.Serializable;
import java.util.Date;

public class TStudent implements Serializable {
    private Long id;

    private String studentCode;

    private String studentName;

    private String studentNumber;

    private String identifier;

    private String guarderName;

    private String guarderPhone;

    private Date createTime;

    private Date updateTime;

    private Long accountId;

    private Long schoolId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode == null ? null : studentCode.trim();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber == null ? null : studentNumber.trim();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier == null ? null : identifier.trim();
    }

    public String getGuarderName() {
        return guarderName;
    }

    public void setGuarderName(String guarderName) {
        this.guarderName = guarderName == null ? null : guarderName.trim();
    }

    public String getGuarderPhone() {
        return guarderPhone;
    }

    public void setGuarderPhone(String guarderPhone) {
        this.guarderPhone = guarderPhone == null ? null : guarderPhone.trim();
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

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
         return  "TStudent{"+
				"id="+this.getId()+
				"studentCode="+this.getStudentCode()+
				"studentName="+this.getStudentName()+
				"studentNumber="+this.getStudentNumber()+
				"identifier="+this.getIdentifier()+
				"guarderName="+this.getGuarderName()+
				"guarderPhone="+this.getGuarderPhone()+
				"createTime="+this.getCreateTime()+
				"updateTime="+this.getUpdateTime()+
				"accountId="+this.getAccountId()+
				"schoolId="+this.getSchoolId()+"}";
    }
}
package com.turingmaker.entity.school;

import java.io.Serializable;
import java.util.Date;

public class TSchool implements Serializable {
    private Long id;

    private String schoolCode;

    private Integer schoolType;

    private String schoolName;

    private Long areaCode;

    private String address;

    private String contactPhone;

    private String contactName;

    private String channelName;

    private String channelPhone;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode == null ? null : schoolCode.trim();
    }

    public Integer getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(Integer schoolType) {
        this.schoolType = schoolType;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public Long getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Long areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getChannelPhone() {
        return channelPhone;
    }

    public void setChannelPhone(String channelPhone) {
        this.channelPhone = channelPhone == null ? null : channelPhone.trim();
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

    @Override
    public String toString() {
         return  "TSchool{"+
				"id="+this.getId()+
				"schoolCode="+this.getSchoolCode()+
				"schoolType="+this.getSchoolType()+
				"schoolName="+this.getSchoolName()+
				"areaCode="+this.getAreaCode()+
				"address="+this.getAddress()+
				"contactPhone="+this.getContactPhone()+
				"contactName="+this.getContactName()+
				"channelName="+this.getChannelName()+
				"channelPhone="+this.getChannelPhone()+
				"createTime="+this.getCreateTime()+
				"updateTime="+this.getUpdateTime()+"}";
    }
}
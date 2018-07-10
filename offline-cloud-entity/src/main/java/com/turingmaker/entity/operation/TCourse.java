package com.turingmaker.entity.operation;

import java.io.Serializable;
import java.util.Date;

public class TCourse implements Serializable {
    private Long id;

    private String courseCode;

    private String courseName;

    private String courseDescription;

    private String courseAvatar;

    private Byte courseState;

    private Date createTime;

    private Date updateTime;

    private Integer lessonNum;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode == null ? null : courseCode.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription == null ? null : courseDescription.trim();
    }

    public String getCourseAvatar() {
        return courseAvatar;
    }

    public void setCourseAvatar(String courseAvatar) {
        this.courseAvatar = courseAvatar == null ? null : courseAvatar.trim();
    }

    public Byte getCourseState() {
        return courseState;
    }

    public void setCourseState(Byte courseState) {
        this.courseState = courseState;
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

    public Integer getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
    }

    @Override
    public String toString() {
         return  "TCourse{"+
				"id="+this.getId()+
				"courseCode="+this.getCourseCode()+
				"courseName="+this.getCourseName()+
				"courseDescription="+this.getCourseDescription()+
				"courseAvatar="+this.getCourseAvatar()+
				"courseState="+this.getCourseState()+
				"createTime="+this.getCreateTime()+
				"updateTime="+this.getUpdateTime()+
				"lessonNum="+this.getLessonNum()+"}";
    }
}
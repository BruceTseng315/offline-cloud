package com.turingmaker.entity.operation;

import java.io.Serializable;
import java.util.Date;

public class TLesson implements Serializable {
    private Long id;

    private String lessonName;

    private String lessonDescription;

    private String lessonAvatar;

    private Integer lessonSection;

    private Integer lessonState;

    private Date createTime;

    private Date updateTime;

    private Long courseId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName == null ? null : lessonName.trim();
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription == null ? null : lessonDescription.trim();
    }

    public String getLessonAvatar() {
        return lessonAvatar;
    }

    public void setLessonAvatar(String lessonAvatar) {
        this.lessonAvatar = lessonAvatar == null ? null : lessonAvatar.trim();
    }

    public Integer getLessonSection() {
        return lessonSection;
    }

    public void setLessonSection(Integer lessonSection) {
        this.lessonSection = lessonSection;
    }

    public Integer getLessonState() {
        return lessonState;
    }

    public void setLessonState(Integer lessonState) {
        this.lessonState = lessonState;
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
         return  "TLesson{"+
				"id="+this.getId()+
				"lessonName="+this.getLessonName()+
				"lessonDescription="+this.getLessonDescription()+
				"lessonAvatar="+this.getLessonAvatar()+
				"lessonSection="+this.getLessonSection()+
				"lessonState="+this.getLessonState()+
				"createTime="+this.getCreateTime()+
				"updateTime="+this.getUpdateTime()+
				"courseId="+this.getCourseId()+"}";
    }
}
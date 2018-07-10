package com.turingmaker.entity.teachter;

import java.io.Serializable;
import java.util.Date;

public class TClassLesson implements Serializable {
    private Long id;

    private Long classId;

    private Long lessonId;

    private Long courseId;

    private Integer lessonSection;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getLessonSection() {
        return lessonSection;
    }

    public void setLessonSection(Integer lessonSection) {
        this.lessonSection = lessonSection;
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
         return  "TClassLesson{"+
				"id="+this.getId()+
				"classId="+this.getClassId()+
				"lessonId="+this.getLessonId()+
				"courseId="+this.getCourseId()+
				"lessonSection="+this.getLessonSection()+
				"createTime="+this.getCreateTime()+
				"updateTime="+this.getUpdateTime()+"}";
    }
}
package com.turingmaker.entity.school;

import java.io.Serializable;
import java.util.Date;

public class TClassCourseTeacher implements Serializable {
    private Long id;

    private Long classId;

    private Long courseId;

    private Long teacherId;

    private Date createTime;

    private Date updateTime;

    private Byte state;

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Override
    public String toString() {
         return  "TClassCourseTeacher{"+
				"id="+this.getId()+
				"classId="+this.getClassId()+
				"courseId="+this.getCourseId()+
				"teacherId="+this.getTeacherId()+
				"createTime="+this.getCreateTime()+
				"updateTime="+this.getUpdateTime()+
				"state="+this.getState()+"}";
    }
}
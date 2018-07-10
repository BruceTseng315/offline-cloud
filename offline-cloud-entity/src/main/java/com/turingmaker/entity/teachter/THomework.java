package com.turingmaker.entity.teachter;

import java.io.Serializable;
import java.util.Date;

public class THomework implements Serializable {
    private Long id;

    private Long studentId;

    private Long programId;

    private Integer isDelete;

    private Long taskId;

    private String homeworkDesc;

    private String homeworkName;

    private String homeworkAvatar;

    private Integer mark;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getHomeworkDesc() {
        return homeworkDesc;
    }

    public void setHomeworkDesc(String homeworkDesc) {
        this.homeworkDesc = homeworkDesc == null ? null : homeworkDesc.trim();
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName == null ? null : homeworkName.trim();
    }

    public String getHomeworkAvatar() {
        return homeworkAvatar;
    }

    public void setHomeworkAvatar(String homeworkAvatar) {
        this.homeworkAvatar = homeworkAvatar == null ? null : homeworkAvatar.trim();
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
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
         return  "THomework{"+
				"id="+this.getId()+
				"studentId="+this.getStudentId()+
				"programId="+this.getProgramId()+
				"isDelete="+this.getIsDelete()+
				"taskId="+this.getTaskId()+
				"homeworkDesc="+this.getHomeworkDesc()+
				"homeworkName="+this.getHomeworkName()+
				"homeworkAvatar="+this.getHomeworkAvatar()+
				"mark="+this.getMark()+
				"createTime="+this.getCreateTime()+
				"updateTime="+this.getUpdateTime()+"}";
    }
}
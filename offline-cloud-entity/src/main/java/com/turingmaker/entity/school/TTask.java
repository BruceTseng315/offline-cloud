package com.turingmaker.entity.school;

import java.io.Serializable;
import java.util.Date;

public class TTask implements Serializable {
    private Long id;

    private String taskName;

    private String taskDescurl;

    private Long lessonId;

    private Long programId;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskDescurl() {
        return taskDescurl;
    }

    public void setTaskDescurl(String taskDescurl) {
        this.taskDescurl = taskDescurl == null ? null : taskDescurl.trim();
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
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
         return  "TTask{"+
				"id="+this.getId()+
				"taskName="+this.getTaskName()+
				"taskDescurl="+this.getTaskDescurl()+
				"lessonId="+this.getLessonId()+
				"programId="+this.getProgramId()+
				"createTime="+this.getCreateTime()+
				"updateTime="+this.getUpdateTime()+"}";
    }
}
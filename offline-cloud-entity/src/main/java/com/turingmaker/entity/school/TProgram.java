package com.turingmaker.entity.school;

import java.io.Serializable;
import java.util.Date;

public class TProgram implements Serializable {
    private Long id;

    private String programContentUrl;

    private String programName;

    private String programContent;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgramContentUrl() {
        return programContentUrl;
    }

    public void setProgramContentUrl(String programContentUrl) {
        this.programContentUrl = programContentUrl == null ? null : programContentUrl.trim();
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName == null ? null : programName.trim();
    }

    public String getProgramContent() {
        return programContent;
    }

    public void setProgramContent(String programContent) {
        this.programContent = programContent == null ? null : programContent.trim();
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
         return  "TProgram{"+
				"id="+this.getId()+
				"programContentUrl="+this.getProgramContentUrl()+
				"programName="+this.getProgramName()+
				"programContent="+this.getProgramContent()+
				"createTime="+this.getCreateTime()+
				"updateTime="+this.getUpdateTime()+"}";
    }
}
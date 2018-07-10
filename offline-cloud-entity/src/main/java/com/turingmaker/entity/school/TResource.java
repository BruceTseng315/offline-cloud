package com.turingmaker.entity.school;

import java.io.Serializable;
import java.util.Date;

public class TResource implements Serializable {
    private Long id;

    private String resourceName;

    private String resourceUrl;

    private Integer resourceType;

    private String resourceFileType;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceFileType() {
        return resourceFileType;
    }

    public void setResourceFileType(String resourceFileType) {
        this.resourceFileType = resourceFileType == null ? null : resourceFileType.trim();
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
         return  "TResource{"+
				"id="+this.getId()+
				"resourceName="+this.getResourceName()+
				"resourceUrl="+this.getResourceUrl()+
				"resourceType="+this.getResourceType()+
				"resourceFileType="+this.getResourceFileType()+
				"createTime="+this.getCreateTime()+
				"updateTime="+this.getUpdateTime()+"}";
    }
}
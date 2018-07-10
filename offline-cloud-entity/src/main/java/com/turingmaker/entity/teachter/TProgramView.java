package com.turingmaker.entity.teachter;

import java.io.Serializable;
import java.util.Date;

public class TProgramView implements Serializable {
    private Long id;

    private Long programId;

    private Integer viewCount;

    private String likeUsers;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getLikeUsers() {
        return likeUsers;
    }

    public void setLikeUsers(String likeUsers) {
        this.likeUsers = likeUsers == null ? null : likeUsers.trim();
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
         return  "TProgramView{"+
				"id="+this.getId()+
				"programId="+this.getProgramId()+
				"viewCount="+this.getViewCount()+
				"likeUsers="+this.getLikeUsers()+
				"createTime="+this.getCreateTime()+
				"updateTime="+this.getUpdateTime()+"}";
    }
}
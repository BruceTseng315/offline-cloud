package com.turingmaker.entity.teachter;

import java.io.Serializable;
import java.util.Date;

public class TProgramLike implements Serializable {
    private Long id;

    private Long programId;

    private Integer likeCount;

    private String likeUsers;

    private Date updateTime;

    private Date createTime;

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

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getLikeUsers() {
        return likeUsers;
    }

    public void setLikeUsers(String likeUsers) {
        this.likeUsers = likeUsers == null ? null : likeUsers.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
         return  "TProgramLike{"+
				"id="+this.getId()+
				"programId="+this.getProgramId()+
				"likeCount="+this.getLikeCount()+
				"likeUsers="+this.getLikeUsers()+
				"updateTime="+this.getUpdateTime()+
				"createTime="+this.getCreateTime()+"}";
    }
}
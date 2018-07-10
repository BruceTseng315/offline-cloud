package com.turingmaker.entity.teachter;

import java.io.Serializable;
import java.util.Date;

public class TWork implements Serializable {
    private Long id;

    private Long userId;

    private String workDesc;

    private String workAuthor;

    private String workName;

    private String workAvatar;

    //默认未删除
    private Integer isDelete = 0;

    private Long programId;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc == null ? null : workDesc.trim();
    }

    public String getWorkAuthor() {
        return workAuthor;
    }

    public void setWorkAuthor(String workAuthor) {
        this.workAuthor = workAuthor == null ? null : workAuthor.trim();
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName == null ? null : workName.trim();
    }

    public String getWorkAvatar() {
        return workAvatar;
    }

    public void setWorkAvatar(String workAvatar) {
        this.workAvatar = workAvatar == null ? null : workAvatar.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
         return  "TWork{"+
				"id="+this.getId()+
				"userId="+this.getUserId()+
				"workDesc="+this.getWorkDesc()+
				"workAuthor="+this.getWorkAuthor()+
				"workName="+this.getWorkName()+
				"workAvatar="+this.getWorkAvatar()+
				"isDelete="+this.getIsDelete()+
				"programId="+this.getProgramId()+
				"state="+this.getState()+
				"createTime="+this.getCreateTime()+
				"updateTime="+this.getUpdateTime()+"}";
    }
}
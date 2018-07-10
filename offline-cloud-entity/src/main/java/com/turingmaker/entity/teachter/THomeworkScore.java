package com.turingmaker.entity.teachter;

import java.io.Serializable;
import java.util.Date;

public class THomeworkScore implements Serializable {
    private Long id;

    private Long homeworkId;

    private Integer selfLearn;

    private Integer inquiryLearn;

    private Integer cooperativeLearn;

    private Integer utilization;

    private String comment;

    private Long teachterId;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Integer getSelfLearn() {
        return selfLearn;
    }

    public void setSelfLearn(Integer selfLearn) {
        this.selfLearn = selfLearn;
    }

    public Integer getInquiryLearn() {
        return inquiryLearn;
    }

    public void setInquiryLearn(Integer inquiryLearn) {
        this.inquiryLearn = inquiryLearn;
    }

    public Integer getCooperativeLearn() {
        return cooperativeLearn;
    }

    public void setCooperativeLeran(Integer cooperativeLearn) {
        this.cooperativeLearn = cooperativeLearn;
    }

    public Integer getUtilization() {
        return utilization;
    }

    public void setUtilization(Integer utilization) {
        this.utilization = utilization;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Long getTeachterId() {
        return teachterId;
    }

    public void setTeachterId(Long teachterId) {
        this.teachterId = teachterId;
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
         return  "THomeworkScore{"+
				"id="+this.getId()+
				"homeworkId="+this.getHomeworkId()+
				"selfLearn="+this.getSelfLearn()+
				"inquiryLearn="+this.getInquiryLearn()+
				"cooperativeLeran="+this.getCooperativeLearn()+
				"utilization="+this.getUtilization()+
				"comment="+this.getComment()+
				"teachterId="+this.getTeachterId()+
				"updateTime="+this.getUpdateTime()+
				"createTime="+this.getCreateTime()+"}";
    }
}
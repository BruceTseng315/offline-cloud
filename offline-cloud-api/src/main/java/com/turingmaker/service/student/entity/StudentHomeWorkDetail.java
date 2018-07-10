package com.turingmaker.service.student.entity;

import com.turingmaker.entity.teachter.THomework;
import com.turingmaker.entity.teachter.THomeworkScore;

/**
 * 学生作品详情
 * @author tzj
 */
public class StudentHomeWorkDetail extends THomework{


    /**
     * 浏览量
     */
    private Long  viewCount = 0L;

    /**
     * 点赞数
     */
    private  Long likeCount = 0L;

    private Boolean liked;
    /**
     * 自主学习能力
     */
    private Integer selfLearn;

    /**
     * 探究学习能力
     */
    private Integer inquiryLearn;

    /**
     * 合作学习能力
     */
    private Integer cooperativeLearn;

    /**
     * 知识运用能力
     */
    private Integer utilization;

    /**
     * 评语
     */
    private String comment;


    private Double score;

    public void calScore(){

    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
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

    public void setCooperativeLearn(Integer cooperativeLeran) {
        this.cooperativeLearn = cooperativeLeran;
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
        this.comment = comment;
    }


    @Override
    public String toString() {
        return "StudentHomeWorkDetail{" +
                "viewCount=" + viewCount +
                ", likeCount=" + likeCount +
                ", selfLearn=" + selfLearn +
                ", inquiryLearn=" + inquiryLearn +
                ", cooperativeLeran=" + cooperativeLearn +
                ", utilization=" + utilization +
                ", comment='" + comment + '\'' +
                '}';
    }
}

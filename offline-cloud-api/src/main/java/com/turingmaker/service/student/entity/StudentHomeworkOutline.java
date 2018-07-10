package com.turingmaker.service.student.entity;

import com.turingmaker.entity.teachter.THomework;

/**
 * @Author zengdingyang
 * @Date 2018\7\6 0006
 */
public class StudentHomeworkOutline extends THomework {
    private String courseName;
    private Integer lessonSection;
    private String taskName;
    private Double score;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

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

    public void setCooperativeLearn(Integer cooperativeLearn) {
        this.cooperativeLearn = cooperativeLearn;
    }

    public Integer getUtilization() {
        return utilization;
    }

    public void setUtilization(Integer utilization) {
        this.utilization = utilization;
    }


    public void calScore(){
        if(this.selfLearn == null && this.inquiryLearn == null && this.cooperativeLearn == null && this.utilization == null){
            this.score = null;
        }else{
            int sum = 0;
            if(this.selfLearn != null){
                sum+=this.selfLearn;
            }
            if(this.inquiryLearn != null){
                sum += this.inquiryLearn;
            }
            if(this.cooperativeLearn != null) {
                sum += this.cooperativeLearn;
            }
            if(this.utilization != null) {
                sum += this.utilization;
            }
            this.score = sum/4d;
        }

    }
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getLessonSection() {
        return lessonSection;
    }

    public void setLessonSection(Integer lessonSection) {
        this.lessonSection = lessonSection;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}

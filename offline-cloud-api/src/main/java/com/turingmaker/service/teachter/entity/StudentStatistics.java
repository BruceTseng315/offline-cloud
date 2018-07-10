package com.turingmaker.service.teachter.entity;

/**
 * 学生的统计信息
 * @author tzj
 */
public class StudentStatistics {


    /**
     *  "studentId":1,
     *                 "studentName":"",
     *                 "onloneTime":123,
     *                 "workCount":3,
     *                 "noCorrectCount":4//未批阅作品数
     */


    private  Long studentId;

    private  String studentName;

    private  Integer onloneTime;

    private  Integer workCount;

    private  Integer homeWorkCount;

    private  Integer noCorrectCount;


    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getOnloneTime() {
        return onloneTime;
    }

    public void setOnloneTime(Integer onloneTime) {
        this.onloneTime = onloneTime;
    }

    public Integer getWorkCount() {
        return workCount;
    }

    public void setWorkCount(Integer workCount) {
        this.workCount = workCount;
    }

    public Integer getHomeWorkCount() {
        return homeWorkCount;
    }

    public void setHomeWorkCount(Integer homeWorkCount) {
        this.homeWorkCount = homeWorkCount;
    }

    public Integer getNoCorrectCount() {
        return noCorrectCount;
    }

    public void setNoCorrectCount(Integer noCorrectCount) {
        this.noCorrectCount = noCorrectCount;
    }


    @Override
    public String toString() {
        return "StudentStatistics{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", onloneTime=" + onloneTime +
                ", workCount=" + workCount +
                ", homeWorkCount=" + homeWorkCount +
                ", noCorrectCount=" + noCorrectCount +
                '}';
    }
}

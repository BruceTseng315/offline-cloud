package com.turingmaker.service.teachter.entity;



/**
 * 班级作业实体
 * @author  tzj
 */
public class ClassHomeWork {


    /**
     *    "homeworkId":1,//作业id
     *               "studentName":"qst",//学生姓名
     *               "courseName":"sss",//课程名
     *               "lessonName":"sss",//课时名
     *               "homeworkName":"sss",//作业名
     *               "score":2.4//作业得分
     */
    private  Long homeworkId;
    private  String studentName;
    private  String courseName;
    private  String lessonName;
    private  String homeworkName;
    private Integer selfLearn;
    private Integer inquiryLearn;
    private Integer cooperativeLeran;
    private  Integer score;


    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
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

    public Integer getCooperativeLeran() {
        return cooperativeLeran;
    }

    public void setCooperativeLeran(Integer cooperativeLeran) {
        this.cooperativeLeran = cooperativeLeran;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ClassHomeWork{" +
                "homeworkId=" + homeworkId +
                ", studentName='" + studentName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", lessonName='" + lessonName + '\'' +
                ", homeworkName='" + homeworkName + '\'' +
                ", selfLearn=" + selfLearn +
                ", inquiryLearn=" + inquiryLearn +
                ", cooperativeLeran=" + cooperativeLeran +
                ", score=" + score +
                '}';
    }
}

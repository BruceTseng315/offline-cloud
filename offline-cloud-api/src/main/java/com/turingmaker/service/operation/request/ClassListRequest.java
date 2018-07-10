package com.turingmaker.service.operation.request;

public class ClassListRequest {
    private Integer pageNo;
    private Integer pageSize;
    private String classCode;
    private String className;
    private Long courseId;
    private Integer grade;
    private Byte classType;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Byte getClassType() {
        return classType;
    }

    public void setClassType(Byte classType) {
        this.classType = classType;
    }

    @Override
    public String toString() {
        return "ClassListRequest{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", classCode='" + classCode + '\'' +
                ", className='" + className + '\'' +
                ", courseId='" + courseId + '\'' +
                ", grade=" + grade +
                ", classType=" + classType +
                '}';
    }
}

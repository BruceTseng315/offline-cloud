package com.turingmaker.service.school.bo;

import com.turingmaker.entity.school.TClass;
import com.turingmaker.entity.school.TClassCourseTeacher;

import java.util.Date;
import java.util.List;

public class ClassBo {
    private Long id;

    private String classCode;

    private String className;

    private Byte classType;

    private Date createTime;

    private Date updateTime;

    private Long schoolId;

    private Integer grade;

    private Integer studentCount;

    private List<ClassCourseBo> courses;

    public void setByClass(TClass tClass){
        this.id = tClass.getId();
        this.classCode = tClass.getClassCode();
        this.className = tClass.getClassName();
        this.classType = tClass.getClassType();
        this.createTime = tClass.getCreateTime();
        this.updateTime = tClass.getUpdateTime();
        this.schoolId = tClass.getSchoolId();
        this.grade = tClass.getGrade();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Byte getClassType() {
        return classType;
    }

    public void setClassType(Byte classType) {
        this.classType = classType;
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

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public List<ClassCourseBo> getCourses() {
        return courses;
    }

    public void setCourses(List<ClassCourseBo> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "ClassBo{" +
                "id=" + id +
                ", classCode='" + classCode + '\'' +
                ", className='" + className + '\'' +
                ", classType=" + classType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", schoolId=" + schoolId +
                ", grade=" + grade +
                ", studentAccount=" + studentCount +
                ", courses=" + courses +
                '}';
    }


}

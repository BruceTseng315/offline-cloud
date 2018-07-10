package com.turingmaker.entity.school;

import java.io.Serializable;

public class TSchoolCourse implements Serializable {
    private Long id;

    private Long schoolId;

    private Long courseId;

    private Byte state;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Override
    public String toString() {
         return  "TSchoolCourse{"+
				"id="+this.getId()+
				"schoolId="+this.getSchoolId()+
				"courseId="+this.getCourseId()+
				"state="+this.getState()+"}";
    }
}
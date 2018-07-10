package com.turingmaker.service.operation.response;

import com.turingmaker.service.school.bo.TeacherSelectAss;

import java.util.List;

public class TeacherListResponse {

    private Pageinfo pageinfo;

    private List<TeacherSelectAss> teachers;

    public Pageinfo getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(Pageinfo pageinfo) {
        this.pageinfo = pageinfo;
    }

    public List<TeacherSelectAss> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherSelectAss> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "TeacherListResponse{" +
                "pageinfo=" + pageinfo +
                ", teachers=" + teachers +
                '}';
    }
}

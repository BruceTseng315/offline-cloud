package com.turingmaker.service.operation.response;

import com.turingmaker.entity.school.TTeacher;

import java.util.List;

public class TeacherResponse {

     private Pageinfo pageinfo;

     private List<TTeacher> teacherList;

    public Pageinfo getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(Pageinfo pageinfo) {
        this.pageinfo = pageinfo;
    }

    public List<TTeacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TTeacher> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public String toString() {
        return "TeacherResponse{" +
                "pageinfo=" + pageinfo +
                ", teacherList=" + teacherList +
                '}';
    }
}

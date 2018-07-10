package com.turingmaker.service.operation.response;

import java.util.List;

import com.turingmaker.service.school.bo.StudentBo;

public class StudentListResponse {
    private Pageinfo pageinfo;
    private List<StudentBo> studentBoList;

    public Pageinfo getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(Pageinfo pageinfo) {
        this.pageinfo = pageinfo;
    }

    public List<StudentBo> getStudentBoList() {
        return studentBoList;
    }

    public void setStudentBoList(List<StudentBo> studentBoList) {
        this.studentBoList = studentBoList;
    }

    @Override
    public String toString() {
        return "StudentListResponse{" +
                "pageinfo=" + pageinfo +
                ", studentBoList=" + studentBoList +
                '}';
    }
}

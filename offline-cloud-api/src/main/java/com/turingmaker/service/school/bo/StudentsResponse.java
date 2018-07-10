package com.turingmaker.service.school.bo;

import com.turingmaker.service.operation.response.Pageinfo;

import java.util.List;

/**
 * Created by zengdingyang on 2018\6\28 0028.
 */
public class StudentsResponse {
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
        return "StudentsResponse{" +
                "pageinfo=" + pageinfo +
                ", studentBoList=" + studentBoList +
                '}';
    }
}

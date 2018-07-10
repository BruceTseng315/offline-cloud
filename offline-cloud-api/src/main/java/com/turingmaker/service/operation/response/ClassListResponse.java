package com.turingmaker.service.operation.response;

import java.util.List;

import com.turingmaker.service.school.bo.ClassBo;

public class ClassListResponse {
    private Pageinfo pageinfo;
    private List<ClassBo> classes;

    public Pageinfo getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(Pageinfo pageinfo) {
        this.pageinfo = pageinfo;
    }

    public List<ClassBo> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassBo> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "ClassListResponse{" +
                "pageinfo=" + pageinfo +
                ", classBoList=" + classes +
                '}';
    }
}

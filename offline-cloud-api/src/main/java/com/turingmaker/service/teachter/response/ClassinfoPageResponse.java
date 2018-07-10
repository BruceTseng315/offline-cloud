package com.turingmaker.service.teachter.response;

import com.turingmaker.service.operation.response.LessonResponse;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.teachter.entity.ClassInfo;

import java.util.List;

/**
 * 班级分页列表
 */
public class ClassinfoPageResponse {



    Pageinfo page;

    List<ClassInfo> classes;

    public Pageinfo getPage() {
        return page;
    }

    public void setPage(Pageinfo page) {
        this.page = page;
    }

    public List<ClassInfo> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassInfo> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "ClassinfoResponse{" +
                "page=" + page +
                ", classes=" + classes +
                '}';
    }

    public ClassinfoPageResponse(Pageinfo page, List<ClassInfo> classes) {
        this.page = page;
        this.classes = classes;
    }

    public ClassinfoPageResponse() {
    }
}

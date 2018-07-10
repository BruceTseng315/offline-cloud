package com.turingmaker.service.teachter.response;

import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.teachter.entity.ClassInfo;

import java.util.List;

/**
 * @author  tzj
 *
 */
public class ClassinfoPage {

    private  Pageinfo pageInfo;

    private List<ClassInfo> classes;

    public ClassinfoPage(){}
    public ClassinfoPage(Pageinfo pageInfo, List<ClassInfo> classes) {
        this.pageInfo = pageInfo;
        this.classes = classes;
    }

    public Pageinfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(Pageinfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<ClassInfo> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassInfo> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "ClassinfoPage{" +
                "pageInfo=" + pageInfo +
                ", classes=" + classes +
                '}';
    }
}

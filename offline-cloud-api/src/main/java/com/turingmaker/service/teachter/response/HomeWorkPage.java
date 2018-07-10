package com.turingmaker.service.teachter.response;

import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.teachter.entity.ClassHomeWork;

import java.util.List;

public class HomeWorkPage {

    private Pageinfo pageinfo;

    private List<ClassHomeWork> homeworks;

    public HomeWorkPage() {
    }

    public HomeWorkPage(Pageinfo pageinfo, List<ClassHomeWork> homeworks) {
        this.pageinfo = pageinfo;
        this.homeworks = homeworks;
    }

    public Pageinfo getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(Pageinfo pageinfo) {
        this.pageinfo = pageinfo;
    }

    public List<ClassHomeWork> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<ClassHomeWork> homeworks) {
        this.homeworks = homeworks;
    }

    @Override
    public String toString() {
        return "HomeWorkPage{" +
                "pageinfo=" + pageinfo +
                ", homeworks=" + homeworks +
                '}';
    }
}

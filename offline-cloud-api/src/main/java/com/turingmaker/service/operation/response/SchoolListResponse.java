package com.turingmaker.service.operation.response;

import java.util.List;

public class SchoolListResponse {
    private Pageinfo pageinfo;
    private List<SchoolInfo> schoolInfoList;

    public Pageinfo getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(Pageinfo pageinfo) {
        this.pageinfo = pageinfo;
    }

    public List<SchoolInfo> getSchoolInfoList() {
        return schoolInfoList;
    }

    public void setSchoolInfoList(List<SchoolInfo> schoolInfoList) {
        this.schoolInfoList = schoolInfoList;
    }

    @Override
    public String toString() {
        return "SchoolListResponse{" +
                "pageinfo=" + pageinfo +
                ", schoolInfoList=" + schoolInfoList +
                '}';
    }
}

package com.turingmaker.service.teachter.response;

import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.teachter.entity.StudentStatistics;


import java.util.List;

/**
 * 返回分页对象
 * @author  tzj
 * @param <
 */
public class StudentStatisticsPage{

    private Pageinfo pageInfo;

    private List<StudentStatistics> students;

    public StudentStatisticsPage() {
    }

    public StudentStatisticsPage(Pageinfo pageInfo, List<StudentStatistics> students) {
        this.pageInfo = pageInfo;
        this.students = students;
    }

    public Pageinfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(Pageinfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<StudentStatistics> getStudents() {
        return students;
    }

    public void setStudents(List<StudentStatistics> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "StudentStatisticsPage{" +
                "pageInfo=" + pageInfo +
                ", students=" + students +
                '}';
    }
}

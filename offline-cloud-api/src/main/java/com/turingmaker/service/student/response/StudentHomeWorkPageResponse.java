package com.turingmaker.service.student.response;

import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.student.entity.StudentHomeworkOutline;

import java.util.List;

/**
 * @author  tzj
 * 学生作业列表返回
 */
public class StudentHomeWorkPageResponse {

    private  Pageinfo page;

    /**
     * 作业列表
     */
    List<StudentHomeworkOutline> homeworks;


    public Pageinfo getPage() {
        return page;
    }

    public void setPage(Pageinfo page) {
        this.page = page;
    }

    public List<StudentHomeworkOutline> getStudentHomeWorks() {
        return homeworks;
    }

    public void setStudentHomeWorks(List<StudentHomeworkOutline> studentHomeWorks) {
        this.homeworks = studentHomeWorks;
    }
}

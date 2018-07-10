package com.turingmaker.service.school.bo;

import com.turingmaker.service.school.bo.ClassBo;
import com.turingmaker.service.school.bo.StudentBo;

import java.util.List;

/**
 * Created by zengdingyang on 2018\6\27 0027.
 */
public class ClassDetailResponse  {
    private StudentsResponse students;
    private ClassBo classBo;

    public StudentsResponse getStudents() {
        return students;
    }

    public void setStudents(StudentsResponse students) {
        this.students = students;
    }

    public ClassBo getClassBo() {
        return classBo;
    }

    public void setClassBo(ClassBo classBo) {
        this.classBo = classBo;
    }

    @Override
    public String toString() {
        return "ClassDetailResponse{" +
                "studentBos=" + students.toString() +
                "\n"+
                "classBo="+classBo.toString()+
                '}';
    }
}

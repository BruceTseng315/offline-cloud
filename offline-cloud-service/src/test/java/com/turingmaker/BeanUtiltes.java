package com.turingmaker;

import com.turingmaker.entity.operation.TCourse;
import com.turingmaker.entity.school.TSchoolCourse;
import com.turingmaker.service.operation.response.SchoolCourseInfo;
import com.turingmaker.service.operation.response.SchoolInfo;
import org.springframework.beans.BeanUtils;

public class BeanUtiltes {
    public static void main(String[] args) {
        TCourse course = new TCourse();
        course.setCourseAvatar("//");
        SchoolCourseInfo schoolInfo = new SchoolCourseInfo();
        BeanUtils.copyProperties(course,schoolInfo);
        System.out.println(schoolInfo);
    }

}

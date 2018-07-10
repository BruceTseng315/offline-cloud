package com.turingmaker.controll.controllers;

import com.turingmaker.common.config.Constant;
import com.turingmaker.common.domain.Result;
import com.turingmaker.controll.vo.UserSession;
import com.turingmaker.service.operation.response.SchoolCourseInfo;
import com.turingmaker.service.school.api.SchoolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/turing/api/v2/school_manage")
public class CourseController {
    @Autowired
    SchoolService schoolService;

    /**
     * 获取学校所有开设的课程
     * @param session
     * @return
     */
    @GetMapping("/course/all")
    public Result getAllCourseBySchoolId(HttpSession session){
        UserSession userSession = (UserSession)session.getAttribute(Constant.SESSION_USER_KEY);
        long schoolId = userSession.getSchoolId();

        List<SchoolCourseInfo> schoolCourseInfoList = schoolService.findCoursesBySchoolId(schoolId);

        return Result.success(schoolCourseInfoList);
    }
}

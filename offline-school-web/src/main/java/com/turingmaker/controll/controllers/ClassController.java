package com.turingmaker.controll.controllers;

import com.alibaba.fastjson.JSONObject;
import com.turingmaker.common.domain.Result;
import com.turingmaker.controll.vo.UserSession;
import com.turingmaker.entity.operation.TCourse;
import com.turingmaker.entity.school.TClass;
import com.turingmaker.service.api.DataimportService;
import com.turingmaker.service.operation.request.AddClassRequest;
import com.turingmaker.service.operation.request.ClassListRequest;
import com.turingmaker.service.operation.response.ClassListResponse;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.school.api.ClassService;
import com.turingmaker.service.school.bo.ClassBo;
import com.turingmaker.service.school.bo.ClassDetailResponse;
import com.turingmaker.service.school.bo.ClassEditRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/turing/api/v2/school_manage/class")
public class ClassController {

    @Autowired
    ClassService classService;


    Logger logger= LoggerFactory.getLogger(getClass());
    /**
     * 获取指定学校班级详情列表
     * @param session
     * @param classListRequest
     * @return
     */
    @PostMapping("/all/details")
    public Result getClassDetailsList(HttpSession session,@RequestBody ClassListRequest classListRequest){
        UserSession userSession = (UserSession)session.getAttribute("user");
        long schoolId = userSession.getSchoolId();

        ClassListResponse classListResponse = classService.getClassDetailsList(schoolId,classListRequest);

        return Result.success(classListResponse);

    }

    @GetMapping("/all")
    public Result getAllClass(HttpSession session) {
        UserSession userSession = (UserSession)session.getAttribute("user");
        logger.info("userSession:"+userSession);
        Long schoolId = userSession.getSchoolId();

        if(schoolId == null) {
            return Result.success(null);
        }
        List<TClass> classList = classService.getAllClassBySchoolId(schoolId);

        return Result.success(classList);
    }

    /**
     * 新增班级
     * @param session
     * @param addClassRequest
     * @return
     */
    @PostMapping("/add")
    public Result addClass(HttpSession session,@RequestBody AddClassRequest addClassRequest){
        UserSession userSession = (UserSession)session.getAttribute("user");
        long schoolId = userSession.getSchoolId();

        ClassBo classBo = classService.addClass(schoolId,addClassRequest);

        return Result.success(classBo);
    }

    /**
     * 班级课程开课
     * @param params
     * @return
     */
    @PostMapping("/course/open")
    public Result classCourseOpen(@RequestBody  JSONObject params){
        long classId = params.getLong("classId");
        long courseId = params.getLong("courseId");
        classService.classCourseOpen(classId,courseId);

        return Result.successresult;
    }

    /**
     * 班级课程停课
     * @param params
     * @return
     */
    @PostMapping("/course/close")
    public Result classCourseClose(@RequestBody  JSONObject params){
        long classId = params.getLong("classId");
        long courseId = params.getLong("courseId");
        classService.classCourseClose(classId,courseId);

        return Result.successresult;
    }

    /**
     * 编辑班级
     * @param classEditRequest
     * @return
     */
    @PostMapping("/edit")
    public Result editClass(@RequestBody ClassEditRequest classEditRequest){
        logger.info("request:"+classEditRequest.toString());
        if(classEditRequest.getClassId() == null) {
            return Result.errorrResult("班级id不能为空");
        }
        classService.editClass(classEditRequest);

        return Result.successresult;
    }

    /**
     * 导入学生信息
     * @param
     * @param file
     * @return
     */
    @PostMapping("/import_students")
    public Result importStudents(@RequestParam(value = "classId")Long classId,@RequestParam(value = "file") MultipartFile file) {

        try {
            InputStream is = file.getInputStream();
            classService.importStudents(classId,is);
        }catch (IOException e){
            e.printStackTrace();
            if(logger.isDebugEnabled()) {
                logger.warn("接收文件异常："+e.getMessage());
            }
        }
        return Result.successresult;
    }

    /**
     * 导入班级
     * @param session
     * @param classType
     * @param file
     * @return
     */
    @PostMapping("/import_classes")
    public Result importClasses(HttpSession session,@RequestParam(value="classType")Byte classType,@RequestParam(value = "file")MultipartFile file) {
        UserSession userSession = (UserSession)session.getAttribute("user");
        Long schoolId = userSession.getSchoolId();
        try{
            InputStream is = file.getInputStream();
            classService.importClasses(schoolId,classType,is);
        }catch(Exception e){
            e.printStackTrace();
            if(logger.isDebugEnabled()) {
                logger.warn("接收文件异常："+e.getMessage());
            }
            return Result.errorrResult("接受文件出错");
        }

        return Result.successresult;
    }

    /**
     * 获取指定班级详情
     * @param classId
     * @return
     */
    @GetMapping("/details")
    public Result getClassDetailsByClassId(@RequestParam("classId")Long classId,@RequestParam("pageNo")Integer pageNo,@RequestParam("pageSize")Integer pageSize) {
        Pageinfo pageinfo = new Pageinfo();
        pageinfo.setPageNo(pageNo);
        pageinfo.setPageSize(pageSize);
        ClassDetailResponse classDetailResponse = classService.getClassDetailsByClassId(pageinfo,classId);

        return Result.success(classDetailResponse);
    }

    /**
     * 获取班级可开课程
     * @param session
     * @param classId
     * @return
     */
    @GetMapping("/course_add_able")
    public Result getClassAddableCourses(HttpSession session,@RequestParam("classId")Long classId){
        UserSession userSession = (UserSession)session.getAttribute("user");
        long schoolId = userSession.getSchoolId();

        List<TCourse> courseList = classService.getClassAddableCourses(schoolId,classId);

        return Result.success(courseList);

    }
}

package com.turingmaker.controll.controllers;

import com.alibaba.fastjson.JSONObject;
import com.turingmaker.common.domain.Result;
import com.turingmaker.controll.config.Constant;
import com.turingmaker.controll.vo.UserSession;
import com.turingmaker.dao.mapper.operation.ext.UserMapperExt;
import com.turingmaker.dao.mapper.school.ext.TTeacherMapperExt;
import com.turingmaker.entity.operation.YUser;
import com.turingmaker.entity.school.TTeacher;
import com.turingmaker.service.api.DataexportService;
import com.turingmaker.service.operation.api.DataMode;
import com.turingmaker.service.operation.response.SchoolInfo;
import com.turingmaker.service.operation.response.TeacherListResponse;
import com.turingmaker.service.operation.response.TeacherResponse;
import com.turingmaker.service.school.api.TeacherService;

import com.turingmaker.service.school.bo.ExportTeacherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/turing/api/v2/school_manage/teacher/")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    TTeacherMapperExt teacherMapperExt;

    @Autowired
    UserMapperExt userMapperExt;
    @Autowired
    DataexportService dataexportService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
    * @Description:获得学校所有教师
    * @Param:
    * @return:
    * @Date: 2018/6/21
    */
    @GetMapping("all")
    public Result getAllTeacher(HttpSession session,@RequestParam("pageNo") Integer pageNo , @RequestParam("pageSize") Integer pageSize){
        UserSession userSession = (UserSession) session.getAttribute("user");
        TeacherResponse teacherResponse =  teacherService.findAllTeacherBySchoolId(userSession.getSchoolId(),pageNo,pageSize);
        return Result.success(teacherResponse);
    }


    /**
    * @Description:  添加一个老师信息
    * @Param: [session, teacherName, phone]
    * @return:
    */
    @PostMapping("add")
    public Result addTeacher(HttpSession session , @RequestBody JSONObject params){
        String teacherName = params.getString("teacherName");
        String phone = params.getString("phone");

        UserSession userSession = (UserSession) session.getAttribute("user");
        YUser user = teacherService.addTeacher(userSession.getSchoolId(),teacherName,phone);
        return Result.success(user);
    }

    /**
     * @Description:  获取老师账号信息
     * @Param: teacherId
     * @return:
     * @Date: 2018/6/20
     */
    @GetMapping("account")
    public Result<?> getSchoolAdmin(Long teacherId){
        TTeacher teacher = teacherMapperExt.selectByPrimaryKey(teacherId);
        if(teacher==null){
            return Result.errorrResult("该老师不存在");
        }
        YUser user = userMapperExt.selectByPrimaryKey(teacher.getAccountId());
        Map<String,String> map = new HashMap<>();
        map.put("teacherName",teacher.getTeacherName());
        map.put("account",user.getAccount());
        map.put("password",user.getPassword());
        return new Result<Object>(map);

    }



    /**
    * @Description:  编辑教师信息并选择是否重置密码
    * @Param: [teacherId, teacherName, phone, refresh]
    * @return: com.turingmaker.common.domain.Result
    * @Date: 2018/6/21
    */
    @GetMapping("edit")
    public Result updateTeacher(Long teacherId ,String teacherName ,String phone ,Integer refresh){
        teacherService.updateInfo(teacherId,teacherName,phone,refresh);
        return Result.successresult;
    }

    /**
    * @Description:  获取单个老师详情
    * @Param: [teacherId]
    * @return: com.turingmaker.common.domain.Result
    * @Date: 2018/6/21
    */
    @GetMapping("details")
    public Result getTeacherDetail(Long teacherId){
        TTeacher tTeacher = teacherMapperExt.selectByPrimaryKey(teacherId);
        YUser user = userMapperExt.selectByPrimaryKey(tTeacher.getAccountId());
        Map<String,Object> map = new HashMap<>();
        map.put("teacher",tTeacher);
        map.put("account",user.getAccount());
        return Result.success(map);
    }
    /**
    * @Description:根据参数查询老师列表
    * @Param: [teacherCode, teacherName, className, courseCode, pageNo, pageSize]
    * @return: com.turingmaker.common.domain.Result
    * @Date: 2018/6/22
    */
    @GetMapping("all/list")
    public Result selectTeacherList(HttpSession session,@RequestParam(value = "teacherCode",required = false) String teacherCode,@RequestParam(value = "teacherName",required = false) String teacherName,@RequestParam(value = "className",required = false) String className,
                                    @RequestParam(value = "courseId",required = false) Long courseId,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
        UserSession userSession = (UserSession) session.getAttribute(Constant.SESSION_KEY_USER);
        Long schoolId = userSession.getSchoolId();
        TeacherListResponse response = teacherService.getTeacherListAssPage(schoolId,teacherCode,teacherName,className,courseId,pageNo,pageSize);

        return Result.success(response);
    }

    /**
     * 导出教师信息
     * @param session
     * @param response
     * @param teacherCode
     * @param teacherName
     * @param className
     * @param courseId
     */
    @GetMapping("/export")
    public void exportTeachers(HttpSession session, HttpServletResponse response,@RequestParam(value = "teacherCode",required = false) String teacherCode, @RequestParam(value = "teacherName",required = false) String teacherName,
                                 @RequestParam(value = "className",required = false) String className, @RequestParam(value = "courseId",required = false) Long courseId) {

        UserSession userSession = (UserSession) session.getAttribute(Constant.SESSION_KEY_USER);
        Long schoolId = userSession.getSchoolId();
        List<ExportTeacherResponse> exportTeacherResponseList = teacherService.getTeacherForExport(schoolId,teacherCode,teacherName,className,courseId);

        DataMode<ExportTeacherResponse> dataMode=new DataMode<>();
        Map<String, String> map=new HashMap<>();
        map.put("教师姓名","teacherName");
        map.put("授课班级","classIn");
        map.put("授课课程","course");
        map.put("教师账号","account");
        map.put("密码","password");
        dataMode.setColumnandFieldNameMap(map);

        String fileName = "教师信息表格" + System.currentTimeMillis() + ".xls";
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"),"ISO8859_1"));
            dataexportService.exportExcel(response.getOutputStream(),dataMode,exportTeacherResponseList);
        }catch(IOException e){
            logger.warn("下载失败",e);
        }
    }
}

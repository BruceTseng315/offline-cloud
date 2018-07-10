package com.turingmaker.controll.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.turingmaker.common.domain.Result;
import com.turingmaker.controll.vo.UserSession;
import com.turingmaker.dao.mapper.school.ext.TStudentMapperExt;
import com.turingmaker.service.api.DataexportService;
import com.turingmaker.service.operation.api.DataMode;
import com.turingmaker.service.operation.response.StudentListResponse;
import com.turingmaker.service.school.api.StudentService;
import com.turingmaker.service.school.bo.StudentAddRequest;
import com.turingmaker.service.school.bo.StudentBo;
import com.turingmaker.service.school.entity.QueryStudentExportAccount;
import com.turingmaker.service.school.request.StudentEditRequest;
import com.turingmaker.service.school.request.StudentListRequest;

@RestController
@RequestMapping("/turing/api/v2/school_manage/student")
public class StudentController {
	
	
	
	Logger logger=LoggerFactory.getLogger(getClass());
    @Autowired
    StudentService studentService;

    @Autowired
    TStudentMapperExt studentMapperExt;
    
    @Autowired
    DataexportService dataexportService;
    /**
     * 获取学生详情
     * @param studentId
     * @return
     */
    @GetMapping("/details")
    public Result getStudentDetailByStudentId(@RequestParam("studentId")Long studentId){
        StudentBo studentBo = studentService.getStudentByStudentId(studentId);

        return Result.success(studentBo);
    }

    /**
     * 编辑学生信息
     * @param studentEditRequest
     * @return
     */
    @PostMapping("/edit")
    public Result editStudent(@RequestBody StudentEditRequest studentEditRequest){
        studentService.editStudent(studentEditRequest);

        return Result.successresult;
    }

    /**
     * 获取指定学校学生详情列表
     * @param session
     * @param studentListRequest
     * @return
     */
    @PostMapping("/all")
    public Result getStudentDetailList(HttpSession session, @RequestBody StudentListRequest studentListRequest){
        UserSession userSession = (UserSession)session.getAttribute("user");
        long schoolId = userSession.getSchoolId();
        StudentListResponse studentListResponse = studentService.getStudentDetailsList(schoolId,studentListRequest);

        return Result.success(studentListResponse);
    }

    
    /**
     * 获取指定学校学生详情列表
     * @param
     * @param
     * @return
     */
    @GetMapping("/export")
    public void exportStudentDetailList(HttpServletResponse response,Long classId){
      
		DataMode<QueryStudentExportAccount> dataMode=new DataMode<>();
		Map<String, String> map=new HashMap<>();
		map.put("姓名", "studentName");
		map.put("账号", "studentAccount");
		map.put("密码", "studentPassword");
		dataMode.setColumnandFieldNameMap(map);

		
        try {
        	response.setHeader("Content-Disposition", "attachment;filename=" + new String("学生信息表格.xls".getBytes("GBK"),"ISO8859_1"));
			dataexportService.exportExcel(response.getOutputStream(), dataMode, studentMapperExt.findStudentListByClassIdForExport(classId));
		} catch (IOException e) {
			logger.warn("下载失败",e);
		}
       
        
    }
    
    /**
     * 学校新增学生到指定班级
     * @param studentAddRequest
     * @return
     */
    @PostMapping("/add")
    @Transactional
    public Result addStudentToSchool(@RequestBody StudentAddRequest studentAddRequest) {
        StudentBo studentBo = studentService.addStudent(studentAddRequest);

        return Result.success(studentBo);
    }

    /**
     * 班级里移出指定学生
     * @param
     *
     * @return
     */
    @PostMapping("/delete")
    public Result deleteStudentFromClass(@RequestBody JSONObject params) {
        Long classId = params.getLong("classId");
        Long studentId = params.getLong("studentId");
        studentService.deleteStudentFromClass(classId,studentId);
        return Result.successresult;
    }
}

package com.turingmaker.controll.controllers;

import com.turingmaker.common.domain.Result;
import com.turingmaker.entity.school.TProgram;
import com.turingmaker.service.student.api.StudentWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zengdingyang
 * @Date 2018\7\9 0009
 */
@RestController
@RequestMapping("/turing/api/v2/student")
public class StudentController {
    @Autowired
    StudentWorkService studentWorkService;

    /**
     *
     * 获取程序代码详情
     * @param programCodeId
     * @return
     */
    @GetMapping("/program/detail")
    public Result getProgramDetail(@RequestParam("programCodeId")Long programCodeId) {
        TProgram program = studentWorkService.getProgramDetail(programCodeId);

        return Result.success(program);
    }
}

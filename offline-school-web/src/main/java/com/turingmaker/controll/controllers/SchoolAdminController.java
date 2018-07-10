package com.turingmaker.controll.controllers;

import com.alibaba.fastjson.JSONObject;
import com.turingmaker.common.domain.Result;
import com.turingmaker.service.school.api.SchoolAdminService;
import com.turingmaker.service.school.bo.RefreshPasswordBo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/turing/api/v2/school_manage")
public class SchoolAdminController {
    @Autowired
    SchoolAdminService schoolAdminService;

    /**
     * 重置指定账户密码
     * @param session
     * @param params
     * @return
     */
    @PostMapping("/password/refresh")
    public Result passwordRefresh(HttpSession session, @RequestBody JSONObject params){
        long accountId = params.getLong("accountId");
        RefreshPasswordBo refreshPasswordBo = schoolAdminService.refreshPassword(accountId);

        return Result.success(refreshPasswordBo);
    }
}

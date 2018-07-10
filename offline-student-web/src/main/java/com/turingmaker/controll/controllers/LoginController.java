package com.turingmaker.controll.controllers;

import com.alibaba.fastjson.JSONObject;
import com.turingmaker.common.domain.Result;
import com.turingmaker.controll.config.StudentSession;
import com.turingmaker.dao.mapper.operation.ext.UserMapperExt;
import com.turingmaker.dao.mapper.school.ext.TStudentMapperExt;
import com.turingmaker.entity.operation.YUser;
import com.turingmaker.entity.school.TStudent;
import com.turingmaker.service.api.AuthService;
import com.turingmaker.service.exception.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author zengdingyang
 * @Date 2018\7\5 0005
 */
@RestController
@RequestMapping("/turing/api/v2/student")
public class LoginController {
    @Autowired
    AuthService authService;
    @Autowired
    UserMapperExt userMapperExt;
    @Autowired
    TStudentMapperExt studentMapperExt;

    final String ROLE_NAME = "ROLE_STUDENT";
    final String SESSION_USER_KEY = "user";

    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/login")
    public Result login(HttpSession session, @RequestBody JSONObject param) {
        String accountName = param.getString("accountName");
        String password = param.getString("password");

        try{
            authService.userLoginByPassword(accountName,password,ROLE_NAME);
        }catch (Exception e){
            logger.warn(accountName+"登录权限校验失败"+e.getMessage());
            throw new HttpException(e.getMessage());
        }

        YUser user = userMapperExt.findUserByUserName(accountName);
        TStudent student = studentMapperExt.findStudentByAccountId(user.getId());
        StudentSession studentSession = new StudentSession();
        studentSession.setUser(user);
        studentSession.setStudent(student);
        session.setAttribute(SESSION_USER_KEY,studentSession);

        return Result.successResult("登陆成功");
    }

    @GetMapping("/login_out")
    public Result loginOut(HttpSession session){
        session.removeAttribute(SESSION_USER_KEY);
        return Result.successResult("退出成功");
    }

   @GetMapping("/check_login")
    public Result checkLogin(HttpSession session) {
        Object o = session.getAttribute(SESSION_USER_KEY);
        JSONObject object = new JSONObject();
        if(o == null){
            object.put("login",false);
        }else{
            object.put("login",true);
        }

        return Result.success(object);
    }
}

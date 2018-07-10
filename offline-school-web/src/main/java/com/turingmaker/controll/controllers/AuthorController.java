package com.turingmaker.controll.controllers;

import com.alibaba.fastjson.JSONObject;
import com.turingmaker.common.config.Constant;
import com.turingmaker.common.domain.Result;
import com.turingmaker.controll.vo.UserSession;
import com.turingmaker.dao.mapper.operation.ext.UserMapperExt;
import com.turingmaker.entity.operation.YUser;
import com.turingmaker.entity.school.TSchoolAdmin;
import com.turingmaker.service.api.AuthService;
import com.turingmaker.service.school.api.SchoolAdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/turing/api/v2/school_manage")
public class AuthorController {
    @Autowired
    AuthService authService;
    @Autowired
    SchoolAdminService schoolAdminService;
    @Autowired
    UserMapperExt userMapperExt;

    final String ROLE_NAME = "ROLE_SCHOOL_ADMIN";

    Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 登录
     * @param session
     * @param params
     * @return
     */
    @PostMapping("/login")
    public Result login(HttpSession session, @RequestBody JSONObject params){
        String accountName = params.getString("accountName");
        String password = params.getString("password");
        authService.userLoginByPassword(accountName,password,ROLE_NAME);
        UserSession userSession = new UserSession();
        YUser user = userMapperExt.findUserByUserName(accountName);
        logger.info("user:"+user);
        TSchoolAdmin schoolAdmin = schoolAdminService.findSchoolAdminByAccountId(user.getId());
        logger.info("schooladmin:"+schoolAdmin);
        userSession.setAccountId(user.getId());
        userSession.setAccountName(user.getAccount());
        userSession.setSchoolId(schoolAdmin.getSchoolId());

        session.setAttribute(Constant.SESSION_USER_KEY,userSession);

        return Result.successresult;
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @PostMapping("/login_out")
    public Result loginOut(HttpSession session){
        if(session.getAttribute(Constant.SESSION_USER_KEY) != null) {
            session.removeAttribute(Constant.SESSION_USER_KEY);
            return Result.successResult("退出成功！");
        }else{
            return Result.errorrResult("尚未登录");
        }
    }

    /**
     * 检查人是否登录
     * @param session
     * @return
     */
    @PostMapping("/check_login")
    public Result checkLogin(HttpSession session){
        JSONObject result = new JSONObject();
        if(session.getAttribute(Constant.SESSION_USER_KEY) == null) {
            result.put("login",false);
            return Result.success(result);
        }else{
            UserSession userSession = (UserSession)session.getAttribute(Constant.SESSION_USER_KEY);
            result.put("login",true);
            result.put("account",userSession.getAccountName());

            return Result.success(result);
        }
    }
    /**
     * @Description:  返回初始密码
     * @Param: []
     * @return: com.turingmaker.common.domain.Result
     * @Date: 2018/6/21
     */
    @GetMapping("initial_password")
    public Result getInitialPassword(){
        Map<String,String> map = new HashMap<>();
        map.put("password","123456");
        return Result.success(map);
    }
}

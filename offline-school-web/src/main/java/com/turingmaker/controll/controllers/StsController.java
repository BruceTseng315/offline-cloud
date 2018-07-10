package com.turingmaker.controll.controllers;

import com.turing.common.sts.OSSCredential;
import com.turingmaker.common.domain.Result;
import com.turingmaker.service.api.StsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by zengdingyang on 2018\6\26 0026.
 */
@RestController
@RequestMapping("/turing/api/v2/sts")
public class StsController {
    @Autowired
    StsService stsService;
    /**
     * 获取oss上传凭证
     * @param session
     * @param type
     * @return
     */
    @RequestMapping(value = "/credential",method = RequestMethod.GET)
    public Result getStsToken(HttpSession session, String type){

        System.out.println("type:"+type);
        OSSCredential result = stsService.getOSSCredential(type);

        return Result.success(result);

    }
}

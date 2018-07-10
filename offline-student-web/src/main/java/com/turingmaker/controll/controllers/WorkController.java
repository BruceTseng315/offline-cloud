package com.turingmaker.controll.controllers;

import com.alibaba.fastjson.JSONObject;
import com.turingmaker.common.domain.Result;
import com.turingmaker.controll.config.Constant;
import com.turingmaker.controll.config.StudentSession;
import com.turingmaker.entity.teachter.TWork;
import com.turingmaker.service.exception.HttpException;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.student.api.CourseService;
import com.turingmaker.service.student.api.StudentWorkService;
import com.turingmaker.service.student.response.StudentWorkDetailResponse;
import com.turingmaker.service.work.api.WorkService;
import com.turingmaker.service.work.entity.WorkDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author zengdingyang
 * @Date 2018\7\5 0005
 */
@RestController
@RequestMapping("/turing/api/v2/student/work")
public class WorkController {
    @Autowired
    CourseService studentService;
    @Autowired
    StudentWorkService studentWorkService;
    @Autowired
    WorkService workService;

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取学生作品列表
     * @param session
     * @param pageSize
     * @param pageNo
     * @return
     */
    @GetMapping("/list")
    public Result getWorList(HttpSession session, @RequestParam("pageSize")Integer pageSize,@RequestParam("pageNo")Integer pageNo,@RequestParam(value = "state",required = false)Integer state) {
        StudentSession studentSession = (StudentSession) session.getAttribute(Constant.SESSION_KEY_USER);
        long accountId = studentSession.getAccountId();
        Pageinfo pageinfo = new Pageinfo();
        pageinfo.setPageSize(pageSize);
        pageinfo.setPageNo(pageNo);
        List<TWork> works = studentWorkService.getWorkListByPage(pageinfo,accountId,state);

        JSONObject result = new JSONObject();
        result.put("pageInfo",pageinfo);
        result.put("works",works);

        return Result.success(result);
    }

    /**
     * 保存作品
     * @param session
     * @param
     * @param
     * @return
     */
    @PostMapping("/save")
    public Result saveWork(HttpSession session,@RequestBody JSONObject param) {
        String workName = param.getString("workName");
        String programCode = param.getString("programCode");
        if(workName == null || programCode == null) {
            throw new HttpException("作品名称或程序代码不能为空");
        }
        StudentSession studentSession = (StudentSession) session.getAttribute(Constant.SESSION_KEY_USER);
        long accountId = studentSession.getAccountId();
        String studentName = studentSession.getStudentName();
        TWork work =studentWorkService.saveWork(accountId,studentName,workName,programCode);

        return Result.success(work);
    }

    /**
     * 删除作品
     * @param workId
     * @return
     */
    @PostMapping("/delete")
    public Result deleteWork(@RequestParam("workId")Long workId) {
        studentWorkService.deleteWork(workId);

        return Result.successresult;
    }

    /**
     * 获取作品详情
     * @param workId
     * @return
     */
    @GetMapping("/detail")
    public Result getWorkDetail(HttpSession session,@RequestParam("workId")Long workId){
        StudentSession studentSession = (StudentSession)session.getAttribute(Constant.SESSION_KEY_USER);
        long userId = studentSession.getAccountId();

        StudentWorkDetailResponse studentWorkDetailResponse = new StudentWorkDetailResponse();
        WorkDetail workDetail = studentWorkService.getWorkDetail(workId);
        if(workDetail == null){
            throw new HttpException("作品不存在");
        }
        BeanUtils.copyProperties(workDetail,studentWorkDetailResponse);
        Boolean liked = workService.isLikeed(userId,workId);
        studentWorkDetailResponse.setLiked(liked);


        return Result.success(studentWorkDetailResponse);
    }

    /**
     * 作品发布
     * @param
     * @param
     * @param
     * @param
     * @return
     */
    @PostMapping("/publish")
    public Result publishWork(@RequestBody JSONObject param){
        Long workId = param.getLong("workId");
        String workName = param.getString("workName");
        String workAvatar = param.getString("workAvatar");
        String workDescription = param.getString("workDescription");
        if(workAvatar == null || workId == null || workName == null){
            throw new HttpException("作品id，封面地址，作品名称不能为空");
        }
        studentWorkService.publishWork(workId,workAvatar,workName,workDescription);

        return Result.successresult;
    }

    /**
     * 作品取消发布
     * @param
     * @return
     */
    @PostMapping("/cancel_publish")
    public Result cancelPublish(@RequestBody JSONObject param){
        Long workId = param.getLong("workId");
        if(workId == null) {
            throw new HttpException("作品id不能为空");
        }
        studentWorkService.cancelPublish(workId);

        return Result.successresult;
    }
}

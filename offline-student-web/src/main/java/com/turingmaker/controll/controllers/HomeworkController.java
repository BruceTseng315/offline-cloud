package com.turingmaker.controll.controllers;

import com.alibaba.fastjson.JSONObject;
import com.turingmaker.common.config.Constant;
import com.turingmaker.common.domain.Result;
import com.turingmaker.controll.config.StudentSession;
import com.turingmaker.entity.teachter.THomework;
import com.turingmaker.service.exception.HttpException;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.student.api.HomeworkService;
import com.turingmaker.service.student.entity.StudentHomeWorkDetail;
import com.turingmaker.service.student.entity.StudentHomeworkOutline;
import com.turingmaker.service.student.response.StudentHomeWorkPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author zengdingyang
 * @Date 2018\7\5 0005
 */
@RestController
@RequestMapping("/turing/api/v2/student/homework")
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;

    /**
     * 保存作业
     * @param session
     * @param
     * @param
     * @param
     * @param
     * @param
     * @return
     */
    @PostMapping("/save")
    public Result saveHomework(HttpSession session,@RequestBody JSONObject param){
        StudentSession studentSession = (StudentSession)session.getAttribute(Constant.SESSION_USER_KEY);
        Long studentId = studentSession.getStudentId();

        String homeworkName = param.getString("homeworkName");
        String programCode = param.getString("programCode");
        Long taskId = param.getLong("taskId");
        String homeworkAvatar = param.getString("homeworkAvatar");
        String homeworkDesc = param.getString("homeworkDesc");
        if(homeworkAvatar == null || taskId == null || programCode == null){
            throw new HttpException("封面地址、任务id、程序代码不能为空");
        }
        THomework homework = homeworkService.saveHomeWork(studentId,taskId,homeworkDesc,homeworkName,programCode,homeworkAvatar);

        return Result.success(homework);
    }

    /**
     * 修改作业
     * @param param
     * @return
     */
    @PostMapping("/update")
    public Result updateHomework(@RequestBody JSONObject param) {
        Long homeworkId = param.getLong("homeworkId");
        String homeworkName = param.getString("homeworkName");
        String programCode = param.getString("programCode");

        THomework homework = homeworkService.updateHomework(homeworkId,homeworkName,programCode);

        return Result.success(homework);

    }

    /**
     * 删除作业
     * @param
     * @return
     */
    @PostMapping("/delete")
    public Result deleteHomework(@RequestBody JSONObject param) {
        Long homeworkId = param.getLong("homeworkId");
        homeworkService.deleteHomework(homeworkId);

        return Result.successresult;
    }

    /**
     * 获取作业详情
     * @param session
     * @param homeworkId
     * @return
     */
    @GetMapping("/detail")
    public Result getHomeworkDetail(HttpSession session,@RequestParam("homeworkId")Long homeworkId) {
        StudentSession studentSession = (StudentSession)session.getAttribute(Constant.SESSION_USER_KEY);
        Long studentId = studentSession.getStudentId();
        StudentHomeWorkDetail studentHomeWorkDetail = homeworkService.getHomeworkDetail(homeworkId);
        Boolean liked = homeworkService.isLiked(studentId,homeworkId);
        studentHomeWorkDetail.setLiked(liked);

        return Result.success(studentHomeWorkDetail);

    }

    /**
     * 学生作业列表
     * @param session
     * @param pageSize
     * @param pageNo
     * @return
     */
    @GetMapping("/list")
    public Result getHomeworkListPage(HttpSession session,@RequestParam("pageSize")Integer pageSize,@RequestParam("pageNo")Integer pageNo,@RequestParam(value = "mark",required = false)Integer mark) {
        StudentSession studentSession = (StudentSession)session.getAttribute(Constant.SESSION_USER_KEY);
        Long studentId = studentSession.getStudentId();
        Pageinfo pageinfo = new Pageinfo();
        pageinfo.setPageNo(pageNo);
        pageinfo.setPageSize(pageSize);

        List<StudentHomeworkOutline> studentHomeworkOutlines = homeworkService.getStudentHomeworkListPage(pageinfo,studentId,mark);
        if(studentHomeworkOutlines != null && studentHomeworkOutlines.size() > 0){
            for (StudentHomeworkOutline studentHomeworkOutline:studentHomeworkOutlines){
                studentHomeworkOutline.calScore();
            }
        }

        StudentHomeWorkPageResponse studentHomeWorkPageResponse = new StudentHomeWorkPageResponse();
        studentHomeWorkPageResponse.setPage(pageinfo);
        studentHomeWorkPageResponse.setStudentHomeWorks(studentHomeworkOutlines);

        return Result.success(studentHomeWorkPageResponse);
    }
}

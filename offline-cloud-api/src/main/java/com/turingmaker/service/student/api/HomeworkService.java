package com.turingmaker.service.student.api;

import com.turingmaker.entity.teachter.THomework;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.student.entity.StudentHomeWorkDetail;
import com.turingmaker.service.student.entity.StudentHomeworkOutline;

import java.util.List;

/**
 * @Author zengdingyang
 * @Date 2018\7\5 0005
 */
public interface HomeworkService {
    /**
     * 保存作业
     * @param studentId
     * @param taskId
     * @param homeworkDesc
     * @param homeworkName
     * @param programCode
     * @param homeworkAvatar
     * @return
     */
    THomework saveHomeWork(Long studentId, Long taskId, String homeworkDesc, String homeworkName, String programCode, String homeworkAvatar);
    void deleteHomework(Long homeworkId);

    /**
     * 获取作业详情
     * @param homeworkId
     * @return
     */
    StudentHomeWorkDetail getHomeworkDetail(Long homeworkId);

     boolean isLiked(Long userId,Long homeworkId);

    /**
     * 获取作业列表
     * @param pageinfo
     * @param studentId
     * @return
     */
     List<StudentHomeworkOutline> getStudentHomeworkListPage(Pageinfo pageinfo,Long studentId,Integer mark);

    /**
     * 修改作业
     * @param homeworkId
     * @param homeworkName
     * @param programCode
     * @return
     */
     THomework updateHomework(Long homeworkId,String homeworkName,String programCode);
}

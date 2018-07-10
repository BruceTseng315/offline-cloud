package com.turingmaker.service.student.impl;

import com.turingmaker.dao.mapper.school.ext.ProgramMapperExt;
import com.turingmaker.dao.mapper.teachter.ext.HomeworkMapperExt;
import com.turingmaker.entity.school.TProgram;
import com.turingmaker.entity.teachter.THomework;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.student.api.HomeworkService;
import com.turingmaker.service.student.entity.StudentHomeWorkDetail;
import com.turingmaker.service.student.entity.StudentHomeworkOutline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zengdingyang
 * @Date 2018\7\5 0005
 */
@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    HomeworkMapperExt homeworkMapperExt;
    @Autowired
    ProgramMapperExt programMapperExt;

    /**
     * 保存作业
     * @param studentId
     * @param homeworkDesc
     * @param homeworkName
     * @param programCode
     * @param homeworkAvatar
     */
    @Override
    public THomework saveHomeWork(Long studentId,Long taskId, String homeworkDesc, String homeworkName, String programCode, String homeworkAvatar) {
        //保存程序
        TProgram program = new TProgram();
        program.setProgramName(homeworkName);
        program.setProgramContent(programCode);
        programMapperExt.insertSelective(program);

        THomework homework = new THomework();
        homework.setHomeworkAvatar(homeworkAvatar);
        homework.setHomeworkDesc(homeworkDesc);
        homework.setHomeworkName(homeworkName);
        homework.setStudentId(studentId);
        homework.setProgramId(program.getId());
        homework.setTaskId(taskId);
        homeworkMapperExt.insertSelective(homework);

        homework = homeworkMapperExt.selectByPrimaryKey(homework.getId());

        return homework;

    }

    @Override
    public void deleteHomework(Long homeworkId) {
        THomework homework = homeworkMapperExt.selectByPrimaryKey(homeworkId);
        homework.setIsDelete(1);
        homeworkMapperExt.updateByPrimaryKeySelective(homework);
    }

    @Override
    public StudentHomeWorkDetail getHomeworkDetail(Long homeworkId) {
        StudentHomeWorkDetail studentHomeWorkDetail = homeworkMapperExt.getHomeworkDetail(homeworkId);
        studentHomeWorkDetail.calScore();

        return studentHomeWorkDetail;
    }

    @Override
    public boolean isLiked(Long userId,Long homeworkId){
        return homeworkMapperExt.checkUserIsLiked(userId,homeworkId);
    }

    @Override
    public List<StudentHomeworkOutline> getStudentHomeworkListPage(Pageinfo pageinfo, Long studentId,Integer mark) {

        return homeworkMapperExt.getStudentHomeworkOutlinePage(pageinfo,studentId,mark);
    }

    @Override
    public THomework updateHomework(Long homeworkId, String homeworkName, String programCode) {
        THomework homework = homeworkMapperExt.selectByPrimaryKey(homeworkId);
        //保存程序
        TProgram program = new TProgram();
        program.setProgramName(homeworkName);
        program.setProgramContent(programCode);
        programMapperExt.insertSelective(program);

        homework.setHomeworkName(homeworkName);
        homework.setProgramId(program.getId());
        homeworkMapperExt.updateByPrimaryKeySelective(homework);

        homework = homeworkMapperExt.selectByPrimaryKey(homeworkId);

        return homework;
    }
}

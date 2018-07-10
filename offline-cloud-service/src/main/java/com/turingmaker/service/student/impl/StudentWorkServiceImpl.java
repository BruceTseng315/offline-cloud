package com.turingmaker.service.student.impl;

import com.turingmaker.dao.mapper.school.ext.ProgramMapperExt;
import com.turingmaker.dao.mapper.teachter.ext.WorkMapperExt;
import com.turingmaker.entity.school.TProgram;
import com.turingmaker.entity.teachter.TWork;
import com.turingmaker.service.exception.HttpException;
import com.turingmaker.service.exception.ServiceException;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.student.api.StudentWorkService;
import com.turingmaker.service.work.entity.WorkDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zengdingyang
 * @Date 2018\7\5 0005
 */
@Service
public class StudentWorkServiceImpl implements StudentWorkService {
    @Autowired
    WorkMapperExt workMapperExt;
    @Autowired
    ProgramMapperExt programMapperExt;

    /**
     * 获取作品列表
     * @param pageinfo
     * @param accountId
     * @param state
     * @return
     */
    @Override
    public List<TWork> getWorkListByPage(Pageinfo pageinfo, Long accountId,Integer state) {
        List<TWork> works = workMapperExt.findWorkListByPage(pageinfo,accountId,state);
        return works;
    }

    /**
     * 保存作品
     * @param userId
     * @param workName
     * @param programCode
     */
    @Override
    public TWork saveWork(Long userId,String studentName, String workName, String programCode) {
        TProgram program = new TProgram();
        program.setProgramContent(programCode);
        program.setProgramName(workName);
        programMapperExt.insertSelective(program);

        TWork work = new TWork();
        work.setWorkName(workName);
        work.setProgramId(program.getId());
        work.setUserId(userId);
        work.setWorkAuthor(studentName);
        workMapperExt.insertSelective(work);

        return work;
    }

    /**
     * 获取作品详情
     * @param workId
     * @return
     */
    @Override
    public WorkDetail getWorkDetail(Long workId) {
        WorkDetail workDetail = workMapperExt.selectWorkDetailById(workId);
        return workDetail;
    }


    /**
     * 删除作品
     * @param workId
     */
    @Override
    public void deleteWork(Long workId) {

        TWork work = workMapperExt.selectByPrimaryKey(workId);
        if(work == null){
            throw new ServiceException(-1,"作品不存在");
        }
        work.setIsDelete(1);
        workMapperExt.updateByPrimaryKeySelective(work);
    }

    /**
     * 发布作品
     * @param workId
     * @param workAvatar
     * @param workName
     * @param workDescription
     */
    @Override
    public void publishWork(Long workId, String workAvatar, String workName, String workDescription) {
        TWork work = workMapperExt.selectByPrimaryKey(workId);
        work.setWorkName(workName);
        work.setWorkAvatar(workAvatar);
        work.setWorkDesc(workDescription);
        work.setState(1);
        workMapperExt.updateByPrimaryKeySelective(work);

    }

    @Override
    public void cancelPublish(Long workId) {
        TWork work = workMapperExt.selectByPrimaryKey(workId);
        work.setState(0);
        workMapperExt.updateByPrimaryKeySelective(work);
    }

    @Override
    public TProgram getProgramDetail(Long programCodeId) {
        TProgram program = programMapperExt.selectByPrimaryKey(programCodeId);
        return program;
    }
}

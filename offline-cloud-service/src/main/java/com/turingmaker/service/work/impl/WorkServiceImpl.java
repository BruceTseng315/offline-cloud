package com.turingmaker.service.work.impl;


import com.turingmaker.dao.mapper.school.ext.ProgramMapperExt;
import com.turingmaker.dao.mapper.teachter.ext.WorkMapperExt;
import com.turingmaker.entity.school.TProgram;
import com.turingmaker.entity.teachter.TWork;
import com.turingmaker.service.teachter.request.SaveWorkRequest;
import com.turingmaker.service.work.api.WorkService;
import com.turingmaker.service.work.entity.WorkDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * workservice 服务实现
 * @author  tzj
 */
@Service
public class WorkServiceImpl  implements WorkService {

    @Autowired
    WorkMapperExt workMapper;

    final String resultKey="result";
    @Autowired
    ProgramMapperExt programMapper;


    /**
     * 保存作品 或者新建作品
     * 更新作品的时候需要同步更新作品的程序
     * @param saveWorkRequest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveWork(SaveWorkRequest saveWorkRequest) {


        Long id=saveWorkRequest.getId();
        TWork work=null;
        TProgram  program = new TProgram();

        program.setProgramName(saveWorkRequest.getWorkName());
        program.setProgramContent(saveWorkRequest.getProgramCode());;

        if(id!=null){
            work=workMapper.selectByPrimaryKey(id);
            work.setWorkName(saveWorkRequest.getWorkName());
            work.setWorkAuthor(saveWorkRequest.getAuthor());
            work.setWorkDesc(saveWorkRequest.getDescription());
            work.setUserId(saveWorkRequest.getUserId());
            program.setId(work.getProgramId());
        }

        if(work==null){

            work = new TWork();
            work.setWorkName(saveWorkRequest.getWorkName());
            work.setWorkAuthor(saveWorkRequest.getAuthor());
            work.setWorkDesc(saveWorkRequest.getDescription());
            work.setUserId(saveWorkRequest.getUserId());

            programMapper.insertSelective(program);
            work.setProgramId(program.getId());
            workMapper.insertSelective(work);

            return;
        }
        programMapper.updateByPrimaryKeySelective(program);
        workMapper.updateByPrimaryKeySelective(work);



    }


    /**
     * 发布或取消发布
     * @param workId
     * @param publish
     */
    @Override
    public void updateWorkPublishState(Long  workId,boolean publish) {
        workMapper.updateWorkPublishState(workId,publish?PUBLISH_STATE:UN_PUBLISH_STATE);
    }

    /**
     * 删除或恢复
     * @param workId
     * @param delete
     */
    @Override
    public void updateWorkDelateState(Long  workId,boolean delete) {
        workMapper.updateWorkDeletehState(workId,delete?DELETE_FLAG:UN_DELETE_FLAG);
    }


    @Override
    public boolean likeWork(Long workId, Long userId, boolean isLike) {
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put("workId",workId);
        paramMap.put("userId",userId);
        paramMap.put("workType",TYPE_WORK);


        workMapper.likeWork(paramMap);

        if(paramMap.containsKey(resultKey)){
            Integer result= (Integer) paramMap.get(resultKey);
            return  result>=0;
        }

        return false;
    }

    @Override
    public boolean viewWork(Long workId, Long userId) {

        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put("workId",workId);
        paramMap.put("userId",userId);
        paramMap.put("workType",TYPE_WORK);

        workMapper.viewWork(paramMap);

        if(paramMap.containsKey(resultKey)){
            Integer result= (Integer) paramMap.get(resultKey);
            return  result>=0;
        }

        return false;
    }


    @Override
    public WorkDetail detailWork(Long workId) {

        return workMapper.selectWorkDetailById(workId);
    }

    @Override
    public List<TWork> listWorkByUserId(Long userId) {
        return workMapper.sectWorksByUserId(userId);
    }

    @Override
    public List<TWork> listWorkByClassId(Long classId) {
        return workMapper.sectWorksByUserClass(classId);
    }


    @Override
    public boolean isLikeed(Long userId,Long workId) {
        return workMapper.checkUserIsLiked(userId,workId);
    }
}

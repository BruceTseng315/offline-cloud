package com.turingmaker.service.student.api;

import com.turingmaker.entity.school.TProgram;
import com.turingmaker.entity.teachter.TWork;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.work.entity.WorkDetail;

import java.util.List;

/**
 * @Author zengdingyang
 * @Date 2018\7\5 0005
 */
public interface StudentWorkService {
    /**
     * 获取作品列表
     * @param pageinfo
     * @param accountId
     * @return
     */
    List<TWork> getWorkListByPage(Pageinfo pageinfo, Long accountId,Integer state);

    /**
     * 保存作品
     * @param workName
     * @param programCode
     */
    TWork saveWork(Long userId,String studentName,String workName,String programCode);

    /**
     * 获取作品详情
     * @param workId
     * @return
     */
    WorkDetail getWorkDetail(Long workId);

    /**
     * 删除作品
     * @param workId
     */
    void deleteWork(Long workId);

    void publishWork(Long workId,String workAvatar,String workName,String workDescription);

    void cancelPublish(Long workId);

    TProgram getProgramDetail(Long programCodeId);

}

package com.turingmaker.service.work.api;

import com.turingmaker.entity.teachter.TWork;
import com.turingmaker.service.teachter.request.SaveWorkRequest;
import com.turingmaker.service.work.entity.WorkDetail;

import java.util.List;

/**
 * @author  tzj
 * 作品的相关service
 */
public interface WorkService {

    /**
     * 未删除和删除标志的值
     */
    int DELETE_FLAG=0;
    int UN_DELETE_FLAG=1;

    /**
     * 发布和未发布
     */
    int UN_PUBLISH_STATE=0;
    int PUBLISH_STATE=1;


    int TYPE_WORK=1;
    int TYPE_HOMEWORK=2;

    /**
     * 保存作品
     */
    void  saveWork(SaveWorkRequest saveWorkRequest);


    /**
     * 发布和未发布接口
     * @param publish
     */
    void  updateWorkPublishState(Long workId, boolean publish);

    /**
     * 删除和恢复作品
     * @param workId
     * @param publish
     */
    void  updateWorkDelateState(Long workId, boolean delete);


    /**
     * 给作品点赞 或取消点赞
     * @param workId  给哪个点赞
     * @param userId  谁点的
     */
    boolean
    likeWork(Long workId, Long userId, boolean isLike);



    /**
     * 给作品添加浏览数
     * @param workId 浏览了哪个
     * @param userId 谁浏览了
     */
    boolean  viewWork(Long workId, Long userId);

    /**
     * 获取作品详情
     * @return
     */
    WorkDetail detailWork(Long workId);


    /**
     * 根据用户id查询作品
     * @return
     */
    List<TWork> listWorkByUserId(Long userId);


    /**
     * 根据班级查询学生的作品
     * @return
     */
    List<TWork> listWorkByClassId(Long classId);


    /**
     * 判断某个人是否点赞了
     * @return
     */
    boolean isLikeed(Long  userId,Long workId);

}

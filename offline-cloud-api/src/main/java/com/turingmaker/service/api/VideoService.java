package com.turingmaker.service.api;

import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

/**
 * Created by zengdingyang on 2018\6\26 0026.
 */
public interface VideoService {
    /**
     * 获取视频上传地址和凭证
     * @param title 视频标题
     * @param description 视频描述
     * @param fileName 视频文件名，包括扩展名
     * @param coverUrl 视频封面地址 可为null
     * @return
     */
    CreateUploadVideoResponse getUploadVideoAuth(String title, String description, String fileName, String coverUrl)throws Exception;

    /**
     * 获取视频播放地址
     * @param videoId 视频id
     * @return
     * @throws Exception
     */
    GetPlayInfoResponse getPlayInfo(String videoId) throws Exception;

    /**
     * 获取视频播放凭证
     * @param videoId 视频id
     * @return
     * @throws Exception
     */
    GetVideoPlayAuthResponse getVideoPlayAuth(String videoId)throws Exception;
}

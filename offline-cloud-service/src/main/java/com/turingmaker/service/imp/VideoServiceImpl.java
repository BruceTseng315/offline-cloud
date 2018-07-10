package com.turingmaker.service.imp;

import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.turing.common.video.VideoUtils;
import com.turingmaker.service.api.VideoService;
import org.springframework.stereotype.Service;

/**
 * Created by zengdingyang on 2018\6\26 0026.
 */
@Service
public class VideoServiceImpl implements VideoService{
    /**
     * 获取视频上传地址和凭证
     * @param title 视频标题
     * @param description 视频描述
     * @param fileName 视频文件名，包括扩展名
     * @param coverUrl 视频封面地址 可为null
     * @return
     * @throws Exception
     */
    @Override
    public CreateUploadVideoResponse getUploadVideoAuth(String title, String description, String fileName, String coverUrl) throws Exception {
        CreateUploadVideoResponse createUploadVideoResponse = VideoUtils.createUploadVideo(title,description,fileName,coverUrl);
        return createUploadVideoResponse;
    }

    /**
     * 获取视频播放地址
     * @param videoId 视频id
     * @return
     * @throws Exception
     */
    @Override
    public GetPlayInfoResponse getPlayInfo(String videoId) throws Exception {
        GetPlayInfoResponse getPlayInfoResponse = VideoUtils.getPlayInfo(videoId);
        return getPlayInfoResponse;
    }

    /**
     * 获取视频播放凭证
     * @param videoId 视频id
     * @return
     * @throws Exception
     */
    @Override
    public GetVideoPlayAuthResponse getVideoPlayAuth(String videoId) throws Exception {
        GetVideoPlayAuthResponse getVideoPlayAuthResponse = VideoUtils.getVideoPlayAuth(videoId);

        return getVideoPlayAuthResponse;
    }
}

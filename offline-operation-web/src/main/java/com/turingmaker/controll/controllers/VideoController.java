package com.turingmaker.controll.controllers;

import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.turingmaker.common.domain.Result;
import com.turingmaker.service.api.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zengdingyang on 2018\6\26 0026.
 * 视频相关操作
 */
@RestController
@RequestMapping("/turing/api/v2/admin_manage/video/")
public class VideoController {
    @Autowired
    VideoService videoService;

    Logger logger= LoggerFactory.getLogger(getClass());

    /**
     * 获取视频上传地址和凭证
     * @param title
     * @param description
     * @param fileName
     * @param coverUrl
     * @return
     */
    @GetMapping("/upload/auth_and_address")
    public Result getUploadVideoAuth(@RequestParam("title") String title, @RequestParam("description") String description,
                                     @RequestParam("fileName") String fileName, @RequestParam(value = "coverUrl",required = false) String coverUrl) {
        try {
            CreateUploadVideoResponse createUploadVideoResponse = videoService.getUploadVideoAuth(title, description, fileName, coverUrl);


            return Result.success(createUploadVideoResponse);
        }catch (Exception e){
            logger.warn("获取视频上传地址和凭证出错："+e.getMessage());
            return Result.errorresult;
        }
    }

    /**
     * 获取视频播放凭证
     * @param videoId
     * @return
     */
    @GetMapping("/play/auth")
    public Result  getVideoPlayAuth(@RequestParam("videoId") String videoId) {
        try{
            GetVideoPlayAuthResponse getVideoPlayAuthResponse = videoService.getVideoPlayAuth(videoId);
            return Result.success(getVideoPlayAuthResponse);
        }catch (Exception e){
            logger.warn("获取视频播放凭证出错："+e.getMessage());
            return Result.errorrResult(e.getMessage());
        }
    }

    /**
     * 获取视频播放地址
     * @param videoId
     * @return
     */
    @GetMapping("/play/address")
    public Result getPlayInfo(@RequestParam("videoId") String videoId) {
        try{
            GetPlayInfoResponse getPlayInfoResponse = videoService.getPlayInfo(videoId);
            return Result.success(getPlayInfoResponse);
        }catch (Exception e){
            logger.warn("获取视频播放地址出错："+e.getMessage());
            return Result.errorrResult(e.getMessage());
        }
    }
}

package com.turingmaker.service.student.response;

import com.turingmaker.service.work.entity.WorkDetail;

/**
 * @Author zengdingyang
 * @Date 2018\7\5 0005
 */
public class StudentWorkDetailResponse extends WorkDetail{
    private Boolean liked;

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }
}

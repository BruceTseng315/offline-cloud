package com.turingmaker.service.work.entity;

import com.turingmaker.entity.teachter.TWork;

/**
 * 作品详情，包括学生作品，老师作品
 * @author  tzj
 */
public class WorkDetail extends TWork {


    /**
	 * 
	 */
	private static final long serialVersionUID = -5874165580894361364L;

	/**
     * 浏览量
     */
    private Long  viewCount = 0L;

    /**
     * 点赞数
     */
    private  Long likeCount = 0L;

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }



    @Override
    public String toString() {
        return "WorkDetail{" +
                ", viewCount=" + viewCount +
                ", likeCount=" + likeCount +
                '}'+super.toString();
    }
}

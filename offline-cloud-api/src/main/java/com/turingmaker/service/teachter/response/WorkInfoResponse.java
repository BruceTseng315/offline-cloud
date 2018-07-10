package com.turingmaker.service.teachter.response;

/**
 * @author  tzj
 * 作品详情返回
 */
public class WorkInfoResponse {
    /**
     * id
     */
   private  String   id;

    /**
     * 作品名称
     */
    private  String        workName;

    /**
     * 作品描述
     */
    private  String      workDesc;
    /**
     * 浏览量
     */
    private Long  viewCount;

    /**
     * 点赞数
     */
    private  Long likeCount;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

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
        return "WorkInfoResponse{" +
                "id='" + id + '\'' +
                ", workName='" + workName + '\'' +
                ", workDesc='" + workDesc + '\'' +
                ", viewCount=" + viewCount +
                ", likeCount=" + likeCount +
                '}';
    }
}

package com.turingmaker.service.operation.response;

public class AreaResponse {

    private Long areaId;

    private String areaName;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return "AreaResponse{" +
                "areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                '}';
    }
}

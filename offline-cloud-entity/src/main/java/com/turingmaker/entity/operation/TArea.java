package com.turingmaker.entity.operation;

import java.io.Serializable;

public class TArea implements Serializable {
    private Long id;

    private String areaName;

    private String areaCode;

    private Long areaPid;

    private Integer disorder;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public Long getAreaPid() {
        return areaPid;
    }

    public void setAreaPid(Long areaPid) {
        this.areaPid = areaPid;
    }

    public Integer getDisorder() {
        return disorder;
    }

    public void setDisorder(Integer disorder) {
        this.disorder = disorder;
    }

    @Override
    public String toString() {
         return  "TArea{"+
				"id="+this.getId()+
				"areaName="+this.getAreaName()+
				"areaCode="+this.getAreaCode()+
				"areaPid="+this.getAreaPid()+
				"disorder="+this.getDisorder()+"}";
    }
}
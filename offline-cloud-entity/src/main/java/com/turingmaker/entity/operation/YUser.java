package com.turingmaker.entity.operation;

import java.io.Serializable;
import java.util.Date;

public class YUser implements Serializable {
    private Long id;

    private String account;

    private String password;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
         return  "YUser{"+
				"id="+this.getId()+
				"account="+this.getAccount()+
				"password="+this.getPassword()+
				"createTime="+this.getCreateTime()+
				"updateTime="+this.getUpdateTime()+"}";
    }
}
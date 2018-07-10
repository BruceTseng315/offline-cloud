package com.turingmaker.entity.operation;

import java.io.Serializable;

public class YRoleUser extends YRoleUserKey implements Serializable {
    private Long roleId;

    private static final long serialVersionUID = 1L;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
         return  "YRoleUser{"+
				"id="+this.getId()+
				"userId="+this.getUserId()+
				"roleId="+this.getRoleId()+"}";
    }
}
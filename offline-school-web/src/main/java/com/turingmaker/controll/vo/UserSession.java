package com.turingmaker.controll.vo;

import java.io.Serializable;

public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long schoolId;
    private Long accountId;
    private String accountName;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "schoolId=" + schoolId +
                ", accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}

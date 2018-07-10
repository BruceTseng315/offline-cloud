package com.turingmaker.controll.vo;

import java.io.Serializable;

public class PasswordRefreshVo  implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long accountId;
    private String accountName;
    private String password;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PasswordRefreshVo{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

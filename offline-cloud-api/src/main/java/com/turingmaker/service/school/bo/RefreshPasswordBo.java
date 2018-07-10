package com.turingmaker.service.school.bo;

import java.io.Serializable;

public class RefreshPasswordBo  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long accountId;
    private String accountName;
    private String password;



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
        return "RefreshPasswordBo{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

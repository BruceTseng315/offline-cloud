package com.turingmaker.controll.config;

import com.turingmaker.entity.operation.YUser;
import com.turingmaker.entity.school.TStudent;

import java.io.Serializable;

/**
 * @Author zengdingyang
 * @Date 2018\7\5 0005
 */
public class StudentSession implements Serializable {
    private static final long serialVersionUID = 1L;

    private String avatar;
    private String studentName;
    private Long studentId;
    private Long accountId;
    private String accountName;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public void setUser(YUser user){
        this.accountId = user.getId();
        this.accountName = user.getAccount();
    }
    public  void setStudent(TStudent student ){
        this.studentId = student.getId();
        this.studentName = student.getStudentName();
    }
    @Override
    public String toString() {
        return "StudentSession{" +
                "studentName='" + studentName + '\'' +
                ", studentId=" + studentId +
                ", accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}

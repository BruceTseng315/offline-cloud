package com.turingmaker.service.school.bo;

import com.turingmaker.entity.operation.YUser;
import com.turingmaker.entity.school.TClass;
import com.turingmaker.entity.school.TStudent;

import java.io.Serializable;
import java.util.List;

public class StudentBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long studentId;
    private String studentNo;
    private String studentName;
    private String identifier;
    private List<TClass> classes;
    private String studentCode;
    private String guarderName;
    private String guarderPhone;
    private Long accountId;
    private String accountName;
    private String password;


    public void setStudent(TStudent student){
        this.studentId = student.getId();
        this.studentCode = student.getStudentCode();
        this.studentName = student.getStudentName();
        this.identifier = student.getIdentifier();
        this.studentNo = student.getStudentNumber();
        this.guarderPhone = student.getGuarderPhone();
        this.guarderName = student.getGuarderName();
    }
    public void setAccount(YUser user){
        this.accountId = user.getId();
        this.accountName = user.getAccount();
        this.password = user.getPassword();
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<TClass> getClasses() {
        return classes;
    }

    public void setClasses(List<TClass> classes) {
        this.classes = classes;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getGuarderName() {
        return guarderName;
    }

    public void setGuarderName(String guarderName) {
        this.guarderName = guarderName;
    }

    public String getGuarderPhone() {
        return guarderPhone;
    }

    public void setGuarderPhone(String guarderPhone) {
        this.guarderPhone = guarderPhone;
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
        return "StudentBo{" +
                "studentId=" + studentId +
                ", studentNo='" + studentNo + '\'' +
                ", studentName='" + studentName + '\'' +
                ", classNames=" + classes +
                ", studentCode='" + studentCode + '\'' +
                ", guargerName='" + guarderName + '\'' +
                ", guarderPhone='" + guarderPhone + '\'' +
                ", accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

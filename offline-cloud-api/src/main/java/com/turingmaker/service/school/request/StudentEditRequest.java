package com.turingmaker.service.school.request;

public class StudentEditRequest  {
    private Long studentId;
    private String studentName;
    private String identifier;
    private String studentNo;
    private String guarderName;
    private String guarderPhone;
    private Boolean passwordRefresh;

    public Boolean getPasswordRefresh() {
        return passwordRefresh;
    }

    public void setPasswordRefresh(Boolean passwordRefresh) {
        this.passwordRefresh = passwordRefresh;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }



    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
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

    @Override
    public String toString() {
        return "StudentEditRequest{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", identifier='" + identifier + '\'' +
                ", studentNo='" + studentNo + '\'' +
                ", guarderName='" + guarderName + '\'' +
                ", guarderPhone='" + guarderPhone + '\'' +
                "passwordReresh=" + passwordRefresh +
                '}';
    }
}

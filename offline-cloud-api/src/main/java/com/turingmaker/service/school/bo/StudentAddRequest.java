package com.turingmaker.service.school.bo;

public class StudentAddRequest {
    private Long classId;
    private String studentName;
    private String studentNo;
    private String identifier;
    private String guarderName;
    private String guarderPhone;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
        return "StudentAddRequest{" +
                "classId=" + classId +
                ", studentName='" + studentName + '\'' +
                ", studentNo='" + studentNo + '\'' +
                ", identifier='" + identifier + '\'' +
                ", guarderName='" + guarderName + '\'' +
                ", guarderPhone='" + guarderPhone + '\'' +
                '}';
    }
}

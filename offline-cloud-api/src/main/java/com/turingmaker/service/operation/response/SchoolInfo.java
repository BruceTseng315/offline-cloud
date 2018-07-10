package com.turingmaker.service.operation.response;

import java.util.List;
import java.util.Map;

public class SchoolInfo {
    private Long schoolId;
    private Long classCount;
    private Long teacherCount;
    private Long studentCount;
    private String adminName;
    private String schoolCode;
    private String schoolName;
    private Map<String,AreaResponse> region;
    private List<String> courses;
    private String areaName;
    
    
    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getClassCount() {
        return classCount;
    }

    public void setClassCount(Long classCount) {
        this.classCount = classCount;
    }

    public Long getTeacherCount() {
        return teacherCount;
    }

    public void setTeacherCount(Long teacherCount) {
        this.teacherCount = teacherCount;
    }

    public Long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Long studentCount) {
        this.studentCount = studentCount;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Map<String, AreaResponse> getRegion() {
        return region;
    }

    public void setRegion(Map<String, AreaResponse> region) {
        this.region = region;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    
    public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public String toString() {
		return "SchoolInfo [schoolId=" + schoolId + ", classCount=" + classCount + ", teacherCount=" + teacherCount
				+ ", studentCount=" + studentCount + ", adminName=" + adminName + ", schoolCode=" + schoolCode
				+ ", schoolName=" + schoolName + ", region=" + region + ", courses=" + courses + ", areaName="
				+ areaName + "]";
	}

	
}

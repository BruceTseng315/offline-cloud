package com.turingmaker.service.teachter.entity;

/**
 * @author tzj
 */
public class ClassInfo {

    /**
     * "classId":1,//班级id
     *                 "className":"test",//班级名称
     *                 "studentCount":45//班级学生人数
     */

    private  Long classId;
    private  String className;
    private  Integer studentCount;
    private String classCode;
    private Byte classType;
    private Long schoolId;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }
    
    

    public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public Byte getClassType() {
		return classType;
	}

	public void setClassType(Byte classType) {
		this.classType = classType;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	@Override
	public String toString() {
		return "ClassInfo [classId=" + classId + ", className=" + className + ", studentCount=" + studentCount
				+ ", classCode=" + classCode + ", classType=" + classType + ", schoolId=" + schoolId + "]";
	}

	
	
}

package com.turingmaker.service.school.entity;

/**
 * 
 * @author 导出学生账号
 *
 */
public class QueryStudentExportAccount {

	private String studentName;
	
	private String studentAccount;
	
	private String studentPassword;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentAccount() {
		return studentAccount;
	}

	public void setStudentAccount(String studentAccount) {
		this.studentAccount = studentAccount;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	@Override
	public String toString() {
		return "QueryStudentExportAccount [studentName=" + studentName + ", studentAccount=" + studentAccount
				+ ", studentPassword=" + studentPassword + "]";
	}
	
	
	
}

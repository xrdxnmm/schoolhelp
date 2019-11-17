package com.example.domain;

import java.sql.Date;
/**
 * 
 * @author XRDMM
 *
 */
public class User {

	private String uName;
	private String uPassword;
	private String name;
	private String academy;
	private String major;
	private String grade;
	private String tel;
//	private Date time;
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
/*	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}*/
	
	
}

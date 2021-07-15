package com.codeplanet.model;

import org.springframework.web.multipart.MultipartFile;

public class User {
	
	   String userName;
	   String emailId;
	   String mobile;
	   String password;
	   int userId;
	MultipartFile fileupload;
	public MultipartFile getFileupload() {
		return fileupload;
	}
	public void setFileupload(MultipartFile fileupload) {
		this.fileupload = fileupload;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}

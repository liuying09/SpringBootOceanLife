package com.oceanLife.dto;

import org.springframework.web.multipart.MultipartFile;

import com.oceanLife.bean.UserModel;
import com.oceanLife.enumlist.UserRole;
import com.oceanLife.enumlist.UserStatus;

public class UserCreateDTO {

	private UserModel userModel;
	private UserStatus userStatus;
	private UserRole userRole;
	private MultipartFile fileField;
	private String authCode;
	
	
	public UserModel getUserModel() {
		return userModel;
	}
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	
	public UserStatus getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}	
	
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public MultipartFile getFileField() {
		return fileField;
	}
	public void setFileField(MultipartFile fileField) {
		this.fileField = fileField;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	
	
}

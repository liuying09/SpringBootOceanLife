package com.oceanLife.dto;


import com.oceanLife.bean.UserModel;

public class UserCreateDTO {

	private UserModel userModel;
	private String authCode;
	private int actionType;
	
	public UserModel getUserModel() {
		return userModel;
	}
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	public int getActionType() {
		return actionType;
	}
	public void setActionType(int actionType) {
		this.actionType = actionType;
	}
	
	
	
}

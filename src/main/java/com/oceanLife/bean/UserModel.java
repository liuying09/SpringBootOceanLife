package com.oceanLife.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OCEAN_user")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="userId")
	 private int userId;
	
	@Column(name="userAccount")
	 private String userAccount;
	
	@Column(name="userSalt")
	private String userSalt;

	@Column(name="userPass")
	private String userPass;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="userGender")
	private String userGender;
	
	@Column(name="userStatus")
	private String userStatus;
	
	@Column(name="userBirth")
	private String userBirth;
	
	@Column(name="userCountry")
	private String userCountry;
	
	@Column(name="userDistrict")
	private String userDistrict;
	
	@Column(name="userRoad")
	private String userRoad;
	
	@Column(name="userImg")
	private byte[] userImg;
	
	@Column(name="userPhone")
	private String userPhone;
	
	@Column(name="userSub")
	private String userSub;
	
	@Column(name="favoriteID")
	private String favoriteID;
	
	@Column(name="create_date")
	 private String createDate;
	
	@Column(name="update_date")
	 private String updateDate;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}

	public String getUserDistrict() {
		return userDistrict;
	}

	public void setUserDistrict(String userDistrict) {
		this.userDistrict = userDistrict;
	}

	public String getUserRoad() {
		return userRoad;
	}

	public void setUserRoad(String userRoad) {
		this.userRoad = userRoad;
	}

	public byte[] getUserImg() {
		return userImg;
	}

	public void setUserImg(byte[] userImg) {
		this.userImg = userImg;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserSub() {
		return userSub;
	}

	public void setUserSub(String userSub) {
		this.userSub = userSub;
	}

	public String getFavoriteID() {
		return favoriteID;
	}

	public void setFavoriteID(String favoriteID) {
		this.favoriteID = favoriteID;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getUserSalt() {
		return userSalt;
	}

	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}
	
}

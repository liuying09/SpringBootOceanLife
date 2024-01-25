package com.oceanLife.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OCEAN_activity")
public class ActivityModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="activityId")
	 private int activityId;
	
	@Column(name="activityTitle")
	 private String activityTitle;
	
	@Column(name="activityContent")
	 private String activityContent;
	
	@Column(name="activityDate")
	 private String activityDate;
	
	@Column(name="activityTime")
	 private String activityTime;
	
	@Column(name="activityRemark")
	 private String activityRemark;
	
	@Column(name="activityImg")
	 private byte[] activityImg;

	@Column(name="activityStatus")
	private String activityStatus;

	@Column(name="create_date")
	 private String createDate;
	
	@Column(name="update_date")
	 private String updateDate;

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getActivityContent() {
		return activityContent;
	}

	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	public String getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}

	public String getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}

	public String getActivityRemark() {
		return activityRemark;
	}

	public void setActivityRemark(String activityRemark) {
		this.activityRemark = activityRemark;
	}

	public byte[] getActivityImg() {
		return activityImg;
	}

	public void setActivityImg(byte[] activityImg) {
		this.activityImg = activityImg;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
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

	
	
}

package com.oceanLife.dto;

import org.springframework.web.multipart.MultipartFile;

import com.oceanLife.bean.ActivityModel;

public class ActivityCreateDTO {
	
    private ActivityModel activityModel;
    private MultipartFile fileField;
    
	public ActivityModel getActivityModel() {
		return activityModel;
	}
	public void setActivityModel(ActivityModel activityModel) {
		this.activityModel = activityModel;
	}
	public MultipartFile getFileField() {
		return fileField;
	}
	public void setFileField(MultipartFile fileField) {
		this.fileField = fileField;
	}
	
}

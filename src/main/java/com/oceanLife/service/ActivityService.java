package com.oceanLife.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oceanLife.dao.ActivityRepository;
import com.oceanLife.model.bean.ActivityModel;
import com.oceanLife.model.req.ActivityCreateDTO;
import com.oceanLife.utils.DateUtils;


@Service
public class ActivityService {

	private static final Logger logger = LoggerFactory.getLogger(ActivityService.class);
	
	@Autowired
	private ActivityRepository activityRepository;

	public void upsert(ActivityCreateDTO activityCreateDTO) throws IOException {
		
		LocalDateTime dt = LocalDateTime.now();
	    ActivityModel activityModel = activityCreateDTO.getActivityModel();
	    MultipartFile fileField = activityCreateDTO.getFileField();
		ActivityModel aModel;

		byte[] image;
		if (fileField == null) {
			image = null;
		} else {
			image = fileField.getBytes();
		}

	    if(activityModel.getActivityId() != 0) {
	    	aModel = activityRepository.findByActivityId(activityModel.getActivityId());
	    }else {
	    	aModel = new ActivityModel();
	    	aModel.setCreateDate(dt);
	    }
		
		aModel.setActivityTitle(activityModel.getActivityTitle());
		aModel.setActivityContent(activityModel.getActivityContent().replace("\n", "<br>"));
		aModel.setActivityDate(activityModel.getActivityDate());
		aModel.setActivityTime(activityModel.getActivityTime());
		aModel.setActivityRemark(activityModel.getActivityRemark().replace("\n", "<br>"));
		aModel.setActivityImg(image);
		aModel.setActivityStatus(activityModel.getActivityStatus());
		aModel.setUpdateDate(dt);
		
		activityRepository.save(aModel);

	}
	
	
	public ActivityModel getActivityById(Integer activityId) {
		return activityRepository.findByActivityId(activityId);
	}
	
	public List<ActivityModel> getActivity(){
		return activityRepository.findAll();
	}
	
	public void delete(Integer[] ids) {
		for(int i = 0; i < ids.length; i++) {
			if(activityRepository.findByActivityId(ids[i]) != null) {
				activityRepository.deleteById(ids[i]);
			}
		}
	}
	

}

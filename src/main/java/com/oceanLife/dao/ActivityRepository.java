package com.oceanLife.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oceanLife.bean.ActivityModel;

public interface ActivityRepository extends JpaRepository<ActivityModel,Integer> {
	ActivityModel findByActivityId(int activityId);
}

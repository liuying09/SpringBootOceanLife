package com.oceanLife.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.oceanLife.model.bean.ActivityModel;
import com.oceanLife.model.req.ActivityCreateDTO;
import com.oceanLife.service.ActivityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/activity")
@Tag(name = "活動") //swagger api標題
public class ActivityController {

	private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);
	
    @Autowired
    private ActivityService activityService;
	
    @Operation(summary = "新增、更新活動", description = "新增、更新活動資料")
	@PostMapping()
	public ResponseEntity<Map<String, Object>> upsert(@RequestBody ActivityCreateDTO activityCreateDTO){
				
		try {
			activityService.upsert(activityCreateDTO);
			
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "活動 - 新增、更新成功");
			
	        return ResponseEntity.ok(response);
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException("Error creating or updating activity");
		}
	}
    
    @Operation(summary = "搜尋活動", description = "透過活動ID，搜尋出對應活動資料")
    @GetMapping("{id}")
	public ResponseEntity<Map<String, Object>> getActivityById(@PathVariable("id") Integer id){
    	Map<String, Object> response = new HashMap<>();
    	ActivityModel activityModel = activityService.getActivityById(id);
    	
    	if(activityModel != null) {
    		response.put("message", "活動 - 搜尋成功");
    		response.put("activity", activityModel);
    		
    		return ResponseEntity.status(HttpStatus.OK).body(response);
    	}else {
    		response.put("message", "活動 - 搜尋成功，目前無任何活動");
    		return ResponseEntity.status(HttpStatus.OK).body(response);
    	}
    	
    }
    
    @Operation(summary = "搜尋活動", description = "搜尋出全部活動資料")
    @GetMapping()
	public ResponseEntity<Map<String, Object>> getActivity(){
    	Map<String, Object> response = new HashMap<>();
    	
    	List<ActivityModel> list = activityService.getActivity();
    	
    	if(list.size() != 0) {
    		response.put("message", "活動 - 搜尋成功");
    		response.put("activity", list);
    		return ResponseEntity.status(HttpStatus.OK).body(response);
    	}else {
    		response.put("message", "活動 - 搜尋成功，目前無任何活動");
    		return ResponseEntity.status(HttpStatus.OK).body(response);
    	}
	}
    
    @Operation(summary = "刪除活動", description = "透過活動ID，刪除對應活動資料")
    @DeleteMapping()
    public ResponseEntity<Map<String, Object>> delete(@RequestParam(name="idList",required = false) Integer[] ids ) {

    	activityService.delete(ids);
    		
	    Map<String, Object> response = new HashMap<>();
	    response.put("message", "活動 - 刪除成功");
			
	    return ResponseEntity.ok(response);
    }
    
}

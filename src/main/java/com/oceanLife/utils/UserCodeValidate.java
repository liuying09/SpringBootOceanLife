package com.oceanLife.utils;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserCodeValidate {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
	
    public interface CHECKCODE{
        int notExist=0;
        int OK=1;
        int NoMatch=2;
    }
    
    public void isUseCode(String mail) {
    	String datastr =  (String) redisTemplate.opsForValue().get(mail);
    	JSONObject data = new JSONObject(datastr);
		if(data != null && data.has("code")) {
			redisTemplate.delete(mail);
		}
    }
    
	
	public int codeValidate(String mail, String code) {
    	String datastr =  (String) redisTemplate.opsForValue().get(mail);
    	if(datastr != null && !"".equals(datastr)) {
    		JSONObject data = new JSONObject(datastr);
			if(code.equals(data.getString("code"))) {
				return CHECKCODE.OK;
			}else {
				return CHECKCODE.NoMatch;
			}
    	}
		return CHECKCODE.notExist;
	}
}

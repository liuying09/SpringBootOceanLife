package com.oceanLife.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oceanLife.bean.UserModel;
import com.oceanLife.dao.UserRepository;
import com.oceanLife.dto.UserCreateDTO;
import com.oceanLife.enumlist.UserRole;
import com.oceanLife.enumlist.UserStatus;
import com.oceanLife.utils.DateUtils;

@Service
public class UserService {

	private static final Logger _log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void upsert(UserCreateDTO userCreateDTO) throws IOException {
		int actionType = userCreateDTO.getActionType();
		UserModel user = userCreateDTO.getUserModel();
		UserModel uModel;
		
		// 新增 or 更新
		if (actionType == 2) {
			uModel = getByUserAccount(user.getUserAccount());
		}else {
			uModel = new UserModel();
			uModel.setCreateDate(DateUtils.getDateTimeFormat("yyyy-MM-dd"));
		}
		
		// 密碼加密
		String encodedPassword = passwordEncoder.encode(user.getUserPass());

		uModel.setUserPass(encodedPassword);
		
		uModel.setUserAccount(user.getUserAccount());
		uModel.setUserName(user.getUserName());
		uModel.setUserGender(user.getUserGender());
		uModel.setUserBirth(user.getUserBirth());
		uModel.setUserCountry(user.getUserCountry());
		uModel.setUserDistrict(user.getUserDistrict());
		uModel.setUserRoad(user.getUserRoad());
		uModel.setUserPhone(user.getUserPhone());
		uModel.setUserSub(user.getUserSub());
		
		uModel.setUserStatus(user.getUserStatus());
		uModel.setUserRole(user.getUserRole());
		
		uModel.setUpdateDate(DateUtils.getDateTimeFormat("yyyy-MM-dd"));
		
		// 保存用户信息
		userRepository.save(uModel);
	}
	
	public Page<UserModel> getUsers(Pageable pageable){
		return userRepository.findAll(pageable);
	}
	
	public UserModel getByUserAccount(String userAccount) {
		return userRepository.findByUserAccount(userAccount);
	}
	
	public boolean isUserExist(String userAccount) {
		return userRepository.existsByUserAccount(userAccount);
	}
	
	
	public void delete(Integer id) {
		UserModel user = userRepository.findByUserId(id);
		user.setUserStatus(UserStatus.OFF);
		user.setUpdateDate(DateUtils.getDateTimeFormat("yyyy-MM-dd"));
		userRepository.save(user);
	}
	
}

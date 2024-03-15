package com.oceanLife.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

		UserModel user = userCreateDTO.getUserModel();
		MultipartFile fileField = userCreateDTO.getFileField();
		UserModel uModel;
		
		// 檢查使用者帳號是否唯一
		if (userRepository.existsByUserAccount(user.getUserAccount())) {
			uModel = userRepository.findByUserAccount(user.getUserAccount());
		}else {
			uModel = new UserModel();
			uModel.setCreateDate(DateUtils.getDateTimeFormat("yyyy-MM-dd"));
		}

		
		byte[] image;
		if (fileField == null) {
			image = null;
		} else {
			image = fileField.getBytes();
		}
		uModel.setUserImg(image);
		
		// 密碼加密
		String encodedPassword = passwordEncoder.encode(user.getUserPass());
		user.setUserPass(encodedPassword);
		
		uModel.setUserAccount(user.getUserAccount());
		uModel.setUserName(user.getUserName());
		uModel.setUserGender(user.getUserGender());
		uModel.setUserBirth(user.getUserBirth());
		uModel.setUserCountry(user.getUserCountry());
		uModel.setUserDistrict(user.getUserDistrict());
		uModel.setUserRoad(user.getUserRoad());
		uModel.setUserPhone(user.getUserPhone());
		uModel.setUserSub(user.getUserSub());
		
		uModel.setUserStatus(userCreateDTO.getUserStatus());
		uModel.setUserRole(userCreateDTO.getUserRole());
		
		uModel.setUpdateDate(DateUtils.getDateTimeFormat("yyyy-MM-dd"));
		
		// 保存用户信息
		userRepository.save(uModel);
	}
	
	public List<UserModel> getUsers(){
		return userRepository.findAll();
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

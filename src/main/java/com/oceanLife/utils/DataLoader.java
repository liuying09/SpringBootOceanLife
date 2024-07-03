package com.oceanLife.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.oceanLife.bean.UserModel;
import com.oceanLife.dao.UserRepository;
import com.oceanLife.enumlist.UserRole;
import com.oceanLife.enumlist.UserStatus;

@Component
public class DataLoader implements ApplicationRunner {
	/* for h2db first user setting */
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (!userRepository.existsByUserAccount("oceanLife@mail.com")) {
			UserModel uModel = new UserModel();
			uModel.setUserAccount("oceanLife@mail.com");
			uModel.setUserPass(passwordEncoder.encode("123456"));
			uModel.setUserStatus(UserStatus.ON);
			uModel.setUserRole(UserRole.ADMIN);
			
			userRepository.save(uModel);
		}
		UserModel uModel;
		for(int i = 0; i <100; i++) {
			uModel = new UserModel();
			uModel.setUserAccount("account" + i);
			uModel.setUserPass(passwordEncoder.encode("123456"));
			userRepository.save(uModel);
		}
		
	}

	
}

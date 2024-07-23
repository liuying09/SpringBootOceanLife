package com.oceanLife.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oceanLife.model.bean.UserModel;
import com.oceanLife.service.UserService;
import com.oceanLife.utils.enumlist.UserRole;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserModel userModel = userService.getByUserAccount(username);
		if(userModel == null) {
			 throw new UsernameNotFoundException("Can't find user: " + username);
		}
		
		return new UserDetail(userModel);
	}

}

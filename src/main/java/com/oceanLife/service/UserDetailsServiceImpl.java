package com.oceanLife.service;

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

import com.oceanLife.bean.UserModel;
import com.oceanLife.enumlist.UserRole;
import com.oceanLife.utils.UserDetail;

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
		
		// 使用者的role列表
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (UserRole role : UserRole.values()) {
//            if (role.name().equals(userModel.getUserRole())) {
//            	System.out.println("role.name()= "+role.name());
//                authorities.add(new SimpleGrantedAuthority(role.name()));
//            }
//        }
		
//		return new User(userModel.getUserAccount(), userModel.getUserPass(), authorities);
		return new UserDetail(userModel);
	}

}

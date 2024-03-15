package com.oceanLife.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.oceanLife.bean.UserModel;
import com.oceanLife.enumlist.UserRole;

public class UserDetail implements UserDetails {

	private final UserModel userModel;
	
	public UserDetail(UserModel user) {
		this.userModel = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

	    return List.of(userModel.getUserRole());
	}

	@Override
	public String getPassword() {
		return userModel.getUserPass();
	}

	@Override
	public String getUsername() {
		return userModel.getUserAccount();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
	// 自定義的 public 方法
    public int getId() { 
    	return userModel.getUserId();
    }
    public String getAccount() {
    	return userModel.getUserAccount();
    }
    public UserRole getRole() {
    	return userModel.getUserRole();
    }
	
}

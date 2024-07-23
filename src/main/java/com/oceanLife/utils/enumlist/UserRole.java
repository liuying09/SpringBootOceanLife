package com.oceanLife.utils.enumlist;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
	ADMIN, NORMAL, GUEST;

	@Override
	public String getAuthority() {
		return  name();
	}
	
}

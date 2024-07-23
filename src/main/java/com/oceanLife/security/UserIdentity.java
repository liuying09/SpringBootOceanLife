package com.oceanLife.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.oceanLife.model.bean.UserModel;
import com.oceanLife.utils.enumlist.UserRole;

@Component
public class UserIdentity {
	
	private UserDetail getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        System.out.println("principal= "+principal);
        return "anonymousUser".equals(principal)
                ? new UserDetail(new UserModel())
                : (UserDetail) principal;
	}
	

    public int getId() { 
    	return getUserDetails().getId();
    }

    public String getUserAccount() { 
    	return getUserDetails().getAccount();
    }
    
    public UserRole getRole() {
    	return getUserDetails().getRole();
    }
}

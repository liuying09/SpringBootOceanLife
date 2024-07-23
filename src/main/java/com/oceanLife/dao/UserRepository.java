package com.oceanLife.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oceanLife.model.bean.UserModel;

public interface UserRepository extends JpaRepository<UserModel,Integer>{
	
	UserModel findByUserAccountAndUserPass(String userAccount,String userPass);
	UserModel findByUserId(int userId);
	UserModel findByUserAccount(String userAccount);
	
	boolean existsByUserAccount(String userAccount);

}

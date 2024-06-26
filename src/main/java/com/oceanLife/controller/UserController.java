package com.oceanLife.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.oceanLife.bean.ActivityModel;
import com.oceanLife.bean.UserModel;
import com.oceanLife.dto.LoginRequest;
import com.oceanLife.dto.LoginResponse;
import com.oceanLife.dto.UserCreateDTO;
import com.oceanLife.enumlist.UserRole;
import com.oceanLife.service.UserService;
import com.oceanLife.token.TokenService;
import com.oceanLife.utils.UserIdentity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/user")
@Tag(name = "會員") //swagger api標題
public class UserController {
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private UserIdentity userIdentity;
	
    @SecurityRequirements
    @Operation(summary = "登入", description = "使用者登入")
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		 LoginResponse res = tokenService.createToken(request);
		return ResponseEntity.ok(res);
	}
	
    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, String>> refreshAccessToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        String accessToken = tokenService.refreshAccessToken(refreshToken);
        Map<String, String> res = Map.of("accessToken", accessToken);

        return ResponseEntity.ok(res);
    }
	
    @SecurityRequirements
    @Operation(summary = "新增、更新使用者", description = "新增、更新使用者資料")
	@PostMapping()
	public ResponseEntity<Map<String, Object>> upsert(@RequestBody UserCreateDTO userCreateDTO){
    	Map<String, Object> response = new HashMap<>();
		try {
			if (userService.isUserExist(userCreateDTO.getUserModel().getUserAccount())) {
				userService.upsert(userCreateDTO);
		        response.put("message", "更新 - 使用者成功");
		        response.put("status", 200);
			}else {
				userService.upsert(userCreateDTO);
		        
		        response.put("message", "新增 - 使用者成功");
		        response.put("status", 200);
			}
			
	        return ResponseEntity.ok(response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error creating or updating user.", e);
		}
	}
    
    @Operation(summary = "搜尋使用者", description = "搜尋出全部使用者資料")
    @GetMapping()
    public ResponseEntity<List<UserModel>> getUsers(){
    	List<UserModel> list = userService.getUsers();
    	
    	if(list.size() != 0) {
    		return ResponseEntity.status(HttpStatus.OK).body(list);
    	}else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    	}
    }

    @Operation(summary = "停用使用者", description = "將使用者狀態設為停用")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
    	
    	UserRole userRole = userIdentity.getRole();
    	if(userRole == UserRole.ADMIN) {
        	userService.delete(id);
        	return ResponseEntity.ok().build();
    	}else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    	
    }	
    
    
}
